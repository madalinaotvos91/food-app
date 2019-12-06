package com.app.contoller;

import com.app.model.Item;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that converts Html to Java Object Item, restaurant in Html, in java Item.
 * */
public class HtmlToItem {

    private  final static Logger log = Logger.getLogger(HtmlToItem.class);
    private  Element item;

    /**
     * Element in html, named detailswrapper that contains the restaurant data.
     * */
    public HtmlToItem(Element details) {
        this.item = details;
    }

    /**
     * Builds the actual Item (restaurant) object.
     * @param urlRoot : the base url needed to concate in order to retrieve the page info of given restaurant
     * */
    public Item build(String urlRoot) {
        Element detailsElement = null;
        try {
            detailsElement = item.select("div.detailswrapper").first();
            if (detailsElement != null) {
                String name = geItemName(detailsElement);
                if (name != null) {
                    Item restaurant = new Item(name);
                    Integer reviewCount = getItemReviewCount(detailsElement);
                    Double deliveryCost = getDeliveryCost(detailsElement);
                    String zipCode = getZipCode(detailsElement);
                    Integer deliveryTime = getDeliveryTime(detailsElement);
                    Boolean hasWebsite = hasWebsite(detailsElement,urlRoot);
                   List<String> cuisines = getCuisines(detailsElement);
                    return restaurant.setDeliveryTime(deliveryTime).setZipCode(zipCode)
                            .setDeliveryCost(deliveryCost).setReviewCount(reviewCount).setHasWebsite(hasWebsite).setCuisines(cuisines).build();
                }
            }
            return null;
        } catch (Exception e) {
            log.error("Can not find div detailswrapper. Exception: " + e.getMessage());
            return null;
        }
    }

    /**
     * Function to retrieve the delivery cost from div.bottomwrapper.details -> div.delivery-cost.js-delivery-cost
     * */
    protected Double getDeliveryCost(Element item) {
        try {
            Element delivery = item.select("div.bottomwrapper.details").first().select("div.delivery-cost.js-delivery-cost").first();
            String deliveryCost = "";
            if (delivery != null) {
                deliveryCost = delivery.text();
                String doubleStr = deliveryCost.replaceAll(",", ".").replaceAll("[^0-9?!\\.]", "");
                log.info("deliveryCost-> " + doubleStr);
                return (deliveryCost.equals("FREE")) ? 0.0 : Double.parseDouble(doubleStr);
            }
            return null;
        } catch (Exception e) {
            log.info("Exception while trying to get delivery cost: " + e.getMessage());
            return null;
        }
    }

    /**
     * Function to retrieve the zip code from div.pickup.hidden.wrapper-open-distance -> span
     * */
     String getZipCode(Element item) {
        try {
            Element zipCode = item.select("div.pickup.hidden.wrapper-open-distance").select("span").first();
            String zipCodeStr = zipCode.text().replaceAll(", ", "");
            log.info("zip code-> " + zipCodeStr);
            return zipCode != null ? zipCodeStr : null;
        } catch (Exception e) {
            log.info("Exception while trying to get zip code: " + e.getMessage());
            return null;
        }
    }

    /**
     * Function to retrieve the delivery time from div.bottomwrapper.details -> div.avgdeliverytime.avgdeliverytimefull.open
     * Content of From, Closed and Fr. are skipped because the values don't express the delivering time
     * */
    protected Integer getDeliveryTime(Element item) {
        try {
            Element deliveryTime = item.select("div.bottomwrapper.details").first().select("div.avgdeliverytime.avgdeliverytimefull.open").first();
            String deliveryTimeStr = "0";
            if (!deliveryTime.text().contains("From") && !deliveryTime.text().contains("Fr.") &&
                    !deliveryTime.text().contains("Closed") && !deliveryTime.equals("")) {
                deliveryTimeStr = deliveryTime.text().replaceAll("\\D+", "");
            }
            log.info("delivery time-> " + deliveryTimeStr);
            return deliveryTime != null ? Integer.parseInt(deliveryTimeStr) : null;
        } catch (Exception e) {
            log.info("Exception while trying to get delivery time: " + e.getMessage());
            return null;
        }
    }


    protected String geItemName(Element detailsElement) {
        try {
            Element review = detailsElement.select("div[itemprop=review]").first();
            if (review != null) {
                String name = review.select("meta[itemprop=name]").first().attr("content").trim();
                log.info(String.format("Restaurant %s is getting built.", name));
                return name;
            }
            return null;
        } catch (Exception e) {
            log.info("Exception while trying to get restaurant name: " + e.getMessage());
            return null;
        }
    }

    /**
     * Function to retrieve the revie count of given html restaurant, from div[itemprop=review]-> meta[itemprop=reviewCount].content
     * */
    protected Integer getItemReviewCount(Element detailsElement) {
        try {
            Element metaReview = detailsElement.select("div[itemprop=review]").first();
            if (metaReview != null) {
                String reviewCount = metaReview.select("meta[itemprop=reviewCount]").first().attr("content");
                log.info("review count -> " + reviewCount);
                return reviewCount.trim().equals("") ? 0 : Integer.parseInt(reviewCount);
            }
            return null;
        } catch (Exception e) {
            log.info("Exception while trying to get review count: " + e.getMessage());
            return null;
        }
    }

    /**
     * Function to retrieve the cuisines list of html restaurant, from div.kitchens.
     * */
    protected List<String> getCuisines(Element detailsElement){
        List<String> cuisines = Arrays.asList(detailsElement.select("div.kitchens").first().text().split("\\s*,\\s*"));
        log.info("cuisines -> " + cuisines);
        return (cuisines!= null) ? cuisines : new ArrayList<>();
    }

    /**
     * Function that sets if the restaurant object has an independent website or not.
     * A new connection is made to redirect to website urlRoot->info, there the
     * div.infoCard.restaurant-info__restaurant-link->div.infoTabSection->a.href is checked if set or not.
     * */
    protected Boolean hasWebsite(Element detailsElement,String urlRoot){
        try {
            Element className = detailsElement.select("a").first();

            String href= className.attr("href");
            String website="";
            if(href!=null) {
                Document infoPage = Jsoup.connect(urlRoot.concat(href).concat("#info")).timeout(400000) //
                        .method(Connection.Method.GET).get();
                Element websiteInfo = infoPage.select("div.infoCard.restaurant-info__restaurant-link").first();
                website = websiteInfo.select("div.infoTabSection").select("a").attr("href");
                log.info("website-> " + website);
            }
            return  (website!=null || !website.trim().equals("")) ? true : false;
        }catch (Exception e){
            log.warn("Warning website could not be retrieved. Problem: " + e.getMessage() );
            return false;
        }
    }
}
