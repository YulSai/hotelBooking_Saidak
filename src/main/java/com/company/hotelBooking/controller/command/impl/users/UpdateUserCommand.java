package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "update_user"
 */
public class UpdateUserCommand implements ICommand {
	private final IUserService service;

	public UpdateUserCommand(IUserService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest req) {
		UserDto user = getUserFromInput(req);
		UserDto updated = service.update(user);
		req.setAttribute("user", updated);
		req.setAttribute("massage", "User was updated successfully");
		return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_USER);
	}

	private static UserDto getUserFromInput(HttpServletRequest req) {
		UserDto user = new UserDto();
		user.setId(Long.parseLong(req.getParameter("id")));
		user.setFirstName(req.getParameter("first_name"));
		user.setLastName(req.getParameter("last_name"));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("password"));
		user.setPhoneNumber(req.getParameter("phone_number"));
		user.setRole(UserDto.RoleDto.valueOf(req.getParameter("role").toUpperCase()));
		return user;
	}
}