package com.codesika.utils;

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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Import JOptionPane
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;


public class CoursesPage extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JScrollPane pythonScrollPane;
    private JScrollPane javaScrollPane;
    private JScrollPane cppScrollPane; // Declare cppScrollPane
    private JScrollPane jsScrollPane; // Declare jsScrollPane
    private JLabel pythonProgressLabel;
    private JLabel javaProgressLabel;
    private JLabel cppProgressLabel; // Declare cppProgressLabel
    private JLabel jsProgressLabel; // Declare jsProgressLabel
    private JLabel pythonProgressLabelHome;
    private JLabel javaProgressLabelHome;

    public CoursesPage(CardLayout cardLayout, JPanel contentPanel, JLabel pythonProgressLabelHome, JLabel javaProgressLabelHome) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.pythonProgressLabelHome = pythonProgressLabelHome;
        this.javaProgressLabelHome = javaProgressLabelHome;

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
                cppScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
                cardLayout.show(contentPanel, "C++");
            } else if (title.equals("JavaScript")) {
                jsScrollPane.getVerticalScrollBar().setValue(0); // Scroll to the top
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
       // Initialize pythonProgressLabel
    pythonProgressLabel = new JLabel("Python Progression: 0%");
    pythonProgressLabel.setFont(new Font("Inter", Font.BOLD, 18)); // Set font to bold
    pythonProgressLabel.setForeground(Color.BLACK); // Set text color to black
    pythonPanel.add(pythonProgressLabel, BorderLayout.NORTH);

        Quizzes quizzes = new Quizzes();
        JPanel quizPanel = quizzes.createQuizPanel("Python");
        pythonPanel.add(quizPanel, BorderLayout.SOUTH);
    


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

        Quizzes quizzes = new Quizzes();
        JPanel quizPanel = quizzes.createQuizPanel("Java");
        javaPanel.add(quizPanel, BorderLayout.SOUTH);

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
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\3108d\\Desktop\\CodeSika\\CodeSikas\\src\\main\\resources\\notes\\cpp.txt"))) {
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

        textPane.setText(content.toString());

        cppScrollPane = new JScrollPane(textPane);
        cppPanel.add(cppScrollPane, BorderLayout.CENTER);

        // Initialize cppProgressLabel
        cppProgressLabel = new JLabel("C++ Progression: 0%");
        cppProgressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cppPanel.add(cppProgressLabel, BorderLayout.NORTH);

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

        // Add scroll listener to update progress
        cppScrollPane.getViewport().addChangeListener(e -> {
            JViewport viewport = (JViewport) e.getSource();
            int extentHeight = viewport.getExtentSize().height;
            int viewHeight = viewport.getViewSize().height;
            int viewPositionY = viewport.getViewPosition().y;

            int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
            cppProgressLabel.setText("C++ Progression: " + progress + "%");
        });

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

    textPane.setText(content.toString());

    jsScrollPane = new JScrollPane(textPane);
    jsPanel.add(jsScrollPane, BorderLayout.CENTER);

    // Initialize jsProgressLabel
    jsProgressLabel = new JLabel("JavaScript Progression: 0%");
    jsProgressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    jsPanel.add(jsProgressLabel, BorderLayout.NORTH);

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

    // Add scroll listener to update progress
    jsScrollPane.getViewport().addChangeListener(e -> {
        JViewport viewport = (JViewport) e.getSource();
        int extentHeight = viewport.getExtentSize().height;
        int viewHeight = viewport.getViewSize().height;
        int viewPositionY = viewport.getViewPosition().y;

        int progress = (int) ((viewPositionY + extentHeight) / (double) viewHeight * 100);
        jsProgressLabel.setText("JavaScript Progression: " + progress + "%");
    });

    return jsPanel;
}
}