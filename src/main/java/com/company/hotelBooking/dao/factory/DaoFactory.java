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
     * Method creates a list relationships between a class and a DAO object
     */
    private DaoFactory() {
        map = new HashMap<>();
        map.put(IUserDao.class, new UserDaoImpl(DataSource.getINSTANCE()));
        map.put(IRoomDao.class, new RoomDaoImpl(DataSource.getINSTANCE()));
        map.put(IReservationInfoDao.class, new ReservationInfoDaoImpl(DataSource.getINSTANCE(),
                getDao(IRoomDao.class)));
        map.put(IReservationDao.class, new ReservationDaoImpl(DataSource.getINSTANCE(), getDao(IUserDao.class),
                getDao(IReservationInfoDao.class)));
    }

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

    @SuppressWarnings("unchecked")
    public <T> T getDao(Class<?> clazz) {
        T dao = (T) map.get(clazz);
        if (dao == null) {
            throw new RuntimeException("Class " + clazz + "is not constructed");
        }
        return dao;
    }
}