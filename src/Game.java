import java.util.*;

/**
 * the Game class is used to run and execute the game, it has lists for players and countries, and a parser built in for getting simple commands
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-10-25
 *
 */
public class Game {
    private List<Player> players;
    private Player playerOnGoing;
    private int numOfPlayer = 0;
    private int initialTroops = 0;
    private Parser parser;
    private HashMap<String, Country> map;
    int playerIndex;
    boolean pass;
    boolean finished = false;

    /**
     * This constructor of Game
     */
    public Game() {
        parser = new Parser(); // parser for word checks
        players = new ArrayList<>();

        map = new HashMap<>();

        initCountries();
        setNumOfPlayer();
        createPlayer();
        pass = false;
        playerIndex = 0;

        randomAssignCountry();
        randomAssignTroops();
        play();
    }

    /**
     * create a new Game instance
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game();
    }

    /**
     * Print command instructions and get user command.
     */
    public void play() {
        printWelcome();
        while (!finished && !hasWinner()) {
            pass = false;
            playerOnGoing = players.get(playerIndex % numOfPlayer);
            System.out.println();
            System.out.println("Now it is your turn " + playerOnGoing.getName());
            while (!pass && !finished) {

                System.out.println("what do you want to do now, please input your command");
                System.out.println("You can type [attack], [pass], [state], [help], [quit]");

                processCommand(parser.getCommand());
            }
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     *Method tells us if the game has a winner
     * @return boolean return true if there's winner in the game, false if there are no winner yet
     */
    public boolean hasWinner() {
        return players.size() <= 1;
    }

    /**
     * Method remove player with no more country from the players arraylist
     */
    public void removePlayerWithNoCountry() {
        Player beRemovedPlayer = new Player("impossible");
        for (Player p : players) {
            try {

                if (p.getCountriesOwn().isEmpty()) {

                    numOfPlayer--;
                    beRemovedPlayer = p;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        players.remove(beRemovedPlayer);
        if (players.size() == 1) {
            System.out.println("The winner is " + players.get(0).getName());
            finished = true;
        }
    }

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
     * Process command and invoke corresponding method.
     * @param command the command get from user
     */
    private void processCommand(Command command) {
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return;
        }
        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "attack":
                attack();
                break;
            case "quit":
                quit();
                break;
            case "pass":
                pass();
                break;
            case "state":
                printState();
                break;
        }
    }

    /**
     * print help to help the users to get known of the command words and their usages.
     */
    private void printHelp() {
        String s = "";
        s += "You are a general. You are leading your army to conquer the world!\n";
        s += "Ready your armies, for your enemies would be ready for you.\n\n";
        s += "You can input these command words: ";
        s += parser.showCommands();
        s += "[Attack]: can let you choose an enemy country to attack\n";
        s += "[Pass]: use this command when you finish your turn\n";
        s += "[State]: print out the State of the Map (i.e., which player is in which country and with how many armies\n";
        s += "[help]: well, this is going to tell you how to play this game\n";
        s += "[quit]: use this command when you tire of this game\n";
        System.out.println(s);
    }

    /**
     * attack from one country to another country
     */
    private void attack() {
        Player player = playerOnGoing;
        String countryName;
        Country attackCountry;

        do {
            if (player.getAvailableCountries().equals("Player: " + player.getName() + " has countries:\n")) {
                System.out.println("now, you don't have available countries can be used for attacking. So choose your next move again");
                return;
            }
            System.out.println("You have following country can be used to attack");
            System.out.println("Please choose from the list.");
            System.out.println(player.getAvailableCountries());
            System.out.println("You may also choose to change your mind about attacking for a strategic retreat, /n");
            System.out.println("retreat by choosing to 'back' for this turn General.");
            countryName = parser.getCountryName();
            if (countryName.equals("back")) { // the player can also choose to pass while inside attack just in case they change their mind about attacking
                return;
            }
            if (!map.containsKey(countryName)) continue;  //verify country exist
            attackCountry = map.get(countryName); //get the Country and store it into attackCountry
            if (!player.getCountriesOwn().contains(attackCountry)) {
                System.out.println("you don't own this country, please select another country from the list");
                System.out.println();
                continue;
            }

            if (attackCountry.printEnemyCountry().equals("")) {
                System.out.println("The country you chose don't have any adjacent enemy country, choose another one\n");
            } else break;

        } while (true);

        Country defendCountry = null;
        String name;
        do {
            System.out.println("choose the enemy country that you want to attack from the list:");

            System.out.println(attackCountry.printEnemyCountry());
            name = parser.getCountryName();
            if (map.containsKey(name)) {
                defendCountry = map.get(name);
            }

        } while (!attackCountry.getAdjacentCountries().contains(defendCountry));
        System.out.println("the country your are attacking is " + defendCountry.getCountryName());
        //asking general how many troops he want to send to attack
        int num;
        do {
            System.out.println("how many troops do you want to send to attack from " + attackCountry.getCountryName() + " (Maximum " + (attackCountry.getTroopsNum() - 1) + ")");
            Scanner sc = new Scanner(System.in);
            num = sc.nextInt();
            if (num < attackCountry.getTroopsNum()) break;
            else {
                System.out.println("you only have " + (attackCountry.getTroopsNum() - 1) + " troops can use to attack on " + attackCountry.getCountryName());
            }
        } while (true);
        new Battle(attackCountry, defendCountry, num).fight();
        removePlayerWithNoCountry();
    }

    /**
     * invoke quit() method when user wants to quit the game
     * @return  return true if user invoked the method
     */
    private boolean quit() {
        finished = true;
        return true;  // signal that we want to quit
    }

    /**
     * implementing for command "pass", to pass the turn from this player to next player
     */
    private void pass() {
        pass = true;
        playerIndex++;
    }

    /**
     * print the state of all the players,including their name, their country, and troops on the country.
     */
    private void printState() {
        StringBuilder s = new StringBuilder();
        for (Player player : players) {
            s.append(player.getStatus());
        }
        System.out.println(s);
    }

    /**
     * Initialize all countries in the game
     */
    public void initCountries() {
        //North America
        Country Alaska = new Country("Alaska");
        Country Alberta = new Country("Alberta");
        Country CentralAmerica = new Country("Central America");
        Country EasternUnitedStates = new Country("Eastern United States");
        Country Greenland = new Country("Greenland");
        Country NorthwestTerritory = new Country("Northwest Territory");
        Country Ontario = new Country("Ontario");
        Country Quebec = new Country("Quebec");
        Country WesternUnitedStates = new Country("Western United States");

        //South America
        Country Argentina = new Country("Argentina");
        Country Brazil = new Country("Brazil");
        Country Peru = new Country("Peru");
        Country Venezuela = new Country("Venezuela");

        //Europe
        Country GreatBritain = new Country("Great Britain");
        Country Iceland = new Country("Iceland");
        Country NorthernEurope = new Country("Northern Europe");
        Country Scandinavia = new Country("Scandinavia");
        Country SouthernEurope = new Country("Southern Europe");
        Country Ukraine = new Country("Ukraine");
        Country WesternEurope = new Country("Western Europe");

        //Africa
        Country Congo = new Country("Congo");
        Country EastAfrica = new Country("East Africa");
        Country Egypt = new Country("Egypt");
        Country Madagascar = new Country("Madagascar");
        Country NorthAfrica = new Country("North Africa");
        Country SouthAfrica = new Country("South Africa");

        //Asia
        Country Afghanistan = new Country("Afghanistan");
        Country China = new Country("China");
        Country India = new Country("India");
        Country Irkutsk = new Country("Irkutsk");
        Country Japan = new Country("Japan");
        Country Kamchatka = new Country("Kamchatka");
        Country MiddleEast = new Country("Middle East");
        Country Mongolia = new Country("Mongolia");
        Country Siam = new Country("Siam");
        Country Siberia = new Country("Siberia");
        Country Ural = new Country("Ural");
        Country Yakutsk = new Country("Yakutsk");

        //Australia
        Country EasternAustralia = new Country("Eastern Australia");
        Country Indonesia = new Country("Indonesia");
        Country NewGuinea = new Country("New Guinea");
        Country WesternAustralia = new Country("Western Australia");

        //Add adjacent Countries
        //North America
        ///1
        Alaska.addAdjacentCountry(Kamchatka);
        Alaska.addAdjacentCountry(Alberta);
        Alaska.addAdjacentCountry(NorthwestTerritory);
        //2
        Alberta.addAdjacentCountry(Alaska);
        Alberta.addAdjacentCountry(NorthwestTerritory);
        Alberta.addAdjacentCountry(Ontario);
        Alberta.addAdjacentCountry(WesternUnitedStates);
        //3
        CentralAmerica.addAdjacentCountry(EasternUnitedStates);
        CentralAmerica.addAdjacentCountry(WesternUnitedStates);
        CentralAmerica.addAdjacentCountry(Venezuela);
        //4
        EasternUnitedStates.addAdjacentCountry(CentralAmerica);
        EasternUnitedStates.addAdjacentCountry(Ontario);
        EasternUnitedStates.addAdjacentCountry(Quebec);
        EasternUnitedStates.addAdjacentCountry(WesternUnitedStates);
        //5
        Greenland.addAdjacentCountry(NorthwestTerritory);
        Greenland.addAdjacentCountry(Ontario);
        Greenland.addAdjacentCountry(Quebec);
        Greenland.addAdjacentCountry(Iceland);
        //6
        NorthwestTerritory.addAdjacentCountry(Alaska);
        NorthwestTerritory.addAdjacentCountry(Alberta);
        NorthwestTerritory.addAdjacentCountry(Ontario);
        NorthwestTerritory.addAdjacentCountry(Greenland);
        //7
        Ontario.addAdjacentCountry(CentralAmerica);
        Ontario.addAdjacentCountry(EasternUnitedStates);
        Ontario.addAdjacentCountry(Greenland);
        Ontario.addAdjacentCountry(NorthwestTerritory);
        Ontario.addAdjacentCountry(Quebec);
        Ontario.addAdjacentCountry(WesternUnitedStates);
        //8
        Quebec.addAdjacentCountry(EasternUnitedStates);
        Quebec.addAdjacentCountry(Greenland);
        Quebec.addAdjacentCountry(Ontario);
        //9
        WesternUnitedStates.addAdjacentCountry(Alberta);
        WesternUnitedStates.addAdjacentCountry(CentralAmerica);
        WesternUnitedStates.addAdjacentCountry(EasternUnitedStates);
        WesternUnitedStates.addAdjacentCountry(Ontario);

        //South America
        //1
        Argentina.addAdjacentCountry(Brazil);
        Argentina.addAdjacentCountry(Peru);
        //2
        Brazil.addAdjacentCountry(Argentina);
        Brazil.addAdjacentCountry(Peru);
        Brazil.addAdjacentCountry(Venezuela);
        Brazil.addAdjacentCountry(NorthAfrica);
        //3
        Peru.addAdjacentCountry(Argentina);
        Peru.addAdjacentCountry(Brazil);
        Peru.addAdjacentCountry(Venezuela);
        //4
        Venezuela.addAdjacentCountry(Brazil);
        Venezuela.addAdjacentCountry(Peru);
        Venezuela.addAdjacentCountry(CentralAmerica);

        //Europe
        //1, 2347
        GreatBritain.addAdjacentCountry(Iceland);
        GreatBritain.addAdjacentCountry(NorthernEurope);
        GreatBritain.addAdjacentCountry(Scandinavia);
        GreatBritain.addAdjacentCountry(WesternEurope);
        //2, 14  5
        Iceland.addAdjacentCountry(GreatBritain);
        Iceland.addAdjacentCountry(Scandinavia);
        Iceland.addAdjacentCountry(Greenland);
        //3, 14567
        NorthernEurope.addAdjacentCountry(GreatBritain);
        NorthernEurope.addAdjacentCountry(Scandinavia);
        NorthernEurope.addAdjacentCountry(SouthernEurope);
        NorthernEurope.addAdjacentCountry(Ukraine);
        NorthernEurope.addAdjacentCountry(WesternEurope);
        //4, 1236
        Scandinavia.addAdjacentCountry(GreatBritain);
        Scandinavia.addAdjacentCountry(Iceland);
        Scandinavia.addAdjacentCountry(NorthernEurope);
        Scandinavia.addAdjacentCountry(Ukraine);
        //5, 367 35 7
        SouthernEurope.addAdjacentCountry(NorthernEurope);
        SouthernEurope.addAdjacentCountry(Ukraine);
        SouthernEurope.addAdjacentCountry(WesternEurope);
        SouthernEurope.addAdjacentCountry(Egypt);
        SouthernEurope.addAdjacentCountry(NorthAfrica);
        SouthernEurope.addAdjacentCountry(MiddleEast);
        //6, 345  17 11
        Ukraine.addAdjacentCountry(NorthernEurope);
        Ukraine.addAdjacentCountry(Scandinavia);
        Ukraine.addAdjacentCountry(SouthernEurope);
        Ukraine.addAdjacentCountry(Afghanistan);
        Ukraine.addAdjacentCountry(MiddleEast);
        Ukraine.addAdjacentCountry(Ural);
        //7, 135 5
        WesternEurope.addAdjacentCountry(GreatBritain);
        WesternEurope.addAdjacentCountry(NorthernEurope);
        WesternEurope.addAdjacentCountry(SouthernEurope);
        WesternEurope.addAdjacentCountry(NorthAfrica);

        //Africa
        //1, 256
        Congo.addAdjacentCountry(EastAfrica);
        Congo.addAdjacentCountry(NorthAfrica);
        Congo.addAdjacentCountry(SouthAfrica);
        //2, 13456 7
        EastAfrica.addAdjacentCountry(Congo);
        EastAfrica.addAdjacentCountry(Egypt);
        EastAfrica.addAdjacentCountry(Madagascar);
        EastAfrica.addAdjacentCountry(NorthAfrica);
        EastAfrica.addAdjacentCountry(SouthAfrica);
        EastAfrica.addAdjacentCountry(MiddleEast);
        //3, 25 6 7
        Egypt.addAdjacentCountry(EastAfrica);
        Egypt.addAdjacentCountry(NorthAfrica);
        Egypt.addAdjacentCountry(Ukraine);
        Egypt.addAdjacentCountry(MiddleEast);
        //4, 26
        Madagascar.addAdjacentCountry(EastAfrica);
        Madagascar.addAdjacentCountry(SouthAfrica);
        //5, 123 67 2
        NorthAfrica.addAdjacentCountry(Congo);
        NorthAfrica.addAdjacentCountry(EastAfrica);
        NorthAfrica.addAdjacentCountry(Egypt);
        NorthAfrica.addAdjacentCountry(Ukraine);
        NorthAfrica.addAdjacentCountry(WesternEurope);
        NorthAfrica.addAdjacentCountry(Brazil);
        //6, 124
        SouthAfrica.addAdjacentCountry(Congo);
        SouthAfrica.addAdjacentCountry(EastAfrica);
        SouthAfrica.addAdjacentCountry(Madagascar);

        //Asia
        //1, 237 11 6
        Afghanistan.addAdjacentCountry(China);
        Afghanistan.addAdjacentCountry(India);
        Afghanistan.addAdjacentCountry(MiddleEast);
        Afghanistan.addAdjacentCountry(Ural);
        Afghanistan.addAdjacentCountry(Ukraine);
        //2, 1389 10 11
        China.addAdjacentCountry(Afghanistan);
        China.addAdjacentCountry(India);
        China.addAdjacentCountry(Mongolia);
        China.addAdjacentCountry(Siam);
        China.addAdjacentCountry(Siberia);
        China.addAdjacentCountry(Ural);
        //3, 1279
        India.addAdjacentCountry(Afghanistan);
        India.addAdjacentCountry(China);
        India.addAdjacentCountry(MiddleEast);
        India.addAdjacentCountry(Siam);
        //4, 68 10 12
        Irkutsk.addAdjacentCountry(Kamchatka);
        Irkutsk.addAdjacentCountry(Mongolia);
        Irkutsk.addAdjacentCountry(Siberia);
        Irkutsk.addAdjacentCountry(Yakutsk);
        //5, 68
        Japan.addAdjacentCountry(Kamchatka);
        Japan.addAdjacentCountry(Mongolia);
        //6, 458 12 1
        Kamchatka.addAdjacentCountry(Irkutsk);
        Kamchatka.addAdjacentCountry(Japan);
        Kamchatka.addAdjacentCountry(Mongolia);
        Kamchatka.addAdjacentCountry(Yakutsk);
        Kamchatka.addAdjacentCountry(Alaska);
        //7, 13 56 23
        MiddleEast.addAdjacentCountry(Afghanistan);
        MiddleEast.addAdjacentCountry(India);
        MiddleEast.addAdjacentCountry(SouthernEurope);
        MiddleEast.addAdjacentCountry(Ukraine);
        MiddleEast.addAdjacentCountry(EastAfrica);
        MiddleEast.addAdjacentCountry(Egypt);
        //8, 2456 10
        Mongolia.addAdjacentCountry(China);
        Mongolia.addAdjacentCountry(Irkutsk);
        Mongolia.addAdjacentCountry(Japan);
        Mongolia.addAdjacentCountry(Kamchatka);
        Mongolia.addAdjacentCountry(Siberia);
        //9, 23 2
        Siam.addAdjacentCountry(China);
        Siam.addAdjacentCountry(India);
        Siam.addAdjacentCountry(Indonesia);
        //10, 248 11 12
        Siberia.addAdjacentCountry(China);
        Siberia.addAdjacentCountry(Irkutsk);
        Siberia.addAdjacentCountry(Mongolia);
        Siberia.addAdjacentCountry(Ural);
        Siberia.addAdjacentCountry(Yakutsk);
        //11, 12 10 6
        Ural.addAdjacentCountry(Yakutsk);
        Ural.addAdjacentCountry(Siberia);
        Ural.addAdjacentCountry(Ukraine);
        //12, 46 10
        Yakutsk.addAdjacentCountry(Irkutsk);
        Yakutsk.addAdjacentCountry(Kamchatka);
        Yakutsk.addAdjacentCountry(Siberia);

        //Australia
        //1, 34
        EasternAustralia.addAdjacentCountry(NewGuinea);
        EasternAustralia.addAdjacentCountry(WesternAustralia);
        //2, 34 9
        Indonesia.addAdjacentCountry(NewGuinea);
        Indonesia.addAdjacentCountry(WesternAustralia);
        Indonesia.addAdjacentCountry(Siam);
        //3, 124
        NewGuinea.addAdjacentCountry(EasternAustralia);
        NewGuinea.addAdjacentCountry(Indonesia);
        NewGuinea.addAdjacentCountry(WesternAustralia);
        //4, 12
        WesternAustralia.addAdjacentCountry(EasternAustralia);
        WesternAustralia.addAdjacentCountry(Indonesia);

        map.put("alaska", Alaska);
        map.put("alberta", Alberta);
        map.put("centralamerica", CentralAmerica);
        map.put("easternunitedstates", EasternUnitedStates);
        map.put("greenland", Greenland);
        map.put("northwestterritory", NorthwestTerritory);
        map.put("ontario", Ontario);
        map.put("quebec", Quebec);
        map.put("westernunitedstates", WesternUnitedStates);

        map.put("argentina", Argentina);
        map.put("brazil", Brazil);
        map.put("peru", Peru);
        map.put("venezuela", Venezuela);

        map.put("greatbritain", GreatBritain);
        map.put("iceland", Iceland);
        map.put("northerneurope", NorthernEurope);
        map.put("scandinavia", Scandinavia);
        map.put("southerneurope", SouthernEurope);
        map.put("ukraine", Ukraine);
        map.put("westerneurope", WesternEurope);

        map.put("congo", Congo);
        map.put("eastafrica", EastAfrica);
        map.put("egypt", Egypt);
        map.put("madagascar", Madagascar);
        map.put("northafrica", NorthAfrica);
        map.put("southafrica", SouthAfrica);

        map.put("afghanistan", Afghanistan);
        map.put("china", China);
        map.put("india", India);
        map.put("irkutsk", Irkutsk);
        map.put("japan", Japan);
        map.put("kamchatka", Kamchatka);
        map.put("middleeast", MiddleEast);
        map.put("mongolia", Mongolia);
        map.put("siam", Siam);
        map.put("siberia", Siberia);
        map.put("ural", Ural);
        map.put("yakutsk", Yakutsk);

        map.put("easternaustralia", EasternAustralia);
        map.put("indonesia", Indonesia);
        map.put("newguinea", NewGuinea);
        map.put("westernaustralia", WesternAustralia);
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
    private void setNumOfPlayer() {
        do {
            System.out.println("Please input the number of players (2 to 6): ");
            Scanner sc = new Scanner(System.in);

            try {
                this.numOfPlayer = sc.nextInt();
            } catch (Exception e) {
                System.out.println("please only enter valid number input");
            }
        } while (!isValidNum(this.numOfPlayer));

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
    }

    /**
     * create instances of all Player and add them into Arraylist players.
     */
    private void createPlayer() {
        //create all player instance
        for (int i = 0; i < numOfPlayer; i++) {
            players.add(new Player("Player" + i));
        }
    }

    /**
     * randomly assign players with their initial country, and assign country with their owner.
     */
    public void randomAssignCountry() {

        //randomly assign Country-Owner pairs
        Set<String> keySet = map.keySet();
        ArrayList<String> keyList = new ArrayList<>(); //convert to List structure in order to use .shuffle method in Collection.
        keyList.addAll(keySet);
        Collections.shuffle(keyList);//Shuffle the keyList to get it randomized.
        Country countrytemp;
        double numCountryEach = Math.ceil((double) map.size() / players.size());
        int listIndex = 0; //listIndex to keep track of the keyList.
        for (Player p : players) {
            for (int i = 0; i < numCountryEach; i++) {
                try {
                    countrytemp = map.get(keyList.get(listIndex));
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
}
