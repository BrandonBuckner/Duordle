# Duordle 
Creator: Brandon Buckner 

Duordle is an online web game based on the New York Times Wordle. However, instead of guessing one word you guess two at the same time! The few game rules are listed on the index page
with gray being used as an incorrect marker, yellow signaling the right letter but the wrong spot, and green showing the correct letter and correct spot. The top half of the box represents the first word 
you are guessing while the bottom half represents the other. 

# How it's made 
**Tech Used:** HTML, CSS, JavaScript, Bootstrap Framework, Java, Java Spring Boot, PostGres 

This project was built on the Java Spring Bootframework. I decided to start with and create 3 different classes and they were Stats, Users, and Game. The Game class handles all the game logic such as checking a guess, 
generating a word list, getting a random answer word, and everything else. The Users class is responsible for creating a base user who has four main attributes. A unique username, a password, a first name, and a last name. 
Finally, we have the stats class which is responsible for keeping track of a user's stats. As a result, it stores their username and the other current stat fields such as games played, guesses attempted, and games won. Both 
stats and users are then stored in a PostGres database that implements basic CRUD functionality. For learning purposes, this project does not make use of Java Spring Boot hibernate as the database management is not fully 
abstracted. I was then able to create unit and integration tests to check my CRUD functionality along with my SQL statements. From here basic controllers and services were set up to help interact from the game webpage to 
the database. JavaScript was used to interact between the two parties where the display and results of the checkGuess method were used. 

# How to Run 

To run this project you must have or do the following: 
1. Install Visual Studio Code or use another IDE of your choice
2. Install all dependencies (Java and PostGres)
3. Clone the project using Git 
4. Navigate to /src/main/java and open "WordGameApplication.java"
5. Click the run button and then navigate to localhost:8080 and the program should be running!

# Optimizations 
While I didn't find room for many optimization choices, my original choice of a data structure was an array but was quickly switched to a HashSet. This decision was made to make use of the average case look-up time is O(1) as looking 
through thousands of words could become a costly operation. Hashmaps allow this most important part to be done very quickly at the cost of memory. 

# What I learned 
I gathered a lot of insight into full-stack development during the creation of this project. I gained a deeper understanding of Java Spring Boot and the 3 main layers that consist of a website each having its own vital role. 
Through the use of controllers, I was able to use RESTful API Development and gain a better understanding of how requests are mapped and sent from client to server. I used and created different tests to ensure CRUD operations 
and game methods were functioning as intended which allowed me to have more hands-on time with libraries such as Mockito and Jupiter which are commonly used for testing purposes. Creating a product like this was 
amazing as I got to combine my knowledge to make a complete use project with room for future development. Future growth of this project could include a secure login, deployment to a server, new online word games, more statistics, 
and more. 

# Known Issues: 
- **Security Threat with Account Creation:** A user is currently stored inside of local storage and is NOT authenticated. This was done to speed up game development, but as a result NO PERSONAL INFORMATION SHOULD BE USED.
  The information is insecure and leaving meaningful passwords, first names, last names, and or usernames could result in information being stolen. The current implementation is NOT suitable for production release to a
  server until this is fixed/reimplemented. 
- **Resetting a game:** Currently, a game is reset by calling the game page again instead of clearing the information. If states became saved on an account this would result in the reset button not clearing anything.
- **Mobile Support:** The game doesn't offer a way for people on a mobile phone to interact with the game as the current keyboard letter arrangement was intended for display purposes only. 
