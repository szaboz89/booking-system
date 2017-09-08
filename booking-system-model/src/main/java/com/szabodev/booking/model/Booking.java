package com.szabodev.booking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOKING")
public class Booking implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "item")
    @ManyToOne(fetch = FetchType.EAGER)
    private BookableItem item;

    private String name;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date startTime;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date endTime;

    private Long startTime;

    private Long endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookableItem getItem() {
        return item;
    }

    public void setItem(BookableItem item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    //    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }
//
//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", item=" + item +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}