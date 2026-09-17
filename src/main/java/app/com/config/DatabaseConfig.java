package app.com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    //Connection configuration details
    private static final String Url = "jdbc:postgresql://localhost:5432/TMS";
    private static final String Username = "postgres";
    private static final String password = "abdulbar";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Url, Username, password );
    }
}