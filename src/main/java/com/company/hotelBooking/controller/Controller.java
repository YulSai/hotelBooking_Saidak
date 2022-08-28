package com.company.hotelBooking.controller;

import com.company.hotelBooking.controller.command.api.CommandName;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.controller.command.factory.CommandFactory;
import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.exceptions.ExceptionsHandler;
import com.company.hotelBooking.exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

/**
 * Class for processing HttpServletRequest "/controller"
 */
@WebServlet({"/", "/controller"})
@Log4j2
public class Controller extends HttpServlet {
    public static final String REDIRECT = "redirect:";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        validateCommandName(commandName);
        ICommand command = CommandFactory.getINSTANCE().getCommand(commandName);
        String page;
        try {
            page = command.execute(req);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            page = ExceptionsHandler.getINSTANCE().handleException(req, resp, e);
        }
        if (page.startsWith(REDIRECT)) {
            resp.sendRedirect(req.getContextPath() + "/" + page.substring(REDIRECT.length()));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void validateCommandName(String commandName) {
        if (commandName == null || !CommandName.contains(commandName.toUpperCase())) {
            log.error("Incorrect address entered");
            throw new NotFoundException("Incorrect address entered");
        }
    }

    @Override
    public void destroy() {
        DataSource.getINSTANCE().close();
    }
}