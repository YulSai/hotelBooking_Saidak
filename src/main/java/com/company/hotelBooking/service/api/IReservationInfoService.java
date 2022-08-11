package com.company.hotelBooking.service.api;

import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.ReservationInfoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Interface for serving reservationInfo objects according to the business logics of reservation
 */
public interface IReservationInfoService extends IAbstractService<Long, ReservationInfoDto> {

    List<ReservationInfoDto> processBookingInfo(Map<Long, Long> booking, LocalDate checkIn,
                                                LocalDate checkOut, ReservationDto reservation);

}