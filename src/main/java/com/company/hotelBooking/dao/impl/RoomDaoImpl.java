package com.company.hotelBooking.dao.impl;

import com.company.hotelBooking.dao.api.IRoomDao;
import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.dao.entity.Room;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.util.AppConstants;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class object RoomDAO with implementation of CRUD operation operations
 */
@Log4j2
public class RoomDaoImpl implements IRoomDao {
    private final DataSource dataSource;

    public RoomDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Room findById(Long id) {
        log.debug("Accessing the database using the findById  command. Room id = {}", id);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_ROOM_FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return processRoom(result);
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO findById error id = {}", id, e);
            throw new DaoException("Error findById ", e);
        }
        return null;
    }

    public List<Room> findAll() {
        log.debug("Accessing the database using the findAll command");
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(AppConstants.SQL_ROOM_FIND_ALL)) {
            while (result.next()) {
                rooms.add(processRoom(result));
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO findAll", e);
            throw new DaoException("Error findAll rooms", e);
        }
        return rooms;
    }

    @Override
    public Room save(Room room) {
        log.debug("Accessing the database using the create command. Room = {}", room);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_ROOM_CREATE,
                     Statement.RETURN_GENERATED_KEYS)) {
            extractedDate(room, statement);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                Long id = keys.getLong("id");
                return findById(id);
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO create error new room = {}", room, e);
        }
        throw new DaoException("Failed to create new room" + room);
    }

    @Override
    public Room update(Room room) {
        log.debug("Accessing the database using the update command. Room = {}", room);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_ROOM_UPDATE)) {
            extractedDate(room, statement);
            statement.setLong(6, room.getId());

            if (statement.executeUpdate() == 0) {
                log.error("Command update can't be executed");
                throw new DaoException("Command update can't be executed" + room);
            }
            return findById(room.getId());
        } catch (SQLException e) {
            log.error("SQLRoomDAO update error. Failed to update room = {}", room, e);
            throw new DaoException("Failed to update room " + room);
        }
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Accessing the database using the delete command. Room id = {}", id);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_ROOM_DELETE)) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted == 1;
        } catch (SQLException e) {
            log.error("SQLRoomDAO delete error id  = {}", id, e);
            throw new DaoException("Failed to delete room with id " + id);
        }
    }

    @Override
    public List<Room> findAllPages(int limit, long offset) {
        log.debug("Accessing the database using the findAllPages command");
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_ROOM_PAGE)) {
            statement.setInt(1, limit);
            statement.setLong(2, offset);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                rooms.add(processRoom(result));
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO findAllPages error", e);
            throw new DaoException("Failed to find rooms", e);
        }
        return rooms;
    }

    public Room findRoomByNumber(String number) {
        log.debug("Accessing the database using the findRoomByNumber command. Room number = {}", number);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_ROOM_FIND_BY_NUMBER)) {
            statement.setString(1, number);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return processRoom(result);
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO findRoomByNumber error number = {}", number, e);
            throw new DaoException("Failed to find room with number " + number);
        }
        return null;
    }

    @Override
    public List<Room> findAvailableRooms(LocalDate check_in, LocalDate check_out, String type, String capacity) {
        log.debug("Accessing the database using the findAvailableRooms command");
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_ROOM_FIND_AVAILABLE_ROOMS)) {
            statement.setString(1, type.toUpperCase());
            statement.setString(2, capacity.toUpperCase());
            statement.setDate(3, java.sql.Date.valueOf(check_in));
            statement.setDate(4, java.sql.Date.valueOf(check_out));
            statement.setDate(5, java.sql.Date.valueOf(check_in));
            statement.setDate(6, java.sql.Date.valueOf(check_out));

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                rooms.add(processRoom(result));
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO findAllAvailableRooms error", e);
            throw new DaoException("Failed to find available rooms");
        }
        return rooms;
    }

    @Override
    public long countRow() throws DaoException {
        log.debug("Accessing the database using the findRowCount command");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(AppConstants.SQL_ROOM_COUNT_ROOMS)) {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getLong("total");
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO findRowCount error", e);
            throw new DaoException("Failed to find count of rooms", e);
        }
        throw new DaoException("Failed to count rooms");
    }

    /**
     * Method fills the Room object with data from the database
     *
     * @param result resulting query
     * @return Room object
     */
    private Room processRoom(ResultSet result) throws SQLException {

        Room room = new Room();
        room.setId(result.getLong("id"));
        room.setNumber(result.getString("room_number"));
        room.setType(Room.RoomType.valueOf(result.getString("type")));
        room.setCapacity(Room.Capacity.valueOf(result.getString("capacity")));
        room.setPrice(result.getBigDecimal("price"));
        room.setStatus(Room.RoomStatus.valueOf(result.getString("status")));
        return room;
    }

    /**
     * Method extracts the object's fields data
     *
     * @param room      object Room
     * @param statement an object that represents a precompiled SQL statement
     */
    private void extractedDate(Room room, PreparedStatement statement) throws SQLException {
        statement.setString(1, room.getNumber());
        statement.setString(2, room.getType().toString().toUpperCase());
        statement.setString(3, room.getCapacity().toString().toUpperCase());
        statement.setBigDecimal(4, room.getPrice());
        statement.setString(5, room.getStatus().toString().toUpperCase());
    }
}