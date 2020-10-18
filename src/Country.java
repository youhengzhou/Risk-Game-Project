import java.util.ArrayList;

public class Country {
    private String countryName;
    private String owner;
    private int troopsNum;
    private ArrayList<Country> adjacentCountries;

    private Country(){

    }

    public void battle(int troopsNum, int chosenNumber){
        Dice dice1 = new Dice(chosenNumber);
        Dice dice2 = new Dice(chosenNumber);


        //dice1.roll - dice2.roll;


    }
}
