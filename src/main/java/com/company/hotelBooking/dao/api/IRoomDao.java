package com.company.hotelBooking.dao.api;

import com.company.hotelBooking.dao.entity.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface extends IAbstractDao interface for managing Room entities
 */
public interface IRoomDao extends IAbstractDao<Long, Room> {

    /**
     * Method finds room in the data source by its number
     *
     * @param number room number for search
     * @return room
     */
    Room findRoomByNumber(String number);

    /**
     * Method counts the number of rooms in the data source
     *
     * @return amount of rooms
     */
    int countAllRooms() throws SQLException;

    /**
     * Method finds all available rooms limited by time period
     *
     * @param check_in  start date for search
     * @param check_out end date to search
     * @return list of on available rooms
     */
    List<Room> findAllAvailableRooms(LocalDate check_in, LocalDate check_out);
}