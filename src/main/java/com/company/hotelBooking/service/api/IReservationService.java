package com.company.hotelBooking.service.api;

import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.UserDto;

import java.time.LocalDate;
import java.util.Map;

/**
 * Interface for serving reservation objects according to the business logics of reservation
 */
public interface IReservationService extends IAbstractService<Long, ReservationDto> {


    ReservationDto processBooking(Map<Long, Long> booking, UserDto user, LocalDate checkIn,
                                  LocalDate checkOut);
}