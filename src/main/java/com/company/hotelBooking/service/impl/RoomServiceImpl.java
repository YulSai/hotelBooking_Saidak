package com.company.hotelBooking.service.impl;

import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.dao.api.IRoomDao;
import com.company.hotelBooking.dao.entity.Room;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Class object RoomDTO with implementation of CRUD operation operations
 */
@Log4j2
public class RoomServiceImpl implements IRoomService {

    private final IRoomDao roomDao;

    public RoomServiceImpl(IRoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public RoomDto findById(Long id) {
        log.debug("Calling a service method \"findById\". RoomDto id = {}, time = {}", id, new Date());
        RoomDto roomDto = toDto(roomDao.findById(id));
        if (roomDto == null) {
            log.error("SQLRoomService findById error. id = {}", id);
            throw new DaoException("No room with id " + id);
        }
        return roomDto;
    }

    public List<RoomDto> findAll() {
        log.debug("Calling a service method \"findAll\". Time = {}", new Date());
        return roomDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public RoomDto create(RoomDto roomDto) {
        log.debug("Calling a service method \"create\". RoomDto = {}, time = {}", roomDto, new Date());
        Room existing = roomDao.findRoomByNumber(roomDto.getNumber());
        if (existing != null) {
            log.error("Room with number = {} already exists", roomDto.getNumber());
            throw new DaoException("Room already exists");
        }
        return toDto(roomDao.save(toEntity(roomDto)));
    }

    @Override
    public RoomDto update(RoomDto roomDto) {
        log.debug("Calling a service method \"update\". RoomDto = {}, time = {}", roomDto, new Date());
        Room existing = roomDao.findRoomByNumber((roomDto.getNumber()));
        if (existing != null && !existing.getId().equals(roomDto.getId())) {
            log.error("Room with number = {} already exists", roomDto.getNumber());
            throw new DaoException("Room already exists");
        }
        return toDto(roomDao.update(toEntity(roomDto)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Calling a service method \"delete\". RoomDto id = {}, time = {}", id, new Date());
        roomDao.delete(id);
        if (!roomDao.delete(id)) {
            log.error("SQLRoomService deleted error. Failed to delete room with id = {}", id);
            throw new DaoException("Failed to delete room with id " + id);
        }
    }

    @Override
    public List<RoomDto> findAllPages(Paging paging) {
        log.debug("Calling a service method \"findAllPages\". Time = {}", new Date());
        return roomDao.findAllPages(paging.getLimit(), paging.getOffset()).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public long countRow() {
        log.debug("Calling a service method \"countRow\". Time = {}", new Date());
        return roomDao.countRow();
    }

    @Override
    public List<RoomDto> getAllAvailableRooms(LocalDate check_in, LocalDate check_out) {
        log.debug("Calling a service method \"getAllAvailableRooms\". Time = {}", new Date());
        return roomDao.findAllAvailableRooms(check_in, check_out).stream()
                .map(this::toDto)
                .toList();
    }


    public RoomDto findRoomByNumber(String number) {
        log.debug("Calling a service method \"findRoomByNumber\". RoomDto number = {}, time = {}",
                number, new Date());
        RoomDto roomDto = toDto(roomDao.findRoomByNumber(number));
        if (roomDto == null) {
            log.error("SQLRoomService deleted error. No room with number = {}", number);
            throw new DaoException("No room with number" + number);
        }
        return roomDto;
    }

    /**
     * Method transforms object Room into object RoomDto
     *
     * @param entity object Room
     * @return object RoomDto
     */
    private RoomDto toDto(Room entity) {
        log.debug("Calling a service method \"toDto\". Room = {}, time = {}", entity, new Date());
        RoomDto dto = new RoomDto();
        try {
            dto.setId(entity.getId());
            dto.setTypeDto(RoomDto.RoomTypeDto.valueOf(entity.getType().toString()));
            dto.setPrice(entity.getPrice());
            dto.setStatusDto(RoomDto.RoomStatusDto.valueOf(entity.getStatus().toString()));
            dto.setCapacityDto(RoomDto.CapacityDto.valueOf(entity.getCapacity().toString()));
            dto.setNumber(entity.getNumber());
        } catch (NullPointerException e) {
            log.error("This room is not in the catalog.");
            throw new DaoException("This room is not in the catalog");
        }
        return dto;
    }

    /**
     * Method transforms object RoomDto into object Room
     *
     * @param dto RoomDto
     * @return object Room
     */
    private Room toEntity(RoomDto dto) {
        log.debug("Calling a service method \"toEntity\". RoomDto = {}, time = {}", dto, new Date());
        Room entity = new Room();
        entity.setId(dto.getId());
        entity.setType(Room.RoomType.valueOf(dto.getTypeDto().toString()));
        entity.setPrice(dto.getPrice());
        entity.setStatus(Room.RoomStatus.valueOf(dto.getStatusDto().toString()));
        entity.setCapacity(Room.Capacity.valueOf(dto.getCapacityDto().toString()));
        entity.setNumber(dto.getNumber());
        return entity;
    }
}