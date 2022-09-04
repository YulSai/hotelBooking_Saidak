package com.company.hotelBooking.service.impl;

import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.dao.impl.UserDaoImpl;
import com.company.hotelBooking.exceptions.LoginUserException;
import com.company.hotelBooking.service.api.IUserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceImplTest {
    private final IUserService userService = new UserServiceImpl(new UserDaoImpl(DataSource.getINSTANCE()));

    @Test
    public void loginCorrect() {
        userService.login("maxim_hammond@kwontol.com", "111111");
        assertTrue(true);
    }

    @Test
    public void loginCorrectException() throws LoginUserException {
        try {
            userService.login("maxim_hammond@kwontol.com", "111111");
        } catch (LoginUserException e) {
            assertFalse(false);
        }
    }

    @Test
    public void loginIncorrect() throws LoginUserException {
        try {
            userService.login("maxim_hammond@kwontol.com", "000");
        } catch (LoginUserException e) {
            assertTrue(true);
        }
    }

    @Test
    public void loginNull() throws LoginUserException {
        try {
            userService.login(null, null);
        } catch (LoginUserException e) {
            assertTrue(true);
        }
    }
}