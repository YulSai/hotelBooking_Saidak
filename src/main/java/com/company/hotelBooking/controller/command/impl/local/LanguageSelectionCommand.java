package com.company.hotelBooking.controller.command.impl.local;

import com.company.hotelBooking.controller.command.api.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Class for processing HttpServletRequest "language_select"
 */
public class LanguageSelectionCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) {
        String language = req.getParameter("language");
        HttpSession session = req.getSession();
        session.setAttribute("language", language);

        String url = req.getHeader("referer");
        String REFERER = "http://localhost:8080/hotel_booking/";
        return "redirect:" + url.substring(REFERER.length());
    }
}