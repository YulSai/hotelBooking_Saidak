package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "logout"
 */
public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        // req.getSession().invalidate();
        return AppConstants.PAGE_INDEX;
    }
}