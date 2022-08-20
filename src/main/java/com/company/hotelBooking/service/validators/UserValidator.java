package com.company.hotelBooking.service.validators;

import com.company.hotelBooking.exceptions.DaoException;
import lombok.extern.log4j.Log4j2;

/**
 * Class with method for validating user's email and password
 */
@Log4j2
public class UserValidator {
    private static UserValidator INSTANCE;

    /**
     * Method gets an instance of the class object
     *
     * @return an instance of the class object
     */
    public static UserValidator getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserValidator();
        }
        return INSTANCE;
    }

    /**
     * Method checks if the email and password is valid
     *
     * @param email    User email
     * @param password User password
     * @return boolean value. If email and password valid returns true otherwise returns false
     */
    public void isValid(String email, String password) {
        if (email == null || ("").equals(email)) {
            log.error("Invalid input email empty", email);
            throw new RuntimeException("Invalid input email empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            log.error("Invalid input email format", email);
            throw new RuntimeException("Invalid input email format");
        }
        if (password == null || ("").equals(password)) {
            log.error("Invalid input password empty", email);
            throw new RuntimeException("Invalid input password empty");
        }
        if (!password.matches("[A-Za-z0-9_]+")) {
            log.error("Invalid input password format", email);
            throw new RuntimeException("Invalid input password format");
        }
        if (password.length() < 6) {
            log.error("Invalid input password short", email);
            throw new RuntimeException("Invalid input password short");
        }
    }
}