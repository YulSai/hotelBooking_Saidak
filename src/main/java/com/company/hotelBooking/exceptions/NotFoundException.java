package com.company.hotelBooking.exceptions;

/**
 * Class for handling exceptions when information is not found on the request
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}