package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Class for processing HttpServletRequest "user"
 */
@Log4j2
public class UserCommand implements ICommand {
	private final IUserService service;

	public UserCommand(IUserService service) {
		this.service = service;
	}

	@Override
	public String execute(HttpServletRequest req) {
		String regexId = "\\d*";
		String argument = req.getParameter("id");
		if (argument == null) {
			log.error("Incorrect address entered. Time = {}", new Date());
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
		} else if (argument.equals("")) {
			log.error("Incorrect address entered. Time = {}", new Date());
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
		} else if (Pattern.matches(regexId, argument)) {
			Long id = Long.parseLong(req.getParameter("id"));
			UserDto user = service.findById(id);
			if (user.getId() == null) {
				log.error("Incorrect address entered. Time = {}", new Date());
				return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
			} else {
				req.setAttribute("user", user);
				log.info("Appeal to user.jsp. Time = {}", new Date());
				return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_USER);
			}
		} else {
			log.error("Incorrect address entered. Time = {}", new Date());
			return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
		}
	}
}