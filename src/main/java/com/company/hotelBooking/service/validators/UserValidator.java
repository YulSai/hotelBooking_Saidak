package com.company.hotelBooking.service.validators;

import com.company.hotelBooking.exceptions.RegistrationException;
import com.company.hotelBooking.service.dto.UserDto;
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
     * @param userDto User email
     */
    public void isValid(UserDto userDto) {

        String firstName = userDto.getFirstName();
        if (firstName == null || ("").equals(firstName)) {
            log.error("Invalid input firstName empty", firstName);
            throw new RegistrationException("Invalid input first name empty");
        }
        if (!firstName.matches("^[A-Za-z]+")) {
            log.error("Invalid input firstName format", firstName);
            throw new RegistrationException("Invalid input first name format");
        }

        String lastName = userDto.getLastName();
        if (lastName == null || ("").equals(lastName)) {
            log.error("Invalid input last name empty", lastName);
            throw new RegistrationException("Invalid input last name empty");
        }
        if (!lastName.matches("^[A-Za-z-]+")) {
            log.error("Invalid input last name format", lastName);
            throw new RegistrationException("Invalid input last name format");
        }

        String email = userDto.getEmail();
        if (email == null || ("").equals(email)) {
            log.error("Invalid input email empty", email);
            throw new RegistrationException("Invalid input email empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            log.error("Invalid input email format", email);
            throw new RegistrationException("Invalid input email format");
        }

        String password = userDto.getPassword();
        if (password == null || ("").equals(password)) {
            log.error("Invalid input password empty", email);
            throw new RegistrationException("Invalid input password empty");
        }
        if (!password.matches("[A-Za-z0-9_]+")) {
            log.error("Invalid input password format", email);
            throw new RegistrationException("Invalid input password format");
        }
        if (password.length() < 6) {
            log.error("Invalid input password short", email);
            throw new RegistrationException("Invalid input password short");
        }

        String phoneNumber = userDto.getPhoneNumber();
        if (phoneNumber == null || ("").equals(phoneNumber)) {
            log.error("Invalid input phoneNumber empty", phoneNumber);
            throw new RegistrationException("Invalid input phone number empty");
        }
    }
}