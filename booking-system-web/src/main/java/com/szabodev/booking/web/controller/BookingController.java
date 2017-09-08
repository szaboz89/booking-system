package com.szabodev.booking.web.controller;

import com.szabodev.booking.model.BookableItem;
import com.szabodev.booking.model.Booking;
import com.szabodev.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Scope(value = "session")
public class BookingController {

    @ManagedProperty("#{bookingRestService}")
    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private Booking booking;
    private List<Booking> bookings;
    private int selectedItemId;
    private Date startTime;
    private Date endTime;

    @PostConstruct
    public void init() {
        booking = new Booking();
        bookings = bookingService.getBookings();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(int selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public void addBooking() {
        BookableItem bookableItem = bookingService.getBookableItem(selectedItemId);
        booking.setStartTime(startTime.getTime());
        booking.setEndTime(endTime.getTime());
        booking.setItem(bookableItem);
        bookingService.addBooking(booking);
        init();
    }

    public void removeBooking(Booking booking) {
        bookingService.deleteBooking(booking);
    }

    public List<Booking> getBookingsByItemId(int itemId) {
        List<Booking> itemBookings = new ArrayList<>();
        for (Booking actBooking : this.bookings) {
            if (actBooking.getItem().getId() == itemId) {
                itemBookings.add(actBooking);
            }
        }
        return itemBookings;
    }
}
