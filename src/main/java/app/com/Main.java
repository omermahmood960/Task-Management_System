package app.com;

import app.com.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hye-----");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JAR is NOT on the classpath at runtime!");
            e.printStackTrace();
        }

        String sql = "Insert into users (name, email, password) values (?,?,?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
//            preparedStatement.setString(1, "Rohit Shetty");
//            preparedStatement.setString(2, "rohit12@yahoo.com");
//            preparedStatement.setString(3, "ashfjgh12$");
//            preparedStatement.executeUpdate();
            System.out.println("Java application connected successfully to postgresql");
        } catch (SQLException exception) {
            System.out.println("error while inserting records" + exception);
        }
    }
}