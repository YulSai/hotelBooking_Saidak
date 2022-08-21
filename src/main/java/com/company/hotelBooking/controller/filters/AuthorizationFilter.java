package com.company.hotelBooking.controller.filters;

import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/controller/*")
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String command = req.getParameter("command");
        if (requiresAuthorization(command)) {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                req.setAttribute("message", "Please, login");
                req.getRequestDispatcher(
                                ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_LOGIN))
                        .forward(req, res);
                return;
            }
        }
        chain.doFilter(req, res);
    }

    private boolean requiresAuthorization(String command) {
        return switch (command) {
            case "search_available_rooms_form", "search_available_rooms", "rooms_available", "booking", "add_booking",
                    "login_form", "login", "logout", "create_user_form", "create_user" -> false;
            default -> true;
        };
    }
}