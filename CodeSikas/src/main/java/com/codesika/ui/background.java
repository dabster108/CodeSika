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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.codesika.utils.CoursesPage;
import com.codesika.utils.Profile;
import com.codesika.utils.ProgressNotes;

public class background extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel sidebar;
    private JPanel indicatorPanel;
    private JLabel pythonProgressLabelHome; // Progress label for home panel
    private JLabel javaProgressLabelHome; // Progress label for home panel

    public background(String username) {
        // Frame Settings
        setTitle("Code Sika Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set custom logo
        try {
            Image logo = ImageIO.read(new File("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\logo.png"));
            setIconImage(logo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sidebar Navigation Panel
        sidebar = new JPanel();
        sidebar.setBackground(Color.decode("#F9F7E7")); // Sidebar background color
        sidebar.setPreferredSize(new Dimension(220, 700));
        sidebar.setLayout(null); // Absolute positioning for custom design

        // User Profile Placeholder (Replaced with Image)
        ImageIcon userImage = new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\mainsss.png");
        Image scaledImage = userImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel userIcon = new JLabel(new CircularImageIcon(scaledImage));
        userIcon.setBounds(60, 30, 100, 100);
        sidebar.add(userIcon);

        JLabel usernameLabel = new JLabel("Hello " + username);
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setBounds(60, 140, 120, 20);
        usernameLabel.setFont(new Font("Inter", Font.BOLD, 16));
        sidebar.add(usernameLabel);

        // Sidebar Buttons with Hover Animation
        JButton homeBtn = createSidebarButton("Home", 180);
        JButton profileBtn = createSidebarButton("Profile", 240);
        JButton coursesBtn = createSidebarButton("Courses", 300);

        // Add buttons to the sidebar
        sidebar.add(homeBtn);
        sidebar.add(profileBtn);
        sidebar.add(coursesBtn);

        // Light/Dark Theme Toggle Button (Functionality Removed)
        JButton themeToggleBtn = new JButton("Light/Dark");
        themeToggleBtn.setBounds(20, 360, 180, 40); // Positioned above the logout button
        themeToggleBtn.setFocusPainted(false);
        themeToggleBtn.setBackground(Color.decode("#D9D9D9"));
        themeToggleBtn.setForeground(Color.BLACK);
        themeToggleBtn.setFont(new Font("Inter", Font.PLAIN, 16));
        themeToggleBtn.setHorizontalAlignment(SwingConstants.LEFT);
        themeToggleBtn.setBorderPainted(false);
        sidebar.add(themeToggleBtn);

        // Logout Button
        JButton logoutBtn = createSidebarButton("Logout", 420);
        sidebar.add(logoutBtn);

        // Indicator Panel
        indicatorPanel = new JPanel();
        indicatorPanel.setBackground(Color.BLACK);
        indicatorPanel.setBounds(0, 180, 5, 40);
        sidebar.add(indicatorPanel);

        // Main Content Panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.decode("#BBD2D1")); // Home page background color

        // Initialize progress labels
        pythonProgressLabelHome = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\python.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        pythonProgressLabelHome.setText(" Python Progression: 0%");
        pythonProgressLabelHome.setForeground(Color.BLACK); // Set text color to black

        javaProgressLabelHome = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\java.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        javaProgressLabelHome.setText(" Java Progression: 0%");
        javaProgressLabelHome.setForeground(Color.BLACK); // Set text color to black

        // Home Panel
        JPanel homePanel = createHomePanel();
        contentPanel.add(homePanel, "Home");

        // Profile Panel
        Profile profilePanel = new Profile(username);
        contentPanel.add(profilePanel, "Profile");

        // Courses Panel
        CoursesPage coursesPanel = new CoursesPage(cardLayout, contentPanel, pythonProgressLabelHome, javaProgressLabelHome);
        contentPanel.add(coursesPanel, "Courses");

        // Python Course Panel
        JPanel pythonPanel = coursesPanel.createPythonPanel();
        contentPanel.add(pythonPanel, "Python");

        // Java Course Panel
        JPanel javaPanel = coursesPanel.createJavaPanel();
        contentPanel.add(javaPanel, "Java");

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
        coursesBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "Courses");
            updateIndicatorPosition(coursesBtn);
        });
        logoutBtn.addActionListener(e -> {
            dispose(); // Close the current window
            SwingUtilities.invokeLater(SignupPage::new); // Open the login page
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
        button.setBackground(Color.decode("#D9D9D9"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Inter", Font.PLAIN, 16));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);

        // Add Hover Effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(Color.BLACK); // Change color on hover
                button.setForeground(Color.WHITE); // Change text color on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(Color.decode("#D9D9D9")); // Restore color
                button.setForeground(Color.BLACK); // Restore text color
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
        homePanel.setBackground(Color.decode("#BBD2D1")); // Home page background color

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
        titleLabel.setFont(new Font("Inter", Font.BOLD, 56));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(titleLabel, gbc);

        gbc.gridy++;
        JLabel subTitleLabel = new JLabel("A Platform to Learn & Practice Code");
        subTitleLabel.setFont(new Font("Inter", Font.PLAIN, 24));
        subTitleLabel.setForeground(Color.BLACK);
        subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(subTitleLabel, gbc);

        gbc.gridy++;
        JTextArea descriptionArea = new JTextArea("CodeSika is your gateway to mastering coding skills. Whether you're a beginner or an experienced coder, we offer the tools and resources to enhance your knowledge and practice coding effectively.");
        descriptionArea.setFont(new Font("Inter", Font.PLAIN, 20));
        descriptionArea.setForeground(Color.BLACK);
        descriptionArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setPreferredSize(new Dimension(800, 100));
        textPanel.add(descriptionArea, gbc);

        gbc.gridy++;
        // Get Started Button
        JButton getStartedButton = new JButton("Get Started");
        getStartedButton.setFont(new Font("Inter", Font.PLAIN, 18));
        getStartedButton.setForeground(Color.WHITE);
        getStartedButton.setBackground(Color.decode("#2C4B72"));
        getStartedButton.setFocusPainted(false);
        getStartedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getStartedButton.setContentAreaFilled(false);
        getStartedButton.setOpaque(true);
        getStartedButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#2C4B72"), 2, true),
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding for the text
        ));
        getStartedButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30); // Rounded edges
                super.paint(g2, c);
                g2.dispose();
            }
        });
        getStartedButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Courses");
            updateIndicatorPosition(getStartedButton);
        });
        textPanel.add(getStartedButton, gbc);

        // Add course progression box
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHEAST; // Align to the top right corner
        gbc.weightx = 1.0; // Allow the component to take horizontal space
        gbc.weighty = 1.0; // Allow the component to take vertical space

        JPanel horizontalPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        horizontalPanel.setBackground(Color.decode("#BBD2D1")); // Match home page background color

        ProgressNotes progressNotes = new ProgressNotes(pythonProgressLabelHome, javaProgressLabelHome);
        progressNotes.setBackground(Color.decode("#F9F7E7")); // Set course progression box background to #F9F7E7
        progressNotes.setForeground(Color.BLACK); // Set text color to black

        horizontalPanel.add(progressNotes);
        textPanel.add(horizontalPanel, gbc);

        // Add everything to the home panel
        homePanel.add(textPanel, BorderLayout.NORTH);

        return homePanel;
    }

    // Method to create the Profile panel
    private JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.BLACK); // Default to dark mode
        profilePanel.setLayout(new BorderLayout());
        JLabel profileLabel = new JLabel("Hello !, Profile", SwingConstants.CENTER);
        profileLabel.setFont(new Font("Inter", Font.BOLD, 32));
        profileLabel.setForeground(Color.WHITE); // Text color for dark mode
        profilePanel.add(profileLabel, BorderLayout.CENTER);
        return profilePanel;
    }

    // Method to create the Quiz panel
    private JPanel createQuizPanel() {
        JPanel quizPanel = new JPanel(new BorderLayout());
        quizPanel.setBackground(Color.BLACK); // Default to dark mode

        JLabel quizTitle = new JLabel("Python Quiz", SwingConstants.CENTER);
        quizTitle.setFont(new Font("Inter", Font.BOLD, 24));
        quizTitle.setForeground(Color.WHITE); // Text color for dark mode
        quizTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        quizPanel.add(quizTitle, BorderLayout.NORTH);

        // Add more quiz content here

        return quizPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new background("Default User"));
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