package com.company.hotelBooking.controller;

import com.company.hotelBooking.controller.command.api.CommandName;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.controller.command.factory.CommandFactory;
import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.exceptions.ExceptionsHandler;
import com.company.hotelBooking.exceptions.NotFoundException;
import com.company.hotelBooking.managers.MessageManger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Locale;

/**
 * Class for processing HttpServletRequest "/controller"
 */
@WebServlet("/controller")
@MultipartConfig(maxFileSize = Controller.MB * 10, maxRequestSize = Controller.MB * 100)
@Log4j2
public class Controller extends HttpServlet {
    public static final int MB = 1024 * 1024;
    public static final String REDIRECT = "redirect:";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageManger messageManger = getLocale(req);
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
            setMessageSession(req);
            resp.sendRedirect(req.getContextPath() + "/" + page.substring(REDIRECT.length()));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    /**
     * Method checks for a message in the request and, if it's not null, sets it into the session
     *
     * @param req HttpServletRequest
     */
    private void setMessageSession(HttpServletRequest req) {
        String message = (String) req.getAttribute("message");
        if (message != null) {
            HttpSession session = req.getSession();
            session.setAttribute("message", message);
        }
    }

    /**
     * Method gets localization information passes it to MessageManger
     *
     * @param req HttpServletRequest
     * @return object MessageManger
     */
    private MessageManger getLocale(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String language = (String) session.getAttribute("language");
        return new MessageManger(new Locale(language));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * Method validates command names
     *
     * @param commandName command name
     */
    public void validateCommandName(String commandName) {
        if (commandName == null || !CommandName.contains(commandName.toUpperCase())) {
            log.error("Incorrect address entered");
            throw new NotFoundException();
        }
    }

    @Override
    public void destroy() {
        DataSource.getINSTANCE().close();
    }
}