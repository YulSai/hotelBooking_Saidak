package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

/**
 * Class for processing HttpServletRequest "login_form"
 */
@Log4j2
public class LoginFormCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        return AppConstants.PAGE_LOGIN;
    }
}
