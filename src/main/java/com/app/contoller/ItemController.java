package com.app.contoller;

import com.app.model.Item;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Controller that makes sure all possible objects retrieved through the url are converted.
 * The process of retrieving data and convert is  automatized, each x minutes defined by update.pages.each.minute.
 * To avoid intersection a copy of the instantiated objects are kept in copyOfResult.
 * urlRoot is the value of root website needed to concatenate in order to get the info page of the restaurant object.
 */
public class ItemController {
    final static Logger log = Logger.getLogger(ItemController.class);

    private List<String> webPages;
    private String urlRoot;
    private Integer updateEachMinute;
    private Map<String, List<Item>> copyOfResult = null;
    private Boolean isProcessingGoing = false;

    /**
     * The app.properties are read and the job of building item each x minute is scheduled.
     * If flag is.local.file.used is set, the app initiazes the objects from local file local.file.name=index.html,
     * otherwise from website.urls.
     */
    public ItemController() {
        String urls;
        Boolean isLocalFileUsed = Boolean.parseBoolean(AppProperties.getPropertyValue("is.local.file.used"));

        if(isLocalFileUsed){
           urls = AppProperties.getPropertyValue("local.file.name");
        }else {
           urls = AppProperties.getPropertyValue("website.urls");
        }

        webPages = new ArrayList<String>(Arrays.asList(urls.split(",")));


        urlRoot = AppProperties.getPropertyValue("website.url.root");
        updateEachMinute = Integer.parseInt(AppProperties.getPropertyValue("update.pages.each.minute"));
        if (updateEachMinute > 0) {
            Runnable getItemsRunnable = new Runnable() {
                public void run() {
                    Optional<Map<String, List<Item>>> result = buildItems();
                }
            };
            ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
            exec.scheduleAtFixedRate(getItemsRunnable, 0, updateEachMinute, TimeUnit.MINUTES);
        }
    }

    /**
     * Function that returns a map of String ->url, List ->objects retrieved form key url
     */
    protected Optional<Map<String, List<Item>>> buildItems() {
        if (!isProcessingGoing) {
            if (webPages != null) {
                isProcessingGoing = true;
                Map<String, List<Item>> resultMap = new HashMap<>(webPages.size());
                for (String webPage : webPages) {
                    Optional<List<Item>> resultList = getWebPageRestaurants(webPage);
                    if (resultList.isPresent()) {
                        resultMap.put(webPage, resultList.get());
                    }
                }

                if ((copyOfResult == null) || copyOfResult.size() <= resultMap.size()) {
                    isProcessingGoing = false;
                    copyOfResult = resultMap;
                }
                return Optional.of(copyOfResult);
            } else {
                log.info("Please make sure that that at least one valid website.urls is defined in application.properties!");
                return Optional.empty();
            }
        } else {
            if (copyOfResult == null) {
                log.warn("Request was made to get the restaurants but the answer is being built.");
            }
            return Optional.empty();
        }
    }

    public Optional<Map<String, List<Item>>> getItems() {

        if (copyOfResult == null) {
            buildItems();
        }
        return Optional.of(copyOfResult);

    }

    /**
     * Function that retrieves and returns the list of Item (restaurants) for given url.
     * The restaurants are queried from div.restaurant
     */
    public Optional<List<Item>> getWebPageRestaurants(String webPage) {
        try {
            Document html = getDocument(webPage);
            Elements items = html.select("div.restaurant ");
            List<Item> result = new ArrayList<>(50);
            for (Element item : items) {
                HtmlToItem object = new HtmlToItem(item);
                Item itemObject = object.build(urlRoot);
                if (itemObject != null) {
                    result.add(itemObject);
                }
            }
            return Optional.of(result);
        } catch (Exception e) {
            log.error("Make sure div restaurant exists! Error occurred: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Function that retrieves the html Document from file, if the file exists, otherwise as html web page
     */

    public Document getDocument(String webPage) {
        try {
            File file = new File(webPage);
            if (file.exists()) {
                return Jsoup.parse(file, "UTF-8", "");
            } else {
                return Jsoup.connect(webPage).get();
            }
        } catch (IOException e) {
            log.error(String.format("Make sure %s is a valid file, url that contains a valid html. Exception: %s.", webPage, e.getStackTrace()));
            return null;
        }
    }
}
