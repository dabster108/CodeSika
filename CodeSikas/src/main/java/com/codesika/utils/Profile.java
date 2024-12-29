package com.codesika.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Profile extends JPanel {

    private Userdetail userDetail;

    public Profile(String username) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK); // Dark background

        // Fetch user details
        userDetail = new Userdetail();
        if (userDetail.fetchUserDetails(username)) {
            // Title Label
            JLabel titleLabel = new JLabel("Profile Page", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            add(titleLabel, BorderLayout.NORTH);

            // User Details Panel
            JPanel userDetailsPanel = new JPanel();
            userDetailsPanel.setLayout(null);
            userDetailsPanel.setBackground(new Color(45, 45, 45));
            userDetailsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            userDetailsPanel.setPreferredSize(new Dimension(800, 400));
            userDetailsPanel.setBounds(50, 100, 800, 400);

            // User Credentials Label
            JLabel credentialsLabel = new JLabel("User Credentials");
            credentialsLabel.setFont(new Font("Poppins", Font.BOLD, 24));
            credentialsLabel.setForeground(Color.WHITE);
            credentialsLabel.setBounds(20, 20, 200, 30);
            userDetailsPanel.add(credentialsLabel);

            // Full Name Label
            JLabel fullNameLabel = new JLabel("Full Name: " + userDetail.getFullname());
            fullNameLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            fullNameLabel.setForeground(Color.WHITE);
            fullNameLabel.setBounds(20, 60, 360, 30);
            userDetailsPanel.add(fullNameLabel);

            // Username Label
            JLabel usernameLabel = new JLabel("Username: " + userDetail.getUsername());
            usernameLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setBounds(20, 100, 360, 30);
            userDetailsPanel.add(usernameLabel);

            // Email Label
            JLabel emailLabel = new JLabel("Email: " + userDetail.getEmail());
            emailLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            emailLabel.setForeground(Color.WHITE);
            emailLabel.setBounds(20, 140, 360, 30);
            userDetailsPanel.add(emailLabel);

            // Password Label
            JLabel passwordLabel = new JLabel("Password: " + userDetail.getPassword());
            passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            passwordLabel.setForeground(Color.WHITE);
            passwordLabel.setBounds(20, 180, 360, 30);
            userDetailsPanel.add(passwordLabel);

            // Programming Language Label
            JLabel programmingLanguageLabel = new JLabel("Favorite Programming Language: " + userDetail.getProgrammingLanguage());
            programmingLanguageLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            programmingLanguageLabel.setForeground(Color.WHITE);
            programmingLanguageLabel.setBounds(20, 220, 360, 30);
            userDetailsPanel.add(programmingLanguageLabel);

            // Favorite Class Label
            JLabel favClassLabel = new JLabel("Favorite Class: " + userDetail.getFavClass());
            favClassLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            favClassLabel.setForeground(Color.WHITE);
            favClassLabel.setBounds(20, 260, 360, 30);
            userDetailsPanel.add(favClassLabel);

            // Vertical Line
            JPanel verticalLine = new JPanel();
            verticalLine.setBackground(Color.WHITE);
            verticalLine.setBounds(400, 20, 2, 800);
            userDetailsPanel.add(verticalLine);

            // Notes Panel
            JPanel notesPanel = new JPanel(new GridLayout(2, 2, 30, 30));
            notesPanel.setBackground(new Color(45, 45, 45));
            notesPanel.setBounds(420, 20, 800, 800);
            userDetailsPanel.add(notesPanel);

            // Add Notes Sections
            addNotesSection(notesPanel, "Python", "C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\python_notes.txt", "C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\python.png");
            addNotesSection(notesPanel, "Java", "C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\java_notes.txt", "C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\java.png");
            addNotesSection(notesPanel, "C++", "C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\cpp_notes.txt", "C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\c-.png");
            addNotesSection(notesPanel, "JavaScript", "C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\javascript_notes.txt", "C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\java-script.png");

            // Quiz Remarks Panel
            JPanel quizRemarksPanel = new JPanel();
            quizRemarksPanel.setLayout(new BorderLayout());
            quizRemarksPanel.setBackground(new Color(45, 45, 45));
            quizRemarksPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            quizRemarksPanel.setBounds(1240, 20, 400, 800);
            userDetailsPanel.add(quizRemarksPanel);

            JLabel quizRemarksLabel = new JLabel("Quiz Remarks", SwingConstants.CENTER);
            quizRemarksLabel.setFont(new Font("Poppins", Font.BOLD, 24));
            quizRemarksLabel.setForeground(Color.WHITE);
            quizRemarksPanel.add(quizRemarksLabel, BorderLayout.NORTH);

            JPanel quizPanel = new JPanel(new GridLayout(4, 1, 10, 10));
            quizPanel.setBackground(new Color(45, 45, 45));
            quizRemarksPanel.add(quizPanel, BorderLayout.CENTER);

            addQuizSection(quizPanel, "Python Quiz");
            addQuizSection(quizPanel, "Java Quiz");
            addQuizSection(quizPanel, "C++ Quiz");
            addQuizSection(quizPanel, "JavaScript Quiz");

            add(userDetailsPanel, BorderLayout.CENTER);
        } else {
            JLabel errorLabel = new JLabel("Notes Available Soon.", SwingConstants.CENTER);
            errorLabel.setFont(new Font("Arial", Font.BOLD, 24));
            errorLabel.setForeground(Color.RED);
            add(errorLabel, BorderLayout.CENTER);
        }
    }

    private void addNotesSection(JPanel notesPanel, String courseName, String notesFilePath, String imagePath) {
        JPanel coursePanel = new JPanel(new BorderLayout());
        coursePanel.setBackground(new Color(45, 45, 45));
        coursePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        coursePanel.setPreferredSize(new Dimension(360, 180));

        JLabel courseLabel = new JLabel(courseName + " Notes", SwingConstants.CENTER);
        courseLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        courseLabel.setForeground(Color.WHITE);
        coursePanel.add(courseLabel, BorderLayout.NORTH);

        // Horizontal Line
        JPanel horizontalLine = new JPanel();
        horizontalLine.setBackground(Color.WHITE);
        horizontalLine.setPreferredSize(new Dimension(coursePanel.getWidth(), 2));
        coursePanel.add(horizontalLine, BorderLayout.NORTH);

        JLabel courseImage = new JLabel(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH)), SwingConstants.CENTER);
        coursePanel.add(courseImage, BorderLayout.CENTER);

        JTextArea notesArea = new JTextArea();
        notesArea.setFont(new Font("Poppins", Font.PLAIN, 16));
        notesArea.setForeground(Color.WHITE);
        notesArea.setBackground(new Color(45, 45, 45));
        notesArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        notesArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(notesArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        coursePanel.add(scrollPane, BorderLayout.SOUTH);

        loadNotesFromFile(notesFilePath, notesArea);

        notesPanel.add(coursePanel);
    }

    private void addQuizSection(JPanel quizPanel, String quizName) {
        JPanel quizBox = new JPanel(new BorderLayout());
        quizBox.setBackground(new Color(45, 45, 45));
        quizBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        quizBox.setPreferredSize(new Dimension(360, 180));

        JLabel quizLabel = new JLabel(quizName, SwingConstants.CENTER);
        quizLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        quizLabel.setForeground(Color.WHITE);
        quizBox.add(quizLabel, BorderLayout.CENTER);

        quizPanel.add(quizBox);
    }

    private void loadNotesFromFile(String filePath, JTextArea notesArea) {
        StringBuilder notesContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notesContent.append(line).append("\n");
            }
        } catch (IOException e) {
            notesContent.append("No notes available.\n");
        }
        notesArea.setText(notesContent.toString());
    }
}