package services.repository.impl;

import models.Achievement;
import services.repository.AchievementRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AchievementRepositoryImpl implements AchievementRepository {
    String SQL_SELECT_USERS_ACH = "select a.name, a.description, ua.value, a.rvalue from achievements as a join user_achievements as ua on userid = ? and a.id = ua.achId";
    String SQL_SELECT_ALL_ACH = "select * from achievements";
    String SQL_ADD_START_ACH = "insert into user_achievements(userid, achid) values(?,?)";

    private Collection<Achievement> getAllAchievements(Connection connection){
        try (PreparedStatement statement =  connection.prepareStatement(SQL_SELECT_ALL_ACH)){
            ArrayList<Achievement> list = new ArrayList();
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                list.add(Achievement.builder()
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .requiredValue(resultSet.getInt("rvalue"))
                        .build());
            }
            return list;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void updateUserAchValue(Connection connection, int userId, int achId, int newValue){
        String sql = "update user_achievements set value = ? where userid = ? and achid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, newValue);
            statement.setInt(2, userId);
            statement.setInt(3, achId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addUserAchievements(Connection connection, int userId){
        if (checkAchUser(connection, userId)){
            return;
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_START_ACH)){
            int countOfAch = getAllAchievements(connection).size();
            for(int i = 1; i <= countOfAch; i++){
                statement.setInt(1, userId);
                statement.setInt(2, i);
                statement.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateAch(Connection connection, int userId, int achId){
        int temp = checkValues(connection,userId,achId);
        if(temp == -1){
            return;
        }
        temp++;
        updateUserAchValue(connection,userId, achId, temp);
    }

    private int checkValues(Connection connection, int userId, int achId){
        String sql1 = "select rvalue from achievements where id = ?";
        String sql2 = "select value from user_achievements where userid = ? and achid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql1);
             PreparedStatement statement1 = connection.prepareStatement(sql2)){
            statement.setInt(1, achId);
            ResultSet rs1 = statement.executeQuery();
            rs1.next();
            int value1 = rs1.getInt("rvalue");
            statement1.setInt(1, userId);
            statement1.setInt(2, achId);
            ResultSet rs2 = statement1.executeQuery();
            rs2.next();
            int value2 = rs2.getInt("value");
            if(value1 == value2){
                return -1;
            }
            return value2;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private boolean checkAchUser(Connection connection, int userId){
        String sql = "select * from user_achievements where userid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Collection<Achievement> getUserAchievements(Connection connection, int userId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_ACH)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Achievement> list = new ArrayList();
            while(resultSet.next()){
                list.add(Achievement.builder()
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .currentValue(resultSet.getInt("value"))
                        .requiredValue(resultSet.getInt("rvalue"))
                        .build());
            }
            return list;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
