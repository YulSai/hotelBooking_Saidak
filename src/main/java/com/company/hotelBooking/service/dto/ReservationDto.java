package com.company.hotelBooking.service.dto;

import com.company.hotelBooking.dao.entity.Room;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class describing the object ReservationDto
 */
@Data
public class ReservationDto {
    private Long id;
    private LocalDate dateCheckIn;
    private LocalDate dateCheckOut;
    private Room.RoomType type;
    private Room.Capacity capacity;
    private StatusDto status;
    private Long userId;
    private Long roomId;
    private BigDecimal invoice;

    public enum StatusDto {
        IN_PROGRESS,
        CONFIRMED,
        REJECTED
    }
}