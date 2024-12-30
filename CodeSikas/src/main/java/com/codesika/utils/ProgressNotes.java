package com.codesika.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressNotes extends JPanel {

    private JLabel pythonProgressLabel;
    private JLabel javaProgressLabel;
    private JProgressBar pythonProgressBar;
    private JProgressBar javaProgressBar;

    public ProgressNotes(JLabel pythonProgressLabel, JLabel javaProgressLabel) {
        this.pythonProgressLabel = pythonProgressLabel;
        this.javaProgressLabel = javaProgressLabel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#F9F7E7")); // Same background color as home page
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setPreferredSize(new Dimension(400, 300)); // Set preferred size for the box

        // Add title
        JLabel titleLabel = new JLabel("Course Progression");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // Add spacing
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Add progress labels and bars
        pythonProgressLabel.setFont(new Font("Inter", Font.PLAIN, 20));
        pythonProgressLabel.setForeground(Color.BLACK);
        pythonProgressLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(pythonProgressLabel);

        javaProgressLabel.setFont(new Font("Inter", Font.PLAIN, 20));
        javaProgressLabel.setForeground(Color.BLACK);
        javaProgressLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(javaProgressLabel);
    }

    // Method to update Python progress
    
}