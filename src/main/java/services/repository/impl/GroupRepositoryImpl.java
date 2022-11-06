package services.repository.impl;

import models.Group;
import services.repository.GroupRepository;
import services.repository.UsersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class GroupRepositoryImpl implements GroupRepository {

    //language SQL
    private final String FIND_ALL_GROUPS = "select * from groups where userid = ?";
    //language SQL
    private final String SQL_INSERT_GROUP = "insert into groups (name, count, userid) values (?,?,?)";
    //language SQL
    private final String SQL_DELETE_GROUP = "delete from groups where name = ? and userid = ?";
    //language SQL
    private final String SQL_SELECT_GROUP_BY_NAME = "select * from groups where name = ? and userid = ?";
    //language SQL
    private final String SQL_UPDATE_GROUP = "update groups set count = ? where name = ? and userid = ?";

    private final UsersRepository usersRep = new UsersRepositoryImpl();
    private final ScheduleRepositoryImpl scheduleRepository = new ScheduleRepositoryImpl();

    public Collection<Group> findGroups(Connection connection, int userId){
        try (PreparedStatement statement =  connection.prepareStatement(FIND_ALL_GROUPS)){
            statement.setInt(1, userId);
            ArrayList<Group> list = new ArrayList();
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                list.add(Group.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .countOfMembers(resultSet.getInt("count"))
                        .build());
            }
            return list;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public Group findGroup(String name, int userId, Connection connection){
        try (PreparedStatement statement =  connection.prepareStatement(SQL_SELECT_GROUP_BY_NAME)){
            statement.setString(1, name);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Group.builder().build();
            }
            return null;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public boolean addGroup(int userId, Group group, Connection connection){
        if (findGroup(group.getName(), userId, connection) != null){
            return false;
        }
        try (PreparedStatement statement =  connection.prepareStatement(SQL_INSERT_GROUP)) {

            statement.setString(1, group.getName());
            statement.setInt(2, group.getCountOfMembers());
            statement.setInt(3, userId);

            statement.executeUpdate();

            Collection<Group> groups = findGroups(connection,userId);
            int len = groups.size();
            usersRep.updateUserGroup(userId, len, connection);
            return true;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void deleteGroup(String name, int userId, Connection connection){
        try (PreparedStatement statement =  connection.prepareStatement(SQL_DELETE_GROUP)) {
            statement.setString(1, name);
            statement.setInt(2, userId);
            statement.executeUpdate();
            Collection<Group> groups = findGroups(connection,userId);
            int len = groups.size();
            usersRep.updateUserGroup(userId, len, connection);
            scheduleRepository.deleteUserAllSchedule(connection, userId, name);
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public boolean updateGroup(Group group, int userId, String value, Connection connection){
        if (findGroup(group.getName(), userId, connection) == null){
            return false;
        }
        try (PreparedStatement statement =  connection.prepareStatement(SQL_UPDATE_GROUP)) {
            statement.setInt(1, Integer.parseInt(value));
            statement.setString(2, group.getName());
            statement.setInt(3, userId);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
