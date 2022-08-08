package com.company.hotelBooking.controller.command.api;

import com.company.hotelBooking.exceptions.ServicesException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Interface
 */
public interface ICommand {
	
	/**
	 * Method processes the request and returns a response
	 * @param req passed request
	 * @return response
	 */
	String execute(HttpServletRequest req);
}