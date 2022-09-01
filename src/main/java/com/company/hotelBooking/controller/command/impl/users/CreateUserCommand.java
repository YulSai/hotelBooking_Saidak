package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.managers.PagesManager;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "create_user"
 */
public class CreateUserCommand implements ICommand {
    private final IUserService service;

    public CreateUserCommand(IUserService service) {
        this.service = service;
    }

    private static UserDto getUserFromInput(HttpServletRequest req) {
        UserDto user = new UserDto();
        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setPhoneNumber(req.getParameter("phone_number"));
        user.setRole(UserDto.RoleDto.CLIENT);
        return user;
    }

    @Override
    public String execute(HttpServletRequest req) {
        UserDto user = getUserFromInput(req);
        UserDto created = service.create(user);
        req.setAttribute("user", created);
        req.setAttribute("message", MessageManger.getMessage("msg.user.created"));
        return PagesManager.PAGE_USER;
    }
}