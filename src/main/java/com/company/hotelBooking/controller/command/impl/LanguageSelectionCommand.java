package com.company.hotelBooking.controller.command.impl;

import com.company.hotelBooking.controller.command.api.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LanguageSelectionCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) {
        String language = req.getParameter("language");
        HttpSession session = req.getSession();
        session.setAttribute("language", language);

        String url = req.getHeader("referer");
        String REFERER = "http://localhost:8080/hotel_booking/";
        String path = url.substring(REFERER.length());
        return "redirect:";
    }
}