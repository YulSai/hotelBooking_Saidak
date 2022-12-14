package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.ConfigurationManager;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.UUID;

/**
 * Class for processing HttpServletRequest "create_user"
 */
public class CreateUserCommand implements ICommand {
    private final IUserService service;

    public CreateUserCommand(IUserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        UserDto user = getUserFromInput(req);
        UserDto created = service.create(user);
        HttpSession session = req.getSession();
        session.setAttribute("user", created);
        req.setAttribute("message", MessageManger.getMessage("msg.user.created"));
        return "redirect:controller?command=user&id=" + created.getId();
    }

    /**
     * Method gets information about user
     *
     * @param req HttpServletRequest
     * @return UserDto
     */
    private UserDto getUserFromInput(HttpServletRequest req) {
        UserDto user = new UserDto();
        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setPhoneNumber(req.getParameter("phone_number"));
        user.setRole(UserDto.RoleDto.CLIENT);
        user.setAvatar(getAvatarPath(req));
        return user;
    }

    /**
     * Method writes file and gets path to this file
     *
     * @param req HttpServletRequest
     * @return path to file as String
     */
    private String getAvatarPath(HttpServletRequest req) {
        String avatarName;
        try {
            Part part = req.getPart("avatar");
            if (part.getSize() > 0) {
                avatarName = UUID.randomUUID() + "_" + part.getSubmittedFileName();
                String partFile = ConfigurationManager.getInstance().getString("part.avatar");
                part.write(partFile + avatarName);
            } else {
                avatarName = "defaultAvatar.png";
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        return avatarName;
    }
}