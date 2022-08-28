package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.exceptions.NotFoundException;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;

/**
 * Class for processing HttpServletRequest "room"
 */
@Log4j2
public class RoomCommand implements ICommand {
    private final IRoomService service;

    public RoomCommand(IRoomService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String regexId = "\\d*";
        String argument = req.getParameter("id");
        if (argument == null) {
            log.error("Incorrect address entered");
            throw new NotFoundException("Page not found");
        } else if (argument.equals("")) {
            log.error("Incorrect address entered");
            throw new NotFoundException("Page not found");
        } else if (Pattern.matches(regexId, argument)) {
            Long id = Long.parseLong(req.getParameter("id"));
            RoomDto room = service.findById(id);
            if (room.getId() == null) {
                log.error("Incorrect address entered");
                throw new NotFoundException("Page not found");
            } else {
                req.setAttribute("room", room);
                log.info("Appeal to room.jsp");
                return AppConstants.PAGE_ROOM;
            }
        } else {
            log.error("Incorrect address entered");
            throw new NotFoundException("Page not found");
        }
    }
}