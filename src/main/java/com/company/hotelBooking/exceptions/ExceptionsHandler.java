package com.company.hotelBooking.exceptions;

import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.managers.PagesManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionsHandler {
    private static ExceptionsHandler INSTANCE;


    /**
     * Method gets an instance of the class object
     *
     * @return an instance of the class object
     */
    public static ExceptionsHandler getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ExceptionsHandler();
        }
        return INSTANCE;
    }

    public String handleException(HttpServletRequest req, HttpServletResponse res, Exception e) {
        String page;
        String message;
        int status;

        if (e instanceof DaoException) {
            status = 400;
            message = e.getMessage();
            page = PagesManager.PAGE_ERROR;
        } else if (e instanceof ServiceException) {
            status = 400;
            message = e.getMessage();
            page = PagesManager.PAGE_ERROR;
        } else if (e instanceof LoginUserException) {
            status = 400;
            message = MessageManger.getMessage("msg.incorrect.email.password");
            page = PagesManager.PAGE_LOGIN;
        } else if (e instanceof RegistrationException) {
            status = 400;
            message = e.getMessage();
            page = PagesManager.PAGE_CREATE_USER;
        } else if (e instanceof UnAuthorizedException) {
            status = 401;
            message = e.getMessage();
            page = PagesManager.PAGE_LOGIN;
        } else if (e instanceof ForbiddenException) {
            status = 403;
            message = e.getMessage();
            page = PagesManager.PAGE_INDEX;
        } else if (e instanceof NotFoundException) {
            status = 404;
            message = MessageManger.getMessage("msg.not.found");
            page = PagesManager.PAGE_404;
        } else if (e instanceof ConnectionPoolException) {
            status = 404;
            message = e.getMessage();
            page = PagesManager.PAGE_404;
        } else {
            status = 500;
            message = MessageManger.getMessage("msg.internal.error");
            page = PagesManager.PAGE_ERROR;
        }
        req.setAttribute("status", status);
        req.setAttribute("message", message);
        res.setStatus(status);
        return page;
    }
}