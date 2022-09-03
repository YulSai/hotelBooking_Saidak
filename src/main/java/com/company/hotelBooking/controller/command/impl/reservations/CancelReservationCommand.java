package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.managers.PagesManager;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "cancel_reservation"
 */
public class CancelReservationCommand implements ICommand {
    private final IReservationService service;

    public CancelReservationCommand(IReservationService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        ReservationDto reservation = service.findById(Long.valueOf(req.getParameter("id")));
        reservation.setStatus(ReservationDto.StatusDto.REJECTED);

        ReservationDto updated = service.update(reservation);
        req.setAttribute("reservation", updated);
        req.setAttribute("message", MessageManger.getMessage("msg.reservation.updated"));
        return PagesManager.PAGE_RESERVATION;
    }
}