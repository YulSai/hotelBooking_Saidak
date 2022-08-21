package com.company.hotelBooking.controller.command.impl.rooms;

import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.controller.command.util.PagingUtil;
import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.dto.RoomDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Class for processing HttpServletRequest "rooms"
 */
@Log4j2
public class RoomsCommand implements ICommand {
    private final IRoomService roomService;
    private final PagingUtil pagingUtil;

    public RoomsCommand(IRoomService service, PagingUtil pagingUtil) {
        this.roomService = service;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Paging paging = pagingUtil.getPaging(req);
        List<RoomDto> rooms = roomService.findAllPages(paging);
        if (rooms.size() == 0) {
            log.error("Incorrect address entered");
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
        } else {
            pagingUtil.setTotalPages(req, paging, roomService);
            req.setAttribute("rooms", rooms);
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ROOMS);
        }
    }
}
