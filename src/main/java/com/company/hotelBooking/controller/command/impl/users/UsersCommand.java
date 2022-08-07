package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

/**
 * Class for processing HttpServletRequest "users"
 */
@Log4j2
public class UsersCommand implements ICommand {
	private final IUserService service;

	public UsersCommand(IUserService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest req) {
		List<UserDto> users = service.findAll();
		if (users.size() == 0) {
			log.error("Incorrect address entered. Time = {}", new Date());
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
		} else {
			req.setAttribute("users", users);
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_USERS);
		}
	}
}