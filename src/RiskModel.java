import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * the Game class is used to run and execute the game, it has lists for players and countries, and a parser built in for getting simple commands
 *
 * @version 1.0
 * @auther Avengers
 * @since 2020-10-25
 */
public class RiskModel {

    public static enum Phase {ATTACK, AIATTACK, RESIGN, FORTIFY}
    private Phase State;
    private List<Player> players;
    private List<PlayerAI> playersAI;

    private Player playerOnGoing;
    private int numOfPlayer = 0;
    private int numOfAI = 0;
    private int initialTroops = 0;
    public WorldMap gameMap;
    int playerIndex;
    private int attackTroops = 0;
    private Country firstSelected = null;
    private Country secondSelected = null;
    private String battleResult;
    private int survivedTroops;
    private List<RiskModelListener> modelListeners;
    private String selectedCountryInfo;
    private int newArmy;

    private ArrayList<Country> preCountries;
    private boolean canMove = false;
    private boolean attackWin = false;

    /**
     * This constructor of Game
     */
    public RiskModel() {
        preCountries = new ArrayList<>();
        players = new ArrayList<>();
        gameMap = new WorldMap();
        createPlayer();
        playerIndex = 0;
        playerOnGoing = players.get(playerIndex % numOfPlayer);
        modelListeners = new ArrayList<>();
        randomAssignCountry();
        randomAssignTroops();
        refreshNewArmy();
        selectedCountryInfo = "Click on country \nto see its information";
    }

    //used for test
    public RiskModel(int num){
        preCountries = new ArrayList<>();
        players = new ArrayList<>();
        gameMap = new WorldMap();
        this.State = Phase.RESIGN;
        this.setNumOfPlayer(num);
        createPlayer();
        playerIndex = 0;
        playerOnGoing = players.get(playerIndex % numOfPlayer);
        modelListeners = new ArrayList<>();
        randomAssignCountry();
        randomAssignTroops();
        refreshNewArmy();
        newArmy = gameMap.getNumOfNewArmy(playerOnGoing);
        selectedCountryInfo = "Click on country \nto see its information";
    }

    /**
     * Method tells us if the game has a winner
     *
     * @return boolean return true if there's winner in the game, false if there are no winner yet
     */
    public boolean hasWinner() {
        return players.size() < 2;
    }

