package com.company.hotelBooking.controller.command.api;

import com.company.hotelBooking.controller.command.impl.ErrorCommand;
import com.company.hotelBooking.controller.command.impl.authorizations.LoginCommand;
import com.company.hotelBooking.controller.command.impl.authorizations.LoginFormCommand;
import com.company.hotelBooking.controller.command.impl.authorizations.LogoutCommand;

import com.company.hotelBooking.controller.command.impl.reservations.AddBookingCommand;
import com.company.hotelBooking.controller.command.impl.reservations.BookingCommand;
import com.company.hotelBooking.controller.command.impl.reservations.CreateReservationCommand;
import com.company.hotelBooking.controller.command.impl.reservations.ReservationCommand;
import com.company.hotelBooking.controller.command.impl.reservations.ReservationsCommand;
import com.company.hotelBooking.controller.command.impl.rooms.CreateRoomCommand;
import com.company.hotelBooking.controller.command.impl.rooms.CreateRoomFormCommand;
import com.company.hotelBooking.controller.command.impl.rooms.RoomCommand;
import com.company.hotelBooking.controller.command.impl.rooms.RoomsAvailableCommand;
import com.company.hotelBooking.controller.command.impl.rooms.RoomsCommand;
import com.company.hotelBooking.controller.command.impl.rooms.RoomsSearchAvailableCommand;
import com.company.hotelBooking.controller.command.impl.rooms.RoomsSearchAvailableFormCommand;
import com.company.hotelBooking.controller.command.impl.rooms.UpdateRoomCommand;
import com.company.hotelBooking.controller.command.impl.rooms.UpdateRoomFormCommand;
import com.company.hotelBooking.controller.command.impl.users.CreateUserCommand;
import com.company.hotelBooking.controller.command.impl.users.CreateUserFormCommand;
import com.company.hotelBooking.controller.command.impl.users.UpdateUserCommand;
import com.company.hotelBooking.controller.command.impl.users.UpdateUserFormCommand;
import com.company.hotelBooking.controller.command.impl.users.UserCommand;
import com.company.hotelBooking.controller.command.impl.users.UsersCommand;
import com.company.hotelBooking.controller.command.util.PagingUtil;
import com.company.hotelBooking.service.api.IReservationInfoService;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.factory.ServiceFactory;

/**
 * Enum with names of commands
 */
public enum CommandName {

    //Authorizations
    LOGIN_FORM(new LoginFormCommand(), SecurityLevel.GUEST_LEVEL),
    LOGIN(new LoginCommand(ServiceFactory.getINSTANCE().getService(IUserService.class)), SecurityLevel.GUEST_LEVEL),
    LOGOUT(new LogoutCommand(), SecurityLevel.CLIENT_LEVEL),

    // Users
    USER(new UserCommand(ServiceFactory.getINSTANCE().getService(IUserService.class)), SecurityLevel.CLIENT_LEVEL),
    USERS(new UsersCommand(ServiceFactory.getINSTANCE().getService(IUserService.class), PagingUtil.INSTANCE),
            SecurityLevel.ADMIN_LEVEL),
    CREATE_USER_FORM(new CreateUserFormCommand(), SecurityLevel.GUEST_LEVEL),
    CREATE_USER(new CreateUserCommand(ServiceFactory.getINSTANCE().getService(IUserService.class)),
            SecurityLevel.GUEST_LEVEL),
    UPDATE_USER_FORM(new UpdateUserFormCommand(ServiceFactory.getINSTANCE().getService(IUserService.class)),
            SecurityLevel.CLIENT_LEVEL),
    UPDATE_USER(new UpdateUserCommand(ServiceFactory.getINSTANCE().getService(IUserService.class)),
            SecurityLevel.CLIENT_LEVEL),

    //Rooms
    ROOM(new RoomCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class)), SecurityLevel.ADMIN_LEVEL),
    ROOMS(new RoomsCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class), PagingUtil.INSTANCE),
            SecurityLevel.ADMIN_LEVEL),
    CREATE_ROOM_FORM(new CreateRoomFormCommand(), SecurityLevel.ADMIN_LEVEL),
    CREATE_ROOM(new CreateRoomCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class)),
            SecurityLevel.ADMIN_LEVEL),
    UPDATE_ROOM_FORM(new UpdateRoomFormCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class)),
            SecurityLevel.ADMIN_LEVEL),
    UPDATE_ROOM(new UpdateRoomCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class)),
            SecurityLevel.ADMIN_LEVEL),

    //Reservations
    RESERVATION(new ReservationCommand(ServiceFactory.getINSTANCE().getService(IReservationService.class)),
            SecurityLevel.CLIENT_LEVEL),
    RESERVATIONS(new ReservationsCommand(ServiceFactory.getINSTANCE().getService(IReservationService.class),
            PagingUtil.INSTANCE), SecurityLevel.ADMIN_LEVEL),
    SEARCH_AVAILABLE_ROOMS_FORM(new RoomsSearchAvailableFormCommand(), SecurityLevel.GUEST_LEVEL),
    SEARCH_AVAILABLE_ROOMS(
            new RoomsSearchAvailableCommand((ServiceFactory.getINSTANCE().getService(IRoomService.class))),
            SecurityLevel.GUEST_LEVEL),
    ROOMS_AVAILABLE(new RoomsAvailableCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class)),
            SecurityLevel.GUEST_LEVEL),
    ADD_BOOKING(new AddBookingCommand(), SecurityLevel.GUEST_LEVEL),
    BOOKING(new BookingCommand(ServiceFactory.getINSTANCE().getService(IReservationService.class)),
            SecurityLevel.GUEST_LEVEL),
    CREATE_RESERVATION(new CreateReservationCommand(ServiceFactory.getINSTANCE().getService(IReservationService.class),
            ServiceFactory.getINSTANCE().getService(IReservationInfoService.class)), SecurityLevel.CLIENT_LEVEL),

    //Other
    ERROR(new ErrorCommand(), SecurityLevel.GUEST_LEVEL);

    private final ICommand command;
    private final SecurityLevel level;

    CommandName(ICommand command, SecurityLevel level) {
        this.command = command;
        this.level = level;
    }

    /**
     * Method to check if the command exists
     *
     * @param command passed command
     * @return true/false
     */
    public static boolean contains(String command) {
        try {
            CommandName.valueOf(command);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ICommand getCommand() {
        return command;
    }

    public SecurityLevel getLevel() {
        return level;
    }
}