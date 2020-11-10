# Risk-Game-Project
The Hasbro Game Of Global Domination

Authors: Defa Hu, Youheng Zhou, Shaopeng Liu, Shizhong Shang

the GitHub URL is : https://github.com/youhengzhou/Risk-Game-Project

RISK, the 2-6 players game of global domination.

This game is written in Java with a team of four students using IntelliJ IDEA with Java and Git/Github for version control.

For the second milestone of the project, we have the deliverables of a readme file, UML and sequence diagrams, a test class for testing our java code, our up to date version of the Risk game with the View and the Controller, and also plenty of java documentation embedded inside the java classes.

We kept the Battle.java, Country.java, Dice.java, and Player.java from milestone 1.

We changed Game.java into RiskController.java, and added RiskModel.java, RiskModelTest.java, and RiskView.java.

The RiskController.java, from Game.java serve as the logic behind our game, while RiskView displays the necessary GUI elements to the user, and using RiskController to call and receive informations about the JButton GUI events.

We have made the necessary changes to our UML diagrams which included the newly made RiskView and RiskController classes. We have also updated our previous Game class into the new RiskModel class.

By using MVC programming techniques, we were able to create a separate GUI from our main classes, that can display our information of the game, and including sending and receiving events from our JButtons, which are present on our JPanels on our GUI. In addition, we created several test cases featured in RiskModelTest.java, which can test whether our project so far works or not.

We also took the feedback from last time to heart, and started wording our commit messages more carefully, and have also checked our new UML diagram in detail, to make sure our links between classes are correct.

For the road ahead, we hope to increase elements of userfriendliness, and flesh out the army placement and troop placement phase of the game.

Known Bugs:
the game still cannot finish because we cannot redraft troops, and move troops from territory to territory


