package com.company.hotelBooking.dao.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class describing the object Reservation
 */
@Data
public class Reservation {
    private Long id;
    private User user;
    private Long roomId;
    private Room.RoomType type;
    private Room.Capacity capacity;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Status status;
    private BigDecimal invoice;

    public enum Status {
        IN_PROGRESS,
        CONFIRMED,
        REJECTED
    }
}