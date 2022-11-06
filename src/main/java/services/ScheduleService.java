package services;

import models.Schedule;
import services.repository.ScheduleRepository;

import java.sql.Connection;
import java.util.Collection;

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Collection<Schedule> getUserSchedule(Connection connection, int userId){
        return scheduleRepository.getUserSchedule(connection, userId);
    }

    public void deleteUserSchedule(Connection connection, int userId, Schedule schedule){
        scheduleRepository.deleteUserSchedule(connection, userId, schedule);
    }

    public void deleteUserAllSchedule(Connection connection, int userId, String group){
        scheduleRepository.deleteUserAllSchedule(connection, userId, group);
    }

    public boolean addUserSchedule(Connection connection, int userId, Schedule schedule){
        return scheduleRepository.addUserSchedule(connection, userId,schedule);
    }
}
