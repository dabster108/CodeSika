
import java.awt.*;
import javax.swing.*;

public class SignupPage extends JFrame {

    public SignupPage() {
        setTitle("Code Sika");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 450, 600);
        leftPanel.setBackground(new Color(34, 45, 65));
        leftPanel.setLayout(new BorderLayout());

        JLabel brandingLabel = new JLabel("Code Tutor");
        brandingLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        brandingLabel.setForeground(new Color(255, 255, 255));
        brandingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        brandingLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        leftPanel.add(brandingLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBounds(450, 0, 450, 600);
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(245, 245, 245));

        JLabel animatedLabel = new JLabel();
        animatedLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        animatedLabel.setForeground(new Color(34, 45, 65));
        animatedLabel.setBounds(50, 30, 400, 30);
        formPanel.add(animatedLabel);

        animateText(animatedLabel);

        JLabel usernameLabel = createLabel("USERNAME", 50, 80);
        formPanel.add(usernameLabel);

        JTextField usernameField = createTextField(50, 110);
        formPanel.add(usernameField);

        JLabel passwordLabel = createLabel("PASSWORD", 50, 160);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 190, 300, 30);
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        formPanel.add(passwordField);

        // Show/Hide Password Icon
        JButton showHideButton = new JButton(new ImageIcon("eye_icon.png"));
        showHideButton.setBounds(360, 190, 30, 30);
        showHideButton.setFocusPainted(false);
        showHideButton.setContentAreaFilled(false);
        showHideButton.setBorderPainted(false);
        showHideButton.addActionListener(e -> {
            if (passwordField.getEchoChar() == '*') {
                passwordField.setEchoChar((char) 0);
                showHideButton.setIcon(new ImageIcon("eye_closed_icon.png"));
            } else {
                passwordField.setEchoChar('*');
                showHideButton.setIcon(new ImageIcon("eye_icon.png"));
            }
        });
        formPanel.add(showHideButton);

        // Forgot Password Link
        JLabel forgotPasswordLabel = new JLabel("<HTML><U>Forgot Password?</U></HTML>");
        forgotPasswordLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        forgotPasswordLabel.setForeground(new Color(34, 45, 65));
        forgotPasswordLabel.setBounds(50, 230, 120, 20);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openForgotPasswordWindow();
            }
        });
        formPanel.add(forgotPasswordLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 280, 120, 40);
        loginButton.setBackground(new Color(34, 45, 65));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Verdana", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        formPanel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(190, 280, 120, 40);
        signUpButton.setBackground(new Color(34, 45, 65));
        signUpButton.setForeground(new Color(255, 255, 255));
        signUpButton.setFont(new Font("Verdana", Font.BOLD, 16));
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
        String secondText = "Login or Sign Up";
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
        newWindow.setSize(400, 550); // Adjust window size for additional fields
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newWindow.setLocationRelativeTo(null);
        newWindow.setLayout(null);  // Use absolute positioning

        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setBounds(50, 30, 100, 20);
        fullNameLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        fullNameLabel.setForeground(new Color(34, 45, 65));
        newWindow.add(fullNameLabel);

        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(50, 60, 300, 30);
        fullNameField.setFont(new Font("Verdana", Font.PLAIN, 14));
        fullNameField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        newWindow.add(fullNameField);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 100, 100, 20);
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        usernameLabel.setForeground(new Color(34, 45, 65));
        newWindow.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 130, 300, 30);
        usernameField.setFont(new Font("Verdana", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        newWindow.add(usernameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 170, 100, 20);
        emailLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        emailLabel.setForeground(new Color(34, 45, 65));
        newWindow.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 200, 300, 30);
        emailField.setFont(new Font("Verdana", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        newWindow.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 240, 100, 20);
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        passwordLabel.setForeground(new Color(34, 45, 65));
        newWindow.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 270, 300, 30);
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        newWindow.add(passwordField);

        
        JButton showHideButton = new JButton(new ImageIcon("eye_icon.png"));
        showHideButton.setBounds(360, 270, 30, 30);
        showHideButton.setFocusPainted(false);
        showHideButton.setContentAreaFilled(false);
        showHideButton.setBorderPainted(false);
        showHideButton.addActionListener(e -> {
            if (passwordField.getEchoChar() == '*') {
                passwordField.setEchoChar((char) 0);
                showHideButton.setIcon(new ImageIcon("eye_closed_icon.png"));
            } else {
                passwordField.setEchoChar('*');
                showHideButton.setIcon(new ImageIcon("eye_icon.png"));
            }
        });
        newWindow.add(showHideButton);

        
        JLabel favLangLabel = new JLabel("What's your favorite programming language?");
        favLangLabel.setBounds(50, 310, 300, 20);
        favLangLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        favLangLabel.setForeground(new Color(34, 45, 65));
        newWindow.add(favLangLabel);

        JTextField favLangField = new JTextField();
        favLangField.setBounds(50, 340, 300, 30);
        favLangField.setFont(new Font("Verdana", Font.PLAIN, 14));
        favLangField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        newWindow.add(favLangField);

        
        JLabel favClassLabel = new JLabel("What's your favorite class?");
        favClassLabel.setBounds(50, 380, 300, 20);
        favClassLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        favClassLabel.setForeground(new Color(34, 45, 65));
        newWindow.add(favClassLabel);

        JTextField favClassField = new JTextField();
        favClassField.setBounds(50, 410, 300, 30);
        favClassField.setFont(new Font("Verdana", Font.PLAIN, 14));
        favClassField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        newWindow.add(favClassField);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 460, 300, 40);
        registerButton.setBackground(new Color(34, 45, 65));
        registerButton.setForeground(new Color(255, 255, 255));
        registerButton.setFont(new Font("Verdana", Font.BOLD, 16));
        registerButton.setFocusPainted(false);
        newWindow.add(registerButton);

        newWindow.setVisible(true);
    }

    private void openForgotPasswordWindow() {
        JFrame forgotPasswordWindow = new JFrame("Forgot Password");
        forgotPasswordWindow.setSize(400, 300);
        forgotPasswordWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        forgotPasswordWindow.setLocationRelativeTo(null);
        forgotPasswordWindow.setLayout(null);

        // Favorite Programming Language
        JLabel favLangLabel = new JLabel("What's your favorite programming language?");
        favLangLabel.setBounds(50, 30, 300, 20);
        favLangLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        favLangLabel.setForeground(new Color(34, 45, 65));
        forgotPasswordWindow.add(favLangLabel);

        JTextField favLangField = new JTextField();
        favLangField.setBounds(50, 60, 300, 30);
        favLangField.setFont(new Font("Verdana", Font.PLAIN, 14));
        favLangField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        forgotPasswordWindow.add(favLangField);

       
        JLabel favClassLabel = new JLabel("What's your favorite class?");
        favClassLabel.setBounds(50, 100, 300, 20);
        favClassLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        favClassLabel.setForeground(new Color(34, 45, 65));
        forgotPasswordWindow.add(favClassLabel);

        JTextField favClassField = new JTextField();
        favClassField.setBounds(50, 130, 300, 30);
        favClassField.setFont(new Font("Verdana", Font.PLAIN, 14));
        favClassField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        forgotPasswordWindow.add(favClassField);

      
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(50, 180, 300, 40);
        submitButton.setBackground(new Color(34, 45, 65));
        submitButton.setForeground(new Color(255, 255, 255));
        submitButton.setFont(new Font("Verdana", Font.BOLD, 16));
        submitButton.setFocusPainted(false);
        forgotPasswordWindow.add(submitButton);

        forgotPasswordWindow.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 300, 20);
        label.setFont(new Font("Verdana", Font.BOLD, 12));
        label.setForeground(new Color(34, 45, 65));
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 300, 30);
        textField.setFont(new Font("Verdana", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(34, 45, 65), 1));
        return textField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignupPage::new);
    }
}