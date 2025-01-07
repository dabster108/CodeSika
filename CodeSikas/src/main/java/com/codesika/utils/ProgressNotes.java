package com.codesika.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class ProgressNotes extends JPanel {

    private JLabel pythonProgressLabel;
    private JLabel javaProgressLabel;
    private JLabel cppProgressLabel;
    private JLabel jsProgressLabel;

    public ProgressNotes(JLabel pythonProgressLabel, JLabel javaProgressLabel, JLabel cppProgressLabel, JLabel jsProgressLabel) {
        this.pythonProgressLabel = pythonProgressLabel;
        this.javaProgressLabel = javaProgressLabel;
        this.cppProgressLabel = cppProgressLabel;
        this.jsProgressLabel = jsProgressLabel;

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

        // Add progress labels and separators
        addProgressLabelWithSeparator(pythonProgressLabel);
        addProgressLabelWithSeparator(javaProgressLabel);
        addProgressLabelWithSeparator(cppProgressLabel);
        addProgressLabelWithSeparator(jsProgressLabel);
    }

    private void addProgressLabelWithSeparator(JLabel progressLabel) {
        progressLabel.setFont(new Font("Inter", Font.PLAIN, 20));
        progressLabel.setForeground(Color.BLACK);
        progressLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(progressLabel);

        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2)); // Make the separator a bit bolder
        separator.setForeground(Color.BLACK); // Set the separator color to black
        add(separator);

        // Add spacing after the separator
        add(Box.createRigidArea(new Dimension(0, 10)));
    }
}