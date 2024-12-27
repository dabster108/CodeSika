package com.codesika;

import javax.swing.SwingUtilities;

import com.codesika.ui.SignupPage;

public class codesikaApp {

    public static void main(String[] args) {
        // Run the Swing application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(codesikaApp::startApplication);
    }

    private static void startApplication() {
        try {
            // Create and display the signup page
            new SignupPage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Uncomment the following block if you want to create and display the background page directly
        // try {
        //     // Create and display the background page
        //     new background("testUser");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}