import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class background extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel sidebar;
    private JPanel indicatorPanel;

    public background() {
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
        ImageIcon userImage = new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\manss.png");
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
        JButton dashboardBtn = createSidebarButton("Dashboard", 180);
        JButton profileBtn = createSidebarButton("Profile", 240);
        JButton aboutBtn = createSidebarButton("About", 300);
        JButton pointsBtn = createSidebarButton("Points", 360);
        JButton logoutBtn = createSidebarButton("Logout", 420);

        // Add buttons to the sidebar
        sidebar.add(dashboardBtn);
        sidebar.add(profileBtn);
        sidebar.add(aboutBtn);
        sidebar.add(pointsBtn);
        sidebar.add(logoutBtn);

        // Indicator Panel
        indicatorPanel = new JPanel();
        indicatorPanel.setBackground(new Color(255, 255, 255));
        indicatorPanel.setBounds(0, 180, 5, 40);
        sidebar.add(indicatorPanel);

        // Main Content Panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Dashboard Panel
        JPanel dashboardPanel = createDashboardPanel();
        contentPanel.add(dashboardPanel, "Dashboard");

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

        // Quiz Panel
        JPanel quizPanel = createQuizPanel();
        contentPanel.add(quizPanel, "Quiz");

        // Add components to the frame
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Add action listeners to sidebar buttons
        dashboardBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "Dashboard");
            updateIndicatorPosition(dashboardBtn);
        });
        profileBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "Profile");
            updateIndicatorPosition(profileBtn);
        });
        aboutBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "About");
            updateIndicatorPosition(aboutBtn);
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
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(70, 70, 70)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(45, 45, 45)); // Restore color
            }
        });

        return button;
    }

    // Method to update the position of the indicator panel
    private void updateIndicatorPosition(JButton button) {
        indicatorPanel.setLocation(0, button.getY());
    }

    // Method to create the dashboard panel
    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setBackground(new Color(240, 240, 240));
        dashboardPanel.setLayout(new BorderLayout());
        dashboardPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        // Title in the middle top
        JLabel titleLabel = new JLabel("Code Sika", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing
        dashboardPanel.add(titleLabel, BorderLayout.NORTH);

        // Courses Section Title
        JLabel coursesTitle = new JLabel("Courses Offered There", SwingConstants.CENTER);
        coursesTitle.setFont(new Font("Arial", Font.BOLD, 22));
        coursesTitle.setForeground(new Color(50, 50, 50));
        coursesTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        dashboardPanel.add(coursesTitle, BorderLayout.CENTER);

        // Scrollable Horizontal Panel for Courses
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Horizontal layout with gaps
        coursePanel.setBackground(new Color(240, 240, 240)); // Set background color

        // Add course panels
        coursePanel.add(createCoursePanel("React Basics", "45 Videos"));
        coursePanel.add(createCoursePanel("Data Structures", "60 Videos"));
        coursePanel.add(createCoursePanel("Machine Learning", "78 Videos"));
        coursePanel.add(createCoursePanel("HTML & CSS", "35 Videos"));
        coursePanel.add(createCoursePanel("Django Framework", "50 Videos"));

        JScrollPane courseScrollPane = new JScrollPane(coursePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dashboardPanel.add(courseScrollPane, BorderLayout.CENTER); // Directly add coursePanel

        // Quiz Panel below the courses
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BorderLayout());
        quizPanel.setBackground(new Color(240, 240, 255));
        quizPanel.setPreferredSize(new Dimension(1200, 120));

        JLabel quizTitleLabel = new JLabel("Quiz: Java Basics", SwingConstants.CENTER);
        quizTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        quizPanel.add(quizTitleLabel, BorderLayout.NORTH);

        JLabel quizDescriptionLabel = new JLabel("Test your Java skills with this quiz!", SwingConstants.CENTER);
        quizDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quizPanel.add(quizDescriptionLabel, BorderLayout.CENTER);

        JButton startQuizButton = new JButton("Start Quiz");
        startQuizButton.setFocusPainted(false);
        startQuizButton.setBackground(new Color(30, 144, 255)); // Set button color
        startQuizButton.setForeground(Color.WHITE); // Set text color
        startQuizButton.setFont(new Font("Arial", Font.BOLD, 12));
        startQuizButton.setBorderPainted(false);
        startQuizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startQuizButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startQuizButton.setBackground(new Color(0, 120, 215)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startQuizButton.setBackground(new Color(30, 144, 255)); // Reset color when not hovered
            }
        });
        startQuizButton.addActionListener(e -> cardLayout.show(contentPanel, "Quiz"));
        quizPanel.add(startQuizButton, BorderLayout.SOUTH);

        dashboardPanel.add(quizPanel, BorderLayout.SOUTH);

        return dashboardPanel;
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
        learnMoreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                learnMoreButton.setBackground(new Color(0, 120, 215)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                learnMoreButton.setBackground(new Color(30, 144, 255)); // Reset color when not hovered
            }
        });
        panel.add(learnMoreButton, BorderLayout.SOUTH);

        return panel; // Return the created panel
    }

    // Method to create the quiz panel
    private JPanel createQuizPanel() {
        JPanel quizPanel = new JPanel();
        quizPanel.setBackground(new Color(240, 240, 240));
        quizPanel.setLayout(new BorderLayout());

        JLabel quizTitle = new JLabel("Java Basics Quiz", SwingConstants.CENTER);
        quizTitle.setFont(new Font("Arial", Font.BOLD, 24));
        quizTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        quizPanel.add(quizTitle, BorderLayout.NORTH);

        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Sample questions
        String[] questions = {
            "What is the size of int in Java?",
            "Which of the following is not a Java feature?",
            "What is the default value of a boolean variable?"
        };

        String[][] options = {
            {"1 byte", "2 bytes", "4 bytes", "8 bytes"},
            {"Object-oriented", "Use of pointers", "Portable", "Dynamic"},
            {"true", "false", "0", "1"}
        };

        int[] correctAnswers = {2, 1, 1}; // Index of correct answers

        ButtonGroup[] buttonGroups = new ButtonGroup[questions.length];
        JRadioButton[][] radioButtons = new JRadioButton[questions.length][4];

        for (int i = 0; i < questions.length; i++) {
            JLabel questionLabel = new JLabel((i + 1) + ". " + questions[i]);
            questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            questionsPanel.add(questionLabel);

            buttonGroups[i] = new ButtonGroup();
            JPanel optionsPanel = new JPanel();
            optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

            for (int j = 0; j < 4; j++) {
                radioButtons[i][j] = new JRadioButton(options[i][j]);
                buttonGroups[i].add(radioButtons[i][j]);
                optionsPanel.add(radioButtons[i][j]);
            }

            questionsPanel.add(optionsPanel);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.setFocusPainted(false);
        submitButton.setBackground(new Color(30, 144, 255)); // Set button color
        submitButton.setForeground(Color.WHITE); // Set text color
        submitButton.setFont(new Font("Arial", Font.BOLD, 12));
        submitButton.setBorderPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(new Color(0, 120, 215)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(new Color(30, 144, 255)); // Reset color when not hovered
            }
        });
        submitButton.addActionListener(e -> {
            int score = 0;
            for (int i = 0; i < questions.length; i++) {
                for (int j = 0; j < 4; j++) {
                    if (radioButtons[i][j].isSelected() && j == correctAnswers[i]) {
                        score++;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Your score: " + score + "/" + questions.length);
            cardLayout.show(contentPanel, "Dashboard");
        });

        quizPanel.add(questionsPanel, BorderLayout.CENTER);
        quizPanel.add(submitButton, BorderLayout.SOUTH);

        return quizPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new background());
    }
}