package com.company.hotelBooking.dao.api;

import com.company.hotelBooking.dao.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

/*
 * Interface extends IAbstractDao interface for managing Reservation entities
 */
public interface IReservationDao extends IAbstractDao<Long, Reservation> {

    /**
     * Method returns difference between two dates in reservation
     * @param id reservation id
     * @return difference between two dates
     */
    Integer differenceBetweenDate(Long id);

    /**
     * Method finds all records reservations by room id
     *
     * @param id    room id
     * @param begin start date to search
     * @param end   end date for search
     * @return list of reservations by room id and limited by time period
     */
    List<Reservation> findAllByRoomIdAndTimePeriod(Long id, LocalDate begin, LocalDate end);

    /**
     * Method finds all records reservations by user id and limited by date*
     * @param id    room id
     * @param begin start date to search
     * @return list of reservations by user id and limited by date
     */
    List<Reservation> findAllByUserIdAndTimePeriod(Long id, LocalDate begin);



}
