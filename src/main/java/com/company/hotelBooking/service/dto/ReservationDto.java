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
    private UserDto userDto;
    private Long roomId;
    private RoomDto.RoomTypeDto type;
    private RoomDto.CapacityDto capacity;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private StatusDto status;
    private BigDecimal invoice;

    public enum StatusDto {
        IN_PROGRESS,
        CONFIRMED,
        REJECTED
    }
}