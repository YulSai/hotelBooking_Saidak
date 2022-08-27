package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.exceptions.NotFoundException;
import com.company.hotelBooking.util.ConfigurationManager;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;

/**
 * Class for processing HttpServletRequest "user"
 */
@Log4j2
public class UserCommand implements ICommand {
    private final IUserService service;

    public UserCommand(IUserService service) {
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
            UserDto user = service.findById(id);
            if (user.getId() == null) {
                log.error("Incorrect address entered");
                throw new NotFoundException("Page not found");
            } else {
                HttpSession session = req.getSession();
                UserDto userDto = (UserDto) session.getAttribute("user");
                if ("CLIENT".equals(userDto.getRole().toString())) {
                    user = service.findById(userDto.getId());
                }
                req.setAttribute("user", user);
                log.info("Appeal to user.jsp.");
                return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_USER);
            }
        } else {
            log.error("Incorrect address entered");
            throw new NotFoundException("Page not found");
        }
    }
}