package com.company.hotelBooking.service.impl;

import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.dao.api.IReservationDao;
import com.company.hotelBooking.dao.api.IRoomDao;
import com.company.hotelBooking.dao.entity.Reservation;
import com.company.hotelBooking.dao.entity.ReservationInfo;
import com.company.hotelBooking.dao.entity.Room;
import com.company.hotelBooking.dao.entity.User;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.ReservationInfoDto;
import com.company.hotelBooking.service.dto.RoomDto;
import com.company.hotelBooking.service.dto.UserDto;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Class object ReservationDTO with implementation of CRUD operation operations
 */
@Log4j2
public class ReservationServiceImpl implements IReservationService {

    private final IReservationDao reservationDao;
    private final IRoomDao roomDao;

    public ReservationServiceImpl(IReservationDao reservationDao, IRoomDao roomDao) {
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
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
    public ReservationDto processBooking(Map<Long, Long> booking, UserDto user, LocalDate checkIn,
                                         LocalDate checkOut) {
        ReservationDto reservation = new ReservationDto();
        reservation.setUser(user);
        reservation.setStatus(ReservationDto.StatusDto.IN_PROGRESS);
        List<ReservationInfoDto> details = new ArrayList<>();
        booking.forEach((roomId, quantity) -> {
            ReservationInfoDto info = new ReservationInfoDto();
            RoomDto room = getRoomDto(roomId);
            info.setRoom(room);
            info.setCheckIn(checkIn);
            info.setCheckOut(checkOut);
            info.setRoomPrice(room.getPrice());
            details.add(info);
        });
        reservation.setDetails(details);
        BigDecimal totalCost = calculatePrice(details);
        reservation.setTotalCost(totalCost);
        return reservation;
    }

    private BigDecimal calculatePrice(List<ReservationInfoDto> details) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (ReservationInfoDto detail : details) {
            BigDecimal roomPrice = detail.getRoomPrice();
            Long nights = ChronoUnit.DAYS.between(detail.getCheckIn(), detail.getCheckOut());
            totalCost = totalCost.add(roomPrice.multiply(BigDecimal.valueOf(nights)));
        }
        return totalCost;
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
            dto.setUser(getUserDto(entity));
            dto.setRoomPrice(entity.getRoomPrice());
            dto.setTotalCost(entity.getTotalCost());
            dto.setStatus(ReservationDto.StatusDto.valueOf(entity.getStatus().toString()));
            List<ReservationInfoDto> reservationInfoDto = new ArrayList<>();
            List<ReservationInfo> reservationInfo = entity.getDetails();
            for (ReservationInfo info : reservationInfo) {
                ReservationInfoDto resDto = getReservationInfoDto(info);
                reservationInfoDto.add(resDto);
            }
            dto.setDetails(reservationInfoDto);
        } catch (NullPointerException e) {
            log.error("This reservation is not in the catalog.");
            throw new DaoException("No reservation");
        }
        return dto;
    }

    private ReservationInfoDto getReservationInfoDto(ReservationInfo info) {
        ReservationInfoDto resDto = new ReservationInfoDto();
        resDto.setId(info.getId());
        resDto.setReservationId(info.getReservationId());
        resDto.setRoom(getRoomDto(info.getRoom().getId()));
        resDto.setCheckIn(info.getCheckIn());
        resDto.setCheckOut(info.getCheckOut());
        resDto.setNights(info.getNights());
        resDto.setRoomPrice(info.getRoomPrice());
        return resDto;
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
            dto.setRole(UserDto.RoleDto.valueOf(entity.getUser().getRole().toString()));
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
            entity.setRoomPrice(dto.getRoomPrice());
            entity.setTotalCost(dto.getTotalCost());
            entity.setStatus(Reservation.Status.valueOf(dto.getStatus().toString()));
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
            entity.setId(dto.getUser().getId());
            entity.setFirstName(dto.getUser().getFirstName());
            entity.setLastName(dto.getUser().getLastName());
            entity.setEmail(dto.getUser().getEmail());
            entity.setPassword(dto.getUser().getPassword());
            entity.setPhoneNumber(dto.getUser().getPhoneNumber());
            entity.setRole(User.Role.valueOf(dto.getUser().getRole().toString()));
        } catch (NullPointerException e) {
            log.error("This user is not in the catalog.");
            throw new DaoException("No user");
        }
        return entity;
    }

    /**
     * Method transforms object RoomDto into object Room
     *
     * @param roomId Id Room
     * @return object RoomDto
     */
    private RoomDto getRoomDto(Long roomId) {
        RoomDto room = new RoomDto();
        Room entity = roomDao.findById(roomId);
        room.setId(entity.getId());
        room.setType(RoomDto.RoomTypeDto.valueOf(entity.getType().toString()));
        room.setCapacity(RoomDto.CapacityDto.valueOf(entity.getCapacity().toString()));
        room.setStatus(RoomDto.RoomStatusDto.valueOf(entity.getStatus().toString()));
        room.setPrice(entity.getPrice());
        room.setNumber(entity.getNumber());
        return room;
    }
}