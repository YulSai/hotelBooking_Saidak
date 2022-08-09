package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.RoomDto;
import com.company.hotelBooking.service.dto.UserDto;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingCommand implements ICommand {
    private final IRoomService roomService;
    private final IReservationService reservationService;

    public BookingCommand(IRoomService roomService, IReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        UserDto user = (UserDto) session.getAttribute("user");
        Long roomId = Long.valueOf(req.getParameter("room_id"));
        RoomDto room = roomService.findById(roomId);
        LocalDate checkIn = LocalDate.parse(req.getParameter("check_in"));
        LocalDate checkOut = LocalDate.parse(req.getParameter("check_out"));
        Long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        String roomNumber = room.getNumber();
        ReservationDto reservation = reservationService.processBooking(user, room, roomId, checkIn, checkOut);
        reservationService.create(reservation);

        session.setAttribute("reservation", reservation);
        session.setAttribute("nights", nights);
        session.setAttribute("room_number", roomNumber);

        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_BOOKING);
    }
}