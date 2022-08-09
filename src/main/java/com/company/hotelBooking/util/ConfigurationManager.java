package com.company.hotelBooking.util;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final String RESOURCE_NAME = "application";

    // DataBase Connection Properties
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_DRIVER = "db.driver";

    // SQL
    // Reservation
    public static final String SQL_RESERVATION_FIND_BY_ID = "sql.reservation.find.by.id";
    public static final String SQL_RESERVATION_FIND_ALL = "sql.reservation.find.all";
    public static final String SQL_RESERVATION_CREATE = "sql.reservation.create";
    public static final String SQL_RESERVATION_UPDATE = "sql.reservation.update";
    public static final String SQL_RESERVATION_DELETE = "sql.reservation.delete";
    public static final String SQL_RESERVATION_COUNT_RESERVATIONS = "sql.reservation.count.reservations";
    public static final String SQL_RESERVATION_PAGE = "sql.reservation.page";


    // ReservationInfo
    public static final String SQL_RESERVATION_INFO_FIND_BY_ID = "sql.reservation.info.find.by.id";
    public static final String SQL_RESERVATION_INFO_FIND_ALL = "sql.reservation.info.find.all";
    public static final String SQL_RESERVATION_INFO_CREATE = "sql.reservation.info.create";
    public static final String SQL_RESERVATION_INFO_UPDATE = "sql.reservation.info.update";
    public static final String SQL_RESERVATION_INFO_DELETE = "sql.reservation.info.delete";
    public static final String SQL_RESERVATION_INFO_PAGE = "sql.reservation.info.page";
    public static final String SQL_RESERVATION_INFO_COUNT_RESERVATIONS = "sql.reservation.info.count";

    // Room
    public static final String SQL_ROOM_FIND_BY_ID = "sql.room.find.by.id";
    public static final String SQL_ROOM_FIND_ALL = "sql.room.find.all";
    public static final String SQL_ROOM_CREATE = "sql.room.create";
    public static final String SQL_ROOM_UPDATE = "sql.room.update";
    public static final String SQL_ROOM_DELETE = "sql.room.delete";
    public static final String SQL_ROOM_FIND_BY_NUMBER = "sql.room.find.by.number";
    public static final String SQL_ROOM_COUNT_ROOMS = "sql.room.count.rooms";
    public static final String SQL_ROOM_FIND_AVAILABLE_ROOMS = "sql.room.find.all.available.rooms";
    public static final String SQL_ROOM_PAGE = "sql.room.page";

    // User
    public static final String SQL_USER_FIND_BY_ID = "sql.user.find.by.id";
    public static final String SQL_USER_FIND_ALL = "sql.user.find.all";
    public static final String SQL_USER_CREATE = "sql.user.create";
    public static final String SQL_USER_UPDATE = "sql.user.update";
    public static final String SQL_USER_DELETE = "sql.user.delete";
    public static final String SQL_USER_FIND_BY_EMAIL = "sql.user.find.by.email";
    public static final String SQL_USER_COUNT_USERS = "sql.user.count.users";
    public static final String SQL_USER_PAGE = "sql.user.page";

    // JSP
    // Index
    public static final String PAGE_INDEX = "page.index";
    public static final String PAGE_MAIN = "page.main";

    // Error
    public static final String PAGE_ERROR = "page.error";

    // Reservation
    public static final String PAGE_RESERVATIONS = "page.reservations";
    public static final String PAGE_RESERVATION = "page.reservation";
    public static final String PAGE_CREATE_RESERVATION = "page.create.reservation";
    public static final String PAGE_UPDATE_RESERVATION = "page.update.reservation";
    public static final String PAGE_BOOKING = "page.booking";
    public static final String PAGE_INVOICES = "page.invoices";

    // Room
    public static final String PAGE_ROOMS = "page.rooms";
    public static final String PAGE_ROOM = "page.room";
    public static final String PAGE_CREATE_ROOM = "page.create.room";
    public static final String PAGE_UPDATE_ROOM = "page.update.room";
    public static final String PAGE_ROOMS_AVAILABLE = "page.rooms.available";

    public static final String PAGE_SEARCH_AVAILABLE_ROOMS = "page.search.available";

    // User
    public static final String PAGE_USERS = "page.users";
    public static final String PAGE_USER = "page.user";
    public static final String PAGE_CREATE_USER = "page.create.user";
    public static final String PAGE_UPDATE_USERS = "page.update.user";
    public static final String PAGE_LOGIN = "page.login";

    private ConfigurationManager() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    private final ResourceBundle resourceBundle;
    private static ConfigurationManager INSTANCE;

    public static ConfigurationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigurationManager();
        }
        return INSTANCE;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

}