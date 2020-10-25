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

    public Player(String name) {
        this.name = name;
        this.countriesOwn = new ArrayList<>();
    }

    public void addCountry(Country country) {
        this.countriesOwn.add(country);
    }

    public void removeCountry(Country country) {
        this.countriesOwn.remove(country);
    }

    public String getStatus() {
        String s = "";
        s += "Player: " + this.name + " has countries:\n";
        for (Country country : countriesOwn) {
            s += "  " + country.printState() + "\n";
        }
        return s + "\n";
    }

    public List<Country> getCountriesOwn() { // helper method used for getting all the available countries in a printable list for the player
        return countriesOwn;
    }

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

    public String getName() {
        return name;
    }
}