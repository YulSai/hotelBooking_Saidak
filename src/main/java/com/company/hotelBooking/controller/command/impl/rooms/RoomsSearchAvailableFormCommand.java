package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "search_available_rooms_form"
 */
public class RoomsSearchAvailableFormCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        return AppConstants.PAGE_SEARCH_AVAILABLE_ROOMS;
    }
}