package com.company.hotelBooking.service.impl;

import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.dao.api.IReservationInfoDao;
import com.company.hotelBooking.dao.api.IRoomDao;
import com.company.hotelBooking.dao.entity.ReservationInfo;
import com.company.hotelBooking.dao.entity.Room;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.service.api.IReservationInfoService;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.ReservationInfoDto;
import com.company.hotelBooking.service.dto.RoomDto;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class object ReservationInfoDTO with implementation of CRUD operation operations
 */
@Log4j2
public class ReservationInfoServiceImpl implements IReservationInfoService {
    private final IReservationInfoDao reservationInfoDao;
    private final IRoomDao roomDao;

    public ReservationInfoServiceImpl(IReservationInfoDao reservationInfoDao, IRoomDao roomDao) {
        this.reservationInfoDao = reservationInfoDao;
        this.roomDao = roomDao;
    }

    @Override
    public ReservationInfoDto findById(Long id) {
        log.debug("Calling a service method \"findById\". ReservationInfoDto id = {}, time = {}", id, new Date());
        ReservationInfoDto reservationInfo = toDto(reservationInfoDao.findById(id));
        if (reservationInfo == null) {
            log.error("SQLReservationInfoService findById error. id = {}", id);
            throw new DaoException("No reservationInfo with id " + id);
        }
        return reservationInfo;
    }

    @Override
    public List<ReservationInfoDto> findAll() {
        log.debug("Calling a service method \"findAll\". Time = {}", new Date());
        return reservationInfoDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ReservationInfoDto create(ReservationInfoDto entity) {
        log.debug("Calling a service method \"create\". ReservationInfo = {}, time = {}", entity, new Date());
        return toDto(reservationInfoDao.save(toEntity(entity)));
    }

    @Override
    public List<ReservationInfoDto> processBookingInfo(Map<Long, Long> booking, LocalDate checkIn,
                                                       LocalDate checkOut, ReservationDto reservation) {
        log.debug("Calling a service method \"processBookingInfo\". Time = {}", new Date());
        return reservationInfoDao.processBookingInfo(booking, checkIn, checkOut, reservation).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ReservationInfoDto update(ReservationInfoDto entity) {
        log.debug("Calling a service method \"update\". ReservationInfo = {}, time = {}", entity, new Date());
        return toDto(reservationInfoDao.update(toEntity(entity)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Calling a service method \"delete\". ReservationInfo id = {}, time = {}", id, new Date());
        reservationInfoDao.delete(id);
        if (!reservationInfoDao.delete(id)) {
            log.error("SQLReservationService deleted error. Failed to delete reservation info with id = {}", id);
            throw new DaoException("Failed to delete reservation info with id " + id);
        }
    }

    @Override
    public List<ReservationInfoDto> findAllPages(Paging paging) {
        log.debug("Calling a service method \"findAllPages\". Time = {}", new Date());
        return reservationInfoDao.findAllPages(paging.getLimit(), paging.getOffset()).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public long countRow() {
        log.debug("Calling a service method \"countRow\". Time = {}", new Date());
        return reservationInfoDao.countRow();
    }

    /**
     * Method transforms object ReservationInfo into objectReservationInfoDto
     *
     * @param entity ReservationInfo
     * @return object RReservationInfoDto
     */
    private ReservationInfoDto toDto(ReservationInfo entity) {
        log.debug("Calling a service method \"toDto\". ReservationInfo = {}, time = {}", entity, new Date());
        ReservationInfoDto dto = new ReservationInfoDto();
        try {
            dto.setId(entity.getId());
            dto.setReservationId(entity.getReservationId());
            dto.setRoom(getRoomDto(entity));
            dto.setCheckIn(entity.getCheckIn());
            dto.setCheckOut(entity.getCheckOut());
            dto.setNights(entity.getNights());
            dto.setRoomPrice(entity.getRoomPrice());
        } catch (NullPointerException e) {
            log.error("This reservation info is not in the catalog.");
            throw new DaoException("No reservation");
        }
        return dto;
    }

    /**
     * Method transforms object Room into object RoomDto
     *
     * @param entity ReservationInfo
     * @return object RoomDto
     */
    private RoomDto getRoomDto(ReservationInfo entity) {
        log.debug("Calling a service method \"toDto\". ReservationInfo = {}, time = {}", entity, new Date());
        RoomDto dto = new RoomDto();
        try {
            dto.setId(entity.getRoom().getId());
            dto.setNumber((entity.getRoom().getNumber()));
            dto.setType(RoomDto.RoomTypeDto.valueOf(entity.getRoom().getType().toString()));
            dto.setCapacity(RoomDto.CapacityDto.valueOf(entity.getRoom().getCapacity().toString()));
            dto.setPrice(entity.getRoom().getPrice());
            dto.setStatus(RoomDto.RoomStatusDto.valueOf(entity.getRoom().getStatus().toString()));
        } catch (NullPointerException e) {
            log.error("This room is not in the catalog.");
            throw new DaoException("No room");
        }
        return dto;
    }

    /**
     * Method transforms object ReservationInfoDto into object ReservationInfo
     *
     * @param dto ReservationInfoDto
     * @return object ReservationInfo
     */
    private ReservationInfo toEntity(ReservationInfoDto dto) {
        log.debug("Calling a service method \"toDto\". ReservationInfoDto = {}, time = {}", dto, new Date());
        ReservationInfo entity = new ReservationInfo();
        try {
            entity.setId(dto.getId());
            entity.setReservationId(dto.getReservationId());
            entity.setRoom(getRoom(dto));
            entity.setCheckIn(dto.getCheckIn());
            entity.setCheckOut(dto.getCheckOut());
            entity.setNights(dto.getNights());
            entity.setRoomPrice(dto.getRoomPrice());
        } catch (NullPointerException e) {
            log.error("This reservation info is not in the catalog.");
            throw new DaoException("No reservation info");
        }
        return entity;
    }

    /**
     * Method transforms object RoomDto into object Room
     *
     * @param dto ReservationInfoDto
     * @return object Room
     */
    private Room getRoom(ReservationInfoDto dto) {
        log.debug("Calling a service method \"toDto\". ReservationInfoDto = {}, time = {}", dto, new Date());
        Room entity = new Room();
        try {
            entity.setId(dto.getRoom().getId());
            entity.setNumber((dto.getRoom().getNumber()));
            entity.setType(Room.RoomType.valueOf(dto.getRoom().getType().toString()));
            entity.setCapacity(Room.Capacity.valueOf(dto.getRoom().getCapacity().toString()));
            entity.setPrice(dto.getRoom().getPrice());
            entity.setStatus(Room.RoomStatus.valueOf(dto.getRoom().getStatus().toString()));
        } catch (NullPointerException e) {
            log.error("This room is not in the catalog.");
            throw new DaoException("No room");
        }
        return entity;
    }
}