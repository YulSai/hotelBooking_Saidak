package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginCommand implements ICommand {
    private final IUserService userService;

    public LoginCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null){
            return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_LOGIN);
        }
        UserDto userDto = userService.login(email, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", userDto);
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_INDEX);
    }
}