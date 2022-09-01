package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.PagesManager;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "logout"
 */
public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        // req.getSession().invalidate();
        return PagesManager.PAGE_INDEX;
    }
}