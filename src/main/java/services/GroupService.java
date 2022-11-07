package services;

import models.Group;
import repositories.GroupRepository;

import java.sql.Connection;
import java.util.Collection;

public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Collection<Group> findGroups(Connection connection, int userId){
        return groupRepository.findGroups(connection, userId);
    }

    public Group findGroup(String name, int userId, Connection connection){
        return groupRepository.findGroup(name, userId, connection);
    }

    public boolean addGroup(int userId, Group group, Connection connection){
        return groupRepository.addGroup(userId, group, connection);
    }

    public void deleteGroup(String name, int userId, Connection connection){
        groupRepository.deleteGroup(name, userId, connection);
    }

    public boolean updateGroup(Group group, int userId, String value, Connection connection){
        return groupRepository.updateGroup(group, userId, value, connection);
    }
}
