# Risk-Game-Project
The Hasbro Game Of Global Domination

Authors: Defa Hu, Youheng Zhou, Shaopeng Liu, Shizhong Shang

the GitHub UTL is : https://github.com/youhengzhou/Risk-Game-Project

RISK, the 2-6 players game of global domination.

This game is written in Java with a team of four students using IntelliJ IDEA with Java and Git/Github for version control.

Description of Deliverables:

          1. Game: used to run and execute the game, it has lists for players and countries, and a parser built in for getting simple commands
          2. Parser: used to hold commands inputted by the user through a scanner, to verify whether they are allowed command words
          3. Player: stores all the important details for the players, such as the list of countries they own, and their names
          4. Dice: used for random number generations and putting numbers in integer queues for multiple dice rolls
          5. Country: used to store information of Country instance
          6. CommandWords: used to store the all valid commands
          7. Command: works in conjugation with Parser to take in user commands from the keyboard to strings
          8. Battle: performs the fight between two countries and display the battle result, modifies the state of country owner of the attack country and
             defend country according to the result.

User Manual for Game:

          1. you can type these Commands: [attack], [help], [state], [quit], [pass] to control what to do in the game
          2. [Attack]: can let you choose an enemy country to attack
          3. [help]: well, this is going to tell you how to play this game
          4. [State]: print out the State of the Map (i.e., which player is in which country and with how many armies
          5. [quit]: use this command when you tire of this game
          6. [Pass]: use this command when you finish your turn
          7. Also, during the attack process. If you don't want to attack anymore, you can just type "back" to get out of attack process

For the first milestone of the project, a text based version of the game will be created with the source code and compiled code being put in a .jar. The game will feature simplified features of the classic strategy game RISK, skipping a few elements such as the army placing phase and the bonus card mechanics that the fully featured RISK game has. In addition, a readme file listing the explanation of the rest of deliverables, the names of the authors, and known issues will be created.

Before starting the work on the project, the group members used the steam game version of RISK as the main source of inspiration where they disscussed and analyzed the features and detail mechanics of the game. Some notable insights were found on the dice roll mechanics of RISK, where the player can choose to either fight one on one dice rolls or in the style of three dice versus three dice rolls, opening up to different avenues of attacks for the players.

Beginning the project, the group will first begin a rough draft of the workflow milestones, documentation and readme files to offer the project members a sense of direction in where the project is going to be and detail what kind work everyone has in front of them. And then all the members of the group will then tackle the UML diagram and write up a rough draft of the different classes methods of the Java game with google sheets as a group brainstorming platform before writing the final deliverable UML diagram using violet. The UML diagram will include how they would interact with each other to offer a guideline for the coding part of the text based Java project file next.

This is because it is important to first design the guidelines of the project workflow and gain understanding of the design requirements because mistakes can usually happen if the project members jump into the coding portions of the project first thing without having a complete understanding of the requirements.

The next major phase would be to write up the codes for the Java game. Though when we are in this phase of writing the code, we do not constrain ourselves to simply writing the codes, as the coding process also work in parallel with the UML design process, changing the requirements during the process of programming to better fit the project stakeholder (the course)’s expectations and editing the code to better fit the requirements when a conflict of requirements was found.

Through the process of writing, editing, and re-editing, this phase will lead up to producing the final deliverables of Milestone 1, with all the necessary changes and steps towards perfection being delivered before the Sunday October 25th due date.

We hope through this project we will experience, learn, and share many insights and great moments, and most imporantly learn from this experience in partaking through the project life cycle of a software programmer in the modern era.

And we hope you, the person reading this, whether you are a teaching assistant, professor, code enthusiast, fellow student, or a beginner in learning how to code, to be as delighted as us when we were creating this project.

From yours truly,

SYSC 3110 Group Avengers

(Earth's mightiest programmers)
