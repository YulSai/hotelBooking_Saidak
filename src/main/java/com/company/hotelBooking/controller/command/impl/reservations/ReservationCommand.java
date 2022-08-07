package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

/**
 * Class for processing HttpServletRequest "reservation"
 */
@Log4j2
public class ReservationCommand implements ICommand {
    private final IReservationService service;

    public ReservationCommand(IReservationService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
//		String regexId = "\\d*";
//		String argument = req.getParameter("id");
//		if (argument == null) {
//			log.error("Incorrect address entered. Time = {}", new Date());
//			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
//		} else if (argument.equals("")) {
//			log.error("Incorrect address entered. Time = {}", new Date());
//			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
//		} else if (Pattern.matches(regexId, argument)) {
//			Long id = Long.parseLong(req.getParameter("id"));
//			Reservation reservation = service.findById(id);
//			if (reservation.getId() == null) {
//				log.error("Incorrect address entered. Time = {}", new Date());
//				return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
//			} else {
//				Long nights = null;
//				List<ReservationInfo> info = reservation.getDetails();
//				for (ReservationInfo detail: info) {
//					nights = ChronoUnit.DAYS.between(detail.getCheckIn(),detail.getCheckOut());
//				}
//				req.setAttribute("reservation", reservation);
//				req.setAttribute("nights", nights);
//				log.info("Appeal to reservation.jsp. Time = {}", new Date());
//				return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_RESERVATION);
//			}
//		} else {
//			log.error("Incorrect address entered. Time = {}", new Date());
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
    }
}
