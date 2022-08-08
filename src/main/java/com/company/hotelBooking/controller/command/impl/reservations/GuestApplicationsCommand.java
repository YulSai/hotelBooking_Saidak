package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GuestApplicationsCommand implements ICommand {
    private final IReservationService reservationService;

    public GuestApplicationsCommand(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        //TODO Thinking about realisation
                return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_RESERVATIONS);
    }
}