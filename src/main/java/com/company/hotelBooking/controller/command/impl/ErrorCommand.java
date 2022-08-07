package com.company.hotelBooking.controller.command.impl;

import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

/**
 * Class for processing HttpServletRequest "error"
 */
@Log4j2
public class ErrorCommand implements ICommand {
	@Override
	public String  execute(HttpServletRequest req) {
		log.error("Incorrect address entered. Time = {}", new Date());
		return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
	}
}