package com.company.hotelBooking.service.api;

import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.RoomDto;
import com.company.hotelBooking.service.dto.UserDto;

import java.time.LocalDate;

/**
 * Interface for serving reservation objects according to the business logics of reservation
 */
public interface IReservationService extends IAbstractService<Long, ReservationDto> {


    ReservationDto processBooking(UserDto user, RoomDto room, Long roomId, LocalDate checkIn, LocalDate checkOut);
}