package com.company.hotelBooking.controller.command.impl.users;


import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class executes the command "create_user"
 */
public class CreateUserFormCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest req) {
		return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_CREATE_USER);
	}
}