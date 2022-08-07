package com.company.hotelBooking.service.api;

import com.company.hotelBooking.service.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for serving User objects according to the business logics of User
 */
public interface IUserService extends IAbstractService<Long, UserDto>{

	/**
	 * Method gets user Dto by email
	 * @param email user email
	 * @return UserDto object
	 */
	UserDto findUserByEmail(String email);

	/**
	 * Method gets all user Dto
	 * @return list of user Dto
	 */
	List<UserDto> findAll();

	/**
	 * Method gets amount of users Dto
	 * @return amount of users Dto
	 */
	int countAllUsers() throws SQLException;

	/**
	 * Method is used for user authentication
	 *
	 * @param email    user email
	 * @param password user password
	 * @return User if found
	 */
	UserDto login(String email, String password);
}