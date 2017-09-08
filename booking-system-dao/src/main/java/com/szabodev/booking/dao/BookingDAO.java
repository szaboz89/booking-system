package com.szabodev.booking.dao;


import com.szabodev.booking.model.BookableItem;
import com.szabodev.booking.model.Booking;

import java.util.List;

public interface BookingDAO {

    void addBookableItem(BookableItem p);

    List<BookableItem> listBookableItems();

    BookableItem getBookableItem(int id);

    void addBooking(Booking booking);

    void updateBooking(Booking booking);

    List<Booking> listBooking();

    List<Booking> getBookingsByItemId(int itemId);

    void deleteBookingsForItem(int itemId);

    void deleteBooking(Booking booking);

    Booking getBookingById(int id);
}