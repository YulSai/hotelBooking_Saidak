package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ICommand {
    private final IUserService service;

    public DeleteUserCommand(IUserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        service.delete(id);
        return ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_USER);
    }
}
