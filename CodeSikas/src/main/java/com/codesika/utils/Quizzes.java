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
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.decode("#BBD2D1")); // Match background color
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
    }

    public JPanel createQuizPanel(String courseName) {
        JPanel quizPanel = new JPanel(new BorderLayout());
        quizPanel.setBackground(Color.decode("#BBD2D1")); // Match background color
        quizPanel.setPreferredSize(new Dimension(800, 60)); // Reduced height

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

        startQuizButton.addActionListener(e -> {
            JPanel quizContentPanel = createQuizContentPanel(courseName);
            contentPanel.add(quizContentPanel, courseName + " Quiz");
            cardLayout.show(contentPanel, courseName + " Quiz");
        });

        quizPanel.add(startQuizButton, BorderLayout.CENTER);

        return quizPanel;
    }

    private JPanel createQuizContentPanel(String courseName) {
        JPanel quizContentPanel = new JPanel(new BorderLayout());
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

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(quizContentPanel.getBackground()); // Match background color
        titlePanel.add(quizTitleLabel, BorderLayout.CENTER);

        quizContentPanel.add(titlePanel, BorderLayout.NORTH);

        return quizContentPanel;
    }
}