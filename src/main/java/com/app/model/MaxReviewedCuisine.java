package com.app.model;

/**
 * Java object for max reviewed cuisine response: name: of cuisine , reviews :number of reviews
 * */
public class MaxReviewedCuisine {
    private String cuisine;
    private Integer reviews;

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }
}
