package com.company.hotelBooking.service.impl;

import com.company.hotelBooking.dao.api.IReservationDao;
import com.company.hotelBooking.dao.api.IRoomDao;
import com.company.hotelBooking.dao.entity.Reservation;
import com.company.hotelBooking.dao.entity.Room;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.exceptions.ServicesException;
import com.company.hotelBooking.service.api.IReservationService;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Class object ReservationDTO with implementation of CRUD operation operations
 */
@Log4j2
public class ReservationServiceImpl implements IReservationService {

	IReservationDao reservationDao;
	IRoomDao roomDao;

	public ReservationServiceImpl(IReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}


	@Override
	public Reservation findById(Long id) {
		log.debug("Calling a service method \"findById\". Reservation id = {}, time = {}", id, new Date());
		Reservation reservation = reservationDao.findById(id);
		if (reservation == null) {
			log.error("SQLReservationService findById error. id = {}", id);
			throw new DaoException("No reservation with id " + id);
		}
		return reservation;
	}

	public List<Reservation> findAll() {
		log.debug("Calling a service method \"findAll\". Time = {}", new Date());
		return reservationDao.findAll();
	}

	@Override
	public Reservation create(Reservation reservation) {
		log.debug("Calling a service method \"create\". Reservation = {}, time = {}", reservation, new Date());
		return reservationDao.save(reservation);
	}
	@Override
	public Reservation update(Reservation reservation) {
		log.debug("Calling a service method \"update\". Reservation = {}, time = {}", reservation, new Date());
		return reservationDao.update(reservation);
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
	public Integer getDifferenceDate(Long id) {
		log.debug("Calling a service method \"getDifferenceDate\". Reservation id = {}, time = {}", id, new Date());
//		try {
//
//		} catch (DaoException e) {
//			throw new ServicesException(e);
//		}
		return reservationDao.differenceBetweenDate(id);
	}

	@Override
	public void createInvoice(Long roomId, Long reservationId) {
		try {
			Optional<Room> optionalRoom = Optional.ofNullable(roomDao.findById(roomId));
			Room room = optionalRoom.orElseThrow(() -> new ServicesException("room with id=" + roomId + "doesn't exist"));

			Optional<Reservation> optionalReservation = Optional.ofNullable(reservationDao.findById(reservationId));
			Reservation reservation = optionalReservation.orElseThrow(() ->
					new ServicesException("application with id=" + reservationId + "doesn't exist"));

			Integer differenceDate = reservationDao.differenceBetweenDate(reservationId);
			BigDecimal priceRoom = room.getPrice();
			BigDecimal totalPrice = priceRoom.multiply(new BigDecimal(differenceDate));

			reservation.setStatus(Reservation.Status.CONFIRMED);
			reservation.setRoomId(roomId);
			reservation.setInvoice(totalPrice);
			reservationDao.save(reservation);
		} catch (DaoException | ServicesException e) {
			//throw new ServicesException(e);
		}
	}
}