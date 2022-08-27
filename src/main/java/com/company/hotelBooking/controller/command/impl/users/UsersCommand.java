package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.controller.command.util.PagingUtil;
import com.company.hotelBooking.exceptions.NotFoundException;
import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Class for processing HttpServletRequest "users"
 */
@Log4j2
public class UsersCommand implements ICommand {
    private final IUserService userService;
    private final PagingUtil pagingUtil;

    public UsersCommand(IUserService service, PagingUtil pagingUtil) {
        this.userService = service;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Paging paging = pagingUtil.getPaging(req);
        List<UserDto> users = userService.findAllPages(paging);
        if (users.size() == 0) {
            log.error("Incorrect address entered");
            throw new NotFoundException("Page not found");
        } else {
            pagingUtil.setTotalPages(req, paging, userService);
            req.setAttribute("users", users);
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_USERS);
        }
    }
}