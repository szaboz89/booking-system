package com.szabodev.booking.service.rest;

import com.szabodev.booking.service.util.BookingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigRestService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigRestService.class);

    private BookingConfiguration bookingConfiguration;

    @Autowired
    public void setBookingConfiguration(BookingConfiguration bookingConfiguration) {
        this.bookingConfiguration = bookingConfiguration;
    }

    @RequestMapping(value = "/configs/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getProperty(@PathVariable String id) {
        logger.debug("getProperty: " + id);
        return bookingConfiguration.getProperty(id);
    }

}