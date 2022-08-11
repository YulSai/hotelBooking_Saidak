package com.company.hotelBooking.controller.command.api;

/**
 * Enum with names of commands
 */
public enum CommandName {
    ROOMS, ROOM, USERS, USER, RESERVATIONS, RESERVATION, CREATE_USER_FORM, CREATE_USER, CREATE_ROOM_FORM, CREATE_ROOM,
    UPDATE_USER_FORM, UPDATE_USER, UPDATE_ROOM_FORM, UPDATE_ROOM, LOGIN, LOGIN_FORM, LOGOUT,
    SEARCH_AVAILABLE_ROOMS, SEARCH_AVAILABLE_ROOMS_FORM, ROOMS_AVAILABLE, ADD_BOOKING, BOOKING,
    CREATE_RESERVATION, ERROR;

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
}