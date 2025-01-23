package com.codesika.utils;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Quizzes extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public Quizzes() {
        // Initialize the layout and content panel
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.decode("#BBD2D1")); // Match background color

        // Set the layout for the main panel
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER); // Add the content panel to the main panel
    }

    // Method to create a quiz panel for a specific course
    public JPanel createQuizPanel(String courseName) {
        JPanel quizPanel = new JPanel(new BorderLayout());
        quizPanel.setBackground(Color.decode("#BBD2D1")); // Match background color
        quizPanel.setPreferredSize(new Dimension(800, 60)); // Reduced height

        // Create the "Start Quiz" button
        JButton startQuizButton = new JButton("Start " + courseName + " Quiz");
        startQuizButton.setFont(new Font("Inter", Font.PLAIN, 20));
        startQuizButton.setForeground(Color.WHITE);
        startQuizButton.setBackground(Color.decode("#2C4B72"));
        startQuizButton.setFocusPainted(false);
        startQuizButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startQuizButton.setContentAreaFilled(false);
        startQuizButton.setOpaque(true);
        startQuizButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#2C4B72"), 2, true),
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding for the text
        ));

        // Add action listener to the "Start Quiz" button
        startQuizButton.addActionListener(e -> {
            // Create the quiz content panel for the selected course
            JPanel quizContentPanel = createQuizContentPanel(courseName);

            // Add the quiz content panel to the content panel with a unique name
            contentPanel.add(quizContentPanel, courseName + " Quiz");

            // Show the quiz content panel
            cardLayout.show(contentPanel, courseName + " Quiz");
        });

        // Add the button to the quiz panel
        quizPanel.add(startQuizButton, BorderLayout.CENTER);

        return quizPanel;
    }

    // Method to create the quiz content panel for a specific course
    private JPanel createQuizContentPanel(String courseName) {
        JPanel quizContentPanel = new JPanel(new BorderLayout());

        // Set background color based on the course
        if (courseName.equalsIgnoreCase("Python")) {
            quizContentPanel.setBackground(Color.BLACK); // Black background for Python
        } else if (courseName.equalsIgnoreCase("Java")) {
            quizContentPanel.setBackground(Color.WHITE); // White background for Java
        } else {
            quizContentPanel.setBackground(Color.decode("#BBD2D1")); // Default background color
        }

        // Quiz title
        JLabel quizTitleLabel = new JLabel(courseName + " Quiz", SwingConstants.CENTER);
        quizTitleLabel.setFont(new Font("Inter", Font.BOLD, 32));
        quizTitleLabel.setForeground(courseName.equalsIgnoreCase("Python") ? Color.WHITE : Color.BLACK);

        // Add the title to a panel (to center it)
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(quizContentPanel.getBackground()); // Match background color
        titlePanel.add(quizTitleLabel, BorderLayout.CENTER);

        // Add the title panel to the quiz content panel
        quizContentPanel.add(titlePanel, BorderLayout.NORTH);

        // Add quiz questions or other content here
        // For now, let's add a placeholder label
        JLabel placeholderLabel = new JLabel("Quiz questions will go here!", SwingConstants.CENTER);
        placeholderLabel.setFont(new Font("Inter", Font.PLAIN, 24));
        placeholderLabel.setForeground(courseName.equalsIgnoreCase("Python") ? Color.WHITE : Color.BLACK);
        quizContentPanel.add(placeholderLabel, BorderLayout.CENTER);

        return quizContentPanel;
    }
}