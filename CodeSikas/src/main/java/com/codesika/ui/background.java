package com.codesika.ui;
//updated code for the project
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
    private JLabel cppProgressLabelHome; // Progress label for C++ on home panel
    private JLabel jsProgressLabelHome;  // Progress label for JavaScript on home panel
    private boolean isDarkMode = false; // Track dark mode state

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

        // Light/Dark Theme Toggle Button
       // Light/Dark Theme Toggle Button
JButton themeToggleBtn = new JButton("Dark Mode");
themeToggleBtn.setBounds(20, 360, 180, 40); // Positioned above the logout button
themeToggleBtn.setFocusPainted(false);
themeToggleBtn.setBackground(Color.decode("#D9D9D9"));
themeToggleBtn.setForeground(Color.BLACK);
themeToggleBtn.setFont(new Font("Inter", Font.PLAIN, 16));
themeToggleBtn.setHorizontalAlignment(SwingConstants.LEFT);
themeToggleBtn.setBorderPainted(false);

// Add action listener for theme toggle button
themeToggleBtn.addActionListener(e -> toggleDarkMode(themeToggleBtn));
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
        pythonProgressLabelHome.setForeground(Color.BLACK);

        javaProgressLabelHome = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\java.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        javaProgressLabelHome.setText(" Java Progression: 0%");
        javaProgressLabelHome.setForeground(Color.BLACK);

        cppProgressLabelHome = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\cplus.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        cppProgressLabelHome.setText(" C++ Progression: 0%");
        cppProgressLabelHome.setForeground(Color.BLACK);

        jsProgressLabelHome = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\images\\java-script.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        jsProgressLabelHome.setText(" JavaScript Progression: 0%");
        jsProgressLabelHome.setForeground(Color.BLACK);

        // Home Panel
        JPanel homePanel = createHomePanel();
        contentPanel.add(homePanel, "Home");

        // Profile Panel
        Profile profilePanel = new Profile(username);
        contentPanel.add(profilePanel, "Profile");

        // Courses Panel
        CoursesPage coursesPanel = new CoursesPage(cardLayout, contentPanel, pythonProgressLabelHome, javaProgressLabelHome, cppProgressLabelHome, jsProgressLabelHome);
        contentPanel.add(coursesPanel, "Courses");

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

    // Method to toggle dark mode
    private void toggleDarkMode(JButton themeToggleBtn) {
        isDarkMode = !isDarkMode;
        Color backgroundColor = isDarkMode ? Color.BLACK : Color.decode("#BBD2D1");
        Color textColor = isDarkMode ? Color.WHITE : Color.BLACK;
    
        // Update sidebar
        sidebar.setBackground(isDarkMode ? Color.DARK_GRAY : Color.decode("#F9F7E7"));
        for (Component component : sidebar.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(isDarkMode ? Color.GRAY : Color.decode("#D9D9D9"));
                component.setForeground(textColor);
            }
        }
    
        // Update content panel
        contentPanel.setBackground(backgroundColor);
        for (Component component : contentPanel.getComponents()) {
            updateComponentColors(component, backgroundColor, textColor);
        }
    
        // Update toggle button text
        themeToggleBtn.setText(isDarkMode ? "Light Mode" : "Dark Mode");
    
        // Update other components if necessary
        revalidate();
        repaint();
    }
    
    private void updateComponentColors(Component component, Color backgroundColor, Color textColor) {
        if (component instanceof JPanel) {
            component.setBackground(backgroundColor);
            for (Component child : ((JPanel) component).getComponents()) {
                updateComponentColors(child, backgroundColor, textColor);
            }
        } else if (component instanceof JLabel) {
            component.setForeground(textColor);
        } else if (component instanceof JTextArea) {
            component.setBackground(backgroundColor);
            component.setForeground(textColor);
        }
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
        titleLabel.setFont(new Font("Inter", Font.BOLD, 59)); // Increased font size by 3
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(titleLabel, gbc);

        gbc.gridy++;
        JLabel subTitleLabel = new JLabel("A Platform to Learn & Practice Code");
        subTitleLabel.setFont(new Font("Inter", Font.PLAIN, 27)); // Increased font size by 3
        subTitleLabel.setForeground(Color.BLACK);
        subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(subTitleLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align to the top left
        gbc.insets = new Insets(10, 50, 10, 10); // Add some space from the left
        JTextArea descriptionArea = new JTextArea("CodeSika is your gateway to mastering coding skills. Whether you're a beginner or an experienced coder, we offer the tools and resources to enhance your knowledge and practice coding effectively.");
        descriptionArea.setFont(new Font("Inter", Font.PLAIN, 23)); // Increased font size by 3
        descriptionArea.setForeground(Color.BLACK);
        descriptionArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setPreferredSize(new Dimension(800, 100));
        textPanel.add(descriptionArea, gbc);

        gbc.gridy++;
        JTextArea additionalTextArea = new JTextArea(
            "At Code Sika, we empower you to learn, create, and innovate through coding. Whether you're just starting or looking to master new skills, we have resources tailored for you.\n\n" +
            "Explore courses, practice coding, and build projects with our easy-to-use tools and resources. Start with beginner lessons and work your way to advanced topics in languages like Python, JavaScript, and more."
        );
        additionalTextArea.setFont(new Font("Inter", Font.PLAIN, 23)); // Increased font size by 3
        additionalTextArea.setForeground(Color.BLACK);
        additionalTextArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        additionalTextArea.setEditable(false);
        additionalTextArea.setWrapStyleWord(true);
        additionalTextArea.setLineWrap(true);
        additionalTextArea.setPreferredSize(new Dimension(800, 200));
        textPanel.add(additionalTextArea, gbc);

        gbc.gridy++;
        JTextArea whatYouCanDoTextArea = new JTextArea(
            "What You Can Do on Code Sika:\n" +
            "- Learn new programming languages\n" +
            "- Track your progress with interactive modules\n" +
            "- Participate in coding challenges\n" +
            "- Build real-world projects\n" +
            "- Earn certificates and showcase your skills"
        );
        whatYouCanDoTextArea.setFont(new Font("Inter", Font.PLAIN, 23)); // Increased font size by 3
        whatYouCanDoTextArea.setForeground(Color.BLACK);
        whatYouCanDoTextArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        whatYouCanDoTextArea.setEditable(false);
        whatYouCanDoTextArea.setWrapStyleWord(true);
        whatYouCanDoTextArea.setLineWrap(true);
        whatYouCanDoTextArea.setPreferredSize(new Dimension(800, 150));
        textPanel.add(whatYouCanDoTextArea, gbc);

        gbc.gridy++;
        JPanel readyPanel = new JPanel(new GridBagLayout());
        readyPanel.setOpaque(false); // Make it transparent to show the background
        GridBagConstraints readyGbc = new GridBagConstraints();
        readyGbc.insets = new Insets(10, 10, 10, 10);
        readyGbc.gridx = 0;
        readyGbc.gridy = 0;
        readyGbc.anchor = GridBagConstraints.WEST;

        JLabel readyLabel = new JLabel("Ready to code? Start your first course today!");
        readyLabel.setFont(new Font("Inter", Font.PLAIN, 23)); // Increased font size by 3
        readyLabel.setForeground(Color.BLACK);
        readyLabel.setHorizontalAlignment(SwingConstants.LEFT);
        readyPanel.add(readyLabel, readyGbc);

        readyGbc.gridx++;
        // Get Started Button
        JButton getStartedButton = new JButton("Get Started");
        getStartedButton.setFont(new Font("Inter", Font.PLAIN, 21)); // Increased font size by 3
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
        readyPanel.add(getStartedButton, readyGbc);

        gbc.gridy++;
        textPanel.add(readyPanel, gbc);

        // Add course progression box
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 20, 0, 0); // Add some space to the left
        gbc.weightx = 1.0; // Allow the component to take horizontal space
        gbc.weighty = 1.0; // Allow the component to take vertical space

        JPanel horizontalPanel = new JPanel(new GridLayout(1, 1, 30, 0));
        horizontalPanel.setBackground(Color.decode("#BBD2D1")); // Match home page background color
        horizontalPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border to the progression box

        ProgressNotes progressNotes = new ProgressNotes(pythonProgressLabelHome, javaProgressLabelHome, cppProgressLabelHome, jsProgressLabelHome);
        progressNotes.setBackground(Color.decode("#F9F7E7")); // Set course progression box background to #F9F7E7
        progressNotes.setForeground(Color.BLACK); // Set text color to black

        horizontalPanel.add(progressNotes);
        textPanel.add(horizontalPanel, gbc);

        // Add everything to the home panel
        homePanel.add(textPanel, BorderLayout.CENTER);

        return homePanel;
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