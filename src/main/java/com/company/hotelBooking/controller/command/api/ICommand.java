package com.company.hotelBooking.controller.command.api;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface
 */
public interface ICommand {

    /**
     * Method processes the request and returns a response
     *
     * @param req passed request
     * @return response
     */
    String execute(HttpServletRequest req);
}