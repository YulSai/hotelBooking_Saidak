package com.company.hotelBooking.dao.api;

import com.company.hotelBooking.dao.entity.Reservation;

import java.util.List;

/*
 * Interface extends IAbstractDao interface for managing Reservation entities
 */
public interface IReservationDao extends IAbstractDao<Long, Reservation> {
    List<Reservation> findAllPagesByUsers(int limit, long offset, Long id);
}