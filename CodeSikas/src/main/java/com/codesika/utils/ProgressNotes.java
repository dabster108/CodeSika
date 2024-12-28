package com.codesika.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProgressNotes extends JPanel {

    private JLabel pythonProgressLabel;
    private JLabel javaProgressLabel;

    public ProgressNotes(JLabel pythonProgressLabel, JLabel javaProgressLabel) {
        this.pythonProgressLabel = pythonProgressLabel;
        this.javaProgressLabel = javaProgressLabel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0)); // Same background color as home page
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        setPreferredSize(new Dimension(400, 300)); // Set preferred size for the box

        // Add title
        JLabel titleLabel = new JLabel("Course Progression");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // Add spacing
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Add progress labels
        pythonProgressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        pythonProgressLabel.setForeground(Color.WHITE);
        pythonProgressLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(pythonProgressLabel);

        // Add spacing
        add(Box.createRigidArea(new Dimension(0, 20)));

        javaProgressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        javaProgressLabel.setForeground(Color.WHITE);
        javaProgressLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(javaProgressLabel);
    }
}