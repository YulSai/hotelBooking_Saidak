package com.company.hotelBooking.dao.api;

import com.company.hotelBooking.dao.entity.ReservationInfo;
import com.company.hotelBooking.service.dto.ReservationDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/*
 * Interface extends IAbstractDao interface for managing ReservationInfo entities
 */
public interface IReservationInfoDao extends IAbstractDao<Long, ReservationInfo> {
    List<ReservationInfo> findByReservationId(Long reservationId);

    List<ReservationInfo> processBookingInfo(Map<Long, Long> booking, LocalDate checkIn,
                                             LocalDate checkOut, ReservationDto reservation);
}
