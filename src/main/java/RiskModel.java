import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * the Game class is used to run and execute the game, it has lists for players and countries, and a parser built in for getting simple commands
 *
 * @version 1.0
 * @auther Avengers
 * @since 2020-10-25
 */
public class RiskModel extends DefaultHandler {

    public static enum Phase {ATTACK, AIATTACK, RESIGN, FORTIFY}

    private Phase State;
    private List<Player> players;


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
    private boolean isPlayerIndex = false;
    private int newArmy;

    private ArrayList<Country> preCountries;
    private boolean canMove = false;
    private boolean attackWin = false;

    private String AiPlayInfo = "";
    private String AIattackInfo = "";

    private String mapImagePath = "";

    private boolean isState = false;
    private boolean isPlayerOnGoing = false;
    private boolean isCountryName = false;
    private boolean isTroopsNum = false;
    private boolean isPlayerName = false;
    private boolean isColor = false;
    private boolean isisAI = false;
    private boolean isNumOfPlayer = false;
    private boolean isNumOfAI = false;


    private String loadingPlayerName = "";
    private String loadingCountryName = "";
    private boolean loadingisAI;
    private Player loadingPlayer = null;
    private int mode;

    /**
     * This constructor of Game
     */
    public RiskModel() {
        preCountries = new ArrayList<>();
        players = new ArrayList<>();
        gameMap = new WorldMap();
        playerIndex = 0;
        playerOnGoing = players.get(playerIndex % numOfPlayer);
        modelListeners = new ArrayList<>();
        randomAssignCountry();
        randomAssignTroops();
        refreshNewArmy();
        selectedCountryInfo = "Click on country \nto see its information";
    }

