package com.codesika.utils;

import com.codesika.ui.background;

public class Opendashboard {
    public static void opendashboard(String username) {
        try {
            new background(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}