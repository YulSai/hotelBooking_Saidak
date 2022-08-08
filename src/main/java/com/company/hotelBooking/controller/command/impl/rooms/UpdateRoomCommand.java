package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
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
		RoomDto room = new RoomDto();
		room.setTypeDto(RoomDto.RoomTypeDto.valueOf(req.getParameter("type").toUpperCase()));
		room.setPrice(new BigDecimal(req.getParameter("price")));
		room.setStatusDto(RoomDto.RoomStatusDto.valueOf(req.getParameter("status")));
		room.setCapacityDto(RoomDto.CapacityDto.valueOf(req.getParameter("capacity")));
		room.setNumber(req.getParameter("room_number"));

		RoomDto updated = service.update(room);
		req.setAttribute("room", updated);
		req.setAttribute("massage", "Room was updated successfully");
		return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ROOM);
	}
}