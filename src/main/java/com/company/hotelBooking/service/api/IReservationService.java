package com.company.hotelBooking.service.api;

import com.company.hotelBooking.exceptions.ServicesException;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.RoomDto;
import com.company.hotelBooking.service.dto.UserDto;

import java.time.LocalDate;

/**
 * Interface for serving reservation objects according to the business logics of reservation
 */
public interface IReservationService extends IAbstractService<Long, ReservationDto> {
    /**
     * Method gets difference between two dates in reservation
     *
     * @param id reservation id
     * @return difference between two dates
     */
    Integer getDifferenceDate(Long id) throws ServicesException;

    /**
     * Method creates new Invoice by room id and reservation id
     *
     * @param roomId        room id
     * @param reservationId reservation id
     */
    void createBooking(Long roomId, Long reservationId);

    ReservationDto processBooking(UserDto user, RoomDto room, Long roomId, LocalDate checkIn, LocalDate checkOut);
}