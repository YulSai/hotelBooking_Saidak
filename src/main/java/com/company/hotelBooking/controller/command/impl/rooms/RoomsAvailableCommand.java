package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public class RoomsAvailableCommand implements ICommand {
    private final IRoomService roomService;

    public RoomsAvailableCommand(IRoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String type = req.getParameter("type");
        String capacity = req.getParameter("capacity");
        LocalDate checkIn = LocalDate.parse(req.getParameter("check_in"));
        LocalDate checkOut = LocalDate.parse(req.getParameter("check_out"));
        List<RoomDto> roomsAvailable = roomService.findAvailableRooms(checkIn, checkOut, type, capacity);
        req.setAttribute("rooms_available", roomsAvailable);
        req.setAttribute("check_in", checkIn);
        req.setAttribute("check_out", checkOut);
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ROOMS_AVAILABLE);
    }
}
