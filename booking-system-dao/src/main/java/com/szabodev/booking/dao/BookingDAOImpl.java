package com.szabodev.booking.dao;

import com.szabodev.booking.model.BookableItem;
import com.szabodev.booking.model.Booking;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class BookingDAOImpl implements BookingDAO {

    private static final Logger logger = LoggerFactory.getLogger(BookingDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addBookableItem(BookableItem bookableItem) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(bookableItem);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BookableItem> listBookableItems() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(BookableItem.class);
        return cr.list();
    }

    @Override
    public BookableItem getBookableItem(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (BookableItem) session.get(BookableItem.class, id);
    }

    @Override
    public void addBooking(Booking booking) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(booking);
        logger.info("BookableItem saved successfully, Booking Details=" + booking);
    }

    @Override
    public void updateBooking(Booking booking) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(booking);
        logger.info("Booking updated successfully, Booking Details=" + booking);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Booking> listBooking() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Booking.class);
        List<Booking> bookings = cr.list();
        if (bookings != null && bookings.size() > 1) {
            Collections.sort(bookings, new Comparator<Booking>() {
                @Override
                public int compare(Booking o1, Booking o2) {
                    return o1.getStartTime().compareTo(o2.getStartTime());
                }
            });
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByItemId(int itemId) {
        List<Booking> allBookings = listBooking();
        List<Booking> bookingsByItem = new ArrayList<>();
        for (Booking booking : allBookings) {
            if (booking.getItem().getId() == itemId) {
                bookingsByItem.add(booking);
            }
        }
        return bookingsByItem;
    }

    @Override
    public void deleteBookingsForItem(int itemId) {
        logger.debug("deleteBookingsForItem called, itemId" + itemId);
        List<Booking> bookingsByItemId = getBookingsByItemId(itemId);
        for (Booking booking : bookingsByItemId) {
            deleteBooking(booking);
        }
    }

    @Override
    public void deleteBooking(Booking booking) {
        logger.debug("deleteBooking: " + booking);
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(booking);
    }

    @Override
    public Booking getBookingById(int id) {
        logger.debug("getBookingById: " + id);
        Session session = this.sessionFactory.getCurrentSession();
        return (Booking) session.get(Booking.class, id);
    }

}
