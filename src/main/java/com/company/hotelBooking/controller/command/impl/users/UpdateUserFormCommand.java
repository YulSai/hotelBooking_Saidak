package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class executes the command "update_user"
 */
public class UpdateUserFormCommand implements ICommand {
	private final IUserService service;

	public UpdateUserFormCommand(IUserService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest req) {
		Long id = Long.parseLong(req.getParameter("id"));
		UserDto user = service.findById(id);
		req.setAttribute("user", user);
		return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_UPDATE_USERS);
	}
}