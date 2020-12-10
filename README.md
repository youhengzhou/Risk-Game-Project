# Risk-Game-Project
The Hasbro Game Of Global Domination

Authors: Defa Hu, Youheng Zhou, Shaopeng Liu, Shizhong Shang

the GitHub URL is : https://github.com/youhengzhou/Risk-Game-Project

RISK, the 2-6 players game of global domination.

This game is written in Java with a team of four students using IntelliJ IDEA with Java and Git/Github for version control.

For the fourth milestone of the project, we have the deliverables of a XML read and load system, readme file, UML and sequence diagrams, a test class for testing our java code, our up to date version of the Risk game with the View and the Controller, a new map for the game, saves and loads a lot of fixes to the issues, and also plenty of java documentation embedded inside the java classes.

Description of Deliverables:

          1. RiskModel: used to store all the data of the game (e.g. data of the Territoties, Players), and the algorithm of playing game
          2. RiskView: providing the UI of the game
          3. RiskController: used to connect RiskModel and RiskView. also is the main class of the project
          4. Dice: used for random number generations and putting numbers in integer queues for multiple dice rolls
          5. Country: used to store information of Country instance
          6. Player: stores all the important details for the players, such as the list of countries they own, and their names
          8. Battle: performs the fight between two countries and display the battle result, modifies the state of country owner of the attack country and
             defend country according to the result.
          9. PlayerAI: a computer Player using the PlayerAI class can act to perform computer actions in place of a player for the RISK game
          10. WorldMap: a helper function used to build the initial world map by populating it with countries and troops that became its own class to remove bloat to RiskModel
             
User Manual for Game:

          1. you need to decide the number of players at the begining
          2. You can pick the country with your colors, shown on the upper right
          3. then you choose the action you want to act at the button of the UI
          4. [ATTACK]: Now please choose two countries
                       First one being the country you want to use to Attack
                       Second one being the country you are intending to attack
                       The country you are using to Attack need to have more than 1 troops on it
                       You can not attack your own country 
                       Press [Confirm] after selecting Attacking and Defending countries
          5. [HELP]: well, this is going to tell you how to play this game
          6. [PASS]: use this button when you finish your action
          7. If you want to quit the game, just close the window
          8. [SAVE]: used to save your game state
          9. [LOAD]: used to load your game state


For the  fourth milestone, we have implemented the XML system used to save and load the game states to a XML file, while also having a initial window at the very start of the game that can load a different map in addition to our original own by reading from special XML file.

We have taken into consideration every advice we have been given for the last four projects, while also implementing our own fixes.

There are no further milestone ahead, and this is the final major iteration of the project.

Thank you for a very great semester, to everyone who was part of this journey.

-The Avengers Team


Known Bugs:



