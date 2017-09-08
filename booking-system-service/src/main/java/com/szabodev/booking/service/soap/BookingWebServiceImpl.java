package com.szabodev.booking.service.soap;

import com.szabodev.booking.service.BookingService;
import com.szabodev.booking.service.dto.AvailableItemsResponse;
import com.szabodev.booking.model.BookableItem;
import com.szabodev.booking.model.Booking;
import com.szabodev.booking.service.dto.AvailableItemsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@Service
@WebService(serviceName = "BookingWebService")
public class BookingWebServiceImpl implements BookingWebService {

    private BookingService bookingService;

    @WebMethod(exclude = true)
    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @WebMethod
    @Override
    public void addBookableItem(BookableItem b) {
        this.bookingService.addBookableItem(b);
    }

    @WebMethod
    @Override
    public List<BookableItem> getBookableItems() {
        return this.bookingService.getBookableItems();
    }

    @WebMethod
    @Override
    public BookableItem getBookableItem(int id) {
        return this.bookingService.getBookableItem(id);
    }

    @WebMethod
    @Override
    public void addBooking(Booking b) {
        this.bookingService.addBooking(b);
    }

    @WebMethod
    @Override
    public void updateBooking(Booking b) {
        bookingService.updateBooking(b);
    }

    @WebMethod
    @Override
    public void deleteBooking(Booking b) {
        bookingService.deleteBooking(b);
    }

    @WebMethod
    @Override
    public List<Booking> getBookings() {
        return this.bookingService.getBookings();
    }

    @WebMethod
    @Override
    public List<Booking> getBookingsByItemId(int id) {
        return this.bookingService.getBookingsByItemId(id);
    }

    @WebMethod
    @Override
    public void deleteBookingsForItem(int itemId) {
        this.bookingService.deleteBookingsForItem(itemId);
    }

    @WebMethod
    @Override
    public AvailableItemsResponse getAvailableItems(AvailableItemsRequest availableItemsRequest) {
        return this.bookingService.getAvailableItems(availableItemsRequest);
    }

}