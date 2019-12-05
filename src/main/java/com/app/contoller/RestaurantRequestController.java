package com.app.contoller;

import com.app.model.DeliveryStatistics;
import com.app.model.Item;
import com.app.model.MaxReviewedCuisine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.*;

@Controller
@RequestMapping("/api")

//Requests from cross origin, angular default app are allowed
@CrossOrigin(origins = "http://localhost:4200")

/**
 * Rest controller that exposes the followings:
 * 1. /restaurant-list: -returns the list of restaurant objects by region
 * 2. /zip-code-list - returns the list of distinct zipcodes where deliveries are made in given region
 * 3. /delivery-statistics - DeliveryStatistics min,avg, max delivery time of given area
 * 4. /restaurants-with-website - count of how many restaurants have website by given region
 * 5. /delivery-cost-distribution- distribution of delivery fees by given region
 * 6. /total-delivery-areas-by-zipCode - number of restaurants delivering to input zip Code, input region
 * 7. /total-restaurants-delivering - number of restaurants delivering to input region
 * 8. /cuisine-max-review - MaxReviewed cuisine (name, reviewCount) in given region
 */

public class RestaurantRequestController {
    private ItemController ctrl;

    /**
     * Build the Item restaurants, before requests are made
     **/
    @PostConstruct
    public void init() {
        ctrl = new ItemController();
    }

