package com.company.hotelBooking.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

/**
 * Class describing the object ReservationInfoDto
 */
@Data
public class ReservationInfoDto {
    private Long id;
    private Long reservationId;
    private RoomDto room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal roomPrice;

}