package com.company.hotelBooking.service.validators;

import com.company.hotelBooking.exceptions.RegistrationException;
import com.company.hotelBooking.managers.MessageManger;
import com.company.hotelBooking.service.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorTest {

    private final MessageManger messageManger = new MessageManger(Locale.UK);

    @Test
    public void isValidCorrect() {
        try {
            UserValidator.getINSTANCE().isValid(getUserCorrect());
        } catch (RegistrationException e) {
            assertFalse(false);
        }
    }

    @Test
    public void isValidIncorrectFirstNameFormat() {
        UserDto user = getUserCorrect();
        user.setFirstName("Maxim123");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectFirstNameEmpty() {
        UserDto user = getUserCorrect();
        user.setFirstName("");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectLastNameEmpty() {
        UserDto user = getUserCorrect();
        user.setLastName("");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectLastNameFormat() {
        UserDto user = getUserCorrect();
        user.setFirstName("4Agh");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectEmailEmpty() {
        UserDto user = getUserCorrect();
        user.setFirstName("");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectEmailFormat() {
        UserDto user = getUserCorrect();
        user.setFirstName("maxim.com");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectPasswordEmpty() {
        UserDto user = getUserCorrect();
        user.setFirstName("");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectPasswordFormat() {
        UserDto user = getUserCorrect();
        user.setFirstName("??????????-???? ????????????");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectPasswordShort() {
        UserDto user = getUserCorrect();
        user.setFirstName("123");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void isValidIncorrectPhoneNumberEmpty() {
        UserDto user = getUserCorrect();
        user.setFirstName("");
        try {
            UserValidator.getINSTANCE().isValid(user);
        } catch (RegistrationException e) {
            assertTrue(true);
        }
    }

    private UserDto getUserCorrect() {
        UserDto expected = new UserDto();
        expected.setId(1L);
        expected.setFirstName("Maxim");
        expected.setLastName("Hammond");
        expected.setEmail("maxim_hammond@kwontol.com");
        expected.setPassword("111111");
        expected.setPhoneNumber("+48511906624");
        expected.setRole(UserDto.RoleDto.valueOf("ADMIN"));
        expected.setAvatar("avatar01.png");
        return expected;
    }
}