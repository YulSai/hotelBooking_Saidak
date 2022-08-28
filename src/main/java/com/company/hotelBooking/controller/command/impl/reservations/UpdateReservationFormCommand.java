package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "update_reservation_form"
 */
public class UpdateReservationFormCommand implements ICommand {

    private final IReservationService service;

    public UpdateReservationFormCommand(IReservationService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        ReservationDto reservationDto = service.findById(id);
        req.setAttribute("reservation", reservationDto);
        return AppConstants.PAGE_UPDATE_RESERVATION;
    }
}