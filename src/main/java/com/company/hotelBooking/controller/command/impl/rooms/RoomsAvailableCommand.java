package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;


public class RoomsAvailableCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ROOMS_AVAILABLE);
    }
}