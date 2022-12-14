package com.company.hotelBooking.controller.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Class with filter for localization
 */
@WebFilter(urlPatterns = {"/*", "/controller/*"})
public class LocaleFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res,
                            FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String language = (String) session.getAttribute("language");
        if (language != null) {
            session.setAttribute("language", language);
        } else {
            session.setAttribute("language", "en");
        }
        chain.doFilter(req, res);
    }
}