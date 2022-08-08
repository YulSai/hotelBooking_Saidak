package com.company.hotelBooking.dao.api;

import com.company.hotelBooking.dao.entity.Reservation;

/*
 * Interface extends IAbstractDao interface for managing Reservation entities
 */
public interface IReservationDao extends IAbstractDao<Long, Reservation> {

    /**
     * Method returns difference between two dates in reservation
     *
     * @param id reservation id
     * @return difference between two dates
     */
    Integer differenceBetweenDate(Long id);
}