package com.szabodev.booking.service.rest;

import com.szabodev.booking.service.BookingService;
import com.szabodev.booking.service.dto.UpdateBookingRequest;
import com.szabodev.booking.service.dto.UpdateBookingResponse;
import com.szabodev.booking.model.Booking;
import com.szabodev.booking.service.dto.DeleteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingRestService {

    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private static final Logger logger = LoggerFactory.getLogger(BookingRestService.class);

    @RequestMapping(value = "/bookings", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Booking> getBookings() {
        logger.debug("getBookings called");
        return bookingService.getBookings();
    }

    @RequestMapping(value = "/bookings/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Booking getBooking(@PathVariable int id) {
        logger.debug("getBooking, id: " + id);
        List<Booking> bookings = bookingService.getBookings();
        for (Booking booking : bookings) {
            if (booking.getId() == id)
                return booking;
        }
        return null;
    }

    @RequestMapping(value = "/bookings/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public UpdateBookingResponse updateBooking(@PathVariable int id, @RequestBody UpdateBookingRequest b) {
        logger.debug("updateBooking, id: " + id);
        UpdateBookingResponse response = new UpdateBookingResponse();
        try {
            Booking booking = bookingService.getBookingById(id);
            booking.setStartTime(b.getStartTime());
            booking.setEndTime(b.getEndTime());
            bookingService.updateBooking(booking);
            response.setResponseCode("OK");
            response.setResponseMessage("Booking updated!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updateBooking Error", e);
            response.setResponseCode("NOK");
            response.setResponseMessage("Exception");
        }
        return response;
    }

    @RequestMapping(value = "/bookings/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public DeleteResponse deleteBookingById(@PathVariable int id) {
        logger.debug("deleteBookingById, id: " + id);
        DeleteResponse response = new DeleteResponse();
        try {
            Booking b = new Booking();
            b.setId(id);
            bookingService.deleteBooking(b);
            response.setResponseCode("OK");
            response.setResponseMessage("Booking deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteBookingById Error", e);
            response.setResponseCode("NOK");
            response.setResponseMessage("Exception");
        }
        return response;
    }

}