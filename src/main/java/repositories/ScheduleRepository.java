package repositories;

import models.Schedule;

import java.sql.Connection;
import java.util.Collection;

public interface ScheduleRepository {
    Collection<Schedule> getUserSchedule(Connection connection, int userId);
    void deleteUserSchedule(Connection connection, int userId, Schedule schedule);
    void deleteUserAllSchedule(Connection connection, int userId, String group);
    boolean addUserSchedule(Connection connection, int userId, Schedule schedule);
}
