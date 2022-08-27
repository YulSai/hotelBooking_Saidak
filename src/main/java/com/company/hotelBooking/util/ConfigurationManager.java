package com.company.hotelBooking.util;

import java.util.ResourceBundle;

/**
 * Class with constants
 */
public class ConfigurationManager {
    // DataBase Connection Properties
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_DRIVER = "db.driver";

    // JSP
    // Index
    public static final String PAGE_INDEX = "page.index";
    // Error
    public static final String PAGE_ERROR = "page.error";

    // Reservation
    public static final String PAGE_RESERVATIONS = "page.reservations";
    public static final String PAGE_RESERVATION = "page.reservation";
    public static final String PAGE_BOOKING = "page.booking";
    public static final String PAGE_UPDATE_RESERVATION = "page.update.reservation";

    // Room
    public static final String PAGE_ROOMS = "page.rooms";
    public static final String PAGE_ROOM = "page.room";
    public static final String PAGE_CREATE_ROOM = "page.create.room";
    public static final String PAGE_UPDATE_ROOM = "page.update.room";
    public static final String PAGE_SEARCH_AVAILABLE_ROOMS = "page.search.available";
    public static final String PAGE_ROOMS_AVAILABLE = "page.rooms.available";

    // User
    public static final String PAGE_USERS = "page.users";
    public static final String PAGE_USER = "page.user";
    public static final String PAGE_CREATE_USER = "page.create.user";
    public static final String PAGE_UPDATE_USERS = "page.update.user";
    public static final String PAGE_LOGIN = "page.login";

