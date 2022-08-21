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

import java.io.IOException;

@WebFilter(urlPatterns = "/controller/*")
public class UserRoleFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String command = req.getParameter("command");
        HttpSession session = req.getSession(false);

        if (session.getAttribute("user") != null) {
            UserDto user = (UserDto) session.getAttribute("user");
            SecurityLevel level = CommandName.valueOf(command.toUpperCase()).getLevel();

            if (!"ADMIN".equals(user.getRole().toString()) && level.equals(SecurityLevel.ADMIN_LEVEL)) {
                req.setAttribute("message", "Insufficient access rights");
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
            if (!"CLIENT".equals(user.getRole().toString()) && level.equals(SecurityLevel.CLIENT_LEVEL)) {
                req.setAttribute("message", "Insufficient access rights");
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        }
        chain.doFilter(req, res);
    }
}