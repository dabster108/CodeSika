import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class background extends JFrame {

    public background() {
        // Frame Settings
        setTitle("Code Sika Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar Navigation Panel
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 30, 30)); // Dark sidebar
        sidebar.setPreferredSize(new Dimension(220, 600));
        sidebar.setLayout(null); // Absolute positioning for custom design

        // User Profile Placeholder
        JLabel userIcon = new JLabel("User");
        userIcon.setBounds(60, 30, 100, 100);
        userIcon.setHorizontalAlignment(SwingConstants.CENTER);
        userIcon.setFont(new Font("Arial", Font.BOLD, 20));
        userIcon.setForeground(Color.WHITE);
        userIcon.setOpaque(true);
        userIcon.setBackground(new Color(70, 70, 70)); // Placeholder background
        sidebar.add(userIcon);

        JLabel usernameLabel = new JLabel("Hello, Dikshanta");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(60, 140, 120, 20);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sidebar.add(usernameLabel);

        // Sidebar Buttons with Hover Animation
        JButton dashboardBtn = createSidebarButton("Dashboard", 180);
        JButton profileBtn = createSidebarButton("Profile", 240);
        JButton messagesBtn = createSidebarButton("Messages", 300);
        JButton settingsBtn = createSidebarButton("Settings", 360);
        JButton logoutBtn = createSidebarButton("Logout", 420);

        // Add buttons to the sidebar
        sidebar.add(dashboardBtn);
        sidebar.add(profileBtn);
        sidebar.add(messagesBtn);
        sidebar.add(settingsBtn);
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

        // Main Welcome Message
        JLabel contentLabel = new JLabel("Welcome to the Enhanced Raven Dashboard", SwingConstants.CENTER);
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentLabel.setForeground(new Color(70, 70, 70));
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        // Add components to the frame
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new background());
    }
}
