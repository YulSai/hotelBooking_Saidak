package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.controller.command.util.PagingUtil;
import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

import static com.company.hotelBooking.controller.command.util.PagingUtil.getPaging;

/**
 * Class for processing HttpServletRequest "rooms"
 */
@Log4j2
public class RoomsCommand implements ICommand {
    private final IRoomService roomService;

    public RoomsCommand(IRoomService service) {
        this.roomService = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Paging paging = getPaging(req);
        List<RoomDto> rooms = roomService.findAllPages(paging);
        if (rooms.size() == 0) {
            log.error("Incorrect address entered. Time = {}", new Date());
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
        } else {
            PagingUtil.setTotalPages(req, paging, roomService);
            req.setAttribute("rooms", rooms);
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ROOMS);
        }
    }
}
