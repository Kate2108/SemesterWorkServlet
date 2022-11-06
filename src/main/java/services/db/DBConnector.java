package services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private final String DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/semester-work";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "Katelukk2003";
    private Connection connection;

    public Connection getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
        return connection;
    }
}
