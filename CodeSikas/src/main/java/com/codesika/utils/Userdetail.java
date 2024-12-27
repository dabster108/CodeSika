package com.codesika.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.DatabaseHelper;

public class Userdetail {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String programmingLanguage;
    private String favClass;

    // Getters
    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getFavClass() {
        return favClass;
    }

    // Method to fetch user details from the database
    public boolean fetchUserDetails(String username) {
        String fetchUserSQL = "SELECT username, fullname, email, pass, programming_language, fav_class FROM users WHERE username = ?";

        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSQL)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.username = resultSet.getString("username");
                this.fullname = resultSet.getString("fullname");
                this.email = resultSet.getString("email");
                this.password = resultSet.getString("pass");
                this.programmingLanguage = resultSet.getString("programming_language");
                this.favClass = resultSet.getString("fav_class");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user details from database.");
            e.printStackTrace();
        }
        return false;
    }
}