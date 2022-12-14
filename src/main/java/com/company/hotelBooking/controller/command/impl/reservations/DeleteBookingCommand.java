package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Map;

/**
 * Class for processing HttpServletRequest "delete_booking"
 */
public class DeleteBookingCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        LocalDate checkIn = (LocalDate) session.getAttribute("check_in");
        LocalDate checkOut = (LocalDate) session.getAttribute("check_out");
        @SuppressWarnings("unchecked")
        Map<Long, Long> booking = (Map<Long, Long>) session.getAttribute("booking");
        booking.remove(Long.valueOf(req.getParameter("id")));
        session.setAttribute("booking", booking);
        session.setAttribute("check_in", checkIn);
        session.setAttribute("check_out", checkOut);
        return "redirect:controller?command=booking";
    }
}