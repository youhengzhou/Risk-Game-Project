import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * the PlayerAI class stores all the important details for the AI Player, such as the list of countries they own, and their names
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-11-18
 *
 */
public class PlayerAI extends Player {
    private List<Country> countriesOwn; // the AI's owned countries
    private String name; // the AI's name
    private Color color;

    /**
     * Constructor of the AI Player
     * make an array list for the owner's countries called countriesOwn
     */
    public PlayerAI(String name) {
        super(name);
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
     *Add color to the AI's color
     */
    public void addColor(Color c)
    {
        color = c;
    }

    /**
     *Get color of the AI player
     *@return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @ param county is removed from the countriesOwn array list
     */
    public void removeCountry(Country country) {
        this.countriesOwn.remove(country);
    }

    /**
     * @ return countriesOwn array list
     */
    public List<Country> getCountriesOwn() { // helper method used for getting all the available countries in a printable list for the player
        return countriesOwn;
    }

    /**
     * getAvailableCountries checks of the country has more than 1 troops, if true print out the countries' name
     * @ return a list of available countries
     */
    public String getAvailableCountries() {
        String s = "";

        for (Country country : countriesOwn) {
            if ( !country.printEnemyCountry().equals("")) {
                s += "->" + country.printState() + "\n";
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

    //for test
    public void clear(){this.countriesOwn.clear();}
}
