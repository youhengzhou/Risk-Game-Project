import java.util.ArrayList;
import java.util.List;

/**
 * the Player class stores all the important details for the players, such as the list of countries they own, and their names
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-10-25
 *
 */
public class Player {
    private List<Country> countriesOwn; // the player's owned countries
    private String name; // the player's name

    /**
    * Constructor of Player
    * make an array list for the owner's countries called countriesOwn
    */
    public Player(String name) {
        this.name = name;
        this.countriesOwn = new ArrayList<>();
    }

    /**
    * @ param country is added into the countriesOwn array list 
    */
    public void addCountry(Country country) {
        this.countriesOwn.add(country);
    }

    /**
    * @ param county is removed from the countriesOwn array list
    */
    public void removeCountry(Country country) {
        this.countriesOwn.remove(country);
    }

    /**
    * get all the countries names from the countriesOwn array list
    * @ return a list of the owner's countries
    */
    public String getStatus() {
        String s = "";
        s += "Player: " + this.name + " has countries:\n";
        for (Country country : countriesOwn) {
            s += "  " + country.printState() + "\n";
        }
        return s + "\n";
    }

    /**
    * @ return countriesOwn array list 
    */
    public List<Country> getCountriesOwn() { // helper method used for getting all the available countries in a printable list for the player
        return countriesOwn;
    }

    public String getCountriesInString(){
        String s="";
        for(Country c: countriesOwn){
            s += c.toString();
        }
        return s;
    }

    /**
    * getAvailableCountries checks of the country has more than 1 troops, if true print out the countries' name
    * @ return a list of available countries
    */
    public String getAvailableCountries() {
        String s = "";
        s += "Player: " + this.name + " has countries:\n";
        for (Country country : countriesOwn) {
            if (country.getTroopsNum() > 1 && !country.printEnemyCountry().equals("")) {
                s += "  " + country.printState() + "\n";
            }
        }
        return s;
    }

    /**
    * @ return name of the countries
    */
    public String getName() {
        return name;
    }

    public void clear(){this.countriesOwn.clear();}
}
