package com.company.hotelBooking.exceptions;

import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Exceptions {
    private static Exceptions INSTANCE;

    /**
     * Method gets an instance of the class object
     *
     * @return an instance of the class object
     */
    public static Exceptions getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Exceptions();
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
            page = ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
        } else if (e instanceof ServiceException) {
            status = 400;
            message = e.getMessage();
            page = ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
        } else if (e instanceof LoginUserException) {
            status = 400;
            message = "The email or password provided is incorrect";
            page = ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_LOGIN);
        } else if (e instanceof RegistrationException) {
            status = 400;
            message = e.getMessage();
            page = ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_CREATE_USER);
        } else if (e instanceof UnAuthorizedException) {
            status = 401;
            message = e.getMessage();
            page = ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_LOGIN);
        } else if (e instanceof ForbiddenException) {
            status = 403;
            message = e.getMessage();
            page = ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_INDEX);
        } else if (e instanceof NotFoundException) {
            status = 404;
            message = "Incorrect address entered";
            page = ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
        } else {
            status = 500;
            message = "Internal error";
            page = ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR);
        }
        req.setAttribute("status", status);
        req.setAttribute("message", message);
        return page;
    }
}