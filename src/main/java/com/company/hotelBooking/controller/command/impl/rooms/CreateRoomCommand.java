package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.managers.PagesManager;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

/**
 * Class for processing HttpServletRequest "create_room"
 */
public class CreateRoomCommand implements ICommand {
    private final IRoomService service;

    public CreateRoomCommand(IRoomService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        RoomDto room = new RoomDto();
        room.setType(RoomDto.RoomTypeDto.valueOf(req.getParameter("type").toUpperCase()));
        room.setPrice(new BigDecimal(req.getParameter("price")));
        room.setStatus(RoomDto.RoomStatusDto.valueOf(req.getParameter("status")));
        room.setCapacity(RoomDto.CapacityDto.valueOf(req.getParameter("capacity")));
        room.setNumber(req.getParameter("room_number"));

        RoomDto created = service.create(room);
        req.setAttribute("room", created);
        req.setAttribute("massage", MessageManger.getMessage("msg.room.created"));
        return PagesManager.PAGE_ROOM;
    }
}