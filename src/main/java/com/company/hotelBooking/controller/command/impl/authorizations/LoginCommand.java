package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements ICommand {
    private final IUserService userService;

    public LoginCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || email.equals("") || password == null || password.equals("")) {
            req.setAttribute("massage", "Enter your login details");
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_LOGIN);
        }
        UserDto userDto = userService.login(email, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", userDto);
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_INDEX);
    }
}