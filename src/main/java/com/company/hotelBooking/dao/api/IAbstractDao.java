package com.company.hotelBooking.dao.api;

import java.util.List;

/**
 * Interface
 */
public interface IAbstractDao<K, T> {

    /**
     * Method finds Entity object in the data source by id
     *
     * @param id Object id to be deleted
     */
    T findById(K id);

    /**
     * Method finds all Entity object in the data source
     */
    List<T> findAll();

    /**
     * Method is used for saving Entity objects in the data source
     *
     * @param entity Entity object to be saved
     */
    T save(T entity);


    /**
     * Method is used for saving updating Entity objects in the data source
     *
     * @param entity Entity object to be saved
     */
    T update(T entity);

    /**
     * Method is used for "soft" deleting Entity objects in the data source
     *
     * @param id Object id to be "soft" deleted
     */
    boolean delete(K id);
}