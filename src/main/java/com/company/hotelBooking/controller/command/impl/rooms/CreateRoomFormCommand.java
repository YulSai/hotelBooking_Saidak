package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class executes the command "create_room"
 */
public class CreateRoomFormCommand implements ICommand {
	
	@Override
	public String execute(HttpServletRequest req) {
		return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_CREATE_ROOM);
	}
}