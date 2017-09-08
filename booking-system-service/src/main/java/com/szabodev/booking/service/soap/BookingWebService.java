package com.szabodev.booking.service.soap;

import com.szabodev.booking.service.dto.AvailableItemsResponse;
import com.szabodev.booking.model.BookableItem;
import com.szabodev.booking.model.Booking;
import com.szabodev.booking.service.dto.AvailableItemsRequest;

import java.util.List;

public interface BookingWebService {

    void addBookableItem(BookableItem p);

    List<BookableItem> getBookableItems();

    BookableItem getBookableItem(int id);

    void addBooking(Booking b);

    void updateBooking(Booking b);

    void deleteBooking(Booking b);

    List<Booking> getBookings();

    List<Booking> getBookingsByItemId(int id);

    void deleteBookingsForItem(int itemId);

    AvailableItemsResponse getAvailableItems(AvailableItemsRequest availableItemsRequest);

}