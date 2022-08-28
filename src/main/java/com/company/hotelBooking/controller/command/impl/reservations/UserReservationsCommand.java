package com.company.hotelBooking.controller.command.impl.reservations;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.controller.command.util.PagingUtil;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.dto.ReservationDto;
import com.company.hotelBooking.service.dto.UserDto;
import com.company.hotelBooking.util.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Class for processing HttpServletRequest "reservations"
 */
@Log4j2
public class UserReservationsCommand implements ICommand {
    private final IReservationService reservationService;
    private final PagingUtil pagingUtil;

    public UserReservationsCommand(IReservationService service, PagingUtil pagingUtil) {
        this.reservationService = service;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Paging paging = pagingUtil.getPaging(req);
        Long id = Long.valueOf(req.getParameter("id"));
        List<ReservationDto> reservations = reservationService.findAllPagesByUsers(paging, id);
        if (reservations.isEmpty()) {
            req.setAttribute("message", "No reservations yet");
            return AppConstants.PAGE_RESERVATIONS;
        } else {
            HttpSession session = req.getSession();
            UserDto user = (UserDto) session.getAttribute("user");
            if ("CLIENT".equals(user.getRole().toString())) {
                reservations = reservationService.findAllPagesByUsers(paging, user.getId());
            }
            pagingUtil.setTotalPages(req, paging, reservationService);
            req.setAttribute("reservations", reservations);
            return AppConstants.PAGE_RESERVATIONS;
        }
    }
}