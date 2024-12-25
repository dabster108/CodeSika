# Code Sika - Interactive Code Tutor

**Code Sika** is an interactive, gamified desktop application designed to simplify the learning of programming languages like C, Python, and Java. It provides a platform for learners to engage in hands-on coding practice with quizzes and real-time feedback, enhancing their learning experience. The application is built using Java Swing for the GUI and MySQL for database management.

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Setup](#setup)
4. [Technologies Used](#technologies-used)
5. [Database Setup](#database-setup)
6. [License](#license)

## Introduction
In today's fast-paced world, **Code Sika** addresses the challenges of traditional programming education by providing an engaging, self-paced learning platform. Users can start with basic lessons and unlock advanced ones as they complete quizzes and coding challenges. The application also allows users to track progress, earn points, and interact with the learning community.

## Features
- **User-Friendly Interface:** Easy-to-use GUI that simplifies navigation and enhances the learning experience.
- **Gamified Learning:** Earn points and unlock new courses as you complete quizzes and challenges.
- **Real-Time Feedback:** Immediate feedback on your code helps identify mistakes and learn faster.
- **Multi-Language Support:** Courses available in C, Python, and Java.
- **Secure Login and User Profiles:** Register, login, and track your progress with personalized profiles.
- **Community Interaction:** Users can interact with others to ask questions and share tips.
- **Course Access and Unlocking:** Complete quizzes to unlock new programming courses and challenges.

This project contains a Java program located in the codesikainterior package. Follow the steps below to compile and run the program.

Requirements
Java Development Kit (JDK) installed
Command-line terminal (Command Prompt, PowerShell, or a terminal emulator)
Steps to Compile and Run
1. Navigate to the Source Folder
Make sure you are in the directory containing the src folder. Open a terminal and run:

bash
Copy code
cd <path_to_src_folder>
Replace <path_to_src_folder> with the actual path to the folder.

2. Compile the Code
Use the javac command to compile the background.java file. Run the following command:

bash
Copy code
javac codesikainterior/background.java
If successful, this will generate a background.class file inside the codesikainterior directory.

3. Run the Program
After compilation, use the java command to run the program:

bash
Copy code
java codesikainterior.background
Project Structure
Ensure your project folder has the following structure:

css
Copy code
src/
├── codesikainterior/
│   ├── background.java
Common Issues
Error: Could not find or load main class

Make sure you're in the src directory when running the java command.
Ensure the background.java file has the correct package declaration:
java
Copy code
package codesikainterior;
Error: NoClassDefFoundError

Ensure that the package name matches the folder structure (codesikainterior/background.java).
Run the java command from the correct root directory (src).
