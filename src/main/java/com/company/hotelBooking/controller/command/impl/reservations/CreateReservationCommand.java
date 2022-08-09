package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class CreateReservationCommand implements ICommand {
    private final IReservationService reservationService;

    public CreateReservationCommand(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
//        @SuppressWarnings("unchecked")
//        Map<Long, Long> booking = (Map<Long, Long>) session.getAttribute("booking");
//        if (booking == null) {
//            req.setAttribute("massage", "Booking is empty");
//            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_BOOKING);
//        }
//
//        HttpSession session = req.getSession();
//        @SuppressWarnings("unchecked")
//        Map<Long, Integer> booking = (Map<Long, Integer>) session.getAttribute("booking");
//        if (booking == null){
//            req.setAttribute("massage", "Booking is empty");
//            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_BOOKING);
//        }
//        User user = (User) session.getAttribute("user");
//
//        LocalDateTime reservationDate = (LocalDateTime) session.getAttribute("reservation_date");
//        LocalDate checkIn = (LocalDate) session.getAttribute("check_in");
//        LocalDate checkOut = (LocalDate) session.getAttribute("check_out");
//
//        Reservation created = reservationService.processCart(booking, user, reservationDate, checkIn, checkOut);
//        req.setAttribute("booking", created);
//        req.setAttribute("massage", "Reservation created successfully");
//        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_BOOKING);
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_BOOKING);

    }
}
