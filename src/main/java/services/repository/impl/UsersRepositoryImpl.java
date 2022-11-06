package services.repository.impl;

import models.User;
import services.hashing.MakeHashPassword;
import services.repository.UsersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UsersRepositoryImpl implements UsersRepository {

    //language SQL
    private final String SQL_SELECT_USER_BY_EMAIL = "select * from users where email = ?";
    //language SQL
    private final String SQL_SELECT_USER_BY_ID = "select * from users where id = ?";
    //language SQL
    private final String SQL_INSERT_USER = "insert into users (email,username,password,gender,country,sport)  values (?,?,?,?,?,?)";
    //language SQL
    private final String SQL_DELETE_USER = "delete from users where id = ?";
    //language SQL
    private final String SQL_UPDATE_USER_COUNTRY = "update users set country = ? where id = ?";
    //language SQL
    private final String SQL_UPDATE_USER_GROUP = "update users set countofgroups = ? where id = ?";
    //language SQL
    private final String SQL_SELECT_USER = "select * from users";

    private MakeHashPassword hash = new MakeHashPassword();

    @Override
    public User findUser(String email, Connection connection) {
        try (PreparedStatement statement =  connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL)){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .gender(resultSet.getString("gender"))
                        .country(resultSet.getString("country"))
                        .status(resultSet.getString("status"))
                        .build();
            }
            return null;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Override
    public User findUser(int id, Connection connection) {
        try (PreparedStatement statement =  connection.prepareStatement(SQL_SELECT_USER_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .gender(resultSet.getString("gender"))
                        .country(resultSet.getString("country"))
                        .sport(resultSet.getString("sport"))
                        .category(resultSet.getString("category"))
                        .aClass(resultSet.getString("class"))
                        .exp(resultSet.getInt("workexp"))
                        .countOfGroups(resultSet.getInt("countofgroups"))
                        .status(resultSet.getString("status"))
                        .build();
            }
            return null;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public boolean checkUser(User user, String password) {
        String dbPassword = user.getPassword();
        return dbPassword.equals(hash.makeSecurePassword(password));
    }
    @Override
    public void addUser(User user, Connection connection) {
        try (PreparedStatement statement =  connection.prepareStatement(SQL_INSERT_USER)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, hash.makeSecurePassword(user.getPassword()));
            statement.setString(4, user.getGender());
            statement.setString(5, user.getCountry());
            statement.setString(6, user.getSport());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Override
    public void deleteUser(int id, Connection connection) {
        try (PreparedStatement statement =  connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Override
    public void updateUserCountry(int id, String country, Connection connection) {
        try (PreparedStatement statement =  connection.prepareStatement(SQL_UPDATE_USER_COUNTRY)) {
            statement.setString(1, country);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Override
    public void updateUserGroup(int id, int count, Connection connection) {
        try (PreparedStatement statement =  connection.prepareStatement(SQL_UPDATE_USER_GROUP)) {
            statement.setInt(1, count);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Override
    public void updateUserStat(int id, String stat, String value, Connection connection) {
        String sql = "update users set " + stat + " = ? where id = ?";
        try (PreparedStatement statement =  connection.prepareStatement(sql)) {
            if(stat.equals("workexp")){
                statement.setInt(1, Integer.parseInt(value));
                statement.setInt(2, id);
            }
            else {
                statement.setString(1, value);
                statement.setInt(2, id);
            }
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Override
    public Collection<User> getUsers(Connection connection){
        try (PreparedStatement statement =  connection.prepareStatement(SQL_SELECT_USER)){
            ArrayList<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(User.builder()
                        .id(resultSet.getInt("id"))
                        .email(resultSet.getString("email"))
                        .country(resultSet.getString("country"))
                        .build());
            }
            return users;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
