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
          6. [PASS]: use this button when you finish your turn
          7. If you want to quit the game, just close the window


For the third milestone, we added a PlayerAI.java class for the computer player.

We also revamped our RiskModel.java and RiskView.java to add bonus bonus army placement and troop movement phase, as well as integrating the AI player to the normal game flow in Miletone 2.

The RiskController.java, from Game.java serve as the logic behind our game, while RiskView displays the necessary GUI elements to the user, and using RiskController to call and receive informations about the JButton GUI events.

We have made the necessary changes to our UML diagrams which included the newly made PlayerAI and the WorldMap classes. We have also updated our previous Game class into the new RiskModel class.

By using MVC programming techniques, we were able to create a separate GUI from our main classes, that can display our information of the game, and including sending and receiving events from our JButtons, which are present on our JPanels on our GUI. In addition, we created several test cases featured in RiskModelTest.java, which can test whether our project so far works or not.

We also took the feedback from last time to heart, and started wording our commit messages more carefully, and have also checked our new UML diagram in detail, to make sure our links between classes are correct.

For the road ahead, we hope to increase elements of userfriendliness, and flesh out the army placement and troop placement phase of the game.

Known Bugs:
the game still cannot finish because we cannot redraft troops, and move troops from territory to territory


