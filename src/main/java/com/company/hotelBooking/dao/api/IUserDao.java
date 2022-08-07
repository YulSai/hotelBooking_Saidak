package com.company.hotelBooking.dao.api;

import com.company.hotelBooking.dao.entity.User;

import java.sql.SQLException;

/**
 * Interface extends IAbstractDao interface for managing User entities
 */
public interface IUserDao extends IAbstractDao<Long, User> {

	/**
	 * Method finds user in the data source by email
	 *
	 * @param email for search
	 * @return user
	 */
	User findUserByEmail(String email);

	/**
	 * Method counts the number of users in the data source
	 *
	 * @return amount of users
	 */
	int countAllUsers() throws SQLException;

	/**
	 * Method for authenticating user by login and password
	 *
	 * @param login    user login
	 * @param password user password
	 * @return optional of user
	 */
	//User findUserByLoginAndPassword(String login, String password);
}