package com.codesika.utils;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;

public class CoursesPage extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JScrollPane pythonScrollPane;
    private JScrollPane javaScrollPane;
    private JLabel pythonProgressLabel;
    private JLabel javaProgressLabel;
    private JLabel pythonProgressLabelHome;
    private JLabel javaProgressLabelHome;

    public CoursesPage(CardLayout cardLayout, JPanel contentPanel, JLabel pythonProgressLabelHome, JLabel javaProgressLabelHome) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.pythonProgressLabelHome = pythonProgressLabelHome;
        this.javaProgressLabelHome = javaProgressLabelHome;

        setLayout(new BorderLayout());
        setBackground(new Color(45, 45, 45)); // Dark background

        JLabel coursesTitle = new JLabel("Courses", SwingConstants.CENTER);
        coursesTitle.setFont(new Font("Arial", Font.BOLD, 24));
        coursesTitle.setForeground(Color.WHITE); // Light text color
        coursesTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(coursesTitle, BorderLayout.NORTH);

        JPanel coursesContentPanel = new JPanel();
        coursesContentPanel.setLayout(new BoxLayout(coursesContentPanel, BoxLayout.Y_AXIS));
        coursesContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        coursesContentPanel.setBackground(new Color(45, 45, 45)); // Dark background for content panel

        // Add course panels with two-line descriptions
        coursesContentPanel.add(createCoursePanel("Python", "<html>Learn Python programming<br/>from basics to advanced topics.</html>", ""));
        coursesContentPanel.add(createCoursePanel("Java", "<html>Master Java programming<br/>with hands-on projects and exercises.</html>", ""));
        coursesContentPanel.add(createCoursePanel("C++", "<html>Explore C++ programming<br/>with in-depth tutorials and examples.</html>", ""));
        coursesContentPanel.add(createCoursePanel("JavaScript", "<html>Get started with JavaScript<br/>and build interactive web applications.</html>", ""));

        JScrollPane coursesScrollPane = new JScrollPane(coursesContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(coursesScrollPane, BorderLayout.CENTER);
    }

    // Method to create individual course panels
    private JPanel createCoursePanel(String title, String description, String iconPath) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 120)); // Fixed consistent size
        panel.setBackground(new Color(60, 60, 60)); // Dark background for course panels
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        panel.setLayout(new BorderLayout());

        // Add title
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE); // Light text color
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add description
        JLabel descriptionLabel = new JLabel(description, SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionLabel.setForeground(Color.LIGHT_GRAY); // Light gray text color
        panel.add(descriptionLabel, BorderLayout.CENTER);

        // Add "Learn More" button
        JButton learnMoreButton = new JButton("Learn More");
        learnMoreButton.setFocusPainted(false);
        learnMoreButton.setBackground(new Color(30, 144, 255)); // Button color
        learnMoreButton.setForeground(Color.WHITE); // Set text color
        learnMoreButton.setFont(new Font("Arial", Font.BOLD, 12));
        learnMoreButton.setBorderPainted(false);
        learnMoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        learnMoreButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                learnMoreButton.setBackground(new Color(0, 120, 215)); // Change color on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                learnMoreButton.setBackground(new Color(30, 144, 255)); // Reset color when not hovered
            }
        });
        learnMoreButton.addActionListener(e -> {
            if (title.equals("Python")) {
                pythonScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
                cardLayout.show(contentPanel, "Python");
                contentPanel.setBackground(Color.WHITE); // Change background to white
            } else if (title.equals("Java")) {
                javaScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
                cardLayout.show(contentPanel, "Java");
                contentPanel.setBackground(Color.WHITE); // Change background to white
            }
        });
        panel.add(learnMoreButton, BorderLayout.SOUTH);

        return panel;
    }

    // Method to create the Python course panel
    public JPanel createPythonPanel() {
        JPanel pythonPanel = new JPanel(new BorderLayout());
        pythonPanel.setBackground(Color.WHITE); // Set background to white

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setFont(new Font("Poppins", Font.PLAIN, 28)); // Increased font size
        textPane.setMargin(new Insets(10, 10, 10, 10)); // Add left padding
        textPane.setBackground(Color.WHITE); // White background for text pane
        textPane.setForeground(Color.BLACK); // Black text color

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\python.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 1; i <= 17; i++) {
                    line = line.replaceAll("\\b" + i + "\\b", "<b>" + i + "</b>");
                }
                content.append(line).append("<br>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        textPane.setText(content.toString());

        pythonScrollPane = new JScrollPane(textPane);
        pythonPanel.add(pythonScrollPane, BorderLayout.CENTER);

        // Initialize pythonProgressLabel
        pythonProgressLabel = new JLabel("Python Progression: 0%");
        pythonProgressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        pythonProgressLabel.setForeground(Color.LIGHT_GRAY); // Light gray text color
        pythonPanel.add(pythonProgressLabel, BorderLayout.NORTH);

        // Add Quiz Button
        JButton quizButton = new JButton("Start Python Quiz");
        quizButton.setFont(new Font("Arial", Font.BOLD, 16));
        quizButton.setBackground(new Color(30, 144, 255));
        quizButton.setForeground(Color.WHITE);
        quizButton.setFocusPainted(false);
        quizButton.setBorderPainted(false);
        quizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        quizButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                quizButton.setBackground(new Color(0, 120, 215)); // Change color on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                quizButton.setBackground(new Color(30, 144, 255)); // Reset color when not hovered
            }
        });
        quizButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Quiz");
        });
        pythonPanel.add(quizButton, BorderLayout.SOUTH);

        // Add scroll listener to update progress
        pythonScrollPane.getViewport().addChangeListener(e -> {
            JViewport viewport = (JViewport) e.getSource();
            int extentHeight = viewport.getExtentSize().height;
            int viewHeight = viewport.getViewSize().height;
            int viewPositionY = viewport.getViewPosition().y;

            int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
            pythonProgressLabel.setText("Python Progression: " + progress + "%");
            pythonProgressLabelHome.setText("Python Progression: " + progress + "%"); // Update home label
        });

        return pythonPanel;
    }

    // Method to create the Java course panel
    public JPanel createJavaPanel() {
        JPanel javaPanel = new JPanel(new BorderLayout());
        javaPanel.setBackground(Color.WHITE); // Set background to white

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setFont(new Font("Poppins", Font.PLAIN, 28)); // Increased font size
        textPane.setMargin(new Insets(10, 10, 10, 10)); // Add left padding
        textPane.setBackground(Color.WHITE); // White background for text pane
        textPane.setForeground(Color.BLACK); // Black text color

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\java.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 1; i <= 17; i++) {
                    line = line.replaceAll("\\b" + i + "\\b", "<b>" + i + "</b>");
                }
                content.append(line).append("<br>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        textPane.setText(content.toString());

        javaScrollPane = new JScrollPane(textPane);
        javaPanel.add(javaScrollPane, BorderLayout.CENTER);

        // Initialize javaProgressLabel
        javaProgressLabel = new JLabel("Java Progression: 0%");
        javaProgressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        javaProgressLabel.setForeground(Color.LIGHT_GRAY); // Light gray text color
        javaPanel.add(javaProgressLabel, BorderLayout.NORTH);

        // Add scroll listener to update progress
        javaScrollPane.getViewport().addChangeListener(e -> {
            JViewport viewport = (JViewport) e.getSource();
            int extentHeight = viewport.getExtentSize().height;
            int viewHeight = viewport.getViewSize().height;
            int viewPositionY = viewport.getViewPosition().y;

            int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
            javaProgressLabel.setText("Java Progression: " + progress + "%");
            javaProgressLabelHome.setText("Java Progression: " + progress + "%"); // Update home label
        });

        return javaPanel;
    }
}
