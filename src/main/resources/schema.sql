DROP TABLE IF EXISTS stats;
DROP TABLE IF EXISTS users; 


-- Users Table
CREATE TABLE users (
    username VARCHAR(30) PRIMARY KEY,
    password VARCHAR(255) NOT NULL, 
    first_name VARCHAR(30),
    last_name VARCHAR(30)
);

-- Stats Table
CREATE TABLE stats (
    username VARCHAR(30) PRIMARY KEY,
    games_played INT DEFAULT 0, 
    games_won INT DEFAULT 0, 
    guesses_made INT DEFAULT 0,
    FOREIGN KEY (username) REFERENCES users(username)
);
