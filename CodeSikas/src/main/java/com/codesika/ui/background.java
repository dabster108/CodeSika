
package com.codesika.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class background extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel sidebar;
    private JPanel indicatorPanel;
    private JLabel pythonProgressLabel;
    private JLabel pythonProgressLabelHome; // Progress label for home panel
    private JScrollPane pythonScrollPane;

    public background() {
        // Frame Settings
        setTitle("Code Sika Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar Navigation Panel
        sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 30, 30)); // Dark sidebar
        sidebar.setPreferredSize(new Dimension(220, 700));
        sidebar.setLayout(null); // Absolute positioning for custom design

        // User Profile Placeholder (Replaced with Image)
        ImageIcon userImage = new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\Gradle\\app\\src\\images\\manss.png");
        Image scaledImage = userImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel userIcon = new JLabel(new CircularImageIcon(scaledImage));
        userIcon.setBounds(60, 30, 100, 100);
        sidebar.add(userIcon);

        JLabel usernameLabel = new JLabel("Hello, Dikshanta");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(60, 140, 120, 20);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sidebar.add(usernameLabel);

        // Sidebar Buttons with Hover Animation
        JButton homeBtn = createSidebarButton("Home", 180);
        JButton profileBtn = createSidebarButton("Profile", 240);
        JButton aboutBtn = createSidebarButton("About", 300);
        JButton coursesBtn = createSidebarButton("Courses", 360);
        JButton logoutBtn = createSidebarButton("Logout", 420);

        // Add buttons to the sidebar
        sidebar.add(homeBtn);
        sidebar.add(profileBtn);
        sidebar.add(aboutBtn);
        sidebar.add(coursesBtn);
        sidebar.add(logoutBtn);

        // Indicator Panel
        indicatorPanel = new JPanel();
        indicatorPanel.setBackground(new Color(255, 255, 255));
        indicatorPanel.setBounds(0, 180, 5, 40);
        sidebar.add(indicatorPanel);

        // Main Content Panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Home Panel
        JPanel homePanel = createHomePanel();
        contentPanel.add(homePanel, "Home");

        // Profile Panel
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(255, 255, 255));
        profilePanel.setLayout(new BorderLayout());
        JLabel profileLabel = new JLabel("Hello, Profile", SwingConstants.CENTER);
        profileLabel.setFont(new Font("Arial", Font.BOLD, 28));
        profilePanel.add(profileLabel, BorderLayout.CENTER);
        contentPanel.add(profilePanel, "Profile");

        // About Panel
        JPanel aboutPanel = new JPanel();
        aboutPanel.setBackground(new Color(255, 255, 255));
        aboutPanel.setLayout(new BorderLayout());
        JLabel aboutLabel = new JLabel("Hello, About", SwingConstants.CENTER);
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 28));
        aboutPanel.add(aboutLabel, BorderLayout.CENTER);
        contentPanel.add(aboutPanel, "About");

        // Courses Panel
        JPanel coursesPanel = createCoursesPanel();
        contentPanel.add(coursesPanel, "Courses");

        // Python Course Panel
        JPanel pythonPanel = createPythonPanel();
        contentPanel.add(pythonPanel, "Python");

        // Quiz Panel
        JPanel quizPanel = createQuizPanel();
        contentPanel.add(quizPanel, "Quiz");

        // Add components to the frame
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Add action listeners to sidebar buttons
        homeBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "Home");
            updateIndicatorPosition(homeBtn);
        });
        profileBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "Profile");
            updateIndicatorPosition(profileBtn);
        });
        aboutBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "About");
            updateIndicatorPosition(aboutBtn);
        });
        coursesBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "Courses");
            updateIndicatorPosition(coursesBtn);
        });

        // Show the home panel by default
        cardLayout.show(contentPanel, "Home");

        // Make the frame visible
        setVisible(true);
    }

    // Helper method to create styled sidebar buttons with hover effect
    private JButton createSidebarButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(20, yPosition, 180, 40);
        button.setFocusPainted(false);
        button.setBackground(new Color(45, 45, 45));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);

        // Add Hover Effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(70, 70, 70)); // Change color on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(45, 45, 45)); // Restore color
            }
        });

        return button;
    }

    // Method to update the position of the indicator panel
    private void updateIndicatorPosition(JButton button) {
        indicatorPanel.setLocation(0, button.getY());
    }

    // Method to create the home panel
    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(new Color(0, 0, 0)); // Black background

        // Text overlay (for title, subtitle, description, and button)
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridBagLayout());
        textPanel.setOpaque(false); // Make it transparent to show the background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH; // Align to the top

        JLabel titleLabel = new JLabel("Welcome to CodeSika");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 56));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(titleLabel, gbc);

        gbc.gridy++;
        JLabel subTitleLabel = new JLabel("A Platform to Learn & Practice Code");
        subTitleLabel.setFont(new Font("Poppins", Font.PLAIN, 24));
        subTitleLabel.setForeground(Color.WHITE);
        subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(subTitleLabel, gbc);

        // Progress label for Python
        pythonProgressLabelHome = new JLabel("Python Progression: 0%");
        pythonProgressLabelHome.setFont(new Font("Arial", Font.PLAIN, 16));
        pythonProgressLabelHome.setForeground(Color.WHITE);
        gbc.gridy++;
        textPanel.add(pythonProgressLabelHome, gbc);

        gbc.gridy++;
        JTextArea descriptionArea = new JTextArea("CodeSika is your gateway to mastering coding skills. Whether you're a beginner or an experienced coder, we offer the tools and resources to enhance your knowledge and practice coding effectively.");
        descriptionArea.setFont(new Font("Poppins", Font.PLAIN, 16));
        descriptionArea.setForeground(Color.LIGHT_GRAY);
        descriptionArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setPreferredSize(new Dimension(800, 100));
        textPanel.add(descriptionArea, gbc);

        gbc.gridy++;
        JButton getStartedButton = new JButton("Get Started");
        getStartedButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        getStartedButton.setForeground(Color.WHITE);
        getStartedButton.setBackground(new Color(180, 128, 227));
        getStartedButton.setFocusPainted(false);
        getStartedButton.setBorder(BorderFactory.createEmptyBorder());
        getStartedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getStartedButton.setBorder(BorderFactory.createLineBorder(new Color(180, 128, 227), 2, true)); // Curved borders
        getStartedButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Courses");
            updateIndicatorPosition(getStartedButton);
        });
        textPanel.add(getStartedButton, gbc);

        // Add everything to the home panel
        homePanel.add(textPanel, BorderLayout.NORTH);

        return homePanel;
    }

    // Method to create the courses panel
    private JPanel createCoursesPanel() {
        JPanel coursesPanel = new JPanel();
        coursesPanel.setBackground(new Color(255, 255, 255));
        coursesPanel.setLayout(new BorderLayout());

        JLabel coursesTitle = new JLabel("Courses", SwingConstants.CENTER);
        coursesTitle.setFont(new Font("Arial", Font.BOLD, 24));
        coursesTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        coursesPanel.add(coursesTitle, BorderLayout.NORTH);

        JPanel coursesContentPanel = new JPanel();
        coursesContentPanel.setLayout(new BoxLayout(coursesContentPanel, BoxLayout.Y_AXIS));
        coursesContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add course panels
        coursesContentPanel.add(createCoursePanel("Python", "45 Videos"));
        coursesContentPanel.add(createCoursePanel("Java", "60 Videos"));
        coursesContentPanel.add(createCoursePanel("C++", "78 Videos"));
        coursesContentPanel.add(createCoursePanel("JavaScript", "35 Videos"));

        JScrollPane coursesScrollPane = new JScrollPane(coursesContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        coursesPanel.add(coursesScrollPane, BorderLayout.CENTER);

        return coursesPanel;
    }

    // Method to create individual course panels
    private JPanel createCoursePanel(String title, String description) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 120)); // Fixed consistent size
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        panel.setLayout(new BorderLayout());

        // Add title
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add description
        JLabel descriptionLabel = new JLabel(description, SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(descriptionLabel, BorderLayout.CENTER);

        // Add "Learn More" button
        JButton learnMoreButton = new JButton("Learn More");
        learnMoreButton.setFocusPainted(false);
        learnMoreButton.setBackground(new Color(30, 144, 255)); // Set button color
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
            pythonScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
            cardLayout.show(contentPanel, "Python");
            updateIndicatorPosition(learnMoreButton);
        });
        panel.add(learnMoreButton, BorderLayout.SOUTH);

        return panel;
    }

    // Method to create the Python course panel
    private JPanel createPythonPanel() {
        JPanel pythonPanel = new JPanel(new BorderLayout());
        pythonPanel.setBackground(new Color(255, 255, 255)); // Light mode background

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setFont(new Font("Arial", Font.PLAIN, 18)); // Increase text size
        textPane.setMargin(new Insets(10, 10, 10, 10)); // Add left padding

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\Gradle\\app\\src\\n" + //
                        "otes\\python.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 1  ; i <= 17; i++) {
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
            updateIndicatorPosition(quizButton);
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

    // Method to create the Quiz panel
    private JPanel createQuizPanel() {
        JPanel quizPanel = new JPanel(new BorderLayout());
        quizPanel.setBackground(new Color(255, 255, 255)); // Light mode background

        JLabel quizTitle = new JLabel("Python Quiz", SwingConstants.CENTER);
        quizTitle.setFont(new Font("Arial", Font.BOLD, 24));
        quizTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        quizPanel.add(quizTitle, BorderLayout.NORTH);

        // Add more quiz content here

        return quizPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new background());
    }
}

// Custom class to create a circular image icon
class CircularImageIcon extends ImageIcon {
    public CircularImageIcon(Image image) {
        super(image);
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setClip(new Ellipse2D.Float(x, y, getIconWidth(), getIconHeight()));
        super.paintIcon(c, g2d, x, y);
        g2d.dispose();
    }
}