    /**
     * Method remove player with no more country from the players arraylist
     */
    public void removePlayerWithNoCountry() {
        Player beRemovedPlayer;
        for (Player p : players) {
            try {

                if (p.getCountriesOwn().isEmpty()) {

                    numOfPlayer--;
                    beRemovedPlayer = p;
                    players.remove(beRemovedPlayer);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (players.size() + playersAI.size() == 1) {
            if (players.size() == 1) {
                System.out.println("The winner is " + players.get(0).getName());
            }
            if (playersAI.size() == 1) {
                System.out.println("The winner is " + playersAI.get(0).getName());
            }
        }
    }
//
//    /**
//     * Method remove AI players with no more country from the players arraylist
//     */
//    public void removeAIPlayerWithNoCountry() {
//        Player beRemovedPlayer;
//        PlayerAI beRemovedPlayerAI = new PlayerAI(beRemovedPlayer);
//
//        for (PlayerAI p : playersAI) {
//            try {
//
//                if (p.getPlayerSource().getCountriesOwn().isEmpty()) {
//
//                    numOfPlayer--;
//                    beRemovedPlayerAI = p;
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        playersAI.remove(beRemovedPlayerAI);
//        if (players.size() + playersAI.size() == 1) {
//            if (players.size() == 1) {
//                System.out.println("The winner is " + players.get(0).getName());
//            }
//            if (playersAI.size() == 1) {
//                System.out.println("The winner is " + playersAI.get(0).getPlayerSource().getName());
//            }
//        }
//    }

    /**
     * print welcome to users
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the RISK!");
        System.out.println("RISK is the Hasbro game of Global Domination");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
    }

    /**
     * print help to help the users to get known of the command words and their usages.
     */
    public String printHelp() {
        String s = "";
        s += "You are a General. You are leading your army to conquer the world!\n";
        s += "You can pick the country with your colors, shown on the upper right\n";
        s += "After selecting the attack button, select your country, and click on an adjacent country to attack\n";
        s += "Then select the confirm button to finalize it, are you ready General?\n";
        s += "when you are at ATTACK Phase, and you done with attacking, you need to press PASS, to move on to FORTIFY";
        s += "You have the following command buttons: \n";
        s += "[Help]: bring up this pop-up window to show you how to play again\n";
        s += "[Attack]: can let you choose an enemy country to attack\n";
        s += "[Pass]: use this command to finish your turn\n";
        s += "[Confirm]: use this command when you have confirmed your attack\n";
        return s;
    }

    /**
     * attack from one country to another country
     */
    public boolean attack() {
        if (firstSelected == null || secondSelected == null) {
            System.out.println("return false here");
            return false;
        }
        if (!playerOnGoing.getCountriesOwn().contains(firstSelected)){
            System.out.println("return false");
            return false;
        }
        if (!firstSelected.getAdjacentCountries().contains(secondSelected)) {
            System.out.println("return ");
            return false;
        }
        if(firstSelected.getTroopsNum() < 2) return false;
        Battle battle = new Battle(firstSelected, secondSelected, attackTroops);

         battleResult = battle.fight();
        this.attackWin = battle.isAttackerWin();
         survivedTroops =battle.getTroopSurvive();
        removePlayerWithNoCountry();
        return true;
    }

    /**
     * set attackWin is false 
     */
    public void iniAttackWin(){attackWin = false;}

    public void addRiskModelListener(RiskModelListener rml)
    {
        modelListeners.add(rml);
    }

    public void updateModelListeners()
    {
        for(RiskModelListener rml:modelListeners)
        {
            rml.handleRiskModelUpdate(new RiskModelUpdateEvent(this));
        }
    }
    
    /**
     * check if the attacker win
     *@return attackwin
     */
    public boolean getAttackWin(){return attackWin;}
    
    /**
     * get the number of the survied troops
     *@return the surviedTroops 
     */
    public int getSurvivedTroops() {
        return survivedTroops;
    }
    
    /**
     * make the player choose how many troops they want to move to the new country after attack
     */
    public void handleSurvivedTroops(int num)
    {

        firstSelected.addTroops(getSurvivedTroops()-num);
        secondSelected.addTroops(num);
    }
    
    /**
     * print out the battle result
     *@return battle result
     */
    public String printBattleResult()
    {
        return battleResult;
    }

    /**
     * implementing for command "pass", to pass the turn from this player to next player
     */
    public void pass() {
        playerIndex++;
        playerOnGoing =   players.get(playerIndex % numOfPlayer);
        this.releaseSelected();
        updateState(Phase.FORTIFY);
        updateModelListeners();
    }

    /**
     * to check if the user input number is in the range of Player
     *
     * @param num User input for the number of player
     * @return True or False
     */
    private boolean isValidNum(int num) {
        return num >= 2 && num <= 6;
    }



    /**
     * set the number of players in the game and decide how many troops should each one of them have.
     */
    private boolean setNumOfPlayer(int num) {
        if (!isValidNum(num)) return false;
        this.numOfPlayer = num;
        switch (this.numOfPlayer) {
            case 2:
                this.initialTroops = 50;
                break;
            case 3:
                this.initialTroops = 35;
                break;
            case 4:
                this.initialTroops = 30;
                break;
            case 5:
                this.initialTroops = 25;
                break;
            case 6:
                this.initialTroops = 20;
                break;
        }
        return true;
    }

    /**
     * create instances of all Player and add them into Arraylist players.
     */
    private void createPlayer() {
        Color[] colors = {Color.pink, Color.yellow, Color.cyan, Color.GREEN, Color.white, Color.orange};
        int count = 0;
        for (int i = 0; i < numOfPlayer - numOfAI; i++) {
            Player p = new Player("Player" + count, false);
            p.addColor(colors[count]);
            count++;
            players.add(p);
        }
        for(int i = 0; i < numOfAI; i++){
            PlayerAI p = new PlayerAI("Player"+ count);
        }
    }

    /**
     * randomly assign players with their initial country, and assign country with their owner.
     */
    public void randomAssignCountry() {
        //randomly assign Country-Owner pairs
        Set<String> keySet = gameMap.map.keySet();
        ArrayList<String> keyList = new ArrayList<>(); //convert to List structure in order to use .shuffle method in Collection.
        keyList.addAll(keySet);
        Collections.shuffle(keyList);//Shuffle the keyList to get it randomized.
        Country countrytemp;
        double numCountryEach = Math.ceil((double) gameMap.map.size() / players.size());
        int listIndex = 0; //listIndex to keep track of the keyList.
        for (Player p : players) {
            for (int i = 0; i < numCountryEach; i++) {
                try {
                    countrytemp = gameMap.map.get(keyList.get(listIndex));
                } catch (Exception e) {
                    break;
                }
                countrytemp.changeOwner(p);
                p.addCountry(countrytemp);
                listIndex++; //increment to access the next element in keyList.
            }
        }
    }

    /**
     * Randomly assign initial troops to countries.
     */
    public void randomAssignTroops() {
        int avoidableTroop;
        int troopGive;  //the number of troop gives to country.
        Random r = new Random();
        for (Player p : players) {
            avoidableTroop = initialTroops; //total number of troops that each Player has when begin

            for (Country c : p.getCountriesOwn()) {

                //make sure each country at least has at least one troop
                c.addTroops(1);
                avoidableTroop -= 1; //decrement the available troops that a player left

            }
            for (Country c : p.getCountriesOwn()) {
                //distributing the rest of troop to country randomly.
                if (avoidableTroop <= 0) break; //stop when there are no more troops that available to be assigned.
                troopGive = r.nextInt(10);

                //if random number is too big, we just assign to the country with whatever amount of troops we have left
                if (avoidableTroop - troopGive <= 0) troopGive = avoidableTroop;
                c.addTroops(troopGive);
                avoidableTroop -= troopGive; //decrement the available troops that a player left
            }
        }
    }

    /**
     *set the number of the players in this game
     */
    public void setPlayerOnGoing(Player p){this.playerOnGoing = p;}

    /**
     * get the number of the players
     *@return the number of players 
     */
    public int getNumOfPlayer() {
        return this.numOfPlayer;
    }

    
    /**
     * get the number of the players if chnaged
     *@return the new number of players 
     */
    public Player getPlayerOnGoing() {
        return this.playerOnGoing;
    }
 
    
    /**
     * get the first selected country
     *@return the first selected country
     */
    public Country getFirstSelected() {
        return firstSelected;
    }

     /**
     * get the second selected country
     *@return the second selected country
     */
    public Country getSecondSelected() {
        return secondSelected;
    }

     /**
     * release the first and second selected country
     */
    public void releaseSelected(){
        this.firstSelected = null;
        this.secondSelected = null;
    }

     /**
     * update the state of the countries 
     */
    public void updateState(Phase phase) {
        this.State = phase;
    }

     /**
     * get the state of the countries 
     *@return the state
     */
    public Phase getState() {
        return this.State;
    }

     /**
     * check if the attacker's troops number is smaller than the the country that get attack
     *@return true if yes
     *@return false if no
     */
    public boolean setAttackTroops(int num) {
        if (num < firstSelected.getTroopsNum()) {
            this.attackTroops = num;
            return true;
        }
        return false;
    }

     /**
     * set the attacked country under by new owner if the new owner won 
     */
    public void setSelected(Country country) {
        if (!State.equals(Phase.ATTACK) && !State.equals(Phase.FORTIFY)) {
            firstSelected = country;
        } else if (firstSelected != null && (State.equals(Phase.ATTACK) || State.equals(Phase.FORTIFY))) {
            secondSelected = country;
        } else if (firstSelected == null && (State.equals(Phase.ATTACK) || State.equals(Phase.FORTIFY))) {
            firstSelected = country;
        }
    }

     /**
     * assign the buttons under the countries on the map
     */
    public void assignButtonToCountry(JButton button) {
        String countryName = button.getActionCommand();
        Country c = gameMap.map.get(countryName);
        c.addButton(button);
    }

    public Country getCountry(String key)
    {
        return gameMap.getCountry(key);
    }

    /**
     * @param countryName the name of country
     * Set the adjacentCountryInfo for the use of AdjacentCountriesText in RiskView
     */
    public void setSelectedCountryInfo(String countryName) {
        Country country = gameMap.map.get(countryName);
        String s = "";
        s += "COUNTRY SELECTED \n" + country.printState() +
                "\n\nOwner: " + country.getOwner().getName() + "\n\nAdjacent Enemy Country: \n" +
                country.printEnemyCountry();
        selectedCountryInfo = s;
    }

    /**
     * get the selectedCountryInfo for the use of AdjacentCountriesText in RiskView
     * @return selectedCountryInfo
     */
    public String getSelectedCountryInfo()
    {
        return selectedCountryInfo;
    }

    /**
     * recursive method to see if we can reach the target Country from the firstSelected Country
     * @param TFromCountry
     * @return
     */
    public boolean availableToMove(Country TFromCountry){
        //return false if not owning the countries or too few troops on the From country
        if(!playerOnGoing.getCountriesOwn().contains(firstSelected) || !playerOnGoing.getCountriesOwn().contains(secondSelected) || firstSelected.getTroopsNum() <= 1) return false;
        canMove = false;
        for(Country c: TFromCountry.getAdjacentCountries()){
            if(playerOnGoing.getCountriesOwn().contains(c)){
                if(preCountries.contains(c))continue;
                if(c.equals(secondSelected)) {
                    canMove = true;
                    break;
                }
                if(canMove) break;
                preCountries.add(TFromCountry);
                availableToMove(c);
            }
        }
        return canMove;
    }

    public void clearPreCountries(){preCountries.clear();}

    /**
     * move num of player to from one country to another.
     * @param num
     */
    public void moveTroops(int num) {
        firstSelected.removeTroops(num);
        secondSelected.addTroops(num);
    }

    /**
     * assign a new value to the newArmy, method would be called when pass to next player
     */
    public void refreshNewArmy()
    {
        newArmy = gameMap.getNumOfNewArmy(playerOnGoing);

    }

    /**
     * decrement the NewArmy variable after the player assigned number of new Troops
      * @param num
     * @return true is there are no more troops left to be assigned.
     */
    public boolean decrementNewArmy(int num) // will be true when all army have been assigned
    {
        newArmy-=num;
        return newArmy==0;
    }

    public int getNewArmyLeft()
    {
        return newArmy;
    }



}
