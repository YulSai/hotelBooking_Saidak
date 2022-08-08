package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

/**
 * Class for processing HttpServletRequest "login"
 */
@Log4j2
public class LoginFormCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) {
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_LOGIN);
    }
}
