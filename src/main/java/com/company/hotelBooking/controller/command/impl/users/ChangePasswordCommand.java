package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for processing HttpServletRequest "change_password"
 */
public class ChangePasswordCommand implements ICommand {
    private final IUserService service;

    public ChangePasswordCommand(IUserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        UserDto user = getUserFromInput(req);
        UserDto updated = service.changePassword(user);
        return "redirect:controller?command=user&id=" + updated.getId();
    }

    private UserDto getUserFromInput(HttpServletRequest req) {
        UserDto user = new UserDto();
        user.setId(Long.parseLong(req.getParameter("id")));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setPhoneNumber(req.getParameter("phone_number"));
        user.setRole(UserDto.RoleDto.valueOf(req.getParameter("role").toUpperCase()));
        user.setAvatar(req.getParameter("avatar"));
        return user;
    }
}