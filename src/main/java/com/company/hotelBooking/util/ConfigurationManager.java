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
    public static final String SQL_RESERVATION_DIFFERENCE_DATE = "sql.reservation.difference.date";
    public static final String SQL_RESERVATION_FIND_ALL_RESERVATION_BY_ROOM_ID_AND_TIME_PERIOD =
            "sql.reservation.find.all.reservation.by.room.id.and.time.period";
    public static final String SQL_RESERVATION_FIND_ALL_RESERVATION_BY_USER_ID_AND_TIME_PERIOD =
            "sql.reservation.find.all.reservation.by.user.id.and.time.period";

    // Room
    public static final String SQL_ROOM_FIND_BY_ID = "sql.room.find.by.id";
    public static final String SQL_ROOM_FIND_ALL = "sql.room.find.all";
    public static final String SQL_ROOM_CREATE = "sql.room.create";
    public static final String SQL_ROOM_UPDATE = "sql.room.update";
    public static final String SQL_ROOM_DELETE = "sql.room.delete";
    public static final String SQL_ROOM_FIND_BY_NUMBER = "sql.room.find.by.number";
    public static final String SQL_ROOM_FIND_ROOMS_BY_DATES_AND_TYPE = "sql.room.find.by.dates.and.type";
    public static final String SQL_ROOM_COUNT_ROOMS = "sql.room.count.rooms";
    public static final String SQL_ROOM_FIND_ALL_AVAILABLE_ROOMS = "sql.room.find.all.available.rooms";

    // User
    public static final String SQL_USER_FIND_BY_ID = "sql.user.find.by.id";
    public static final String SQL_USER_FIND_ALL = "sql.user.find.all";
    public static final String SQL_USER_CREATE = "sql.user.create";
    public static final String SQL_USER_UPDATE = "sql.user.update";
    public static final String SQL_USER_DELETE = "sql.user.delete";
    public static final String SQL_USER_FIND_BY_EMAIL = "sql.user.find.by.email";
    public static final String SQL_USER_FIND_BY_LAST_NAME = "sql.user.find.by.last.name";
    public static final String SQL_USER_COUNT_USERS = "sql.user.count.users";
    public static final String SQL_USER_FIND_BY_EMAIL_AND_PASSWORD = "sql.user.find.by.email.and.password";

    // JSP
    // Index
    public static final String PAGE_INDEX = "page.index";

    // Error
    public static final String PAGE_ERROR = "page.error";

    // Payment
    public static final String PAGE_PAYMENTS = "page.payments";
    public static final String PAGE_PAYMENT = "page.payment";
    public static final String PAGE_CREATE_PAYMENT = "page.create.payment";
    public static final String PAGE_UPDATE_PAYMENT = "page.update.payment";

    // Reservation
    public static final String PAGE_RESERVATIONS = "page.reservations";
    public static final String PAGE_RESERVATION = "page.reservation";
    public static final String PAGE_CREATE_RESERVATION = "page.create.reservation";
    public static final String PAGE_UPDATE_RESERVATION = "page.update.reservation";
    public static final String PAGE_BOOKING = "page.booking";

    // Room
    public static final String PAGE_ROOMS = "page.rooms";
    public static final String PAGE_ROOM = "page.room";
    public static final String PAGE_CREATE_ROOM = "page.create.room";
    public static final String PAGE_UPDATE_ROOM = "page.update.room";

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