package com.company.hotelBooking.controller.command.impl.authorizations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.managers.PagesManager;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

/**
 * Class for processing HttpServletRequest "login"
 */
@Log4j2
public class LoginCommand implements ICommand {
    private final IUserService userService;

    public LoginCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || ("").equals(email) || password == null || ("").equals(password)) {
            req.setAttribute("massage", MessageManger.getMessage("msg.login.details"));
            return PagesManager.PAGE_LOGIN;
        }
        UserDto userDto = userService.login(email, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", userDto);
        log.info("Appeal to login.jsp.");
        return "redirect:";
    }
}