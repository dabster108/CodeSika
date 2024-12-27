package com.codesika.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.DatabaseHelper;

public class userAuth {

    public boolean isUserExists(String username) {
        String checkUserSQL = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(checkUserSQL)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error checking user existence in database.");
            e.printStackTrace();
        }

        return false;
    }

    public boolean registerUser(String username, String fullname, String email, String password, String programmingLanguage, String favClass) {
        if (isUserExists(username)) {
            return false; // User already exists
        }

        String insertUserSQL = "INSERT INTO users (username, fullname, email, pass, programming_language, fav_class) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, fullname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, programmingLanguage);
            preparedStatement.setString(6, favClass);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting user into database.");
            e.printStackTrace();
            return false;
        }
    }
}