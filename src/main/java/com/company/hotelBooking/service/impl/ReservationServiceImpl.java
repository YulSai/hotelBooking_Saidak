package com.company.hotelBooking.service.impl;

import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.dao.api.IReservationDao;
import com.company.hotelBooking.dao.entity.Reservation;
import com.company.hotelBooking.dao.entity.Room;
import com.company.hotelBooking.dao.entity.User;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.RoomDto;
import com.company.hotelBooking.service.dto.UserDto;
import lombok.extern.log4j.Log4j2;

import java.util.*;

/**
 * Class object ReservationDTO with implementation of CRUD operation operations
 */
@Log4j2
public class ReservationServiceImpl implements IReservationService {

    IReservationDao reservationDao;

    public ReservationServiceImpl(IReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }


    @Override
    public ReservationDto findById(Long id) {
        log.debug("Calling a service method \"findById\". Reservation id = {}, time = {}", id, new Date());
        ReservationDto reservation = toDto(reservationDao.findById(id));
        if (reservation == null) {
            log.error("SQLReservationService findById error. id = {}", id);
            throw new DaoException("No reservation with id " + id);
        }
        return reservation;
    }

    public List<ReservationDto> findAll() {
        log.debug("Calling a service method \"findAll\". Time = {}", new Date());
        return reservationDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ReservationDto create(ReservationDto entity) {
        log.debug("Calling a service method \"create\". Reservation = {}, time = {}", entity, new Date());
        return toDto(reservationDao.save(toEntity(entity)));
    }

    @Override
    public ReservationDto update(ReservationDto entity) {
        log.debug("Calling a service method \"update\". Reservation = {}, time = {}", entity, new Date());
        return toDto(reservationDao.update(toEntity(entity)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Calling a service method \"delete\". Reservation id = {}, time = {}", id, new Date());
        reservationDao.delete(id);
        if (!reservationDao.delete(id)) {
            log.error("SQLReservationService deleted error. Failed to delete reservation with id = {}", id);
            throw new DaoException("Failed to delete reservation with id " + id);
        }
    }

    @Override
    public List<ReservationDto> findAllPages(Paging paging) {
        log.debug("Calling a service method \"findAllPages\". Time = {}", new Date());
        return reservationDao.findAllPages(paging.getLimit(), paging.getOffset()).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public long countRow() {
        log.debug("Calling a service method \"countRow\". Time = {}", new Date());
        return reservationDao.countRow();
    }

    @Override
    public Integer getDifferenceDate(Long id) {
        log.debug("Calling a service method \"getDifferenceDate\". Reservation id = {}, time = {}", id, new Date());
        return reservationDao.differenceBetweenDate(id);
    }

    @Override
    public void createBooking(Long roomId, Long reservationId) {
        //TODO Thinking about realisation
//        try {
//            Optional<Room> optionalRoom = Optional.ofNullable(roomDao.findById(roomId));
//            Room room = optionalRoom.orElseThrow(() -> new ServicesException("room with id=" + roomId + "doesn't exist"));
//
//            Optional<Reservation> optionalReservation = Optional.ofNullable(reservationDao.findById(reservationId));
//            Reservation reservation = optionalReservation.orElseThrow(() ->
//                    new ServicesException("application with id=" + reservationId + "doesn't exist"));
//
//            Integer differenceDate = reservationDao.differenceBetweenDate(reservationId);
//            BigDecimal priceRoom = room.getPrice();
//            BigDecimal totalPrice = priceRoom.multiply(new BigDecimal(differenceDate));
//
//            reservation.setStatus(Reservation.Status.CONFIRMED);
//            reservation.setRoomId(roomId);
//            reservation.setInvoice(totalPrice);
//            reservationDao.save(reservation);
//        } catch (DaoException | ServicesException e) {
//            log.error("SQLReservationService create error. Failed to create reservation with roomid = {} " +
//                    "and reservationid = {}", roomId, reservationId);
//            throw new RuntimeException("Failed to create reservation", e);
//        }
    }

    /**
     * Method transforms object Reservation into object ReservationDto
     *
     * @param entity Reservation
     * @return object ReservationDto
     */
    private ReservationDto toDto(Reservation entity) {
        log.debug("Calling a service method \"toDto\". Reservation = {}, time = {}", entity, new Date());
        ReservationDto dto = new ReservationDto();
        try {
            dto.setId(entity.getId());
            dto.setUserDto(getUserDto(entity));
            dto.setRoomId(entity.getRoomId());
            dto.setType(RoomDto.RoomTypeDto.valueOf(entity.getType().toString()));
            dto.setCapacity(RoomDto.CapacityDto.valueOf(entity.getCapacity().toString()));
            dto.setCheckIn(entity.getCheckIn());
            dto.setCheckOut(entity.getCheckOut());
            dto.setStatus(ReservationDto.StatusDto.valueOf(entity.getStatus().toString()));
            dto.setInvoice(entity.getInvoice());
        } catch (NullPointerException e) {
            log.error("This reservation is not in the catalog.");
            throw new DaoException("No reservation");
        }
        return dto;
    }

    /**
     * Method transforms object User into object UserDto
     *
     * @param entity Reservation
     * @return object UserDto
     */
    private UserDto getUserDto(Reservation entity) {
        log.debug("Calling a service method \"toDto\". Reservation = {}, time = {}", entity, new Date());
        UserDto dto = new UserDto();
        try {
            dto.setId(entity.getUser().getId());
            dto.setFirstName(entity.getUser().getFirstName());
            dto.setLastName(entity.getUser().getLastName());
            dto.setEmail(entity.getUser().getEmail());
            dto.setPassword(entity.getUser().getPassword());
            dto.setPhoneNumber(entity.getUser().getPhoneNumber());
            dto.setRoleDto(UserDto.RoleDto.valueOf(entity.getUser().getRole().toString()));
        } catch (NullPointerException e) {
            log.error("This user is not in the catalog.");
            throw new DaoException("No user");
        }
        return dto;
    }

    /**
     * Method transforms object ReservationDto into object Reservation
     *
     * @param dto ReservationDto
     * @return object Reservation
     */
    private Reservation toEntity(ReservationDto dto) {
        log.debug("Calling a service method \"toDto\". ReservationDto = {}, time = {}", dto, new Date());
        Reservation entity = new Reservation();
        try {
            entity.setId(dto.getId());
            entity.setUser(getUser(dto));
            entity.setRoomId(dto.getRoomId());
            entity.setType(Room.RoomType.valueOf(dto.getType().toString()));
            entity.setCapacity(Room.Capacity.valueOf(dto.getCapacity().toString()));
            entity.setCheckIn(dto.getCheckIn());
            entity.setCheckOut(dto.getCheckOut());
            entity.setStatus(Reservation.Status.valueOf(dto.getStatus().toString()));
            entity.setInvoice(dto.getInvoice());
        } catch (NullPointerException e) {
            log.error("This reservation is not in the catalog.");
            throw new DaoException("No reservation");
        }
        return entity;
    }

    /**
     * Method transforms object UserDto into object User
     *
     * @param dto ReservationDto
     * @return object User
     */
    private User getUser(ReservationDto dto) {
        log.debug("Calling a service method \"toDto\". ReservationDto = {}, time = {}", dto, new Date());
        User entity = new User();
        try {
            entity.setId(dto.getUserDto().getId());
            entity.setFirstName(dto.getUserDto().getFirstName());
            entity.setLastName(dto.getUserDto().getLastName());
            entity.setEmail(dto.getUserDto().getEmail());
            entity.setPassword(dto.getUserDto().getPassword());
            entity.setPhoneNumber(dto.getUserDto().getPhoneNumber());
            entity.setRole(User.Role.valueOf(dto.getUserDto().getRoleDto().toString()));
        } catch (NullPointerException e) {
            log.error("This user is not in the catalog.");
            throw new DaoException("No user");
        }
        return entity;
    }
}