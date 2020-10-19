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
    public Game()
    {
        players= new ArrayList<>();
        countries = new ArrayList<>();
        hasWinner = false;

        setNumOfPlayer();

        System.out.println("Let's play the game, you can type 'help' to see how to play this game");
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
        Alaska.addAdjacentCountry(NorthwestTerritory);
        Alaska.addAdjacentCountry(Kamchatka);
        Alberta.addAdjacentCountry(NorthwestTerritory);
        Alberta.addAdjacentCountry(Ontario);
        Alberta.addAdjacentCountry(WesternUnitedStates);
        CentralAmerica.addAdjacentCountry(EasternUnitedStates);
        CentralAmerica.addAdjacentCountry(WesternUnitedStates);
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
