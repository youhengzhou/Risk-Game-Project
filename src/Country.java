import java.util.ArrayList;

public class Country {
    private String countryName;
    private Player owner;
    private int troopsNum;
    private ArrayList<Country> adjacentCountries;

    private Country(){

    }

    public void battle(int troopsNum, int chosenNumber){
        Dice dice1 = new Dice(chosenNumber);
        Dice dice2 = new Dice(chosenNumber);


        //dice1.roll - dice2.roll;


    }

    public String printState() {
        return this.countryName + " has armies: " + this.troopsNum;
    }
}
