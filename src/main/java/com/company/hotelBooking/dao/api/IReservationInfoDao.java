package com.company.hotelBooking.dao.api;

import com.company.hotelBooking.dao.entity.ReservationInfo;

import java.util.List;

/*
 * Interface extends IAbstractDao interface for managing ReservationInfo entities
 */
public interface IReservationInfoDao extends IAbstractDao<Long, ReservationInfo> {
    List<ReservationInfo> findByReservationId(Long reservationId);
}
