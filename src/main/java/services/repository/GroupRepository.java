package services.repository;

import models.Group;

import java.sql.Connection;
import java.util.Collection;

public interface GroupRepository {
    Collection<Group> findGroups(Connection connection, int userId);
    Group findGroup(String name, int userId, Connection connection);
    boolean addGroup(int userId, Group group, Connection connection);
    void deleteGroup(String name, int userId, Connection connection);
    boolean updateGroup(Group group, int userId, String value, Connection connection);
}
