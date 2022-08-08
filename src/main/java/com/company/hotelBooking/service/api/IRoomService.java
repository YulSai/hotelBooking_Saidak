package com.company.hotelBooking.service.api;

import com.company.hotelBooking.service.dto.RoomDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for serving Room objects according to the business logics of Room
 */
public interface IRoomService extends IAbstractService<Long, RoomDto> {

    /**
     * Method gets Room Dto by room number
     *
     * @param number rooms number
     * @return Room Dto object
     */
    RoomDto findRoomByNumber(String number);

    /**
     * Method gets list of an available Rooms limited by time period
     *
     * @param check_in  start date to search
     * @param check_out end date to search
     * @return List of reservations
     */
    List<RoomDto> getAllAvailableRooms(LocalDate check_in, LocalDate check_out);
}