    /**
     * Returns the list of restaurant objects by region
     */
    @RequestMapping(value = "/restaurant-list", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getRestaurants(@RequestParam(name = "region") String region) {
        Optional<Map<String, List<Item>>> restaurants = ctrl.getItems();
        if (restaurants.isPresent()) {
            List<Item> result = restaurants.get().get(region);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    /**
     * Returns the list of distinct zip Codes where deliveries are made in given request param region
     */
    @RequestMapping(value = "/zip-code-list", method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getZipCodes(@RequestParam(name = "region") String region) {
        Optional<Map<String, List<Item>>> restaurants = ctrl.getItems();
        Set<String> result = new HashSet();
        if (restaurants.isPresent() && restaurants.get().get(region) != null) {
            for (Item item : restaurants.get().get(region)) {
                if (!result.contains(item.getZipCode()) && item.getZipCode() != null) {
                    result.add(item.getZipCode());
                }
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }


    /**
     * Minimum, maximum and average delivery times for the region
     */
    @RequestMapping(value = "/delivery-statistics", method = RequestMethod.GET)
    public ResponseEntity<DeliveryStatistics> getStatistics(@RequestParam(name = "region") String region) {
        Optional<Map<String, List<Item>>> restaurants = ctrl.getItems();
        if (restaurants.isPresent() && restaurants.get().containsKey(region)) {
            List<Item> items = restaurants.get().get(region);
            int minDelivery = Integer.MAX_VALUE;
            int maxDelivery = Integer.MIN_VALUE;
            int sumDelivery = 0;
            int count = 0;
            for (Item item : items) {
                if (item.getDeliveryTime() != null && item.getDeliveryTime() != 0) {
                    if (minDelivery > item.getDeliveryTime()) {
                        minDelivery = item.getDeliveryTime();
                    }

                    if (maxDelivery < item.getDeliveryTime()) {
                        maxDelivery = item.getDeliveryTime();
                    }

                    sumDelivery += item.getDeliveryTime();
                    count++;
                }
            }
            DeliveryStatistics statistics = new DeliveryStatistics();
            statistics.setMinDeliveryTime(minDelivery);
            statistics.setMaxDeliveryTime(maxDelivery);
            statistics.setAvgDeliveryTime((double) (sumDelivery / count));
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new DeliveryStatistics(), HttpStatus.NOT_FOUND);
        }
    }


    /**
     * How many of the restaurants have a website being listed to the customer
     */
    @RequestMapping(value = "/restaurants-with-website", method = RequestMethod.GET)
    public ResponseEntity<Integer> getWebsiteListedTotalNr(@RequestParam(name = "region") String region) {
        Optional<Map<String, List<Item>>> restaurants = ctrl.getItems();
        int totalNr = 0;
        if (restaurants.isPresent()) {
            List<Item> items = restaurants.get().get(region);
            for (Item item : items) {
                if (item.getHasWebsite()) {
                    totalNr++;
                }
            }
        }
        return new ResponseEntity<>(totalNr, HttpStatus.OK);
    }

    /**
     * The distribution of delivery fees being charged by different restaurants in that area.
     * If the zipcode of item equals to the req.param, then the delivery cost is added to the result.
     */
    @RequestMapping(value = "/delivery-cost-distribution", method = RequestMethod.GET)
    public ResponseEntity<List<Double>> getDeliveryDistribution(@RequestParam(name = "region") String region, @RequestParam(name = "zipCode") String zipCode) {
        Optional<Map<String, List<Item>>> restaurants = ctrl.getItems();
        List<Double> result = new ArrayList<>(5);
        if (restaurants.isPresent()) {
            List<Item> items = restaurants.get().get(region);
            for (Item item : items) {
                if (item.getZipCode() != null && item.getZipCode().equals(zipCode)) {
                    result.add(item.getDeliveryCost());
                }
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Determining the number of zip codes (restaurant location) delivering to that delivery area on given zip code
     * If the zipcode of Item equals to request.param.zipcode, the result is increased
     */
    @RequestMapping(value = "/total-delivery-areas-by-zipCode", method = RequestMethod.GET)
    public ResponseEntity<Integer> getDeliveryAreas(@RequestParam(name = "region") String region, @RequestParam(name = "zipCode") String zipCode) {
        Integer result = 0;
        Optional<Map<String, List<Item>>> restaurants = ctrl.getItems();
        if (restaurants.isPresent()) {
            List<Item> items = restaurants.get().get(region);
            for (Item item : items) {
                if (item.getZipCode() != null && item.getZipCode().equals(zipCode)) {
                    result++;
                }
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Determining the number of zip codes (restaurant location) delivering to that delivery area per region
     * Distinct zipcodes that delivering is happening, by region.
     */
    @RequestMapping(value = "/total-restaurants-delivering", method = RequestMethod.GET)
    public ResponseEntity<Integer> getDeliveryAreas(@RequestParam(name = "region") String region) {
        Optional<Map<String, List<Item>>> restaurants = ctrl.getItems();
        Set zipCodes = new HashSet(50);
        if (restaurants.isPresent()) {
            List<Item> items = restaurants.get().get(region);
            for (Item item : items) {
                if (item.getZipCode() != null && !zipCodes.contains(item.getZipCode())) {
                    zipCodes.add(item.getZipCode());
                }
            }
        }
        return new ResponseEntity<>(zipCodes.size(), HttpStatus.OK);
    }


    /**
     * Determining which food cuisine gets the maximum number of reviews overall in this region.
     * After the {cuisine,review} map is built, the maximum is calculated and returned as MaxReviewedCuisine object.
     */
    @RequestMapping(value = "/cuisine-max-review", method = RequestMethod.GET)
    public ResponseEntity<MaxReviewedCuisine> getMaxReviewCuisine(@RequestParam(name = "region") String region) {

        Optional<Map<String, List<Item>>> restaurants = ctrl.getItems();
        String cuisineName = "";
        Integer maxReview = Integer.MIN_VALUE;
        if (restaurants.isPresent()) {
            List<Item> items = restaurants.get().get(region);
            Map<String, Integer> cuisines = getCuisinesWithReview(items);
            for (Map.Entry<String, Integer> data : cuisines.entrySet()) {
                if (data.getValue() > maxReview) {
                    maxReview = data.getValue();
                    cuisineName = data.getKey();
                }
            }
        }
        MaxReviewedCuisine result = new MaxReviewedCuisine();
        result.setCuisine(cuisineName);
        result.setReviews(maxReview);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Helper function that builds up the {cuisine, review} map from list of Item restaurants.
     * */
    private Map<String, Integer> getCuisinesWithReview(List<Item> restaurants) {
        Map<String, Integer> cuisines = new HashMap<>(200);
        for (Item item : restaurants) {
            for (String cuisine : item.getCuisines()) {
                if (cuisines.containsKey(cuisine)) {
                    if (cuisines.get(cuisine) < item.getReviewCount()) {
                        cuisines.put(cuisine, item.getReviewCount());
                    }
                } else {
                    cuisines.put(cuisine, item.getReviewCount());
                }
            }
        }
        return cuisines;
    }
}
