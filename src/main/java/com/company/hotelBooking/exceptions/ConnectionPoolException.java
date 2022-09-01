package com.company.hotelBooking.exceptions;

import java.sql.SQLException;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String s, SQLException e) {
    }
}
