package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "rooms_available"
 */
public class RoomsAvailableCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        return AppConstants.PAGE_ROOMS_AVAILABLE;
    }
}