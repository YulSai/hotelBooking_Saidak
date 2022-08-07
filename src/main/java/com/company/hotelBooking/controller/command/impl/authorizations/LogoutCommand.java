package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws IOException {
        req.getSession().removeAttribute("user");
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_INDEX);
    }
}
