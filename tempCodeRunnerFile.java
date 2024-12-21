import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Background extends JFrame {

    public Background() {
        // Frame Settings
        setTitle("Code Sika Dashboard");
        setSize(1200, 600); // Adjusted for extra space for the right panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar Navigation Panel
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 30, 30)); // Dark sidebar
        sidebar.setPreferredSize(new Dimension(220, 600));
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

        // Add action listeners for buttons
        dashboardBtn.addActionListener(e -> showDashboard());
        profileBtn.addActionListener(e -> showProfile());
        aboutBtn.addActionListener(e -> showAbout());
        pointsBtn.addActionListener(e -> showPoints());
        logoutBtn.addActionListener(e -> logout());

        // Add buttons to the sidebar
        sidebar.add(dashboardBtn);
        sidebar.add(profileBtn);
        sidebar.add(aboutBtn);
        sidebar.add(pointsBtn);
        sidebar.add(logoutBtn);

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(240, 240, 240));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2)); // Add border

        // Title in the middle top
        JLabel titleLabel = new JLabel("Code Sika", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Scrollable Horizontal Panel for Courses
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new GridLayout(1, 0, 10, 10)); // Horizontal layout
        coursePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Sample courses
        coursePanel.add(createCoursePanel("Java Basics", "20 Videos"));
        coursePanel.add(createCoursePanel("Python Fundamentals", "30 Videos"));
        coursePanel.add(createCoursePanel("C Programming", "50 Videos"));

        JScrollPane scrollPane = new JScrollPane(coursePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // No border for scroll pane
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Right Panel for Courses
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(300, 600)); // Set size for right panel
        rightPanel.setBackground(new Color(250, 250, 250));
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Add padding

        // Title for right panel
        JLabel rightPanelTitle = new JLabel("Popular Courses", SwingConstants.CENTER);
        rightPanelTitle.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanelTitle.setForeground(new Color(50, 50, 50));
        rightPanel.add(rightPanelTitle, BorderLayout.NORTH);

        // Scrollable panel for right side courses
        JPanel rightCoursesPanel = new JPanel();
        rightCoursesPanel.setLayout(new GridLayout(5, 1, 10, 10)); // Vertical layout with 5 cards
        rightCoursesPanel.setBackground(new Color(250, 250, 250));

        // Add cards to the right panel
        rightCoursesPanel.add(createCoursePanel("React Basics", "45 Videos"));
        rightCoursesPanel.add(createCoursePanel("Data Structures", "60 Videos"));
        rightCoursesPanel.add(createCoursePanel("Machine Learning", "78 Videos"));
        rightCoursesPanel.add(createCoursePanel("HTML & CSS", "35 Videos"));
        rightCoursesPanel.add(createCoursePanel("Django Framework", "50 Videos"));

        JScrollPane rightScrollPane = new JScrollPane(rightCoursesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightPanel.add(rightScrollPane, BorderLayout.CENTER);

        // Add components to the frame
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST); // Add the right panel

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

    // Method to create individual course panels
    private JPanel createCoursePanel(String title, String description) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 100)); // Fixed consistent size
        panel.setBackground(new Color(240, 240, 255));
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
        panel.add(learnMoreButton, BorderLayout.SOUTH);

        return panel; // Return the created panel
    }

    // Example Methods for Button Actions
    private void showDashboard() {
        JOptionPane.showMessageDialog(this, "Showing Dashboard");
    }

    private void showProfile() {
        JOptionPane.showMessageDialog(this, "Showing Profile");
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this, "Showing About");
    }

    private void showPoints() {
        JOptionPane.showMessageDialog(this, "Showing Points");
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Background());
    }
}
