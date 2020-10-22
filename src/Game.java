

import javax.crypto.spec.PSource;
import java.util.*;

public class Game {
    private List<Player> players;
    private List<Country> countries;
    private Player playerOnGoing;
    private int numOfPlayer = 0;
    private int initialTroops = 0;
	private Parser parser;
	private Player currentPlayer;
	private HashMap<String,Country> map;
    int playerIndex;
    boolean pass;

    public Game() 
    {
		parser = new Parser(); // parser for word checks
        players= new ArrayList<>();
        countries = new ArrayList<>();
        map = new HashMap<>();

        initCountries();
        setNumOfPlayer();
        createPlayer();
        currentPlayer = players.get(0);
        pass=false;
        playerIndex=0;

        randomAssignCountry();
        randomAssignTroops();
        printWelcome();
        play();
    }

    public static void main (String[] args){
        Game game = new Game();
    }
	
	public void play() 
    {            
        boolean finished = false;


        while (! finished || !hasWinner()) {
            pass = false;
            playerOnGoing=players.get(playerIndex%numOfPlayer);
            System.out.println("");
            System.out.println("Now it is your turn " + playerOnGoing.getName());
            while(!pass) {

                System.out.println("what do you want to do now, please input your command");

                processCommand(parser.getCommand());
            }
            }
        System.out.println("Thank you for playing. Good bye.");
    }

    public boolean hasWinner(){
        if(players.size()>1) return false;
        return true;
    }

    public void printPlayerEliminated()
    {
        for(Player p:players)
        {
            if(p.getCountriesOwn().size()==0)
            {
                System.out.println(p.getName()+"is eliminated");
                players.remove(p);
            }
        }
    }
	
