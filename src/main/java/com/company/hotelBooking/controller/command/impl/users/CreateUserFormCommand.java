package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class executes the command "create_user_form"
 */
public class CreateUserFormCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) {
        return AppConstants.PAGE_CREATE_USER;
    }
}