    // SQL
    // Reservation
    public static final String SQL_RESERVATION_FIND_BY_ID = "SELECT rs.id, rs.user_id, rs.total_cost, sr.name AS status " +
            "FROM reservations rs JOIN reservation_statuses sr ON sr.id = rs.status_id WHERE rs.id=? AND rs.deleted=false";
    public static final String SQL_RESERVATION_FIND_ALL = "SELECT rs.id, rs.user_id, rs.total_cost, sr.name AS status " +
            "FROM reservations rs JOIN reservation_statuses sr ON sr.id = rs.status_id WHERE rs.deleted=false";
    public static final String SQL_RESERVATION_CREATE = "INSERT INTO reservations (user_id, total_cost, status_id) " +
            "VALUES (?, ?, (SELECT id FROM reservation_statuses s WHERE s.name=?))";
    public static final String SQL_RESERVATION_UPDATE = "UPDATE reservations SET user_id=?, total_cost=?, status_id=" +
            "(SELECT s.id FROM reservation_statuses s WHERE s.name=?) WHERE id=? AND deleted=false";
    public static final String SQL_RESERVATION_DELETE = "UPDATE reservations SET deleted=true WHERE id=?;";
    public static final String SQL_RESERVATION_COUNT_RESERVATIONS = "SELECT COUNT(*) AS total FROM reservations";
    public static final String SQL_RESERVATION_PAGE = "SELECT rs.id, rs.user_id, rs.total_cost, sr.name AS status " +
            "FROM reservations rs JOIN reservation_statuses sr ON sr.id = rs.status_id WHERE rs.deleted = false " +
            "ORDER BY id LIMIT ? OFFSET ?";
    public static final String SQL_RESERVATION_PAGE_BY_USER = "SELECT rs.id, rs.user_id, rs.total_cost, sr.name AS status " +
            "FROM reservations rs JOIN reservation_statuses sr ON sr.id = rs.status_id WHERE rs.user_id=? " +
            "AND rs.deleted=false ORDER BY rs.id LIMIT ? OFFSET ?";
    // ReservationInfo
    public static final String SQL_RESERVATION_INFO_FIND_BY_ID = "SELECT i.id, i.reservation_id, i.room_id, i.check_in, " +
            "i.check_out, i.nights, i.room_price FROM reservation_info i WHERE i.id=? AND i.deleted=false";
    public static final String SQL_RESERVATION_INFO_FIND_ALL = "SELECT i.id, i.reservation_id, i.room_id, i.check_in, " +
            "i.check_out, i.nights, i.room_price FROM reservation_info i WHERE i.deleted=false";
    public static final String SQL_RESERVATION_INFO_CREATE = "INSERT INTO reservation_info (reservation_id, room_id, " +
            "check_in, check_out, nights, room_price) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SQL_RESERVATION_INFO_DELETE = " UPDATE reservation_info SET deleted=true WHERE id=?";
    public static final String SQL_RESERVATION_INFO_PAGE = "SELECT i.id, i.reservation_id, i.room_id, i.check_in, " +
            "i.check_out, i.nights, i.room_price FROM reservation_info i WHERE i.deleted = false ORDER BY id LIMIT ? " +
            "OFFSET ?";
    public static final String SQL_RESERVATION_INFO_COUNT_RESERVATIONS_INFO = "SELECT COUNT(*) AS total " +
            "FROM reservation_info WHERE i.deleted = false";
    public static final String SQL_RESERVATION_INFO_FIND_BY_RESERVATION_ID = "SELECT i.id, i.reservation_id, i.room_id, " +
            "i.check_in, i.check_out, i.nights, i.room_price FROM reservation_info i  WHERE i.reservation_id=? " +
            "AND i.deleted=false";
    // Room
    public static final String SQL_ROOM_FIND_BY_ID = "SELECT r.id, r.room_number, t.name AS type, c.name AS capacity, " +
            "r.price, s.name AS status FROM rooms r JOIN room_type t ON r.room_type_id = t.id JOIN room_capacity c " +
            "ON r.room_capacity_id = c.id JOIN room_status s ON r.room_status_id = s.id WHERE r.id = ? " +
            "AND r.deleted = false";
    public static final String SQL_ROOM_FIND_ALL = "SELECT r.id, r.room_number, t.name AS type, c.name AS capacity, " +
            "r.price, s.name AS status FROM rooms r JOIN room_type t ON r.room_type_id = t.id JOIN room_capacity c " +
            "ON r.room_capacity_id = c.id JOIN room_status s ON r.room_status_id = s.id WHERE r.deleted = false";
    public static final String SQL_ROOM_CREATE = "INSERT INTO rooms (room_number, room_type_id, room_capacity_id, price, " +
            "room_status_id) VALUES (?, (SELECT id FROM room_type t WHERE t.name=?), (SELECT id FROM room_capacity c " +
            "WHERE c.name=?), ?, (SELECT id FROM room_status s WHERE s.name=?))";
    public static final String SQL_ROOM_UPDATE = "UPDATE rooms r SET room_number=?, room_type_id=(SELECT id FROM " +
            "room_type t WHERE t.name=?), room_capacity_id=(SELECT id FROM room_capacity c WHERE c.name=?), price=?, " +
            "room_status_id=(SELECT id FROM room_status s WHERE s.name=?) WHERE id = ? AND r.deleted = false";
    public static final String SQL_ROOM_DELETE = "UPDATE rooms SET deleted = true WHERE id = ?";
    public static final String SQL_ROOM_FIND_BY_NUMBER = "SELECT r.id, r.room_number, t.name AS type, c.name " +
            "AS capacity, r.price, s.name AS status FROM rooms r JOIN room_type t ON r.room_type_id = t.id " +
            "JOIN room_capacity c ON r.room_capacity_id = c.id JOIN room_status s ON r.room_status_id = s.id " +
            "WHERE r.room_number = ? AND r.deleted = false";
    public static final String SQL_ROOM_COUNT_ROOMS = "SELECT COUNT(*) AS total FROM rooms";
    public static final String SQL_ROOM_FIND_AVAILABLE_ROOMS = "SELECT r.id, r.room_number, t.name AS type, c.name AS " +
            "capacity, r.price, s.name AS status FROM rooms r JOIN room_type t ON r.room_type_id = t.id " +
            "JOIN room_capacity c ON r.room_capacity_id = c.id  JOIN room_status s ON r.room_status_id = s.id " +
            "WHERE r.room_status_id=(SELECT id FROM room_status s WHERE s.name='AVAILABLE') AND r.room_type_id=" +
            "(SELECT id FROM room_type t WHERE t.name=?) AND r.room_capacity_id=(SELECT id FROM room_capacity c " +
            "WHERE c.name=?) AND NOT EXISTS (SELECT NULL FROM reservation_info i JOIN reservations rv " +
            "ON rv.id = i.reservation_id WHERE i.room_id = r.id AND (rv.status_id=(SELECT id " +
            "FROM reservation_statuses rs WHERE rs.name='CONFIRMED') AND ((? between i.check_in AND i.check_out) " +
            "OR (? between i.check_in AND i.check_out) OR ((? < i.check_in) AND (? > i.check_out)))))";
    public static final String SQL_ROOM_PAGE = "SELECT r.id, r.room_number, t.name AS type, c.name AS capacity, r.price, " +
            "s.name AS status FROM rooms r JOIN room_type t ON r.room_type_id = t.id JOIN room_capacity c " +
            "ON r.room_capacity_id = c.id JOIN room_status s ON r.room_status_id = s.id WHERE r.deleted = false " +
            "ORDER BY r.room_number LIMIT ? OFFSET ?";
    // User
    public static final String SQL_USER_FIND_BY_ID = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, " +
            "u.phone_number, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id = ? " +
            "AND u.deleted = false";
    public static final String SQL_USER_FIND_ALL = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, " +
            "u.phone_number, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.deleted = false";
    public static final String SQL_USER_CREATE = "INSERT INTO users (first_name, last_name, email, password, " +
            "phone_number, role_id) VALUES (?, ?, ?, ?, ?, (SELECT id FROM roles r WHERE r.name = ?))";
    public static final String SQL_USER_UPDATE = "UPDATE users u SET first_name = ?, last_name = ?, email = ?, " +
            "password = ?, phone_number = ?, role_id = (SELECT id FROM roles r WHERE r.name = ?) " +
            "WHERE u.id = ? AND u.deleted = false";
    public static final String SQL_USER_DELETE = "UPDATE users SET deleted = true WHERE id = ?";
    public static final String SQL_USER_FIND_BY_EMAIL = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, " +
            "u.phone_number, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = ? " +
            "AND u.deleted = false";
    public static final String SQL_USER_COUNT_USERS = "SELECT COUNT(*) AS total FROM users";
    public static final String SQL_USER_PAGE = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, " +
            "u.phone_number, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.deleted = false " +
            "ORDER BY last_name LIMIT ? OFFSET ?";
    private static final String RESOURCE_NAME = "application";
    private static ConfigurationManager INSTANCE;
    private final ResourceBundle resourceBundle;

    private ConfigurationManager() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

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