	private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the RISK!");
        System.out.println("RISK is the Hasbro game of Global Domination");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
    }
	
	private void processCommand(Command command)
    {
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return;
        }
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("attack")) {
            attack(command);
        }
        else if (commandWord.equals("quit")) {
            quit(command);
        } else if(commandWord.equals("pass")){
            pass();
        }
    }
	
	private void printHelp() 
    {
        System.out.println("You are a general. You are leading your army to conquer the world!");
        System.out.println("Ready your armies, for your enemies would be ready for you.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
	
	private void attack(Command command)
    {
        Player player = currentPlayer;
        String countryname;
        Country attackCountry = null;

        do{
            System.out.println("you have those countries, which one you want to use for attack?");
            System.out.println("Please choose from the list");
            System.out.println(player.printStatus());
            countryname = parser.getCountryName();
            if(!map.containsKey(countryname)) continue;
            else{
                attackCountry = map.get(countryname);
            }
        }while(!player.getCountriesOwn().contains(attackCountry));

        Country defendCountry = null;
        String name;
        do{
            System.out.println("choose the enemy country that you want to attack from the list:");

            System.out.println(attackCountry.printEnemyCountry());
            name = parser.getCountryName();
            if(!map.containsKey(name)) ;
            else{
                defendCountry = map.get(name);
            }
            if(player.getCountriesOwn().contains(defendCountry))
            {
                System.out.println(defendCountry.getCountryName()+"is your own country");
                System.out.println(" please choose a different one");
                continue;
            }

        }while(!attackCountry.getAdjacentCountries().contains(defendCountry));
        System.out.println("the country your are attacking is "+attackCountry.getCountryName());

         Battle bt = new Battle(attackCountry, defendCountry);
         if(bt.fight())
         {
             System.out.println("You win! now "+defendCountry.getCountryName()+"is yours");
         }
         else
         {
             System.out.println("Unfortunately you lose the battle with "+defendCountry.getCountryName());
         }
    }
	
	private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
            return true;  // signal that we want to quit
    }

    /**
     * implementing for command "pass", to pass the turn from this player to next player
     * @param
     * @return boolean
     */
    private void pass(){
        pass=true;
        playerIndex++;
    }

    public void initCountries()
    {
        //North America
        Country Alaska = new Country("Alaska");
        Country Alberta = new Country("Alberta");
        Country CentralAmerica = new Country("Central America");
        Country EasternUnitedStates = new Country("Eastern United States");
        Country Greenland = new Country("Greenland");
        Country NorthwestTerritory = new Country("Northwest Territory");
        Country Ontario = new Country("Ontario");
        Country Quebec = new Country("Quebec");
        Country WesternUnitedStates = new Country("WesternUnitedState");

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
        Country Ukraine= new Country("Ukraine");
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
        Country Siam  = new Country("Siam");
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

        map.put("alberta",Alberta );
        map.put("centralamerica",CentralAmerica  );
        map.put("easternunitedstates",EasternUnitedStates  );
        map.put("greenland",Greenland  );
        map.put("northwestterritory ",NorthwestTerritory  );
        map.put("ontario",Ontario  );
        map.put("quebec",Quebec  );
        map.put("westernunitedstates",WesternUnitedStates  );

        map.put("argentina",Argentina  );
        map.put("brazil",Brazil  );
        map.put("peru",Peru  );
        map.put("venezuela",Venezuela  );

        map.put("greatbritain",GreatBritain  );
        map.put("iceland",Iceland);
        map.put("northerneurope",NorthernEurope  );
        map.put("scandinavia",Scandinavia  );
        map.put("southerneurope",SouthernEurope  );
        map.put("ukraine",Ukraine );
        map.put("westerneurope",WesternEurope);

        map.put("congo",Congo );
        map.put("eastafrica",EastAfrica );
        map.put("egypt",Egypt );
        map.put("madagascar",Madagascar );
        map.put("northafrica",NorthAfrica );
        map.put("southafrica",SouthAfrica );

        map.put("afghanistan",Afghanistan );
        map.put("china",China );
        map.put("india",India );
        map.put("irkutsk",Irkutsk  );
        map.put("japan",Japan  );
        map.put("kamchatka",Kamchatka  );
        map.put("middleeast",MiddleEast  );
        map.put("mongolia",Mongolia  );
        map.put("siam",Siam   );
        map.put("siberia",Siberia  );
        map.put("ural",Ural  );
        map.put("yakutsk",Yakutsk);

        map.put("easternaustralia",EasternAustralia);
        map.put("indonesia",Indonesia  );
        map.put("newguinea",NewGuinea  );
        map.put("westernaustralia",WesternAustralia  );
    }

    /**
     *to check if the user input number is in the range of Player
     * @param num User input for the number of player
     * @return True or False
     */
    private boolean isValidNum(int num){
        if(num < 2 || num > 6) {
            System.out.println("Please input a valid number (from 2 to 6): ");
            return false;
        }

        return true;
    }

    private void setNumOfPlayer(){
        System.out.println("Please input the number of players (2 to 6): ");
        Scanner sc = new Scanner(System.in);
        this.numOfPlayer = sc.nextInt();

        while(!isValidNum(this.numOfPlayer)){
            this.numOfPlayer = sc.nextInt();
        }

        switch(this.numOfPlayer){
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

    private void createPlayer() {
        //create all player instance
        for (int i = 0; i < numOfPlayer; i++) {
            players.add(new Player("Player" + i));
        }
    }

    private Country getAttackCountry(Player p, Command command)
    {
        String countryname;
        Country potentialCountry = null;

        do{
            System.out.println("you have those countries, which one you want to use for attack?");
            System.out.println("Please choose from the list");

            System.out.println(p.printStatus());
            countryname = parser.getCountryName();

            if(!map.containsKey(countryname));
            else{
                potentialCountry = map.get(countryname);
            }
            /*if(potentialCountry.printEnemyCountry()=="") {
                System.out.println("no enemy country nearby, please choose another one");
                continue;
            }*/

        }while(!p.getCountriesOwn().contains(potentialCountry));
        System.out.println("country your choose is "+potentialCountry.getCountryName());
        return potentialCountry;
    }

    private Country getDefendCountry(Player attackPlayer, Country attackCountry)
    {
        String countryname;
        Country potentialCountry = new Country("impossibleCountry");

        do{

            System.out.println("choose the enemy country that you want to attack from the list:");

            System.out.println(attackCountry.printEnemyCountry());
            countryname = parser.getCountryName();
            if(!map.containsKey(countryname)) ;
            else{
                potentialCountry = map.get(countryname);
            }
            if(attackPlayer.getCountriesOwn().contains(potentialCountry))
            {
                System.out.println(potentialCountry.getCountryName()+"is your own country");
                System.out.println(" please choose a different one");
                continue;
            }


        }while(!attackCountry.getAdjacentCountries().contains(potentialCountry));
        System.out.println("the country your are attacking is "+potentialCountry.getCountryName());
        return potentialCountry;
    }

    //ask uer what they want to do next, they can continue to attck or choose to pass to another player.
    private String nextStep()
    {
        System.out.println("what do you want to do next, you can enter [Attack] to keep attacking or enter [Pass] to pass on to next player" );
        Parser parser = new Parser();
        Command cmd = parser.getCommand();
        System.out.println(cmd.getCommandWord());

        return cmd.getCommandWord();
    }

    public void randomAssignCountry() {
        //randomly assign Country-Owner pairs
        Set<String> keySet = map.keySet();
        ArrayList<String> keyList = new ArrayList<String>();//convert to List structure in order to use .shuffle method in Collection.
        for (String s : keySet) {
            keyList.add(s);
        }
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

    public void randomAssignTroops() {
        int avoidableTroop;
        int troopGive;  //the number of troop gives to country.
        Random r = new Random();
        for (Player p : players) {
            avoidableTroop = initialTroops; //total number of troops that each Player has when begin

            for (Country c : p.getCountriesOwn()) {
                //make sure each country at least has at least one troop
                c.addtroops(1);
                avoidableTroop -= 1; //decrement the available troops that a player left

            }
            for (Country c : p.getCountriesOwn()) {
                //distributing the rest of troop to country randomly.
                if (avoidableTroop <= 0) break; //stop when there are no more troops that available to be assigned.
                troopGive = r.nextInt(10);

                //if random number is too big, we just assign to the country with whatever amount of troops we have left
                if (avoidableTroop - troopGive <= 0) troopGive = avoidableTroop;
                c.addtroops(troopGive);
                avoidableTroop -= troopGive; //decrement the available troops that a player left
            }
        }
    }
}
