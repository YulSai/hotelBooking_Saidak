package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.dao.entity.Reservation;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

/**
 * Class for processing HttpServletRequest "reservations"
 */
@Log4j2
public class ReservationsCommand implements ICommand {
	private final IReservationService service;

	public ReservationsCommand(IReservationService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest req) {
		List<Reservation> reservations = service.findAll();
		if (reservations.size() == 0) {
			log.error("Incorrect address entered. Time = {}", new Date());
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
		} else {
			req.setAttribute("reservations", reservations);
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_RESERVATIONS);
		}
	}
}