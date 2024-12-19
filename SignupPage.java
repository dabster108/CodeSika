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

        JLabel animatedLabel = new JLabel();
        animatedLabel.setFont(new Font("Arial", Font.BOLD, 24));
        animatedLabel.setForeground(new Color(8, 2, 5));
        animatedLabel.setBounds(50, 30, 400, 30);
        formPanel.add(animatedLabel);

        animateText(animatedLabel);

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
        signUpButton.addActionListener(e -> openSignupWindow());
        formPanel.add(signUpButton);

        add(leftPanel);
        add(formPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void animateText(JLabel label) {
        String firstText = "Welcome to Code Sika";
        String secondText = "Sign In or Sign Up";
        int duration = 15000;

        Timer timer = new Timer(100, null);
        StringBuilder currentText = new StringBuilder();
        boolean[] isSecondPhase = {false};
        long startTime = System.currentTimeMillis();
        int[] charIndex = {0};

        timer.addActionListener(e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= duration) {
                label.setText(firstText);
                timer.stop();
                return;
            }

            String targetText = isSecondPhase[0] ? secondText : firstText;

            if (charIndex[0] < targetText.length()) {
                currentText.append(targetText.charAt(charIndex[0]));
                label.setText(currentText.toString());
                charIndex[0]++;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                isSecondPhase[0] = !isSecondPhase[0];
                currentText.setLength(0);
                charIndex[0] = 0;
            }
        });

        timer.start();
    }

    private void openSignupWindow() {
        JFrame newWindow = new JFrame("Sign Up");
        newWindow.setSize(400, 400); // Adjust window size for additional fields
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newWindow.setLocationRelativeTo(null);
        newWindow.setLayout(null);  // Use absolute positioning

        // Full Name
        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setBounds(50, 30, 100, 20);
        fullNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        fullNameLabel.setForeground(new Color(8, 2, 5));
        newWindow.add(fullNameLabel);

        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(50, 60, 300, 30);
        fullNameField.setBorder(BorderFactory.createLineBorder(new Color(8, 2, 5), 1));
        newWindow.add(fullNameField);

        // Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 100, 100, 20);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usernameLabel.setForeground(new Color(8, 2, 5));
        newWindow.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 130, 300, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(8, 2, 5), 1));
        newWindow.add(usernameField);

        // Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 170, 100, 20);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        emailLabel.setForeground(new Color(8, 2, 5));
        newWindow.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 200, 300, 30);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(8, 2, 5), 1));
        newWindow.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 240, 100, 20);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passwordLabel.setForeground(new Color(8, 2, 5));
        newWindow.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 270, 300, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(8, 2, 5), 1));
        newWindow.add(passwordField);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 320, 300, 40);
        registerButton.setBackground(new Color(8, 2, 5));
        registerButton.setForeground(new Color(122, 157, 177));
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setFocusPainted(false);
        // You can add an action listener to handle registration logic
        newWindow.add(registerButton);

        newWindow.setVisible(true);
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
