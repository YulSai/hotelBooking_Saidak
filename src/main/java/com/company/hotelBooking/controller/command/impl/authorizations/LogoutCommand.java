package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        // req.getSession().invalidate();
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_INDEX);
    }
}