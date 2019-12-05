package com.app.model;

import java.util.List;

/**
 * Item that contains the fields of HTML to Java object, Restaurant object.
 * Designed based on builder pattern.
 * */
public class Item {
    private String name;
    private Integer reviewCount;
    private Integer deliveryTime;
    private Double deliveryCost;
    private String zipCode;
    private List<String> cuisines;
    private Boolean hasWebsite;

    public Item(String name){
        this.name  = name;
    }

    public String getName() {
        return name;

    }

    public Item setName(String name) {
        if(name!= null) {
            this.name = name;
        }
        return this;
    }

    public Item setCuisines(List<String> cuisines){
        if(cuisines != null) {
            this.cuisines = cuisines;
        }
        return this;
    }

    public List<String> getCuisines(){
        return this.cuisines;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public Item setHasWebsite(Boolean value){
        this.hasWebsite = value;
        return this;
    }

    public Boolean getHasWebsite(){
        return this.hasWebsite;
    }

    public Item setReviewCount(Integer reviewCount) {
        if(reviewCount!= null) {
            this.reviewCount = reviewCount;
        }
        return this;
    }


    public Item setDeliveryTime(Integer deliveryTime) {
        if(deliveryTime != null) {
            this.deliveryTime = deliveryTime;
        }
        return this;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public Item setDeliveryCost(Double deliveryCost) {
        if(deliveryCost!= null) {
            this.deliveryCost = deliveryCost;
        }
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Item setZipCode(String zipCode) {
        if(zipCode!= null){
            this.zipCode = zipCode;
        }
        return this;
    }

    public Item build(){
        Item item =  new Item();
        item.deliveryTime = this.deliveryTime;
        item.name = this.name;
        item.reviewCount = this.reviewCount;
        item.zipCode = this.zipCode;
        item.deliveryCost = this.deliveryCost;
        item.hasWebsite = this.hasWebsite;
        item.cuisines = this.cuisines;
        return item;
    }
    private Item(){}

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", reviewCount=" + reviewCount +
                ", deliveryCost=" + deliveryCost +
                ", zipCode='" + zipCode + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", hasWebsite=" + hasWebsite +
                ", cuisines=" + cuisines +
                '}';
    }
}
