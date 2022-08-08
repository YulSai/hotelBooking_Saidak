package com.company.hotelBooking.service.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Class describing the object RoomDto
 */
@Data
public class RoomDto {
    private Long id;
    private RoomTypeDto type;
    private BigDecimal price;
    private RoomStatusDto status;
    private CapacityDto capacity;
    private String number;

    public enum RoomStatusDto {
        AVAILABLE,
        UNAVAILABLE
    }

    public enum RoomTypeDto {
        STANDARD,
        COMFORT,
        LUX,
        PRESIDENT
    }
    public enum CapacityDto {
        SINGLE,
        DOUBLE,
        TRIPLE,
        FAMILY
    }
}