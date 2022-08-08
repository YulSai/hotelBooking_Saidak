package com.company.hotelBooking.controller.command.util;

import com.company.hotelBooking.service.api.IAbstractService;
import jakarta.servlet.http.HttpServletRequest;

public class PagingUtil {
    public static Paging getPaging(HttpServletRequest req) {
        String limitStr = req.getParameter("limit");
        String offsetStr = req.getParameter("page");

        int limit = limitStr == null ? 10 : Integer.parseInt(limitStr);
        long page = offsetStr == null ? 1 : Long.parseLong(offsetStr);
        long offset = (page - 1) * limit;
        return new Paging(limit, offset, page);
    }

    public static long getTotalPages(long totalEntities, int limit) {
        long totalPages = totalEntities / limit;
        return totalPages + totalEntities - (totalPages * limit) > 0 ? 1 : 0;
    }

    public static void setTotalPages(HttpServletRequest req, Paging paging, IAbstractService service) {
        long totalEntities = service.countRow();
        long totalPages = getTotalPages(totalEntities, paging.getLimit());
        req.setAttribute("current_page", paging.getPage());
        req.setAttribute("total_pages", totalPages);
    }
}
