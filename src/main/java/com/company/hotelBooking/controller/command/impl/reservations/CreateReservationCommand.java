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

public class CreateReservationCommand implements ICommand {
    private final IReservationService reservationService;

    public CreateReservationCommand(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        LocalDate checkIn = (LocalDate) session.getAttribute("check_in");
        LocalDate checkOut = (LocalDate) session.getAttribute("check_out");
        if (user == null) {
            req.setAttribute("message", "Please, login");
        }
        @SuppressWarnings("unchecked")
        Map<Long, Long> booking = (Map<Long, Long>) session.getAttribute("booking");
        ReservationDto processed = reservationService.processBooking(booking, user, checkIn, checkOut);

        ReservationDto created = reservationService.create(processed);
        req.setAttribute("booking", created);
        req.setAttribute("massage", "Reservation created successfully");
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_RESERVATION);
    }
}
