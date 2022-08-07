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
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Room.RoomType roomType;
    private Room.Capacity roomCapacity;
    private Status status;
    private Long userId;
    private Long roomId;
    private BigDecimal invoice;

    public enum Status {
        IN_PROGRESS,
        CONFIRMED,
        REJECTED
    }
}