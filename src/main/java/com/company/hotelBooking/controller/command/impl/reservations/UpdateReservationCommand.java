package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateReservationCommand implements ICommand {
    private final IReservationService service;

    public UpdateReservationCommand(IReservationService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        ReservationDto reservation = service.findById(Long.valueOf(req.getParameter("id")));
        String status = req.getParameter("status").toUpperCase();
        reservation.setStatus(ReservationDto.StatusDto.valueOf(status));

        ReservationDto updated = service.update(reservation);
        req.setAttribute("reservation", updated);
        req.setAttribute("massage", "Reservation was updated successfully");
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_RESERVATION);
    }
}