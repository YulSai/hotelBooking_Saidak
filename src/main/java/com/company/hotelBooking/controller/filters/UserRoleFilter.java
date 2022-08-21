package com.company.hotelBooking.controller.filters;

import com.company.hotelBooking.controller.command.api.CommandName;
import com.company.hotelBooking.controller.command.api.SecurityLevel;
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
        SecurityLevel level = CommandName.valueOf(command.toUpperCase()).getLevel();
        HttpSession session = req.getSession(false);
        String role = "GUEST";
        if (session.getAttribute("user") != null) {
            UserDto user = (UserDto) session.getAttribute("user");
            role = user.getRole().toString();
        }
        if (requiredUserCommand(role, level)) {
            log.error("Insufficient access rights");
            req.setAttribute("message", "Insufficient access rights");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
        chain.doFilter(req, res);
    }

    private boolean requiredUserCommand(String role, SecurityLevel level) {
        return switch (role) {
            case "ADMIN" -> false;
            case "CLIENT" -> level.equals("CLIENT_LEVEL") || level.equals("GUEST_LEVEL");
            case "GUEST" -> level.equals("GUEST_LEVEL");
            default -> true;
        };
    }
}