    //used for test
    public RiskModel(int num, int numOfAi,int mode) {
        this.mode = mode;
        preCountries = new ArrayList<>();
        players = new ArrayList<>();
        iniMap();
        this.State = Phase.RESIGN;
        this.setNumOfPlayer(num);
        numOfAI = numOfAi;
        createPlayer();
        playerIndex = 0;
        playerOnGoing = players.get(playerIndex % numOfPlayer);
        modelListeners = new ArrayList<>();
        randomAssignCountry();
        randomAssignTroops();
        refreshNewArmy();
        newArmy = gameMap.getNumOfNewArmy(playerOnGoing,mode);
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

    public void iniMap()
    {
        if(mode==RiskController.ORIGINAL)
        {
            gameMap = WorldMap.loadMapFromXML("map.xml");
        }
        else
        {
            gameMap = WorldMap.loadMapFromXML("starMap.xml");
        }
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
        if (players.size() == 1) {

            System.out.println("The winner is " + players.get(0).getName());

        }

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
        s += "when you are at ATTACK Phase, and you done with attacking, you need to press PASS, to move on to FORTIFY\n";
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
        if (!playerOnGoing.getCountriesOwn().contains(firstSelected)) {
            System.out.println("return false");
            return false;
        }
        if (!firstSelected.getAdjacentCountries(gameMap).contains(secondSelected)) {
            System.out.println("return ");
            return false;
        }
        if (firstSelected.getCountryTroopsNumber() < 2) return false;
        Battle battle;
        if (playerOnGoing.isAi()) {
            PlayerAI p = (PlayerAI) playerOnGoing;
            int attackTroop = p.getTroopNeedToAttack();
            System.out.println("from " + firstSelected);
            System.out.println("To: " + secondSelected);
            battle = new Battle(firstSelected, secondSelected, attackTroop);
            AIattackInfo += "Letting " + firstSelected.getCountryName() + " attacks " + secondSelected.getCountryName() + "\n" + attackTroop + " troops sent" + "\n";
            battleResult = battle.fight();
            this.attackWin = battle.isAttackerWin();
            survivedTroops = battle.getTroopSurvive();
            secondSelected.addTroops(survivedTroops);

            if (attackWin) {
                AIattackInfo += "Result: battle win! conquored " + secondSelected.getCountryName() + "\n\n";
            } else {
                AIattackInfo += "Result: battle lost\n\n";
            }
        } else {
            battle = new Battle(firstSelected, secondSelected, attackTroops);
            battleResult = battle.fight();
            this.attackWin = battle.isAttackerWin();
            survivedTroops = battle.getTroopSurvive();
        }


        removePlayerWithNoCountry();
        return true;
    }

    /**
     * set attackWin is false
     */
    public void iniAttackWin() {
        attackWin = false;
    }

    public void addRiskModelListener(RiskModelListener rml) {
        modelListeners.add(rml);
    }

    public void updateModelListeners() {
        for (RiskModelListener rml : modelListeners) {
            rml.handleRiskModelUpdate(new RiskModelUpdateEvent(this));
        }
    }

    /**
     * check if the attacker win
     *
     * @return attackwin
     */
    public boolean getAttackWin() {
        return attackWin;
    }

    /**
     * get the number of the survied troops
     *
     * @return the surviedTroops
     */
    public int getSurvivedTroops() {
        return survivedTroops;
    }

    /**
     * make the player choose how many troops they want to move to the new country after attack
     */
    public void handleSurvivedTroops(int num) {

        firstSelected.addTroops(getSurvivedTroops() - num);
        secondSelected.addTroops(num);
    }

    /**
     * print out the battle result
     *
     * @return battle result
     */
    public String printBattleResult() {
        return battleResult;
    }

    /**
     * implementing for command "pass", to pass the turn from this player to next player
     */
    public void pass() {
        playerIndex++;
        playerOnGoing = players.get(playerIndex % numOfPlayer);
        if (playerOnGoing.isAi()) {
            PlayerAI p = (PlayerAI) playerOnGoing;
            Aiplay(p);
            return;
        }
        this.releaseSelected();
        updateState(Phase.RESIGN);
        updateModelListeners();
    }

    public Player getPlayerComing() {
        return players.get((players.indexOf(playerOnGoing) + 1) % numOfPlayer);
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

    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return AIrecruitInfo
     */


    /**
     * AIattackInfo
     *
     * @return
     */
    public String getAIattackInfo() {
        return AIattackInfo;
    }

    /**
     * AImoveInfo
     * @return
     */


    /**
     * The logic of AI play its round
     *
     * @param p
     */
    public String Aiplay(PlayerAI p) {
        AiPlayInfo = "";
        AIattackInfo = "Attack Phase:\n";
        String AIrecruitInfo = "";
        String AImoveInfo = "";

        refreshNewArmy();
        p.setNewTroops(newArmy);
        AIrecruitInfo = p.AIrecruit(gameMap); //recruit for AI
        Random r = new Random();
        int i = r.nextInt(2) + 3;
        while (i > 0) {
            if (!p.calculateAttack(gameMap))
                break; // If calculateAttack() return false, then we won't perform AIattack, because no suitable country.
            firstSelected = p.getAttackFromCountry();
            secondSelected = p.getAttackToCountry();
            attack(); //attack will assign AttackInfo
            i--;
        }

        AImoveInfo = p.AIMove(gameMap);
        //  updateModelListeners();
        AiPlayInfo += AIrecruitInfo + "------------------------------------------------------------------------\n";
        AiPlayInfo += AIattackInfo + "---------------------------------------------------------------------------\n";
        AiPlayInfo += AImoveInfo + "\n------------------------------------------------------------------------------";
        return AiPlayInfo;
    }

    public String getAiPlayInfo() {
        return AiPlayInfo;
    }


    /**
     * create instances of all Player and add them into Arraylist players.
     */
    private void createPlayer() {
        Color[] colors = {Color.pink, Color.yellow, Color.cyan, Color.GREEN, Color.white, Color.orange};
        int count = 0;
        int leftToCreate = numOfPlayer;
        int leftAI = numOfAI;
        for (int i = 0; i < numOfPlayer; i++) {
            if (i % 2 == 0 && leftToCreate > leftAI) {
                Player p = new Player("Player" + count, false);
                p.addColor(colors[count]);
                players.add(p);
            } else {
                Player p = new PlayerAI("Player" + count + "(AI)");
                p.addColor(colors[count]);
                players.add(p);
                leftAI--;
            }
            leftToCreate--;
            count++;

        }
    }

    /**
     * randomly assign players with their initial country, and assign country with their owner.
     */
    public void randomAssignCountry() {
        //randomly assign Country-Owner pairs

        Set<String> keySet = gameMap.getMap().keySet();
        ArrayList<String> keyList = new ArrayList<>(); //convert to List structure in order to use .shuffle method in Collection.
        keyList.addAll(keySet);
        Collections.shuffle(keyList);//Shuffle the keyList to get it randomized.
        Country countrytemp;
        double numCountryEach = Math.ceil((double) gameMap.getMap().size() / players.size());
        int listIndex = 0; //listIndex to keep track of the keyList.
        for (Player p : players) {
            for (int i = 0; i < numCountryEach; i++) {
                try {
                    countrytemp = gameMap.getMap().get(keyList.get(listIndex));
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
     * set the number of the players in this game
     */
    public void setPlayerOnGoing(Player p) {
        this.playerOnGoing = p;
    }

    /**
     * get the number of the players
     *
     * @return the number of players
     */
    public int getNumOfPlayer() {
        return this.numOfPlayer;
    }


    /**
     * get the number of the players if chnaged
     *
     * @return the new number of players
     */
    public Player getPlayerOnGoing() {
        return this.playerOnGoing;
    }


    /**
     * get the first selected country
     *
     * @return the first selected country
     */
    public Country getFirstSelected() {
        return firstSelected;
    }

    /**
     * get the second selected country
     *
     * @return the second selected country
     */
    public Country getSecondSelected() {
        return secondSelected;
    }

    /**
     * release the first and second selected country
     */
    public void releaseSelected() {
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
     *
     * @return the state
     */
    public Phase getState() {
        return this.State;
    }

    /**
     * check if the attacker's troops number is smaller than the the country that get attack
     *
     * @return false if no
     */
    public boolean setAttackTroops(int num) {
        if (num < firstSelected.getCountryTroopsNumber()) {
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
        Country c = gameMap.getMap().get(countryName);
        c.addButton(button);
    }

    public Country getCountry(String key) {
        return gameMap.getMap().get(key);
    }

    /**
     * @param countryName the name of country
     *                    Set the adjacentCountryInfo for the use of AdjacentCountriesText in RiskView
     */
    public void setSelectedCountryInfo(String countryName) {
        Country country = gameMap.getMap().get(countryName);
        String s = "";
        s += "COUNTRY SELECTED \n" + country.printTrumpNumState() +
                "\n\nOwner: " + country.getOwner().getName() + "\n\nAdjacent Enemy Country: \n" +
                country.printEnemyCountry(gameMap);
        selectedCountryInfo = s;
    }

    /**
     * get the selectedCountryInfo for the use of AdjacentCountriesText in RiskView
     *
     * @return selectedCountryInfo
     */
    public String getSelectedCountryInfo() {
        return selectedCountryInfo;
    }

    /**
     * recursive method to see if we can reach the target Country from the firstSelected Country
     *
     * @param TFromCountry
     * @return
     */
    public boolean availableToMove(Country TFromCountry) {
        //return false if not owning the countries or too few troops on the From country
        if (!playerOnGoing.getCountriesOwn().contains(firstSelected) || !playerOnGoing.getCountriesOwn().contains(secondSelected) || firstSelected.getCountryTroopsNumber() <= 1)
            return false;
        canMove = false;
        for (Country c : TFromCountry.getAdjacentCountries(gameMap)) {

            if (playerOnGoing.getCountriesOwn().contains(c)) {
                if (preCountries.contains(c)) continue;
                if (c.equals(secondSelected)) {
                    canMove = true;
                    break;
                }
                if (canMove) break;
                preCountries.add(TFromCountry);
                availableToMove(c);
            }
        }
        return canMove;
    }

    public void clearPreCountries() {
        preCountries.clear();
    }

    /**
     * move num of player to from one country to another.
     *
     * @param num
     */
    public void moveTroops(int num) {
        firstSelected.removeTroops(num);
        secondSelected.addTroops(num);
    }

    /**
     * assign a new value to the newArmy, method would be called when pass to next player
     */
    public void refreshNewArmy() {
        newArmy = gameMap.getNumOfNewArmy(playerOnGoing,mode);

    }

    /**
     * decrement the NewArmy variable after the player assigned number of new Troops
     *
     * @param num
     * @return true is there are no more troops left to be assigned.
     */
    public boolean decrementNewArmy(int num) // will be true when all army have been assigned
    {
        newArmy -= num;
        return newArmy == 0;
    }

    public int getNewArmyLeft() {
        return newArmy;
    }

    public String getGameMapImagePath() {
        return mapImagePath;
    }

    public String toXML() {
        String s = "<RiskModel>\n";
        s += "<State>" + State.toString() + "</State>\n";
        s += "<numOfPlayer>" + numOfPlayer + "</numOfPlayer>\n";
        s += "<numOfAI>" + numOfAI + "</numOfAI>\n";
        s += "<playerIndex>" + playerIndex + "</playerIndex>\n";
        for (Player p : players) {
            s += p.toXML();
        }
        s += "<playerOnGoing>" + playerOnGoing.getName() + "</playerOnGoing>\n";
        s += "</RiskModel>\n";
        return s;
    }

    public void exportToXMLFile(String fileName) throws IOException {
        FileWriter os;
        if(mode == 1){
             os = new FileWriter( "Map1/"+ fileName + ".txt");
        } else {
             os = new FileWriter( "Map2/"+ fileName + ".txt");
        }

        BufferedWriter bufferedWriter = new BufferedWriter(os);
        bufferedWriter.write(this.toXML());
        bufferedWriter.close();
    }

    //testing
    public void importFromXmlFile(String fileName) {
        File inputFile;
        if(mode == 1){
            inputFile = new File("Map1/" + fileName + ".txt");
        } else {
            inputFile = new File("Map2/" + fileName + ".txt");
        }
        try {
            players.clear();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //PlayerIndex is not done
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("RiskModel")) {
            players.clear();
        } else if (qName.equalsIgnoreCase("State")) {
            isState = true;
        } else if (qName.equalsIgnoreCase("playerOnGoing")) {
            isPlayerOnGoing = true;
        } else if (qName.equalsIgnoreCase("name")) {
            isPlayerName = true;
        } else if (qName.equalsIgnoreCase("isAi")) {
            isisAI = true;
        } else if (qName.equalsIgnoreCase("color")) {
            isColor = true;
        } else if (qName.equalsIgnoreCase("countryName")) {
            isCountryName = true;
        } else if (qName.equalsIgnoreCase("troopsNum")) {
            isTroopsNum = true;
        } else if (qName.equalsIgnoreCase("numOfPlayer")) {
            isNumOfPlayer = true;
        } else if (qName.equalsIgnoreCase("numOfAI")) {
            isNumOfAI = true;
        } else if(qName.equalsIgnoreCase("playerIndex")){
            isPlayerIndex = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

//        int num = Integer.parseInt(s);

        if (isState) {
            State = Phase.valueOf(new String(ch, start, length));
            isState = false;
        } else if (isNumOfPlayer) {
            setNumOfPlayer(Integer.parseInt(new String(ch, start, length)));
            isNumOfPlayer = false;
        } else if (isNumOfAI) {
            numOfAI = Integer.parseInt(new String(ch, start, length));
            isNumOfAI = false;
        } else if (isPlayerName) {
            loadingPlayerName = new String(ch, start, length);
            isPlayerName = false;
        } else if (isisAI) {
            loadingisAI = Boolean.parseBoolean(new String(ch, start, length));
            isisAI = false;
        } else if (isColor) {
            loadingPlayer.addColor(new Color(Integer.parseInt(new String(ch, start, length))));
            isColor = false;
        } else if (isCountryName) {
            loadingCountryName = "";
            String[] strings = new String(ch, start, length).toLowerCase().split(" ");
            for (String string : strings) {
                loadingCountryName += string;
            }
            isCountryName = false;
        } else if (isTroopsNum) {
            int num = Integer.parseInt(new String(ch, start, length));
            gameMap.getCountry(loadingCountryName).setCountryTroopsNumber(num);
            isTroopsNum = false;
        } else if (isPlayerOnGoing) {
            findPlayer(new String(ch, start, length));
            isPlayerOnGoing = false;
        } else if(isPlayerIndex){
            this.playerIndex = Integer.parseInt(new String(ch, start, length));
            isPlayerIndex = false;
        }
    }

    //adding Country to the Player is not yet done
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("isAi")) {
            if (loadingisAI) {
                loadingPlayer = new PlayerAI(loadingPlayerName);
            } else {
                loadingPlayer = new Player(loadingPlayerName, false);
            }
            players.add(loadingPlayer);
        }  else if(qName.equalsIgnoreCase("countryName")) {

//            gameMap.getCountry(loadingCountryName).setOwner(loadingPlayer);
            loadingPlayer.addCountry(gameMap.getCountry(loadingCountryName));
        }
    }

    public void setMode(int num) {
        mode = num;
    }

    public WorldMap getGameMap() {
        return gameMap;
    }

    public boolean findPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                playerOnGoing = p;
                return true;
            }
        }
        return false;
    }

    public static void main (String[] args) {
        RiskModel model = new RiskModel(2, 1,1);
        try {
            model.exportToXMLFile("testing");

        } catch (IOException e) {
            e.printStackTrace();
        }
        RiskModel testingModel = new RiskModel(3,1,1);
        testingModel.setMode(1);
        testingModel.importFromXmlFile("testing");
        System.out.print(testingModel.toXML());

    }
}
