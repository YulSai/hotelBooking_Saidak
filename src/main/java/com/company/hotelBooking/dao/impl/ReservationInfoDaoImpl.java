package com.company.hotelBooking.dao.impl;

import com.company.hotelBooking.dao.api.IReservationInfoDao;
import com.company.hotelBooking.dao.api.IRoomDao;
import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.dao.entity.ReservationInfo;
import com.company.hotelBooking.dao.entity.Room;
import com.company.hotelBooking.exceptions.DaoException;
import com.company.hotelBooking.util.ConfigurationManager;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class object ReservationInfoDao with implementation of CRUD operation operations
 */
@Log4j2
public class ReservationInfoDaoImpl implements IReservationInfoDao {
    private final DataSource dataSource;
    private final IRoomDao roomDao;

    public ReservationInfoDaoImpl(DataSource dataSource, IRoomDao roomDao) {
        this.dataSource = dataSource;
        this.roomDao = roomDao;
    }

    @Override
    public ReservationInfo findById(Long id) {
        log.debug("Accessing the database using the \"findById\" command. ReservationInfo id = {}, time = {}",
                id, new Date());
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(
                ConfigurationManager.getInstance()
                        .getString(ConfigurationManager.SQL_RESERVATION_INFO_FIND_BY_ID))) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return processReservationInfo(result);
            }
        } catch (SQLException e) {
            log.error("SQLReservationInfo findById error: = {}", id, e);
            throw new DaoException("Error findById");
        }
        return null;
    }

    @Override
    public List<ReservationInfo> findAll() {
        log.debug("Accessing the database using the \"findAll\" command. Time = {}", new Date());
        List<ReservationInfo> reservationsInfo = new ArrayList<>();
        try (Statement statement = dataSource.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(ConfigurationManager.getInstance()
                    .getString(ConfigurationManager.SQL_RESERVATION_INFO_FIND_ALL));
            while (result.next()) {
                reservationsInfo.add(processReservationInfo(result));
            }
        } catch (SQLException e) {
            log.error("SQLReservationInfoDAO findAll", e);
            throw new DaoException("Error findAll");
        }
        return reservationsInfo;
    }

    @Override
    public ReservationInfo save(ReservationInfo entity) {
        log.debug("Accessing the database using the \"save\" command. Time = {}", new Date());
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(
                ConfigurationManager.getInstance()
                        .getString(ConfigurationManager.SQL_RESERVATION_INFO_CREATE),
                Statement.RETURN_GENERATED_KEYS)) {
            extractedDate(entity, statement);
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                Long id = keys.getLong("id");
                return findById(id);
            }
        } catch (SQLException e) {
            log.error("SQLReservationInfoDAO save error: " + entity, e);
        }
        throw new DaoException("Failed to save new reservation " + entity);
    }


    @Override
    public ReservationInfo update(ReservationInfo entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Accessing the database using the \"delete\" command. ReservationInfo id = {}, time = {}", id,
                new Date());
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(
                ConfigurationManager.getInstance()
                        .getString(ConfigurationManager.SQL_RESERVATION_INFO_DELETE))) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted == 1;
        } catch (SQLException e) {
            log.error("SQLReservationInfoDAO delete error id  = {}", id, e);
        }
        return false;
    }

    @Override
    public List<ReservationInfo> findAllPages(int limit, long offset) {
        log.debug("Accessing the database using the \"findAllPages\" command. Time = {}", new Date());
        List<ReservationInfo> reservationInfos = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(
                ConfigurationManager.getInstance()
                        .getString(ConfigurationManager.SQL_RESERVATION_INFO_PAGE))) {
            statement.setInt(1, limit);
            statement.setLong(2, offset);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reservationInfos.add(processReservationInfo(result));
            }
        } catch (SQLException e) {
            log.error("SQLReservationInfoDAO findAllPages error", e);
            throw new DaoException("Failed to find reservation info", e);
        }
        return reservationInfos;
    }

    @Override
    public long countRow() throws DaoException {

        log.debug("Accessing the database using the \"findRowCount\" command. Time = {}", new Date());
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(
                ConfigurationManager.getInstance()
                        .getString(ConfigurationManager.SQL_RESERVATION_INFO_COUNT_RESERVATIONS_INFO))) {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getLong("total");
            }
        } catch (SQLException e) {
            log.error("SQLReservationInfoDAO findRowCount error", e);
            throw new DaoException("Failed to find count of reservations info", e);
        }
        throw new DaoException("Failed to count reservations info");
    }

    @Override
    public List<ReservationInfo> findByReservationId(Long id) {
        log.debug("Accessing the database using the \"findByReservationId\" command. Time = {}", new Date());
        List<ReservationInfo> reservationsInfo = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(
                ConfigurationManager.getInstance()
                        .getString(ConfigurationManager.SQL_RESERVATION_INFO_FIND_BY_RESERVATION_ID))) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reservationsInfo.add(processReservationInfo(result));
            }
        } catch (SQLException e) {
            log.error("SQLReservationInfoDAO findByReservationId", e);
            throw new DaoException("Error findByReservationId");
        }
        return reservationsInfo;
    }

    /**
     * Method fills the ReservationInfo object with data from the database
     *
     * @param result resulting query
     * @return ReservationInfo object
     */
    private ReservationInfo processReservationInfo(ResultSet result) throws SQLException {
        ReservationInfo reservationInfo = new ReservationInfo();
        reservationInfo.setId(result.getLong("id"));
        reservationInfo.setReservationId(result.getLong("reservation_id"));
        Room room = roomDao.findById(result.getLong("room_id"));
        reservationInfo.setRoom(room);
        LocalDate checkIn = LocalDate.parse(result.getString("check_in"));
        LocalDate checkOut = LocalDate.parse(result.getString("check_out"));
        reservationInfo.setCheckIn(checkIn);
        reservationInfo.setCheckOut(checkOut);
        reservationInfo.setNights(ChronoUnit.DAYS.between(checkIn, checkOut));
        reservationInfo.setRoomPrice(result.getBigDecimal("room_price"));
        return reservationInfo;
    }

    /**
     * Method extracts the object's fields data
     *
     * @param reservationInfo object ReservationInfo
     * @param statement       an object that represents a precompiled SQL statement
     */
    private void extractedDate(ReservationInfo reservationInfo, PreparedStatement statement) throws SQLException {
        statement.setLong(1, reservationInfo.getReservationId());
        statement.setLong(2, reservationInfo.getRoom().getId());
        statement.setDate(3, java.sql.Date.valueOf(reservationInfo.getCheckIn()));
        statement.setDate(4, java.sql.Date.valueOf(reservationInfo.getCheckOut()));
        statement.setLong(5, reservationInfo.getNights());
        statement.setBigDecimal(6, reservationInfo.getRoomPrice());
    }
}