package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteBookingCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
//        HttpSession session = req.getSession();
//        Long id = Long.valueOf(req.getParameter("id"));
//        session.removeAttribute("id");
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_BOOKING);
    }
}