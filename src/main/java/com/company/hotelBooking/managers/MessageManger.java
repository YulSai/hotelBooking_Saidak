package com.company.hotelBooking.managers;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManger {
    private static final String RESOURCE_NAME = "pageMessage";
    private static ResourceBundle resourceBundle = null;

    public MessageManger(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}