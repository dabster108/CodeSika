CREATE DATABASE codesika;
USE codesika;

-- Create the tables for the database


CREATE TABLE users (
  user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255),
  fullname VARCHAR(255),
  email VARCHAR(255),
  pass VARCHAR(255),
  programming_language VARCHAR(255),
  fav_class VARCHAR(255)
);




SELECT * FROM users;