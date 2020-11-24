# Risk-Game-Project
The Hasbro Game Of Global Domination

Authors: Defa Hu, Youheng Zhou, Shaopeng Liu, Shizhong Shang

the GitHub URL is : https://github.com/youhengzhou/Risk-Game-Project

RISK, the 2-6 players game of global domination.

This game is written in Java with a team of four students using IntelliJ IDEA with Java and Git/Github for version control.

For the third milestone of the project, we have the deliverables of a readme file, UML and sequence diagrams, a test class for testing our java code, our up to date version of the Risk game with the View and the Controller, a new map for the game, a lot of fixes to the issues, and also plenty of java documentation embedded inside the java classes.

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


For the third milestone, we added a PlayerAI.java class for the computer player. And we also added a WorldMap to decouple the map building process from RiskModel that would populate countries to a game world, as well, for future projects, make custom maps for the game.

The AI player will attempt to make its own moves based on the information of the unit it has, the unit it recieves each turn, and the situation of the adjacent provinces of the AI player's provinces.

We also revamped our RiskModel.java and RiskView.java to add bonus bonus army placement and troop movement phase, as well as integrating the AI player to the normal game flow in miletone 2.

We have made the necessary changes to our UML diagrams which included the newly made PlayerAI and the WorldMap classes. As well as redrawn and revised the previous two UML diagrams' mistakes, which were known to be not up to par.

For the road ahead, which is the final milestone, which includes the save function using XML and json

Known Bugs:



