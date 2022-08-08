package com.company.hotelBooking.controller.command.util;

import lombok.Data;

@Data
public class Paging {
    private final int limit;
    private final long offset;
    private final long page;
}
