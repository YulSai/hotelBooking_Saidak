package com.company.hotelBooking.controller.command.factory;

import com.company.hotelBooking.controller.command.api.CommandName;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.controller.command.impl.ErrorCommand;
import com.company.hotelBooking.controller.command.impl.authorizations.LoginCommand;
import com.company.hotelBooking.controller.command.impl.authorizations.LoginFormCommand;
import com.company.hotelBooking.controller.command.impl.authorizations.LogoutCommand;
import com.company.hotelBooking.controller.command.impl.reservations.*;
import com.company.hotelBooking.controller.command.impl.rooms.*;
import com.company.hotelBooking.controller.command.impl.users.*;
import com.company.hotelBooking.controller.command.util.PagingUtil;
import com.company.hotelBooking.service.api.IReservationInfoService;
import com.company.hotelBooking.service.api.IReservationService;
import com.company.hotelBooking.service.api.IRoomService;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.factory.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Class with methods for processing commands
 */
public class CommandFactory {
    private static CommandFactory INSTANCE;
    private final Map<CommandName, ICommand> commands;

    /**
     * Method creates a list relationships between commands and class objects that
     * process them
     */
    private CommandFactory() {
        commands = new HashMap<>();
        //Rooms
        commands.put(CommandName.ROOMS,
                new RoomsCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class), PagingUtil.INSTANCE));
        commands.put(CommandName.ROOM, new RoomCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class)));
        commands.put(CommandName.CREATE_ROOM_FORM, new CreateRoomFormCommand());
        commands.put(CommandName.CREATE_ROOM,
                new CreateRoomCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class)));
        commands.put(CommandName.UPDATE_ROOM_FORM, new UpdateRoomFormCommand(ServiceFactory.getINSTANCE()
                .getService(IRoomService.class)));
        commands.put(CommandName.UPDATE_ROOM, new UpdateRoomCommand(ServiceFactory.getINSTANCE()
                .getService(IRoomService.class)));
        //Users
        commands.put(CommandName.USERS, new UsersCommand(ServiceFactory.getINSTANCE()
                .getService(IUserService.class), PagingUtil.INSTANCE));
        commands.put(CommandName.USER, new UserCommand(ServiceFactory.getINSTANCE()
                .getService(IUserService.class)));
        commands.put(CommandName.CREATE_USER_FORM, new CreateUserFormCommand());
        commands.put(CommandName.CREATE_USER, new CreateUserCommand(ServiceFactory.getINSTANCE()
                .getService(IUserService.class)));
        commands.put(CommandName.UPDATE_USER_FORM, new UpdateUserFormCommand(ServiceFactory.getINSTANCE()
                .getService(IUserService.class)));
        commands.put(CommandName.UPDATE_USER, new UpdateUserCommand(ServiceFactory.getINSTANCE()
                .getService(IUserService.class)));

        //Authorizations
        commands.put(CommandName.LOGIN_FORM, new LoginFormCommand());
        commands.put(CommandName.LOGIN, new LoginCommand(ServiceFactory.getINSTANCE()
                .getService(IUserService.class)));
        commands.put(CommandName.LOGOUT, new LogoutCommand());

        //Reservations
        commands.put(CommandName.RESERVATIONS, new ReservationsCommand(ServiceFactory.getINSTANCE()
                .getService(IReservationService.class), PagingUtil.INSTANCE));
        commands.put(CommandName.RESERVATION, new ReservationCommand(ServiceFactory.getINSTANCE()
                .getService(IReservationService.class)));

        commands.put(CommandName.SEARCH_AVAILABLE_ROOMS_FORM, new RoomsSearchAvailableFormCommand());
        commands.put(CommandName.SEARCH_AVAILABLE_ROOMS, new RoomsSearchAvailableCommand((ServiceFactory.getINSTANCE()
                .getService(IRoomService.class))));
        commands.put(CommandName.ROOMS_AVAILABLE,
                new RoomsAvailableCommand(ServiceFactory.getINSTANCE().getService(IRoomService.class)));
        commands.put(CommandName.ADD_BOOKING, new AddBookingCommand());
        commands.put(CommandName.BOOKING,
                new BookingCommand(ServiceFactory.getINSTANCE().getService(IReservationService.class)));
        commands.put(CommandName.CREATE_RESERVATION,
                new CreateReservationCommand(ServiceFactory.getINSTANCE().getService(IReservationService.class),
                        ServiceFactory.getINSTANCE().getService(IReservationInfoService.class)));

//        //Other
        commands.put(CommandName.ERROR, new ErrorCommand());
    }

    /**
     * Method gets an instance of the class object
     *
     * @return an instance of the class object
     */
    public static CommandFactory getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CommandFactory();
        }
        return INSTANCE;
    }

    /**
     * Method gets command
     *
     * @param commandName passed command
     * @return command
     */
    public ICommand getCommand(String commandName) {
        ICommand command = commands.get(CommandName.valueOf(commandName.toUpperCase()));
        if (command == null) {
            command = commands.get(CommandName.ERROR);
        }
        return command;
    }
}