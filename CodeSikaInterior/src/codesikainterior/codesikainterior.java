package codesikainterior;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CodeSikaInterior extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel sidebar;
    private JPanel indicatorPanel;

    public CodeSikaInterior() {
        // Frame Settings
        setTitle("Code Sika Dashboard");
        setSize(1200, 700); // Adjusted size for the extra space to accommodate quizzes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar Navigation Panel
        sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 30, 30)); // Dark sidebar
        sidebar.setPreferredSize(new Dimension(220, 700));
        sidebar.setLayout(null); // Absolute positioning for custom design

        // User Profile Placeholder (Replaced with Image)
        ImageIcon userImage = new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\images\\manss.png");
        Image scaledImage = userImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel userIcon = new JLabel(new ImageIcon(scaledImage));
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
        profilePanel.setBackground(new Color(240, 240, 240));
        profilePanel.setLayout(new BorderLayout());
        JLabel profileLabel = new JLabel("Hello, Profile", SwingConstants.CENTER);
        profileLabel.setFont(new Font("Arial", Font.BOLD, 28));
        profilePanel.add(profileLabel, BorderLayout.CENTER);
        contentPanel.add(profilePanel, "Profile");

        // About Panel
        JPanel aboutPanel = new JPanel();
        aboutPanel.setBackground(new Color(240, 240, 240));
        aboutPanel.setLayout(new BorderLayout());
        JLabel aboutLabel = new JLabel("Hello, About", SwingConstants.CENTER);
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 28));
        aboutPanel.add(aboutLabel, BorderLayout.CENTER);
        contentPanel.add(aboutPanel, "About");

        // Courses Panel
        JPanel coursesPanel = createCoursesPanel();
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
        aboutBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "About");
            updateIndicatorPosition(aboutBtn);
        });
        coursesBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "Courses");
            updateIndicatorPosition(coursesBtn);
        });

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
        homePanel.setBackground(new Color(240, 240, 240));
        homePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        // Title in the middle top
        JLabel titleLabel = new JLabel("Code Sika", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing
        homePanel.add(titleLabel, BorderLayout.NORTH);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Code Sika Dashboard!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(50, 50, 50));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        homePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Progress Bars Panel
        JPanel progressBarsPanel = createProgressBarsPanel();
        homePanel.add(progressBarsPanel, BorderLayout.CENTER);

        // Placeholder for future additions
        JPanel placeholderPanel = new JPanel();
        placeholderPanel.setPreferredSize(new Dimension(300, 0));
        placeholderPanel.setBackground(new Color(240, 240, 240));
        homePanel.add(placeholderPanel, BorderLayout.EAST);

        return homePanel;
    }

    // Method to create the progress bars panel
    private JPanel createProgressBarsPanel() {
        JPanel progressBarsPanel = new JPanel();
        progressBarsPanel.setLayout(new BoxLayout(progressBarsPanel, BoxLayout.Y_AXIS));
        progressBarsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        progressBarsPanel.setBackground(new Color(240, 240, 240));

        // Add progress bars for each course
        progressBarsPanel.add(createCourseProgressBar("Python", 75));
        progressBarsPanel.add(createCourseProgressBar("Java", 50));
        progressBarsPanel.add(createCourseProgressBar("C++", 30));
        progressBarsPanel.add(createCourseProgressBar("JavaScript", 90));

        return progressBarsPanel;
    }

    // Method to create a course progress bar
    private JPanel createCourseProgressBar(String courseName, int progress) {
        JPanel coursePanel = new JPanel(new BorderLayout());
        coursePanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        coursePanel.setBackground(new Color(240, 240, 240));

        JLabel courseLabel = new JLabel(courseName);
        courseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coursePanel.add(courseLabel, BorderLayout.WEST);

        CustomProgressBar progressBar = new CustomProgressBar(progress);
        coursePanel.add(progressBar, BorderLayout.CENTER);

        return coursePanel;
    }

    // Method to create the courses panel
    private JPanel createCoursesPanel() {
        JPanel coursesPanel = new JPanel();
        coursesPanel.setBackground(new Color(240, 240, 240));
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
        panel.add(learnMoreButton, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CodeSikaInterior());
    }
}

// Custom progress bar class
class CustomProgressBar extends JComponent {
    private int progress;

    public CustomProgressBar(int progress) {
        this.progress = progress;
        setPreferredSize(new Dimension(150, 20)); // Smaller width
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

        // Progress
        g2d.setColor(new Color(30, 144, 255));
        int width = (int) (getWidth() * (progress / 100.0));
        g2d.fillRoundRect(0, 0, width, getHeight(), 15, 15);

        // Text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        String text = progress + "%";
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g2d.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 3);
    }
}