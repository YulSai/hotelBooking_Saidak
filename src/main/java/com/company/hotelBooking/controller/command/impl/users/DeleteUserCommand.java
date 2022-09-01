package com.company.hotelBooking.controller.command.impl.users;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.managers.PagesManager;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.ReservationDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Class for processing HttpServletRequest "delete_user"
 */
public class DeleteUserCommand implements ICommand {
    private final IUserService service;
    private final IReservationService reservationService;

    public DeleteUserCommand(IUserService service, IReservationService reservationService) {
        this.service = service;
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        List<ReservationDto> reservations = reservationService.findAllByUsers(id);
        for (ReservationDto reservation : reservations) {
            reservation.setStatus(ReservationDto.StatusDto.DELETED);
            reservationService.update(reservation);
        }
        service.delete(id);
        req.setAttribute("message", MessageManger.getMessage("msg.user.deleted"));
        return PagesManager.PAGE_DELETE_USER;
    }
}