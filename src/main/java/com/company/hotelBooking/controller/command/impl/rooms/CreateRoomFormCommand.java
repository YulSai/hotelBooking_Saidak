package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class executes the command "create_room_form"
 */
public class CreateRoomFormCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) {
        return AppConstants.PAGE_CREATE_ROOM;
    }
}