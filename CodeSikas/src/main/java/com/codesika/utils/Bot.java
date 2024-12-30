// package com.codesika.utils;

// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Cursor;
// import java.awt.Dimension;
// import java.awt.Font;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;

// import javax.swing.BorderFactory;
// import javax.swing.JButton;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;

// import org.json.JSONArray;
// import org.json.JSONObject;

// public class Bot extends JPanel {

//     private static final String API_KEY = "61251f6383msh83be804227894e3p12f143jsne692b66bd2ad"; // Replace with your actual API key
//     private static final String API_URL = "https://cheapest-gpt-4-turbo-gpt-4-vision-chatgpt-openai-ai-api.p.rapidapi.com/v1/chat/completions"; // Replace with your actual API URL

//     private JTextArea chatArea;
//     private JTextField inputField;
//     private JButton sendButton;

//     public Bot() {
//         setLayout(new BorderLayout());
//         setBackground(new Color(45, 45, 45));
//         setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
//         setPreferredSize(new Dimension(400, 400)); // Adjusted size for horizontal layout

//         // Chat Area
//         chatArea = new JTextArea();
//         chatArea.setFont(new Font("Poppins", Font.PLAIN, 16));
//         chatArea.setForeground(Color.WHITE);
//         chatArea.setBackground(new Color(45, 45, 45));
//         chatArea.setEditable(false);
//         chatArea.setLineWrap(true);
//         chatArea.setWrapStyleWord(true);
//         JScrollPane chatScrollPane = new JScrollPane(chatArea);
//         chatScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//         add(chatScrollPane, BorderLayout.CENTER);

//         // Input Panel
//         JPanel inputPanel = new JPanel(new BorderLayout());
//         inputPanel.setBackground(new Color(45, 45, 45));
//         inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         // Input Field
//         inputField = new JTextField();
//         inputField.setFont(new Font("Poppins", Font.PLAIN, 16));
//         inputField.setForeground(Color.WHITE);
//         inputField.setBackground(new Color(30, 30, 30));
//         inputField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
//         inputPanel.add(inputField, BorderLayout.CENTER);

//         // Send Button
//         sendButton = new JButton("Send Message");
//         sendButton.setFont(new Font("Poppins", Font.BOLD, 16));
//         sendButton.setForeground(Color.WHITE);
//         sendButton.setBackground(new Color(30, 144, 255));
//         sendButton.setFocusPainted(false);
//         sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//         sendButton.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2, true));
//         sendButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String userInput = inputField.getText();
//                 if (!userInput.isEmpty()) {
//                     chatArea.append("User: " + userInput + "\n");
//                     String botResponse = getBotResponse(userInput);
//                     chatArea.append("Bot: " + botResponse + "\n");
//                     inputField.setText("");
//                 }
//             }
//         });
//         inputPanel.add(sendButton, BorderLayout.EAST);

//         add(inputPanel, BorderLayout.SOUTH);
//     }

//     private String getBotResponse(String userInput) {
//         try {
//             URL url = new URL(API_URL);
//             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//             conn.setRequestMethod("POST");
//             conn.setRequestProperty("Content-Type", "application/json");
//             conn.setRequestProperty("x-rapidapi-host", "cheapest-gpt-4-turbo-gpt-4-vision-chatgpt-openai-ai-api.p.rapidapi.com");
//             conn.setRequestProperty("x-rapidapi-key", API_KEY);
//             conn.setDoOutput(true);

//             JSONObject jsonInput = new JSONObject();
//             jsonInput.put("messages", new JSONArray().put(new JSONObject().put("role", "user").put("content", userInput)));
//             jsonInput.put("model", "gpt-4-turbo");

//             try (OutputStream os = conn.getOutputStream()) {
//                 byte[] input = jsonInput.toString().getBytes("utf-8");
//                 os.write(input, 0, input.length);
//             }

//             try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
//                 StringBuilder response = new StringBuilder();
//                 String responseLine;
//                 while ((responseLine = br.readLine()) != null) {
//                     response.append(responseLine.trim());
//                 }
//                 JSONObject jsonResponse = new JSONObject(response.toString());
//                 return jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "Error: Unable to get response from the bot.";
//         }
//     }
// }