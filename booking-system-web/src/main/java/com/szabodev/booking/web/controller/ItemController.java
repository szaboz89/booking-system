package com.szabodev.booking.web.controller;

import com.szabodev.booking.model.BookableItem;
import com.szabodev.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@Component
@Scope(value = "session")
public class ItemController {

    @ManagedProperty("#{bookingRestService}")
    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private BookableItem bookableItem;

    private List<BookableItem> bookableItems;

    @PostConstruct
    public void init() {
        bookableItem = new BookableItem();
        bookableItems = bookingService.getBookableItems();
    }

    public BookableItem getBookableItem() {
        return bookableItem;
    }

    public void setBookableItem(BookableItem bookableItem) {
        this.bookableItem = bookableItem;
    }

    public void addBookableItem() {
        bookingService.addBookableItem(bookableItem);
        init();
    }

    public List<BookableItem> getBookableItems() {
        return bookableItems;
    }

}
