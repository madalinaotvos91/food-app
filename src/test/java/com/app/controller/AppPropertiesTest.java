package com.app.controller;

import com.app.contoller.AppProperties;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class that checks if the app has the required app properties set.
 * */
public class AppPropertiesTest {
    private final Integer EXPECTED_APP_PORT = 8080;
    private final String EXPECTED_WEBSITE_ROOT= "https://www.thuisbezorgd.nl";
    private final Integer EXPECTED_UPDATE_INTERVAL = 0;
    private final String EXPECTED_WEBSITE_LIST = "https://www.thuisbezorgd.nl/en/order-takeaway-amsterdam-stadsdeel-binnenstad-1011,"
            + " https://www.thuisbezorgd.nl/en/order-takeaway-schiphol-1118";

    @Test
    public void  testAppPort(){
        String appPortStr = AppProperties.getPropertyValue("server.port");
        Integer retrievedValue = Integer.parseInt(appPortStr);
        Assert.assertEquals(EXPECTED_APP_PORT,retrievedValue);
    }

    @Test
    public void testWebsiteList(){
        String retrievedValue = AppProperties.getPropertyValue("website.urls");
        Assert.assertEquals(EXPECTED_WEBSITE_LIST,retrievedValue);
    }

    @Test
    public void testWebsiteRoot(){
        String retrievedValue = AppProperties.getPropertyValue("website.url.root");
        Assert.assertEquals(EXPECTED_WEBSITE_ROOT,retrievedValue);
    }

    @Test
    public void  testUpdateInterval(){
        String appPortStr = AppProperties.getPropertyValue("update.pages.each.minute");
        Integer retrievedValue = Integer.parseInt(appPortStr);
        Assert.assertEquals(EXPECTED_UPDATE_INTERVAL,retrievedValue);
    }
}

