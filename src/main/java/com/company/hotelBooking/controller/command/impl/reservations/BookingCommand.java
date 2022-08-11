package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.UserDto;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Map;

public class BookingCommand implements ICommand {
    private final IReservationService reservationService;

    public BookingCommand(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        Map<Long, Long> booking = (Map<Long, Long>) session.getAttribute("booking");
        if (booking == null) {
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_BOOKING);
        } else {
            UserDto user = (UserDto) session.getAttribute("user");
            LocalDate checkIn = (LocalDate) session.getAttribute("check_in");
            LocalDate checkOut = (LocalDate) session.getAttribute("check_out");
            ReservationDto processed = reservationService.processBooking(booking, user, checkIn, checkOut);
            req.setAttribute("booking", processed);
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_BOOKING);
        }
    }
}