package repositories;

import models.User;

import java.sql.Connection;
import java.util.Collection;

public interface UsersRepository {
    User findUser(String email, Connection connection);
    User findUser(int id, Connection connection);
    boolean checkUser(User user, String password);
    void addUser(User user, Connection connection);
    void deleteUser(int id, Connection connection);
    void updateUserCountry(int id, String status, Connection connection);
    void updateUserStat(int id, String stat, String value, Connection connection);
    void updateUserGroup(int id, int count, Connection connection);
    Collection<User> getUsers(Connection connection);
}
