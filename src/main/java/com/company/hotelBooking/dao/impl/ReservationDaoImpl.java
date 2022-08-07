package com.company.hotelBooking.dao.impl;

import com.company.hotelBooking.dao.api.IReservationDao;
import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.dao.entity.Reservation;
import com.company.hotelBooking.dao.entity.Room;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.util.ConfigurationManager;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class object ReservationDao with implementation of CRUD operation operations
 */
@Log4j2
public class ReservationDaoImpl implements IReservationDao {
    private final DataSource dataSource;


    public ReservationDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Method gets the Reservation object from the database by id
     */
    @Override
    public Reservation findById(Long id) {
        log.debug("Accessing the database using the \"findById\" command. Reservation id = {}, time = {}",
                id, new Date());
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(ConfigurationManager.getInstance()
                .getString(ConfigurationManager.SQL_RESERVATION_FIND_BY_ID))) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return processReservation(result);
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO findById error id = {}", id, e);
            throw new DaoException("Error findById with id" + id);
        }
        return null;
    }

    /**
     * Method gets a list of all Reservation objects from the database
     */
    public List<Reservation> findAll() {
        log.debug("Accessing the database using the \"findAll\" command. Time = {}", new Date());
        List<Reservation> reservations = new ArrayList<>();
        try (Statement statement = dataSource.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(ConfigurationManager.getInstance()
                    .getString(ConfigurationManager.SQL_RESERVATION_FIND_ALL));
            while (result.next()) {
                reservations.add(processReservation(result));
            }
        } catch (SQLException e) {
            log.error("SQLRoomDAO findAll", e);
            throw new DaoException("Error findAll reservations");
        }
        return reservations;
    }

    @Override
    public Reservation save(Reservation entity) {
        log.debug("Accessing the database using the \"save\" command. Time = {}", new Date());
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(ConfigurationManager.getInstance()
                .getString(ConfigurationManager.SQL_RESERVATION_CREATE), Statement.RETURN_GENERATED_KEYS)) {
            extractedDate(entity, statement);
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                Long id = keys.getLong("id");
                return findById(id);
            }
        } catch (SQLException e) {
            log.error("SQLReservationDAO save error: " + entity, e);
        }
        throw new DaoException("Failed to save new reservation " + entity);
    }

    @Override
    public Reservation update(Reservation entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Accessing the database using the \"delete\" command. Reservation id = {}, time = {}", id, new Date());
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(ConfigurationManager.getInstance()
                .getString(ConfigurationManager.SQL_RESERVATION_DELETE))) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted == 1;
        } catch (SQLException e) {
            log.error("SQLReservationDAO delete error id  = {}", id, e);
        }
        return false;
    }
    @Override
    public Integer differenceBetweenDate(Long id) {
        Integer differenceDays = null;
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(ConfigurationManager.getInstance()
                .getString(ConfigurationManager.SQL_RESERVATION_DIFFERENCE_DATE))) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                differenceDays = result.getInt("difference");
            }
        } catch (SQLException e) {
            //TODO Exceptions
            throw new RuntimeException(e);
        }
        return differenceDays;
    }

    @Override
    public List<Reservation> findAllByRoomIdAndTimePeriod(Long id, LocalDate begin, LocalDate end) {
        log.debug("Accessing the database using the \"findAllByRoomIdAndTimePeriod\" command. " +
                "Room id = {}, time = {}", id, new Date());
        List<Reservation> reservations = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(ConfigurationManager.getInstance()
                .getString(ConfigurationManager.SQL_RESERVATION_FIND_ALL_RESERVATION_BY_ROOM_ID_AND_TIME_PERIOD))) {
            statement.setLong(1, id);
            statement.setDate(2, java.sql.Date.valueOf(begin));
            statement.setDate(3, java.sql.Date.valueOf(end));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reservations.add(processReservation(result));
            }
        } catch (SQLException e) {
            log.error("SQLReservationDAO findAllByRoomIdAndTimePeriod error. Room id = {}", id, e);
            throw new DaoException("Failed to find rooms", e);
        }
        return null;
    }

    @Override
    public List<Reservation> findAllByUserIdAndTimePeriod(Long id, LocalDate begin) {
        log.debug("Accessing the database using the \"ffindAllByUserIdAndTimePeriod\" command. " +
                "User id = {}, time = {}", id, new Date());
        List<Reservation> reservations = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(ConfigurationManager.getInstance()
                .getString(ConfigurationManager.SQL_RESERVATION_FIND_ALL_RESERVATION_BY_USER_ID_AND_TIME_PERIOD))) {
            statement.setLong(1, id);
            statement.setDate(2, java.sql.Date.valueOf(begin));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reservations.add(processReservation(result));
            }
        } catch (SQLException e) {
            log.error("SQLReservationDAO findAllByRoomIdAndTimePeriod error. User id = {}", id, e);
            throw new DaoException("Failed to find rooms", e);
        }
        return null;
    }

    /**
     * Method fills the Reservation object with data from the database
     *
     * @param result resulting query
     * @return Reservation object
     */
    private Reservation processReservation(ResultSet result) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(result.getLong("id"));
        reservation.setCheckIn(result.getTimestamp("check_in").toLocalDateTime().toLocalDate());
        reservation.setCheckOut(result.getTimestamp("check_out").toLocalDateTime().toLocalDate());
        reservation.setRoomType(Room.RoomType.valueOf(result.getString("room_type")));
        reservation.setRoomCapacity(Room.Capacity.valueOf(result.getString("room_capacity")));
        reservation.setStatus(Reservation.Status.valueOf(result.getString("status")));
        reservation.setUserId(result.getLong("user_id"));
        reservation.setRoomId(result.getLong("room_id"));
        reservation.setInvoice(result.getBigDecimal("invoice"));
        return reservation;
    }

    /**
     * Method extracts the object's fields data
     *
     * @param reservation object Reservation
     * @param statement   an object that represents a precompiled SQL statement
     */
    private void extractedDate(Reservation reservation, PreparedStatement statement) throws SQLException {
        statement.setDate(1, java.sql.Date.valueOf(reservation.getCheckIn()));
        statement.setDate(2, java.sql.Date.valueOf(reservation.getCheckOut()));
        statement.setString(3, reservation.getRoomType().toString().toUpperCase());
        statement.setString(4, reservation.getRoomCapacity().toString().toUpperCase());
        statement.setString(5, reservation.getStatus().toString().toUpperCase());
        statement.setLong(6, reservation.getUserId());
        statement.setLong(7, reservation.getRoomId());
        statement.setBigDecimal(8, reservation.getInvoice());
    }
}