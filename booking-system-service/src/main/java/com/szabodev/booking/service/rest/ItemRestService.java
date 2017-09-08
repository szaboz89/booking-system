package com.szabodev.booking.service.rest;

import com.szabodev.booking.service.dto.*;
import com.szabodev.booking.model.BookableItem;
import com.szabodev.booking.model.Booking;
import com.szabodev.booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
public class ItemRestService {

    private static final Logger logger = LoggerFactory.getLogger(ItemRestService.class);

    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<BookableItem> getItems() {
        logger.debug("getItems called");
        return bookingService.getBookableItems();
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, headers = "Accept=application/json")
    public NewItemResponse addItem(@RequestBody BookableItem bookableItem) {
        logger.info("addItem called, bookableItem: " + bookableItem);
        NewItemResponse newBookingResponse = new NewItemResponse();
        try {
            bookingService.addBookableItem(bookableItem);
            newBookingResponse.setResponseCode("OK");
            newBookingResponse.setResponseMessage("Item created");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("addItem Error", e);
            newBookingResponse.setResponseCode("NOK");
            newBookingResponse.setResponseMessage("Exception");
        }
        return newBookingResponse;
    }

    @RequestMapping(value = "/items/available", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, headers = "Accept=application/json")
    public AvailableItemsResponse getAvailableItems(@RequestBody AvailableItemsRequest availableItemsRequest) {
        logger.info("getAvailableItems called, availableItemsRequest: " + availableItemsRequest);
        return bookingService.getAvailableItems(availableItemsRequest);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public BookableItem getItem(@PathVariable int id) {
        logger.debug("getItem called: " + id);
        List<BookableItem> bookableItems = bookingService.getBookableItems();
        for (BookableItem item : bookableItems) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    @RequestMapping(value = "/items/test", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteItem(HttpServletRequest request) {
        logger.info("deleteItem called: " + request);
    }

    @RequestMapping(value = "/items/{id}/bookings", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public DeleteResponse deleteBookingsForItem(@PathVariable int id) {
        logger.debug("deleteBookingsForItem, id: " + id);
        DeleteResponse deleteResponse = new DeleteResponse();
        try {
            bookingService.deleteBookingsForItem(id);
            deleteResponse.setResponseCode("OK");
            deleteResponse.setResponseMessage("Bookings deleted");
        } catch (Exception e) {
            deleteResponse.setResponseCode("NOK");
            deleteResponse.setResponseMessage(e.getMessage());
        }

        return deleteResponse;
    }

    @RequestMapping(value = "/items/{id}/bookings", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Booking> getBookingsByItemId(@PathVariable int id) {
        logger.debug("getBookingsByItemId, id: " + id);
        return bookingService.getBookingsByItemId(id);
    }

    @RequestMapping(value = "/items/{id}/bookings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, headers = "Accept=application/json")
    public NewBookingResponse addBookingToItem(@RequestBody NewBookingRequest newBookingRequest, @PathVariable int id) {
        logger.info("addBooking called, newBookingRequest: " + newBookingRequest);
        NewBookingResponse newBookingResponse = new NewBookingResponse();
        try {
            BookableItem bookableItem = bookingService.getBookableItem(id);
            if (bookableItem != null) {
                Booking booking = new Booking();
                booking.setItem(bookableItem);
                booking.setName(newBookingRequest.getName());
                booking.setStartTime(newBookingRequest.getStartTime());
                booking.setEndTime(newBookingRequest.getEndTime());
                bookingService.addBooking(booking);
                newBookingResponse.setResponseCode("OK");
                newBookingResponse.setResponseMessage("Booking inserted!");
            } else {
                newBookingResponse.setResponseCode("NOK");
                newBookingResponse.setResponseMessage("Bookable Item not available!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("addBooking Error", e);
            newBookingResponse.setResponseCode("NOK");
            newBookingResponse.setResponseMessage("Exception");
        }
        return newBookingResponse;
    }


}