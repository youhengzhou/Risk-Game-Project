import java.awt.*;
import java.io.Serializable;
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
public class Player implements Serializable {
    private List<Country> countriesOwn; // the player's owned countries
    private String name; // the player's name
    private Color color;
    private boolean isAi;

    /**
    * Constructor of Player
    * make an array list for the owner's countries called countriesOwn
    */
    public Player(String name, boolean ai) {
        this.name = name;
        this.countriesOwn = new ArrayList<>();
        this.isAi = ai;
    }

    /**
    * @ param country is added into the countriesOwn array list 
    */
    public void addCountry(Country country) {
        this.countriesOwn.add(country);
    }
    
    /**
    *Add color to the player's color
    */
    public void addColor(Color c)
    {
        color = c;
    }

    /**
    *Get color of the player
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

    public boolean isAi()
    {
        return isAi;
    }

    /**
    *check if the player is an ai player 
    */
    public void setAi(boolean ai) {
        isAi = ai;
    }

    public String toXML(){
        String s = "<Player>\n";
        s += "<name>" + name + "</name>\n";
        s += "<isAi>" + isAi + "</isAi>\n";
        s += "<color>" + Integer.toString(color.getRGB()) + "</color>\n";
        s += "<countriesOwn>\n";
        for(Country c: countriesOwn){
            s += c.toXML();
        }
        s += "</countriesOwn>\n";
        s += "</Player>\n";
        return s;
    }

    public static void main (String[] args){
        Player a = new Player("a", false);
        Country c1 = new Country("Canada");
        a.addCountry(c1);
        Country c2 = new Country("c2");
        a.addCountry(c2);
        c1.addAdjacentCountry(new Country("aaa"));
        c1.addAdjacentCountry(new Country("bbb"));
        c1.addAdjacentCountry(new Country("ccc"));
        c1.addAdjacentCountry(new Country("ddd"));
        System.out.println(a.toXML());


    }
}
