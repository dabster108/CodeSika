import java.awt.*;
import javax.swing.*;

public class SignupPage extends JFrame {

    public SignupPage() {
        // Frame settings
        setTitle("Code Sika Signup Page");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Left panel for branding
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 450, 600);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());

        JLabel brandingLabel = new JLabel("Code Sika", SwingConstants.CENTER);
        brandingLabel.setFont(new Font("Arial", Font.BOLD, 36));
        brandingLabel.setForeground(new Color(30, 70, 90));
        leftPanel.add(brandingLabel, BorderLayout.CENTER);

        // Right panel for form
        JPanel formPanel = new JPanel();
        formPanel.setBounds(450, 0, 450, 600);
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(30, 40, 50)); // Dark background

        // Title: Sign in or Sign Up
        JLabel titleLabel = new JLabel("Sign in or Sign Up");
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setBounds(50, 30, 300, 30);
        formPanel.add(titleLabel);

        // FULL NAME
        JLabel fullNameLabel = createLabel("FULL NAME", 50, 80);
        formPanel.add(fullNameLabel);

        JTextField fullNameField = createTextField(50, 110);
        formPanel.add(fullNameField);

        // PASSWORD
        JLabel passwordLabel = createLabel("PASSWORD", 50, 160);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 190, 300, 30);
        passwordField.setBorder(null);
        formPanel.add(passwordField);

        // EMAIL
        JLabel emailLabel = createLabel("EMAIL", 50, 240);
        formPanel.add(emailLabel);

        JTextField emailField = createTextField(50, 270);
        formPanel.add(emailField);

        // Checkbox: Terms of Service
        JCheckBox termsCheckBox = new JCheckBox("I agree all terms of service");
        termsCheckBox.setBounds(50, 320, 250, 20);
        termsCheckBox.setForeground(Color.LIGHT_GRAY);
        termsCheckBox.setBackground(formPanel.getBackground());
        formPanel.add(termsCheckBox);

        // Sign Up Button
        JButton signupButton = new JButton("SignUp");
        signupButton.setBounds(50, 360, 120, 40);
        signupButton.setBackground(new Color(50, 200, 180));
        signupButton.setForeground(Color.BLACK);
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.setFocusPainted(false);
        formPanel.add(signupButton);

        // Already a member link
        JLabel memberLabel = new JLabel("Already a member");
        memberLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        memberLabel.setForeground(Color.LIGHT_GRAY);
        memberLabel.setBounds(180, 375, 150, 20);
        formPanel.add(memberLabel);

        // Background image (Optional)
        JLabel backgroundImage = new JLabel(new ImageIcon("C:\\Users\\3108d\\Desktop\\CodeSika\\images\\bg.png"));
        backgroundImage.setBounds(0, 0, 900, 600);
        add(backgroundImage);

        // Add panels to frame
        add(leftPanel);
        add(formPanel);

        // Set frame visibility
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method to create labels
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 300, 20);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.LIGHT_GRAY);
        return label;
    }

    // Helper method to create text fields
    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 300, 30);
        textField.setBorder(null);
        return textField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignupPage::new);
    }
}
