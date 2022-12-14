package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.managers.PagesManager;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

/**
 * Class for processing HttpServletRequest "search_available_rooms"
 */
public class RoomsSearchAvailableCommand implements ICommand {
    private final IRoomService roomService;

    public RoomsSearchAvailableCommand(IRoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        LocalDate checkIn = LocalDate.parse(req.getParameter("check_in"));
        LocalDate checkOut = LocalDate.parse(req.getParameter("check_out"));
        if (checkOut.equals(checkIn) | checkOut.isBefore(checkIn)) {
            req.setAttribute("message", MessageManger.getMessage("msg.incorrect.date"));
            return PagesManager.PAGE_SEARCH_AVAILABLE_ROOMS;
        } else {
            String type = req.getParameter("type");
            String capacity = req.getParameter("capacity");
            List<RoomDto> roomsAvailable = roomService.findAvailableRooms(checkIn, checkOut, type, capacity);
            if (roomsAvailable.isEmpty()) {
                req.setAttribute("message", MessageManger.getMessage("msg.search.no.available.rooms"));
            }
            session.setAttribute("rooms_available", roomsAvailable);
            session.setAttribute("check_in", checkIn);
            session.setAttribute("check_out", checkOut);
            return PagesManager.PAGE_ROOMS_AVAILABLE;
        }
    }
}