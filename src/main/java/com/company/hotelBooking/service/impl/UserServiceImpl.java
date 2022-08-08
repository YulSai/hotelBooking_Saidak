package com.company.hotelBooking.service.impl;

import com.company.hotelBooking.controller.command.util.Paging;
import com.company.hotelBooking.dao.api.IUserDao;
import com.company.hotelBooking.dao.entity.User;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.service.api.IUserService;
import com.company.hotelBooking.service.dto.UserDto;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

/**
 * Class object UserDTO with implementation of CRUD operation operations
 */
@Log4j2
public class UserServiceImpl implements IUserService {
    private final IUserDao userDao;

    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("Calling a service method \"findById\". UserDto id = {}, time = {}", id, new Date());
        UserDto userDto = toDto(userDao.findById(id));
        if (userDto == null) {
            log.error("SQLUserService findById error. No user with id = {}", id);
            throw new DaoException("No user type with id " + id);
        }
        return userDto;
    }

    @Override
    public List<UserDto> findAll() {
        log.debug("Calling a service method \"findAll\". Time = {}", new Date());
        return userDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Calling a service method \"create\". UserDto = {}, time = {}", userDto, new Date());
        User existing = userDao.findUserByEmail(userDto.getEmail());
        if (existing != null) {
            log.error("User with email = {} already exists", userDto.getEmail());
            throw new DaoException("User exists");
        }
        return toDto(userDao.save(toEntity(userDto)));
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Calling a service method \"update\". UserDto = {}, time = {}", userDto, new Date());
        User existing = userDao.findUserByEmail(userDto.getEmail());
        if (existing != null && !existing.getId().equals(userDto.getId())) {
            log.error("User with email = {} already exists", userDto.getEmail());
            throw new DaoException("User exists");
        }
        return toDto(userDao.update(toEntity(userDto)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Calling a service method \"delete\". UserDto id = {}, time = {}", id, new Date());
        userDao.delete(id);
        if (!userDao.delete(id)) {
            log.error("SQLUserService deleted error. Failed to delete user with id = {}", id);
            throw new DaoException("Failed to delete user with id " + id);
        }
    }

    @Override
    public List<UserDto> findAllPages(Paging paging) {
        log.debug("Calling a service method \"findAll\". Time = {}", new Date());
        return userDao.findAllPages(paging.getLimit(), paging.getOffset()).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto login(String email, String password) {
        log.debug("Calling a service method \"login\". UserDto email = {}, password = {}," +
                " time = {}", email, password, new Date());
        UserDto userDto = toDto(userDao.findUserByEmail(email));
        if (userDto == null) {
            log.error("SQLUserService login error. No user with email = {}", email);
            throw new DaoException("No user with that email " + email);
        }
        if (!userDto.getPassword().equals(password)){
            log.error("SQLUserService login error. Wrong password = {}", password);
            throw new DaoException("Wrong password for user with email " + email);
        }
        return userDto;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        log.debug("Calling a service method \"findUserByEmail\". UserDto email = {}, time = {}", email, new Date());
        UserDto userDto = toDto(userDao.findUserByEmail(email));
        if (userDto == null) {
            log.error("SQLUserService findUserByEmail error. No user with email = {}", email);
            throw new DaoException("No user with that email " + email);
        }
        return userDto;
    }

    @Override
    public long countRow() {
        log.debug("Calling a service method \"countRow\". Time = {}", new Date());
            return userDao.countRow();
    }

    /**
     * Method transforms object User into object UserDto
     *
     * @param entity object User
     * @return object UserDto
     */
    private UserDto toDto(User entity) {
        log.debug("Calling a service method \"toDto\". User = {}, time = {}", entity, new Date());
        UserDto dto = new UserDto();
        try {
            dto.setId(entity.getId());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setEmail(entity.getEmail());
            dto.setPassword(entity.getPassword());
            dto.setPhoneNumber(entity.getPhoneNumber());
            dto.setRole(UserDto.RoleDto.valueOf(entity.getRole().toString()));
        } catch (NullPointerException e) {
            log.error("This user is not in the catalog.");
            throw new DaoException("No user");
        }
        return dto;
    }

    /**
     * Method transforms object UserDto into object User
     *
     * @param dto UserDto
     * @return object User
     */
    private User toEntity(UserDto dto) {
        log.debug("Calling a service method \"toEntity\". UserDto = {}, time = {}", dto, new Date());
        User entity = new User();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRole(User.Role.valueOf(dto.getRole().toString()));
        return entity;
    }
}