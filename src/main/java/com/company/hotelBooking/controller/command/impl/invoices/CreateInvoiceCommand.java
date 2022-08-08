package com.company.hotelBooking.controller.command.impl.invoices;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import jakarta.servlet.http.HttpServletRequest;


public class CreateInvoiceCommand implements ICommand {
    private final IReservationService reservationService;

    public CreateInvoiceCommand(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        //TODO Thinking about realisation
//        Long roomId = Long.parseLong(req.getParameter("room_id"));
//        Long applicationId = Long.parseLong(req.getParameter("reservation_id"));
//
//        reservationService.createBooking(roomId, applicationId);
        return "controller?command=in_progress";
    }
}
