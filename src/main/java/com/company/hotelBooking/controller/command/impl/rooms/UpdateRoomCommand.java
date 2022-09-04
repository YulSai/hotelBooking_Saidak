package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.managers.PagesManager;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

/**
 * Class for processing HttpServletRequest "update_room"
 */
public class UpdateRoomCommand implements ICommand {
    private final IRoomService service;

    public UpdateRoomCommand(IRoomService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        RoomDto room = getRoomFromInput(req);
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        if (price.compareTo(BigDecimal.valueOf(0)) < 0 || price.compareTo(BigDecimal.valueOf(10000)) >= 0) {
            req.setAttribute("message", MessageManger.getMessage("msg.create.new.room.price"));
            return PagesManager.PAGE_CREATE_ROOM;
        }
        RoomDto updated = service.update(room);
        req.setAttribute("message", MessageManger.getMessage("msg.room.updated"));
        return "redirect:controller?command=room&id=" + updated.getId();
    }

    private static RoomDto getRoomFromInput(HttpServletRequest req) {
        RoomDto room = new RoomDto();
        room.setId(Long.valueOf(req.getParameter("id")));
        room.setType(RoomDto.RoomTypeDto.valueOf(req.getParameter("type").toUpperCase()));
        room.setPrice(new BigDecimal(req.getParameter("price")));
        room.setStatus(RoomDto.RoomStatusDto.valueOf(req.getParameter("status")));
        room.setCapacity(RoomDto.CapacityDto.valueOf(req.getParameter("capacity")));
        room.setNumber(req.getParameter("room_number"));
        return room;
    }
}