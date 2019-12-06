package com.app.controller;

import com.app.contoller.HtmlToItem;
import com.app.model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class that checks if the conversion from html string to Item objects goes as expected.
 * */
public class HtmlToItemTest {
    private final String URL_ROOT = "https://www.thuisbezorgd.nl";
    private HtmlToItem converter;
    private Item result;

    @Before
    public void init(){
        String htmlObjStr = getHtmlObject();
        Document doc = Jsoup.parse(htmlObjStr);
        Element htmlElement = doc.select("div.restaurant ").first();
        converter = new HtmlToItem(htmlElement);
        result = converter.build(URL_ROOT);
    }

    @Test
    public void testBuiltItemName(){
        Assert.assertEquals(result.getName(),"Eetsalon van Dobben");
    }

    @Test
    public void testBuiltItemReviewCount(){
        Assert.assertEquals(result.getReviewCount().intValue(),139);
    }

    @Test
    public void testBuiltItemDeliveryTime(){
        Assert.assertEquals(result.getDeliveryTime().intValue(),40);
    }

    @Test
    public void testBuiltItemZipCode(){
        Assert.assertEquals(result.getZipCode(),"1017BH");
    }

    @Test
    public void testBuiltItemHasWebsite(){
        Assert.assertEquals(result.getHasWebsite(),true);
    }

    private String getHtmlObject() {
        return "<div class='restaurant ' id='irestaurantQ7R1RN3' onclick='document.location=\"/en/eetsalon-van-dobben\";return false;'"
              + "itemscope itemtype='http://schema.org/Restaurant'>\\r\\n     <div class='logowrapper'>\\r\\n"
              +"<div class='baloon-container restaurantlabel '>\\r\\n      </div>\\r\\n      <div class='logo-n'>\\r\\n"
              +"<a href='/en/eetsalon-van-dobben' class='img-link'> <img class='restlogo' src='/assets/images/restaurantlisting/placeholder.png'"
               +"data-src='//static.thuisbezorgd.nl/images/restaurants/nl/Q7R1RN3/logo_465x320.png' border='0' alt='Eetsalon van Dobben - Lekker en snel!'>"
               +"</a>\\r\\n      </div>\\r\\n      <div class='review-rating'>\\r\\n       <div class='review-stars'>\\r\\n "
               +"<span style='width: 91%;' class='review-stars-range'></span>\\r\\n       </div>\\r\\n       <span class='rating-total'>(139)</span>\\r\\n"
                + "<span class='rating-total-short'>(139)</span>\\r\\n      </div>\\r\\n     </div>\\r\\n     <div class='detailswrapper'>\\r\\n "
                + "<h2 class='restaurantname'> <a class='restaurantname' href='/en/eetsalon-van-dobben' itemprop='name'> Eetsalon van Dobben </a> "
                + "</h2>\\r\\n      <div itemprop='review' itemscope itemtype='http://schema.org/Review'>\\r\\n     "
                + "<meta itemprop='name' content='Eetsalon van Dobben'>\\r\\n       <span itemprop='reviewRating' itemscope "
                + "itemtype='http://schema.org/Rating'>\\r\\n          <meta itemprop='worstRating' content='1'>\\r\\n "
                + "<meta itemprop='ratingValue' content='4'>\\r\\n          <meta itemprop='bestRating' content='5'>\\r\\n"
                + "<meta itemprop='reviewCount' content='139'> </span>\\r\\n      </div>\\r\\n      <div class='kitchens'>\\r\\n  "
                + "Snacks, Lunch, Sandwiches\\r\\n                </div>\\r\\n      <div class='bottomwrapper details'>\\r\\n     "
                + "<div class='delivery js-delivery-container'>\\r\\n        <div class='avgdeliverytime avgdeliverytimefull open'>\\r\\n    "
                + "est.40min\\r\\n                </div>\\r\\n        <div class='avgdeliverytime avgdeliverytimeabbr openAbbr'>\\r\\n "
                + "est.40min\\r\\n                </div>\\r\\n        <div class='delivery-cost js-delivery-cost'>\\r\\n  FREE\\r\\n "
                + "</div>\\r\\n        <div class='min-order'>\\r\\n                Min. \\u20AC 15,00\\r\\n                </div>\\r\\n     "
                + "<div class='label-promoted js-label-promoted'>\\r\\n                Sponsored\\r\\n                </div>\\r\\n      "
                + "</div>\\r\\n       <div class='pickup hidden wrapper-open-distance'>\\r\\n        <div class='distance'>\\r\\n    "
                + "0 M\\r\\n                </div>\\r\\n        <div class='address'>\\r\\n                Korte Reguliersdwarsstraat 5\\r\\n "
                + "<span class=' '>, 1017BH</span>\\r\\n        </div>\\r\\n        <div class='label-promoted js-label-promoted-pickup'>\\r\\n  "
                 +"Sponsored\\r\\n                </div>\\r\\n       </div>\\r\\n      </div>\\r\\n     </div>\\r\\n    </div>";
    }

}
