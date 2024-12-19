import java.awt.*;
import javax.swing.*;

public class SignupPage extends JFrame {

    public SignupPage() {
        // Frame settings
        setTitle("Code Sika Signup Page");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Disable maximize button
        setLayout(null);

        // Left panel for branding
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 450, 600);
        leftPanel.setBackground(new Color(8, 2, 5)); // Deep black color (#080205)
        leftPanel.setLayout(new BorderLayout());

        // Add "Code Tutor" text at the top of the left panel
        JLabel brandingLabel = new JLabel("Code Tutor");
        brandingLabel.setFont(new Font("Arial", Font.BOLD, 28));
        brandingLabel.setForeground(new Color(122, 157, 177)); // Light blue text for contrast
        brandingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        brandingLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        leftPanel.add(brandingLabel, BorderLayout.NORTH);

        // Right panel for form
        JPanel formPanel = new JPanel();
        formPanel.setBounds(450, 0, 450, 600);
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(122, 157, 177)); // Muted blue color (#7A9DB1)

        JLabel animatedLabel = new JLabel();
        animatedLabel.setFont(new Font("Arial", Font.BOLD, 24));
        animatedLabel.setForeground(new Color(8, 2, 5)); // Deep black text
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
        termsCheckBox.setForeground(new Color(8, 2, 5)); // Deep black text
        termsCheckBox.setBackground(formPanel.getBackground());
        formPanel.add(termsCheckBox);

        // Sign In Button
        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(50, 360, 120, 40);
        signInButton.setBackground(new Color(8, 2, 5)); // Deep black button
        signInButton.setForeground(new Color(122, 157, 177)); // Muted blue text
        signInButton.setFont(new Font("Arial", Font.BOLD, 16));
        signInButton.setFocusPainted(false);
        formPanel.add(signInButton);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(190, 360, 120, 40);
        signUpButton.setBackground(new Color(8, 2, 5)); // Deep black button
        signUpButton.setForeground(new Color(122, 157, 177)); // Muted blue text
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.setFocusPainted(false);
        signUpButton.addActionListener(e -> openSignupWindow());
        formPanel.add(signUpButton);

        // Add panels to frame
        add(leftPanel);
        add(formPanel);

        // Set frame visibility
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void animateText(JLabel label) {
        String firstText = "Welcome to Code Sika";
        String secondText = "Sign In or Sign Up";
        int duration = 15000; // 15 seconds

        Timer timer = new Timer(100, null);
        StringBuilder currentText = new StringBuilder();
        boolean[] isSecondPhase = {false}; // Track if the second phase is active
        long startTime = System.currentTimeMillis();
        int[] charIndex = {0};

        timer.addActionListener(e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= duration) {
                label.setText(firstText); // Permanently set "Welcome to Code Sika"
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
                    Thread.sleep(1000); // Pause before switching
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                isSecondPhase[0] = !isSecondPhase[0]; // Toggle phase
                currentText.setLength(0); // Clear current text
                charIndex[0] = 0; // Reset character index
            }
        });

        timer.start();
    }

    private void openSignupWindow() {
        JFrame newWindow = new JFrame("Sign Up");
        newWindow.setSize(400, 300);
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newWindow.setLocationRelativeTo(null);
        newWindow.setVisible(true);
    }

    // Helper method to create labels
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 300, 20);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(8, 2, 5)); // Deep black text
        return label;
    }

    // Helper method to create text fields
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
