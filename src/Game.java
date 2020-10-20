import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private List<Country> countries;
    private boolean hasWinner;
    private Player playerOnGoing;
    private int numOfPlayer = 0;
    private int initialTroops = 0;
	private Parser parser;
    public Game() 
    {
		parser = new Parser(); // parser for word checks
        players= new ArrayList<>();
        countries = new ArrayList<>();
        hasWinner = false;

        setNumOfPlayer();

        System.out.println("Let's play the game, you can type 'help' to see how to play this game");
    }
	
	public void play() 
    {            
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }
	
	private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the RISK!");
        System.out.println("RISK is the Hasbro game of Global Domination");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        //System.out.println(currentCountry.getLongDescription()); something that describes the current player situation
    }
	
	private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("attack")) {
            //attack(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }
	
	private void printHelp() 
    {
        System.out.println("You are a general. You are leading your army to conquer the world");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
	
	/*private void attack(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to attack...
            System.out.println("Attack where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Country adjacentCountry = currentCountry.getAdjacentCountry(direction);

        if (adjacentCountry == null) {
            System.out.println("There is no adjacent country!");
        }
        else {
            currentCountry = nextCountry;
            System.out.println(currentCountry.getLongDescription());
        }
    }*/
	
	private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    public static void main (String[] args){
        Game game = new Game();
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
        Country WesternUnitedStates = new Country("WesterUnitedState");

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
        //1
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
    }

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
            case 3:
                this.initialTroops = 35;
            case 4:
                this.initialTroops = 30;
            case 5:
                this.initialTroops = 25;
            case 6:
                this.initialTroops = 20;
        }
    }
}
