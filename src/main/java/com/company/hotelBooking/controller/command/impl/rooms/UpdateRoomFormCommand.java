package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class executes the command "update_room"
 */
public class UpdateRoomFormCommand implements ICommand {

	private final IRoomService service;

	public UpdateRoomFormCommand(IRoomService service) {
		this.service = service;
	}
	
	@Override
	public String execute(HttpServletRequest req) {
		Long id = Long.parseLong(req.getParameter("id"));
		RoomDto room = service.findById(id);
		req.setAttribute("room", room);
		return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_UPDATE_ROOM);
	}
}