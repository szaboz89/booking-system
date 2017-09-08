package com.szabodev.booking.service;

import com.szabodev.booking.dao.BookingDAO;
import com.szabodev.booking.model.BookableItem;
import com.szabodev.booking.model.Booking;
import com.szabodev.booking.service.dto.AvailableItemsRequest;
import com.szabodev.booking.service.dto.AvailableItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingDAO bookingDAO;

    @Autowired
    public void setBookingDAO(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public void addBookableItem(BookableItem b) {
        this.bookingDAO.addBookableItem(b);
    }

    @Override
    public List<BookableItem> getBookableItems() {
        List<BookableItem> bookableItems = this.bookingDAO.listBookableItems();
        if (bookableItems.isEmpty()) {
            BookableItem item1 = new BookableItem();
            item1.setName("Item 1");
            item1.setDescription("Description 1");
            addBookableItem(item1);

            BookableItem item2 = new BookableItem();
            item2.setName("Item 2");
            item2.setDescription("Description 2");
            addBookableItem(item2);

            BookableItem item3 = new BookableItem();
            item3.setName("Item 3");
            item3.setDescription("Description 3");
            addBookableItem(item3);

            Booking booking1 = new Booking();
            booking1.setName("Booking 1");
            booking1.setItem(item1);
            booking1.setStartTime(Long.valueOf("1493100000000"));
            booking1.setEndTime(Long.valueOf("1493103600000"));
            addBooking(booking1);

            Booking booking2 = new Booking();
            booking2.setName("Booking 2");
            booking2.setItem(item1);
            booking2.setStartTime(Long.valueOf("1493103600000"));
            booking2.setEndTime(Long.valueOf("1493107200000"));
            addBooking(booking2);

            Booking booking3 = new Booking();
            booking3.setName("Booking 3");
            booking3.setItem(item2);
            booking3.setStartTime(Long.valueOf("1493103600000"));
            booking3.setEndTime(Long.valueOf("1493107200000"));
            addBooking(booking3);

            Booking booking4 = new Booking();
            booking4.setName("Booking 4");
            booking4.setItem(item2);
            booking4.setStartTime(Long.valueOf("1493107200000"));
            booking4.setEndTime(Long.valueOf("1493110800000"));
            addBooking(booking4);
        }
        return bookingDAO.listBookableItems();
    }

    @Override
    public BookableItem getBookableItem(int id) {
        return this.bookingDAO.getBookableItem(id);
    }

    @Override
    public void addBooking(Booking b) {
        this.bookingDAO.addBooking(b);
    }

    @Override
    public void updateBooking(Booking b) {
        this.bookingDAO.updateBooking(b);
    }

    @Override
    public void deleteBooking(Booking b) {
        this.bookingDAO.deleteBooking(b);
    }

    @Override
    public List<Booking> getBookings() {
        return this.bookingDAO.listBooking();
    }

    @Override
    public List<Booking> getBookingsByItemId(int id) {
        return this.bookingDAO.getBookingsByItemId(id);
    }

    @Override
    public void deleteBookingsForItem(int itemId) {
        this.bookingDAO.deleteBookingsForItem(itemId);
    }

    @Override
    public AvailableItemsResponse getAvailableItems(AvailableItemsRequest availableItemsRequest) {

        AvailableItemsResponse availableItemsResponse = new AvailableItemsResponse();

        List<BookableItem> items = getBookableItems();
        List<BookableItem> availableItems = new ArrayList<>();
        List<BookableItem> notAvailableItems = new ArrayList<>();
        for (BookableItem item : items) {
            boolean available = true;
            List<Booking> bookingsByItemId = getBookingsByItemId(item.getId());
            for (Booking booking : bookingsByItemId) {
                if ((booking.getStartTime().compareTo(availableItemsRequest.getStartTime()) <= 0
                        && booking.getEndTime().compareTo(availableItemsRequest.getStartTime()) >= 0)
                        || (booking.getStartTime().compareTo(availableItemsRequest.getEndTime()) <= 0
                        && booking.getEndTime().compareTo(availableItemsRequest.getEndTime()) >= 0)
                        || (booking.getStartTime().compareTo(availableItemsRequest.getStartTime()) >= 0
                        && booking.getEndTime().compareTo(availableItemsRequest.getEndTime()) <= 0)) {
                    available = false;
                }
            }
            if (available) {
                availableItems.add(item);
            } else {
                notAvailableItems.add(item);
            }
        }

        availableItemsResponse.setNotAvailableItems(notAvailableItems);
        availableItemsResponse.setAvailableItems(availableItems);
        return availableItemsResponse;
    }

    @Override
    public Booking getBookingById(int id) {
        return this.bookingDAO.getBookingById(id);
    }

}