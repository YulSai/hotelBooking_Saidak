package com.company.hotelBooking.controller.filters;

import com.company.hotelBooking.controller.command.api.SecurityLevel;
import com.company.hotelBooking.controller.command.factory.CommandFactory;
import com.company.hotelBooking.exceptions.ForbiddenException;
import com.company.hotelBooking.service.dto.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

/**
 * Class with filter for user access levels
 */
@WebFilter(urlPatterns = "/controller/*")
@Log4j2
public class UserRoleFilter extends HttpFilter {

    /**
     * Method gets user's role
     *
     * @param session HttpSession
     * @return user's role
     */
    private static String getRole(HttpSession session) {
        String role = "GUEST";
        if (session.getAttribute("user") != null) {
            UserDto user = (UserDto) session.getAttribute("user");
            role = user.getRole().toString();
        }
        return role;
    }

    /**
     * Method checks the access level
     *
     * @param command given command
     * @param role    user's role
     */
    private static void verifyAccessLevel(String command, String role) {
        SecurityLevel levelUser = SecurityLevel.valueOf(role);
        SecurityLevel levelCommand = CommandFactory.getINSTANCE().getSecurityLevel(command);
        if (levelUser.ordinal() < levelCommand.ordinal()) {
            log.error("Insufficient access rights");
            throw new ForbiddenException("Insufficient access rights");
        }
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res,
                            FilterChain chain) throws ServletException, IOException {
        String command = req.getParameter("command");
        HttpSession session = req.getSession(false);
        String role = getRole(session);
        verifyAccessLevel(command, role);
        chain.doFilter(req, res);
    }
}