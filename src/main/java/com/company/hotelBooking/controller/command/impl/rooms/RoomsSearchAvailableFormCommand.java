package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;

public class RoomsSearchAvailableFormCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_SEARCH_AVAILABLE_ROOMS);
    }
}
