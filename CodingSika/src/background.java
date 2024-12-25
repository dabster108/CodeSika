import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class background extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel sidebar;
    private JPanel indicatorPanel;
    private JLabel pythonProgressLabel;
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
        ImageIcon userImage = new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\CodingSika\\images\\profile.png");
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
        homePanel.setBackground(new Color(255, 255, 255)); // Light mode background
        homePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        // Title in the middle top
        JLabel titleLabel = new JLabel("Code Sika Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 0, 0)); // Black text
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing
        homePanel.add(titleLabel, BorderLayout.NORTH);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Code Sika Dashboard!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(0, 0, 0)); // Black text
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        homePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Course Progression Panel
        JPanel courseProgressionPanel = createCourseProgressionPanel();
        homePanel.add(courseProgressionPanel, BorderLayout.SOUTH);

        return homePanel;
    }

    // Method to create the course progression panel
    private JPanel createCourseProgressionPanel() {
        JPanel courseProgressionPanel = new JPanel();
        courseProgressionPanel.setLayout(new BoxLayout(courseProgressionPanel, BoxLayout.Y_AXIS));
        courseProgressionPanel.setBackground(new Color(255, 255, 255)); // Light mode background
        courseProgressionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel courseProgressionTitle = new JLabel("Course Progression", SwingConstants.CENTER);
        courseProgressionTitle.setFont(new Font("Arial", Font.BOLD, 24));
        courseProgressionTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        courseProgressionPanel.add(courseProgressionTitle);

        pythonProgressLabel = new JLabel("Python Progression: 0%", SwingConstants.CENTER);
        pythonProgressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        pythonProgressLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        courseProgressionPanel.add(pythonProgressLabel);

        // Add more course progressions here as needed

        return courseProgressionPanel;
    }

    // Method to create the summary panel
    private JPanel createSummaryPanel() {
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(1, 3, 10, 10));
        summaryPanel.setBackground(new Color(255, 255, 255)); // Light mode background
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        summaryPanel.add(createSummaryCard("Courses", "4", new Color(30, 144, 255)));
        summaryPanel.add(createSummaryCard("Completed", "2", new Color(34, 139, 34)));
        summaryPanel.add(createSummaryCard("In Progress", "2", new Color(255, 165, 0)));

        return summaryPanel;
    }

    // Method to create individual summary cards
    private JPanel createSummaryCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color); // Set background color
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setPreferredSize(new Dimension(150, 100));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(255, 255, 255)); // White text
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(new Color(255, 255, 255)); // White text
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
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

        StringBuilder content = new StringBuilder("<html><body style='font-family: Arial; font-size: 18px;'>");

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\CodingSika\\notes\\python.txt"))) {
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

        content.append("</body></html>");
        textPane.setText(content.toString());

        pythonScrollPane = new JScrollPane(textPane);
        pythonPanel.add(pythonScrollPane, BorderLayout.CENTER);

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
        pythonScrollPane.getViewport().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JViewport viewport = (JViewport) e.getSource();
                int extentHeight = viewport.getExtentSize().height;
                int viewHeight = viewport.getViewSize().height;
                int viewPositionY = viewport.getViewPosition().y;

                int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
                pythonProgressLabel.setText("Python Progression: " + progress + "%");
            }
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