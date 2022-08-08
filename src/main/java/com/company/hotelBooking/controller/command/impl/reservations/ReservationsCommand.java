package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.controller.command.util.PagingUtil;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
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
	private final IReservationService reservationService;
	private final PagingUtil pagingUtil;

	public ReservationsCommand(IReservationService service, PagingUtil pagingUtil) {
		this.reservationService = service;
		this.pagingUtil = pagingUtil;
	}

	@Override
	public String execute(HttpServletRequest req) {
		Paging paging = pagingUtil.getPaging(req);
		List<ReservationDto> reservations = reservationService.findAllPages(paging);
		if (reservations.size() == 0) {
			log.error("Incorrect address entered. Time = {}", new Date());
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
		} else {
			pagingUtil.setTotalPages(req, paging, reservationService);
			req.setAttribute("reservations", reservations);
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_RESERVATIONS);
		}
	}
}