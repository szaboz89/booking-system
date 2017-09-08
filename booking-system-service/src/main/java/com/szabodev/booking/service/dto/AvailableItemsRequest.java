package com.szabodev.booking.service.dto;

public class AvailableItemsRequest {


    private Long startTime;
    private Long endTime;

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "AvailableItemsRequest{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
