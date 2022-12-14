package com.company.hotelBooking.controller.command.impl.errors;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.PagesManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

/**
 * Class for processing HttpServletRequest "error"
 */
@Log4j2
public class ErrorCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) {
        log.error("Incorrect address");
        return PagesManager.PAGE_ERROR;
    }
}