package com.app.controller;

import com.app.contoller.ItemController;
import com.app.model.Item;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * Test class that checks if ITemController can retrieve Document from web and file. Also if the objects retrieved from file are converted to Item list.
 * */
public class ItemControllerTest {
    private final String FILE_LOCATION = "index.html";
    private final String WEB_LOCATION = "https://www.thuisbezorgd.nl/en/order-takeaway-amsterdam-stadsdeel-binnenstad-1011";
    private ItemController ctrl;
    private Optional<List<Item>> itemList;

    @Before
    public void init(){
        ctrl = new ItemController();
        itemList = ctrl.getWebPageRestaurants(FILE_LOCATION);
    }

    @Test
    public void testGetWebPageRestaurants(){
        Assert.assertTrue(itemList.isPresent());
        Assert.assertEquals(itemList.get().size(),259);
    }

    @Test
    public void testGetDocumentFile(){
        Document result = ctrl.getDocument(FILE_LOCATION);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetDocumentWeb(){
        Document result = ctrl.getDocument(WEB_LOCATION);
        Assert.assertNotNull(result);
    }
}
