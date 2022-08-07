package com.company.hotelBooking.service.factory;

import com.company.hotelBooking.dao.api.*;
import com.company.hotelBooking.dao.factory.DaoFactory;
import com.company.hotelBooking.service.api.*;
import com.company.hotelBooking.service.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class with methods for creating a list relationships between a class and a
 * Service object
 */
public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    private final Map<Class<?>, Object> map;

    /**
     * Method gets an instance of the class object
     *
     * @return an instance of the class object
     */
    public static ServiceFactory getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceFactory();
        }
        return INSTANCE;
    }

    /**
     * Method creates a list relationships between a class and a Service object
     */
    private ServiceFactory() {
        map = new HashMap<>();
        map.put(IRoomService.class, new RoomServiceImpl(DaoFactory.getINSTANCE().getDao(IRoomDao.class)));
        map.put(IUserService.class, new UserServiceImpl(DaoFactory.getINSTANCE().getDao(IUserDao.class)));
        map.put(IReservationService.class, new ReservationServiceImpl(DaoFactory.getINSTANCE().getDao(IReservationDao.class)));
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<?> clazz) {
        return (T) map.get(clazz);
    }
}