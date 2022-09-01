package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.PagesManager;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "clean_booking"
 */
public class CleanBookingCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().removeAttribute("booking");
        return PagesManager.PAGE_BOOKING;
    }
}