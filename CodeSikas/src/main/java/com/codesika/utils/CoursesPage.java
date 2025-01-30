package com.codesika.utils;
// updated coursespage
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CoursesPage extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JScrollPane pythonScrollPane;
    private JScrollPane javaScrollPane;
    private JScrollPane cppScrollPane;
    private JScrollPane jsScrollPane;
    private JLabel pythonProgressLabel;
    private JLabel javaProgressLabel;
    private JLabel cppProgressLabel;
    private JLabel jsProgressLabel;
    private JLabel pythonProgressLabelHome;
    private JLabel javaProgressLabelHome;
    private JLabel cppProgressLabelHome;
    private JLabel jsProgressLabelHome;

    public CoursesPage(CardLayout cardLayout, JPanel contentPanel, JLabel pythonProgressLabelHome, JLabel javaProgressLabelHome, JLabel cppProgressLabelHome, JLabel jsProgressLabelHome) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.pythonProgressLabelHome = pythonProgressLabelHome;
        this.javaProgressLabelHome = javaProgressLabelHome;
        this.cppProgressLabelHome = cppProgressLabelHome;
        this.jsProgressLabelHome = jsProgressLabelHome;

        // Initialize the Python panel and its scroll pane
        JPanel pythonPanel = createPythonPanel();
        contentPanel.add(pythonPanel, "Python");

        // Initialize the Java panel and its scroll pane
        JPanel javaPanel = createJavaPanel();
        contentPanel.add(javaPanel, "Java");

        // Initialize the C++ panel and its scroll pane
        JPanel cppPanel = createCppPanel();
        contentPanel.add(cppPanel, "C++");

        // Initialize the JavaScript panel and its scroll pane
        JPanel jsPanel = createJavaScriptPanel();
        contentPanel.add(jsPanel, "JavaScript");

        setLayout(new BorderLayout());
        setBackground(Color.BLACK); // Set background color to black

        JLabel coursesTitle = new JLabel("Courses", SwingConstants.CENTER);
        coursesTitle.setFont(new Font("Poppins", Font.BOLD, 32));
        coursesTitle.setForeground(Color.WHITE); // Set text color to white
        coursesTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(coursesTitle, BorderLayout.NORTH);

        JPanel coursesContentPanel = new JPanel();
        coursesContentPanel.setLayout(new BoxLayout(coursesContentPanel, BoxLayout.Y_AXIS));
        coursesContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add course panels
        coursesContentPanel.add(createCoursePanel("Python", "45 Videos"));
        coursesContentPanel.add(createCoursePanel("Java", "60 Videos"));
        coursesContentPanel.add(createCoursePanel("C++", "78 Videos"));
        coursesContentPanel.add(createCoursePanel("JavaScript", "35 Videos"));

        JScrollPane coursesScrollPane = new JScrollPane(coursesContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(coursesScrollPane, BorderLayout.CENTER);
    }

    // Method to create individual course panels
    private JPanel createCoursePanel(String title, String description) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 120)); // Fixed consistent size
        panel.setBackground(new Color(255, 248, 220));
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
            if (title.equals("Python")) {
                pythonScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
                cardLayout.show(contentPanel, "Python");
            } else if (title.equals("Java")) {
                javaScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
                cardLayout.show(contentPanel, "Java");
            } else if (title.equals("C++")) {
                if (cppScrollPane != null) {
                    cppScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
                }
                cardLayout.show(contentPanel, "C++");
            } else if (title.equals("JavaScript")) {
                if (jsScrollPane != null) {
                    jsScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
                }
                cardLayout.show(contentPanel, "JavaScript");
            }
        });
        panel.add(learnMoreButton, BorderLayout.SOUTH);

        return panel;
    }

    // Method to create the Python course panel
    public JPanel createPythonPanel() {
        JPanel pythonPanel = new JPanel(new BorderLayout());
        pythonPanel.setBackground(new Color(255, 255, 255)); // Light mode background

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setMargin(new Insets(10, 10, 10, 10)); // Add left padding

        StringBuilder content = new StringBuilder();
        content.append("<html><body style='font-family:Inter; font-size:20px;'>"); // Set font to Inter and size to 28

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\python.txt"))) {
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

        // Initialize pythonProgressLabel
        pythonProgressLabel = new JLabel("Python Progression: 0%");
        pythonProgressLabel.setFont(new Font("Inter", Font.BOLD, 18)); // Set font to bold
        pythonProgressLabel.setForeground(Color.BLACK); // Set text color to black
        pythonPanel.add(pythonProgressLabel, BorderLayout.NORTH);

        // Add Quiz Button
        JButton quizButton = new JButton("Start Python Quiz");
        quizButton.setFont(new Font("Arial", Font.BOLD, 16));
        quizButton.setBackground(new Color(30, 144, 255));
        quizButton.setForeground(Color.WHITE);
        quizButton.setFocusPainted(false);
        quizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        quizButton.addActionListener(e -> showQuiz("Python"));
        pythonPanel.add(quizButton, BorderLayout.SOUTH);

        // Add Notes Section
        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "NOTES", 0, 0, new Font("Arial", Font.BOLD, 18)));
        notesPanel.setPreferredSize(new Dimension(400, 200));

        JTextArea notesArea = new JTextArea();
        notesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(notesArea);
        notesPanel.add(notesScrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(30, 144, 255));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\python_notes.txt", true)) {
                writer.write(notesArea.getText() + "\n");
                notesArea.setText("");
                JOptionPane.showMessageDialog(this, "Note saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save note.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        notesPanel.add(saveButton, BorderLayout.SOUTH);

        pythonPanel.add(notesPanel, BorderLayout.EAST);

        // Add scroll listener to update progress
        pythonScrollPane.getViewport().addChangeListener(e -> {
            JViewport viewport = (JViewport) e.getSource();
            int extentHeight = viewport.getExtentSize().height;
            int viewHeight = viewport.getViewSize().height;
            int viewPositionY = viewport.getViewPosition().y;

            int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
            pythonProgressLabel.setText("Python Progression: " + progress + "%");
            pythonProgressLabelHome.setText("Python Progression: " + progress + "%"); // Update home label
        });

        return pythonPanel;
    }

    // Method to create the Java course panel
    public JPanel createJavaPanel() {
        JPanel javaPanel = new JPanel(new BorderLayout());
        javaPanel.setBackground(new Color(255, 255, 255)); // Light mode background

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setMargin(new Insets(10, 10, 10, 10)); // Add left padding

        StringBuilder content = new StringBuilder();
        content.append("<html><body style='font-family:Inter; font-size:24px;'>"); // Set font to Inter and size to 28

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\java.txt"))) {
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

        javaScrollPane = new JScrollPane(textPane);
        javaPanel.add(javaScrollPane, BorderLayout.CENTER);

        // Initialize javaProgressLabel
        javaProgressLabel = new JLabel("Java Progression: 0%");
        javaProgressLabel.setFont(new Font("Inter", Font.BOLD, 18));
        javaProgressLabel.setForeground(Color.black); // Light gray text color
        javaPanel.add(javaProgressLabel, BorderLayout.NORTH);

        // Add Quiz Button
        JButton quizButton = new JButton("Start Java Quiz");
        quizButton.setFont(new Font("Arial", Font.BOLD, 16));
        quizButton.setBackground(new Color(30, 144, 255));
        quizButton.setForeground(Color.WHITE);
        quizButton.setFocusPainted(false);
        quizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        quizButton.addActionListener(e -> showQuiz("Java"));
        javaPanel.add(quizButton, BorderLayout.SOUTH);

        // Add scroll listener to update progress
        javaScrollPane.getViewport().addChangeListener(e -> {
            JViewport viewport = (JViewport) e.getSource();
            int extentHeight = viewport.getExtentSize().height;
            int viewHeight = viewport.getViewSize().height;
            int viewPositionY = viewport.getViewPosition().y;

            int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
            javaProgressLabel.setText("Java Progression: " + progress + "%");
            javaProgressLabelHome.setText("Java Progression: " + progress + "%"); // Update home label
        });

        // Add Notes Section
        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "NOTES", 0, 0, new Font("Arial", Font.BOLD, 18)));
        notesPanel.setPreferredSize(new Dimension(400, 200));

        JTextArea notesArea = new JTextArea();
        notesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(notesArea);
        notesPanel.add(notesScrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(30, 144, 255));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\java_notes.txt", true)) {
                writer.write(notesArea.getText() + "\n");
                notesArea.setText("");
                JOptionPane.showMessageDialog(this, "Note saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save note.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        notesPanel.add(saveButton, BorderLayout.SOUTH);

        javaPanel.add(notesPanel, BorderLayout.EAST);

        return javaPanel;
    }

    // Method to create the C++ course panel
    public JPanel createCppPanel() {
        JPanel cppPanel = new JPanel(new BorderLayout());
        cppPanel.setBackground(new Color(255, 255, 255)); // Light mode background

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setFont(new Font("Poppins", Font.PLAIN, 24)); // Set font to Poppins and size to 24
        textPane.setMargin(new Insets(10, 10, 10, 10)); // Add left padding

        StringBuilder content = new StringBuilder();
        content.append("<html><body style='font-family:Poppins; font-size:24px;'>"); // Set font to Poppins and size to 24

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\cplusplus.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("<br>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        content.append("</body></html>");
        textPane.setText(content.toString());

        cppScrollPane = new JScrollPane(textPane);
        cppPanel.add(cppScrollPane, BorderLayout.CENTER);

        // Initialize cppProgressLabel
        cppProgressLabel = new JLabel("C++ Progression: 0%");
        cppProgressLabel.setFont(new Font("Inter", Font.BOLD, 18));
        cppProgressLabel.setForeground(Color.BLACK); // Set text color to black
        cppPanel.add(cppProgressLabel, BorderLayout.NORTH);

        // Add Quiz Button
        JButton quizButton = new JButton("Start C++ Quiz");
        quizButton.setFont(new Font("Arial", Font.BOLD, 16));
        quizButton.setBackground(new Color(30, 144, 255));
        quizButton.setForeground(Color.WHITE);
        quizButton.setFocusPainted(false);
        quizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        quizButton.addActionListener(e -> showQuiz("C++"));
        cppPanel.add(quizButton, BorderLayout.SOUTH);

        // Add scroll listener to update progress
        cppScrollPane.getViewport().addChangeListener(e -> {
            JViewport viewport = (JViewport) e.getSource();
            int extentHeight = viewport.getExtentSize().height;
            int viewHeight = viewport.getViewSize().height;
            int viewPositionY = viewport.getViewPosition().y;

            int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
            cppProgressLabel.setText("C++ Progression: " + progress + "%");
            cppProgressLabelHome.setText("C++ Progression: " + progress + "%"); // Update home label
        });

        // Add Notes Section
        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "NOTES", 0, 0, new Font("Arial", Font.BOLD, 18)));
        notesPanel.setPreferredSize(new Dimension(400, 200));

        JTextArea notesArea = new JTextArea();
        notesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(notesArea);
        notesPanel.add(notesScrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(30, 144, 255));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\cpp_notes.txt", true)) {
                writer.write(notesArea.getText() + "\n");
                notesArea.setText("");
                JOptionPane.showMessageDialog(this, "Note saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save note.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        notesPanel.add(saveButton, BorderLayout.SOUTH);

        cppPanel.add(notesPanel, BorderLayout.EAST);

        return cppPanel;
    }

    // Method to create the JavaScript course panel
    public JPanel createJavaScriptPanel() {
        JPanel jsPanel = new JPanel(new BorderLayout());
        jsPanel.setBackground(new Color(255, 255, 255)); // Light mode background

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setFont(new Font("Poppins", Font.PLAIN, 24)); // Set font to Poppins and size to 24
        textPane.setMargin(new Insets(10, 10, 10, 10)); // Add left padding

        StringBuilder content = new StringBuilder();
        content.append("<html><body style='font-family:Poppins; font-size:24px;'>"); // Set font to Poppins and size to 24

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\javascript.txt"))) {
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

        jsScrollPane = new JScrollPane(textPane);
        jsPanel.add(jsScrollPane, BorderLayout.CENTER);

        // Initialize jsProgressLabel
        jsProgressLabel = new JLabel("JavaScript Progression: 0%");
        jsProgressLabel.setFont(new Font("Inter", Font.BOLD, 18));
        jsProgressLabel.setForeground(Color.BLACK); // Set text color to black
        jsPanel.add(jsProgressLabel, BorderLayout.NORTH);

        // Add Quiz Button
        JButton quizButton = new JButton("Start JavaScript Quiz");
        quizButton.setFont(new Font("Arial", Font.BOLD, 16));
        quizButton.setBackground(new Color(30, 144, 255));
        quizButton.setForeground(Color.WHITE);
        quizButton.setFocusPainted(false);
        quizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        quizButton.addActionListener(e -> showQuiz("JavaScript"));
        jsPanel.add(quizButton, BorderLayout.SOUTH);

        // Add scroll listener to update progress
        jsScrollPane.getViewport().addChangeListener(e -> {
            JViewport viewport = (JViewport) e.getSource();
            int extentHeight = viewport.getExtentSize().height;
            int viewHeight = viewport.getViewSize().height;
            int viewPositionY = viewport.getViewPosition().y;

            int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
            jsProgressLabel.setText("JavaScript Progression: " + progress + "%");
            jsProgressLabelHome.setText("JavaScript Progression: " + progress + "%"); // Update home label
        });

        // Add Notes Section
        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "NOTES", 0, 0, new Font("Arial", Font.BOLD, 18)));
        notesPanel.setPreferredSize(new Dimension(400, 200));

        JTextArea notesArea = new JTextArea();
        notesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(notesArea);
        notesPanel.add(notesScrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(30, 144, 255));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\javascript_notes.txt", true)) {
                writer.write(notesArea.getText() + "\n");
                notesArea.setText("");
                JOptionPane.showMessageDialog(this, "Note saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save note.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        notesPanel.add(saveButton, BorderLayout.SOUTH);

        jsPanel.add(notesPanel, BorderLayout.EAST);

        return jsPanel;
    }

    // Method to show a quiz for a course
    private void showQuiz(String courseName) {
        JPanel quizPanel = new JPanel(new BorderLayout());
        quizPanel.setBackground(Color.WHITE);

        // Quiz title
        JLabel quizTitle = new JLabel(courseName + " Quiz", SwingConstants.CENTER);
        quizTitle.setFont(new Font("Arial", Font.BOLD, 24));
        quizPanel.add(quizTitle, BorderLayout.NORTH);

        // Quiz questions panel
        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));

        // Add 10 questions
        List<ButtonGroup> buttonGroups = new ArrayList<>();
        List<String[]> questions = getQuestionsForCourse(courseName);

        for (int i = 0; i < 10; i++) {
            String[] questionData = questions.get(i);
            JLabel questionLabel = new JLabel((i + 1) + ". " + questionData[0]);
            questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            questionsPanel.add(questionLabel);

            JRadioButton option1 = new JRadioButton(questionData[1]);
            option1.setActionCommand(questionData[1]);
            JRadioButton option2 = new JRadioButton(questionData[2]);
            option2.setActionCommand(questionData[2]);
            JRadioButton option3 = new JRadioButton(questionData[3]);
            option3.setActionCommand(questionData[3]);
            JRadioButton option4 = new JRadioButton(questionData[4]);
            option4.setActionCommand(questionData[4]);

            ButtonGroup group = new ButtonGroup();
            group.add(option1);
            group.add(option2);
            group.add(option3);
            group.add(option4);

            buttonGroups.add(group);

            questionsPanel.add(option1);
            questionsPanel.add(option2);
            questionsPanel.add(option3);
            questionsPanel.add(option4);
        }

        JScrollPane questionsScrollPane = new JScrollPane(questionsPanel);
        quizPanel.add(questionsScrollPane, BorderLayout.CENTER);

        // Timer label
        JLabel timerLabel = new JLabel("Time remaining: 15:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quizPanel.add(timerLabel, BorderLayout.NORTH);

        // Start the timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int timeLeft = 900; // 15 minutes in seconds

            @Override
            public void run() {
                if (timeLeft > 0) {
                    int minutes = timeLeft / 60;
                    int seconds = timeLeft % 60;
                    timerLabel.setText("Time remaining: " + String.format("%02d:%02d", minutes, seconds));
                    timeLeft--;
                } else {
                    timer.cancel();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(quizPanel, "Time's up! Submitting your quiz.", "Time's Up", JOptionPane.INFORMATION_MESSAGE);
                        evaluateQuiz(buttonGroups, questions);
                    });
                }
            }
        }, 0, 1000); // Update every second

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(30, 144, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(e -> {
            timer.cancel();
            evaluateQuiz(buttonGroups, questions);
        });
        quizPanel.add(submitButton, BorderLayout.SOUTH);

        // Add quiz panel to content panel
        contentPanel.add(quizPanel, courseName + " Quiz");
        cardLayout.show(contentPanel, courseName + " Quiz");
    }

    // Method to get questions for a specific course
    private List<String[]> getQuestionsForCourse(String courseName) {
        List<String[]> questions = new ArrayList<>();
        switch (courseName) {
            case "Python":
                questions.add(new String[]{"What is the output of 'print(2 + 2)'?", "4", "22", "Error", "None of the above", "4"});
                questions.add(new String[]{"Which keyword is used to define a function in Python?", "def", "function", "define", "func", "def"});
                questions.add(new String[]{"What is the correct way to create a list in Python?", "[1, 2, 3]", "{1, 2, 3}", "(1, 2, 3)", "1, 2, 3", "[1, 2, 3]"});
                questions.add(new String[]{"Which of the following is a Python framework?", "Django", "Spring", "React", "Angular", "Django"});
                questions.add(new String[]{"What is the output of 'print(type(3.14))'?", "<class 'float'>", "<class 'int'>", "<class 'str'>", "<class 'double'>", "<class 'float'>"});
                questions.add(new String[]{"Which method is used to add an element to a list?", "append()", "add()", "insert()", "push()", "append()"});
                questions.add(new String[]{"What is the output of 'print(\"Hello\" * 3)'?", "HelloHelloHello", "Hello3", "Error", "None of the above", "HelloHelloHello"});
                questions.add(new String[]{"Which of the following is a Python IDE?", "PyCharm", "Eclipse", "NetBeans", "IntelliJ", "PyCharm"});
                questions.add(new String[]{"What is the output of 'print(len(\"Python\"))'?", "6", "5", "7", "Error", "6"});
                questions.add(new String[]{"Which operator is used for exponentiation in Python?", "**", "^", "^^", "//", "**"});
                break;
            case "Java":
                questions.add(new String[]{"What is the output of 'System.out.println(2 + 2)'?", "4", "22", "Error", "None of the above", "4"});
                questions.add(new String[]{"Which keyword is used to define a class in Java?", "class", "interface", "struct", "object", "class"});
                questions.add(new String[]{"What is the correct way to create an array in Java?", "int[] arr = {1, 2, 3};", "int arr[] = {1, 2, 3};", "int arr = {1, 2, 3};", "int arr = new int[3];", "int[] arr = {1, 2, 3};"});
                questions.add(new String[]{"Which of the following is a Java framework?", "Spring", "Django", "React", "Angular", "Spring"});
                questions.add(new String[]{"What is the output of 'System.out.println(3.14 instanceof Double)'?", "true", "false", "Error", "None of the above", "false"});
                questions.add(new String[]{"Which method is used to add an element to an ArrayList?", "add()", "append()", "insert()", "push()", "add()"});
                questions.add(new String[]{"What is the output of 'System.out.println(\"Hello\" + 3)'?", "Hello3", "HelloHelloHello", "Error", "None of the above", "Hello3"});
                questions.add(new String[]{"Which of the following is a Java IDE?", "IntelliJ", "PyCharm", "Eclipse", "NetBeans", "IntelliJ"});
                questions.add(new String[]{"What is the output of 'System.out.println(\"Java\".length())'?", "4", "5", "6", "Error", "4"});
                questions.add(new String[]{"Which operator is used for exponentiation in Java?", "Math.pow()", "^", "**", "//", "Math.pow()"});
                break;
            case "C++":
                questions.add(new String[]{"What is the output of 'cout << 2 + 2;'?", "4", "22", "Error", "None of the above", "4"});
                questions.add(new String[]{"Which keyword is used to define a class in C++?", "class", "struct", "interface", "object", "class"});
                questions.add(new String[]{"What is the correct way to create an array in C++?", "int arr[] = {1, 2, 3};", "int[] arr = {1, 2, 3};", "int arr = {1, 2, 3};", "int arr = new int[3];", "int arr[] = {1, 2, 3};"});
                questions.add(new String[]{"Which of the following is a C++ framework?", "Qt", "Spring", "React", "Angular", "Qt"});
                questions.add(new String[]{"What is the output of 'cout << sizeof(3.14);'?", "8", "4", "16", "Error", "8"});
                questions.add(new String[]{"Which method is used to add an element to a vector?", "push_back()", "add()", "insert()", "append()", "push_back()"});
                questions.add(new String[]{"What is the output of 'cout << \"Hello\" + 3;'?", "Error", "Hello3", "HelloHelloHello", "None of the above", "Error"});
                questions.add(new String[]{"Which of the following is a C++ IDE?", "Code::Blocks", "PyCharm", "Eclipse", "NetBeans", "Code::Blocks"});
                questions.add(new String[]{"What is the output of 'cout << strlen(\"C++\");'?", "3", "4", "5", "Error", "3"});
                questions.add(new String[]{"Which operator is used for exponentiation in C++?", "pow()", "^", "**", "//", "pow()"});
                break;
            case "JavaScript":
                questions.add(new String[]{"What is the output of 'console.log(2 + 2)'?", "4", "22", "Error", "None of the above", "4"});
                questions.add(new String[]{"Which keyword is used to define a variable in JavaScript?", "let", "var", "const", "all of the above", "all of the above"});
                questions.add(new String[]{"What is the correct way to create an array in JavaScript?", "[1, 2, 3]", "{1, 2, 3}", "(1, 2, 3)", "1, 2, 3", "[1, 2, 3]"});
                questions.add(new String[]{"Which of the following is a JavaScript framework?", "React", "Spring", "Django", "Angular", "React"});
                questions.add(new String[]{"What is the output of 'console.log(typeof 3.14)'?", "number", "float", "string", "double", "number"});
                questions.add(new String[]{"Which method is used to add an element to an array?", "push()", "add()", "insert()", "append()", "push()"});
                questions.add(new String[]{"What is the output of 'console.log(\"Hello\" + 3)'?", "Hello3", "HelloHelloHello", "Error", "None of the above", "Hello3"});
                questions.add(new String[]{"Which of the following is a JavaScript IDE?", "VS Code", "PyCharm", "Eclipse", "NetBeans", "VS Code"});
                questions.add(new String[]{"What is the output of 'console.log(\"JavaScript\".length)'?", "10", "11", "12", "Error", "10"});
                questions.add(new String[]{"Which operator is used for exponentiation in JavaScript?", "**", "^", "^^", "//", "**"});
                break;
        }
        return questions;
    }

    // Method to evaluate the quiz
    private void evaluateQuiz(List<ButtonGroup> buttonGroups, List<String[]> questions) {
        int score = 0;
        for (int i = 0; i < buttonGroups.size(); i++) {
            ButtonGroup group = buttonGroups.get(i);
            String[] questionData = questions.get(i);
            if (group.getSelection() != null && group.getSelection().getActionCommand().equals(questionData[5])) {
                score++;
            }
        }
        JOptionPane.showMessageDialog(this, "Your score: " + score + "/10", "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
    }
}