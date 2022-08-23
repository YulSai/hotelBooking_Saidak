package com.company.hotelBooking.controller.filters;

import com.company.hotelBooking.controller.command.api.SecurityLevel;
import com.company.hotelBooking.controller.command.factory.CommandFactory;
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

@WebFilter(urlPatterns = "/controller/*")
@Log4j2
public class UserRoleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String command = req.getParameter("command");
        HttpSession session = req.getSession(false);
        String role = getRole(session);
        verifyAccessLevel(req, res, command, role);
        chain.doFilter(req, res);
    }

    private static String getRole(HttpSession session) {
        String role = "GUEST";
        if (session.getAttribute("user") != null) {
            UserDto user = (UserDto) session.getAttribute("user");
            role = user.getRole().toString();
        }
        return role;
    }

    private static void verifyAccessLevel(HttpServletRequest req, HttpServletResponse res, String command,
                                          String role) throws ServletException, IOException {
        SecurityLevel levelUser = SecurityLevel.valueOf(role);
        SecurityLevel levelCommand = CommandFactory.getINSTANCE().getSecurityLevel(command);
        if (levelUser.ordinal() < levelCommand.ordinal()) {
            log.error("Insufficient access rights");
            req.setAttribute("message", "Insufficient access rights");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}