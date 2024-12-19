import java.awt.*;
import javax.swing.*;

public class SignupPage extends JFrame {

    public SignupPage() {
        setTitle("Code Sika Signup Page");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 450, 600);
        leftPanel.setBackground(new Color(8, 2, 5));
        leftPanel.setLayout(new BorderLayout());

        JLabel brandingLabel = new JLabel("Code Tutor");
        brandingLabel.setFont(new Font("Arial", Font.BOLD, 28));
        brandingLabel.setForeground(new Color(122, 157, 177));
        brandingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        brandingLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        leftPanel.add(brandingLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBounds(450, 0, 450, 600);
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(122, 157, 177));

        JLabel titleLabel = new JLabel("Sign in or Sign Up");
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        titleLabel.setForeground(new Color(8, 2, 5));
        titleLabel.setBounds(50, 30, 300, 30);
        formPanel.add(titleLabel);

        JLabel fullNameLabel = createLabel("FULL NAME", 50, 80);
        formPanel.add(fullNameLabel);

        JTextField fullNameField = createTextField(50, 110);
        formPanel.add(fullNameField);

        JLabel passwordLabel = createLabel("PASSWORD", 50, 160);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 190, 300, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(8, 2, 5), 1));
        formPanel.add(passwordField);

        JLabel emailLabel = createLabel("EMAIL", 50, 240);
        formPanel.add(emailLabel);

        JTextField emailField = createTextField(50, 270);
        formPanel.add(emailField);

        JCheckBox termsCheckBox = new JCheckBox("I agree to all terms of service");
        termsCheckBox.setBounds(50, 320, 250, 20);
        termsCheckBox.setForeground(new Color(8, 2, 5));
        termsCheckBox.setBackground(formPanel.getBackground());
        formPanel.add(termsCheckBox);

        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(50, 360, 120, 40);
        signInButton.setBackground(new Color(8, 2, 5));
        signInButton.setForeground(new Color(122, 157, 177));
        signInButton.setFont(new Font("Arial", Font.BOLD, 16));
        signInButton.setFocusPainted(false);
        formPanel.add(signInButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(190, 360, 120, 40);
        signUpButton.setBackground(new Color(8, 2, 5));
        signUpButton.setForeground(new Color(122, 157, 177));
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.setFocusPainted(false);
        formPanel.add(signUpButton);

        signUpButton.addActionListener(e -> openSignupWindow());

        add(leftPanel);
        add(formPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openSignupWindow() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 400);
        signupFrame.setLayout(null);
        signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signupFrame.setResizable(false);

        JLabel fullNameLabel = createLabel("Full Name", 50, 30);
        JTextField fullNameField = createTextField(50, 60);
        signupFrame.add(fullNameLabel);
        signupFrame.add(fullNameField);

        JLabel usernameLabel = createLabel("Username", 50, 100);
        JTextField usernameField = createTextField(50, 130);
        signupFrame.add(usernameLabel);
        signupFrame.add(usernameField);

        JLabel passwordLabel = createLabel("Password", 50, 170);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 200, 300, 30);
        passwordField.setBorder(null);
        signupFrame.add(passwordLabel);
        signupFrame.add(passwordField);

        JLabel emailLabel = createLabel("Email", 50, 240);
        JTextField emailField = createTextField(50, 270);
        signupFrame.add(emailLabel);
        signupFrame.add(emailField);

        JButton signupSubmitButton = new JButton("Sign Up");
        signupSubmitButton.setBounds(150, 320, 100, 40);
        signupSubmitButton.setBackground(new Color(8, 2, 5));
        signupSubmitButton.setForeground(Color.WHITE);
        signupSubmitButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupSubmitButton.setFocusPainted(false);
        signupFrame.add(signupSubmitButton);

        signupFrame.setLocationRelativeTo(null);
        signupFrame.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 300, 20);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(8, 2, 5));
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 300, 30);
        textField.setBorder(BorderFactory.createLineBorder(new Color(8, 2, 5), 1));
        return textField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignupPage::new);
    }
}
