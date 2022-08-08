package com.company.hotelBooking.controller.command.impl.invoices;

import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.service.api.IReservationService;
import jakarta.servlet.http.HttpServletRequest;

public class BookingCommand implements ICommand {
    private final IReservationService reservationService;

    public BookingCommand(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        //TODO Thinking about realisation
//        HttpSession session = req.getSession();
//        ReservationDto reservation = new ReservationDto();
//        reservation.setId(0L);
//        reservation.setUserDto((UserDto) session.getAttribute("user"));
//        reservation.setRoomId(0L);
//        reservation.setType(RoomDto.RoomTypeDto.valueOf(req.getParameter("type")));
//        reservation.setCapacity(RoomDto.CapacityDto.valueOf(req.getParameter("capacity")));
//        reservation.setCheckIn(LocalDate.parse(req.getParameter("check_in")));
//        reservation.setCheckOut(LocalDate.parse(req.getParameter("check_out")));
//        reservation.setStatus(ReservationDto.StatusDto.IN_PROGRESS);
//        reservation.setInvoice(BigDecimal.ZERO);
//        reservationService.create(reservation);
//
        return "controller?command=history"; //???
    }
}
