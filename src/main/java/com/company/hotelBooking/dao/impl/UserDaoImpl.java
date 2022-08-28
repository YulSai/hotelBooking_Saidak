package com.company.hotelBooking.dao.impl;

import com.company.hotelBooking.dao.api.IUserDao;
import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.dao.entity.User;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.exceptions.RegistrationException;
import com.company.hotelBooking.util.AppConstants;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class object UserDAO with implementation of CRUD operation operations
 */
@Log4j2
public class UserDaoImpl implements IUserDao {
    private final DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        log.debug("Accessing the database using the findById command. User's id = {}", id);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_USER_FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return processUser(result);
            }
        } catch (SQLException e) {
            log.error("SQLUserDAO findById error {}", id, e);
            throw new DaoException("Error findById {}" + id);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        log.debug("Accessing the database using the findAll command");
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(AppConstants.SQL_USER_FIND_ALL)) {
            while (result.next()) {
                users.add(processUser(result));
            }
        } catch (SQLException e) {
            log.error("SQLUserDAO findAll", e);
            throw new DaoException("Error findAll");
        }
        return users;
    }

    @Override
    public User save(User user) {
        log.debug("Accessing the database using the create command. User = {}", user);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_USER_CREATE,
                     Statement.RETURN_GENERATED_KEYS)) {
            extractedDate(user, statement);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                Long id = keys.getLong("id");
                return findById(id);
            }
        } catch (SQLException e) {
            log.error("SQLUserDAO create error ", e);
        }
        throw new DaoException("Failed to create new user");
    }

    @Override
    public User update(User user) {
        log.debug("Accessing the database using the update command. User = {}", user);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_USER_UPDATE)) {
            extractedDate(user, statement);
            statement.setLong(7, user.getId());

            if (statement.executeUpdate() == 0) {
                log.error("Command update can't be executed");
            }
            return findById(user.getId());

        } catch (SQLException e) {
            log.error("SQLUserDAO update error. Failed to update user {}", user, e);
            throw new DaoException("Failed to update user" + user);
        }
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Accessing the database using the delete command. User's id = {}", id);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_USER_DELETE)) {
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted == 1;
        } catch (SQLException e) {
            log.error("SQLUserDAO delete error {}", id, e);
            throw new DaoException("Failed to delete user with id " + id);
        }
    }

    @Override
    public List<User> findAllPages(int limit, long offset) {
        log.debug("Accessing the database using the findAllPages command");
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_USER_PAGE)) {
            statement.setInt(1, limit);
            statement.setLong(2, offset);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(processUser(result));
            }
        } catch (SQLException e) {
            log.error("SQLUserDAO findAllPages error", e);
            throw new DaoException("Failed to find users", e);
        }
        return users;
    }

    @Override
    public long countRow() throws DaoException {
        log.debug("Accessing the database using the findRowCount command");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_USER_COUNT_USERS)) {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getLong("total");
            }
        } catch (SQLException e) {
            log.error("SQLUserDAO findRowCount error", e);
            throw new DaoException("Failed to find count of users", e);
        }
        throw new DaoException("Failed to count users");
    }

    @Override
    public User findUserByEmail(String email) {
        log.debug("Accessing the database using the findUserByEmail command. User's email = {}", email);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_USER_FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return processUser(result);
            }
        } catch (SQLException e) {
            log.error("SQLUserDAO findUserByEmail error {}", email, e);
            throw new RegistrationException("No user found with this email", e);
        }
        return null;
    }

    /**
     * Method fills the User object with data from the database
     *
     * @param result resulting query
     * @return User object
     */
    private User processUser(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getLong("id"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setEmail(result.getString("email"));
        user.setPassword(result.getString("password"));
        user.setPhoneNumber(result.getString("phone_number"));
        user.setRole(User.Role.valueOf((result.getString("role")).toUpperCase()));
        return user;
    }

    /**
     * Method extracts the object's fields data
     *
     * @param user      object User
     * @param statement an object that represents a precompiled SQL statement
     */
    private void extractedDate(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getPhoneNumber());
        statement.setString(6, user.getRole().toString());
    }
}