package services.repository.impl;


import models.Schedule;
import services.repository.ScheduleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class ScheduleRepositoryImpl implements ScheduleRepository {
    //language SQL
    private final String SQL_SELECT_USER_SCHEDULE = "select d.name, sch.hourStart, sch.minuteStart, sch.hourEnd, sch.minuteEnd, sch.workGroup from user_schedule as sch join days as d on sch.day = d.id where userId = ? order by sch.day, sch.hourStart, sch.minuteStart";
    //language SQL
    private final String SQL_INSERT_USER_SCHEDULE = "insert into user_schedule(userid, day, hourstart, minutestart, hourend, minuteend, workgroup) values (?,?,?,?,?,?,?)";
    //language SQL
    private final String SQL_DELETE_TRAINING = "delete from user_schedule where userid = ? and day = ? and workgroup = ?";
    //language SQL
    private final String SQL_DELETE_SCHEDULE = "delete from user_schedule where userid = ? and workgroup = ?";

    public Collection<Schedule> getUserSchedule(Connection connection, int userId){
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_SCHEDULE)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Schedule> list = new ArrayList();
            while(resultSet.next()){
                list.add(Schedule.builder()
                        .day(resultSet.getString("name"))
                        .hourStart(resultSet.getInt("hourstart"))
                        .minuteStart(resultSet.getInt("minutestart"))
                        .hourEnd(resultSet.getInt("hourend"))
                        .minuteEnd(resultSet.getInt("minuteend"))
                        .group(resultSet.getString("workgroup"))
                        .build());
            }
            return list;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void deleteUserSchedule(Connection connection, int userId, Schedule schedule){
        try (PreparedStatement statement =  connection.prepareStatement(SQL_DELETE_TRAINING)) {
            statement.setInt(1, userId);
            statement.setInt(2, getDayId(connection, schedule.getDay()));
            statement.setString(3, schedule.getGroup());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void deleteUserAllSchedule(Connection connection, int userId, String group){
        try (PreparedStatement statement =  connection.prepareStatement(SQL_DELETE_SCHEDULE)) {
            statement.setInt(1, userId);
            statement.setString(2, group);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public boolean addUserSchedule(Connection connection, int userId, Schedule schedule){
        int day = getDayId(connection, schedule.getDay());
        if (getUserTrainingTime(connection, userId, schedule.getHourStart(), schedule.getMinuteStart(), day) != null
                || getUserTrainingGroup(connection, userId, day, schedule.getGroup()) != null){
            return false;
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER_SCHEDULE)){
            statement.setInt(1, userId);
            statement.setInt(2, day);
            statement.setInt(3, schedule.getHourStart());
            statement.setInt(4, schedule.getMinuteStart());
            statement.setInt(5, schedule.getHourEnd());
            statement.setInt(6, schedule.getMinuteEnd());
            statement.setString(7, schedule.getGroup());

            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private int getDayId(Connection connection, String day){
        String sql = "select id from days where name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, day);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private Schedule getUserTrainingTime(Connection connection, int userId, int hs, int ms, int day){
        String sql = "select * from user_schedule where userid = ? and hourstart = ? and minutestart = ? and day = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, userId);
            statement.setInt(2, hs);
            statement.setInt(3, ms);
            statement.setInt(4, day);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Schedule.builder().build();
            }
        return null;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private Schedule getUserTrainingGroup(Connection connection, int userId, int day, String group){
        String sql = "select * from user_schedule where userid = ? and day = ? and workgroup = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, userId);
            statement.setInt(2, day);
            statement.setString(3, group);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Schedule.builder().build();
            }
            return null;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
