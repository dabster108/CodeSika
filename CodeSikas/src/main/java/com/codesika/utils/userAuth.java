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
            System.err.println("Error registering user in database.");
            e.printStackTrace();
        }

        return false;
    }

    public boolean verifySecurityQuestions(String username, String favLang, String favClass) {
        String verifySQL = "SELECT COUNT(*) FROM users WHERE username = ? AND programming_language = ? AND fav_class = ?";

        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(verifySQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, favLang);
            preparedStatement.setString(3, favClass);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(String username, String newPassword) {
        String updateSQL = "UPDATE users SET pass = ? WHERE username = ?";

        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean verifyPassword(String username, String password) {
        String verifyPasswordSQL = "SELECT pass FROM users WHERE username = ?";

        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(verifyPasswordSQL)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("pass");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}