package com.codesika.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class AboutPage extends JPanel {

    public AboutPage() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0)); // Same background color as home page

        // Title Label
        JLabel aboutLabel = new JLabel("About Page", SwingConstants.CENTER);
        aboutLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        aboutLabel.setForeground(Color.WHITE);
        add(aboutLabel, BorderLayout.NORTH);

        // Border Panel
        JPanel borderPanel = new JPanel();
        borderPanel.setBackground(new Color(0, 0, 0)); // Same background color as home page
        borderPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        borderPanel.setLayout(new BorderLayout());
        add(borderPanel, BorderLayout.CENTER);

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(0, 0, 0)); // Same background color as home page
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        borderPanel.add(contentPanel, BorderLayout.CENTER);

        // Description Text
        JTextArea descriptionArea = new JTextArea(
            "CodeSika is a platform designed to help you learn coding and enhance your programming knowledge.\n" +
            "Dive into a world of learning with interactive study materials, engaging quizzes, and hands-on projects.\n" +
            "Build your skills and become a coding expert with Sika!"
        );
        descriptionArea.setFont(new Font("Poppins", Font.PLAIN, 18));
        descriptionArea.setForeground(Color.LIGHT_GRAY);
        descriptionArea.setBackground(new Color(0, 0, 0)); // Same background color as home page
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(descriptionArea, BorderLayout.NORTH);

        // Features Text
        JTextArea featuresArea = new JTextArea(
            "What You Can Do on CodeSika:\n\n" +
            "• Learn new programming languages\n" +
            "• Track your progress with interactive modules\n" +
            "• Participate in coding challenges\n" +
            "• Build real-world projects\n" +
            "• Earn certificates and showcase your skills"
        );
        featuresArea.setFont(new Font("Poppins", Font.BOLD, 18));
        featuresArea.setForeground(Color.LIGHT_GRAY);
        featuresArea.setBackground(new Color(0, 0, 0)); // Same background color as home page
        featuresArea.setEditable(false);
        featuresArea.setWrapStyleWord(true);
        featuresArea.setLineWrap(true);
        featuresArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(featuresArea, BorderLayout.CENTER);
    }
}