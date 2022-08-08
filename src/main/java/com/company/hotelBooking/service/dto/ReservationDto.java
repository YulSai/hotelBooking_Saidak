package com.company.hotelBooking.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class describing the object ReservationDto
 */
@Data
public class ReservationDto {
    private Long id;
    private UserDto user;
    private Long roomId;
    private RoomDto.RoomTypeDto type;
    private RoomDto.CapacityDto capacity;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private StatusDto status;
    private BigDecimal roomPrice;
    private BigDecimal totalCost;

    public enum StatusDto {
        IN_PROGRESS,
        CONFIRMED,
        REJECTED
    }
}