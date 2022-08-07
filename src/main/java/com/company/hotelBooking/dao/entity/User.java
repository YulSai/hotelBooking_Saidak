package com.company.hotelBooking.dao.entity;

import lombok.Data;

/**
 * Class describing the object User
 */
@Data
public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private Role role;
	
	public enum Role{
		ADMIN,
		CLIENT,
		GUEST
	}
}