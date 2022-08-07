package com.company.hotelBooking.service.api;

import java.util.List;

/**
 * Interface
 */
public interface IAbstractService<K, T> {
    /**
     * Method finds Dto object by id
     *
     * @param id Dto object id
     * @return Dto object
     */
    T findById(K id);


    /**
     * Method finds all Dto object
     *
     * @return list of all Dto object
     */
    List<T> findAll();

    /**
     * Method saves Dto object
     *
     * @param entity Dto object
     */
    T create(T entity);

    /**
     * Method saves an edited Dto object
     *
     * @param entity Dto object
     */
    T update(T entity);

    /**
     * Method "soft" deletes Dto objects
     *
     * @param id Dto object id to be "soft" deleted
     */
    void delete(K id);
}
