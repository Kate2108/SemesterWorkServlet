package services;

import models.User;
import repositories.UsersRepository;

import java.sql.Connection;
import java.util.Collection;

public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findUserEmail(String email, Connection connection){
        return usersRepository.findUser(email, connection);
    }

    public User findUserId(int id, Connection connection){
        return usersRepository.findUser(id, connection);
    }

    public Collection<User> getUsers(Connection connection){
        return usersRepository.getUsers(connection);
    }

    public void deleteUser(int id, Connection connection){
        usersRepository.deleteUser(id, connection);
    }

    public void addUser(User user, Connection connection){
        usersRepository.addUser(user, connection);
    }

    public void updateUserCountry(int id, String country, Connection connection){
        usersRepository.updateUserCountry(id, country, connection);
    }

    public void updateUserStat(int id, String stat, String value, Connection connection){
        usersRepository.updateUserStat(id, stat, value, connection);
    }

    public boolean checkUserPassword(User user, String password){
        return  usersRepository.checkUser(user, password);
    }
}
