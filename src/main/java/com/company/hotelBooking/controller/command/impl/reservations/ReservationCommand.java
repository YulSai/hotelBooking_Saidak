package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.exceptions.NotFoundException;
import com.company.hotelBooking.managers.PagesManager;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;

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
        String regexId = "\\d*";
        String argument = req.getParameter("id");
        if (argument == null) {
            log.error("Incorrect address entered");
            throw new NotFoundException();
        } else if (argument.equals("")) {
            log.error("Incorrect address entered");
            throw new NotFoundException();
        } else if (Pattern.matches(regexId, argument)) {
            Long id = Long.parseLong(req.getParameter("id"));
            ReservationDto reservation = service.findById(id);
            if (reservation.getId() == null) {
                log.error("Incorrect address entered");
                throw new NotFoundException();
            } else {
                req.setAttribute("reservation", reservation);
                log.info("Appeal to reservation.jsp");
                return PagesManager.PAGE_RESERVATION;
            }
        } else {
            log.error("Incorrect address entered");
            throw new NotFoundException();
        }
    }
}
