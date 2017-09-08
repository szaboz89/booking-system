package com.szabodev.booking.service.dto;

import com.szabodev.booking.model.BookableItem;

import java.util.ArrayList;
import java.util.List;

public class AvailableItemsResponse {

    private List<BookableItem> availableItems = new ArrayList<>();
    private List<BookableItem> notAvailableItems = new ArrayList<>();

    public void setAvailableItems(List<BookableItem> availableItems) {
        this.availableItems = availableItems;
    }

    public List<BookableItem> getAvailableItems() {
        return availableItems;
    }

    public void setNotAvailableItems(List<BookableItem> notAvailableItems) {
        this.notAvailableItems = notAvailableItems;
    }

    public List<BookableItem> getNotAvailableItems() {
        return notAvailableItems;
    }
}
