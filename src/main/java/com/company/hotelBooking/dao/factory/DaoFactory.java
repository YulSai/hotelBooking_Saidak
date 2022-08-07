package com.company.hotelBooking.dao.factory;

import com.company.hotelBooking.dao.api.*;
import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.dao.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class with methods for creating a list relationships between a class and a
 * DAO object
 */
public class DaoFactory {
    private static DaoFactory INSTANCE;
    private final Map<Class<?>, Object> map;

    /**
     * Method gets an instance of the class object
     *
     * @return an instance of the class object
     */
    public static DaoFactory getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }
        return INSTANCE;
    }

    /**
     * Method creates a list relationships between a class and a DAO object
     */
    private DaoFactory() {
        map = new HashMap<>();
        map.put(IRoomDao.class, new RoomDaoImpl(DataSource.getINSTANCE()));
        map.put(IUserDao.class, new UserDaoImpl(DataSource.getINSTANCE()));
        map.put(IReservationDao.class, new ReservationDaoImpl(DataSource.getINSTANCE()));

    }

    @SuppressWarnings("unchecked")
    public <T> T getDao(Class<?> clazz) {
        return (T) map.get(clazz);
    }
}