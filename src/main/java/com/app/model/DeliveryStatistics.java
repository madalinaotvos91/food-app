package com.app.model;

/**
 * Java clas for delivery-statistics report per region
 * Min delivery time. avg, and maximum delivery time
 * */
public class DeliveryStatistics {
    private Integer minDeliveryTime;
    private Integer maxDeliveryTime;
    private Double avgDeliveryTime;

    public Integer getMinDeliveryTime() {
        return minDeliveryTime;
    }

    public void setMinDeliveryTime(Integer minDeliveryTime) {
        this.minDeliveryTime = minDeliveryTime;
    }

    public Integer getMaxDeliveryTime() {
        return maxDeliveryTime;
    }

    public void setMaxDeliveryTime(Integer maxDeliveryTime) {
        this.maxDeliveryTime = maxDeliveryTime;
    }

    public Double getAvgDeliveryTime() {
        return avgDeliveryTime;
    }

    public void setAvgDeliveryTime(Double avgDeliveryTime) {
        this.avgDeliveryTime = avgDeliveryTime;
    }
}
