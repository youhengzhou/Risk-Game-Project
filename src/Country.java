import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * the Country class is used to store information of Country instance
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-10-25
 *
 */
public class Country {
    private String countryName;
    private Player owner;
    private int troopsNum;
    private ArrayList<Country> adjacentCountries;
    private ArrayList<JButton> countryButtons;
    /**
     * Constructor for Country,initializing troopsNum,owner and countryName
     */
    public Country(String name) {

        countryName = name;
        adjacentCountries = new ArrayList<>();
        owner = null;

    }

    public void setCountryButtons(ArrayList<JButton> countryButtons)
    {
        this.countryButtons =   countryButtons;
    }
    /**
     * add troops to the country
     * @param num the number of troops to be add.
     */
    public void addTroops(int num) {
        troopsNum += num;
    }

    /**
     * Remove troops from the country.
     * @param num number of troops to be removed
     */
    public void removeTroops(int num) {

        troopsNum -= num;
    }


    /**
     * get number of troops on the country
     * @return troopsNum number of troops on the country
     */
    public int getTroopsNum() {
          return troopsNum;
    }

    /**
     * get the owner of the country
     * @return  the owner of the country
     */
    public Player getOwner() {

        return owner;
    }

    /**
     * get the name of the country
     * @return countryName name of the country
     */
    public String getCountryName() {

        return countryName;
    }

    /**
     * Change the owner of the country
     * @param newOwner the new owner of the country
     */
    public void changeOwner(Player newOwner) {
        if (owner == null) {
            owner = newOwner;
            return;
        }
        owner.removeCountry(this);
        this.owner = newOwner;
    }

    /**
     * add adjacent countries to the AdjacentCountries list
     * @param adc the adjacent country to be add.
     */
    public void addAdjacentCountry(Country adc) {

        adjacentCountries.add(adc);
    }

    /**
     * get the list of the adjacent countries
     * @return adjacentCountries Arraylist of the adjacent counties.
     */
    public ArrayList<Country> getAdjacentCountries() {

        return adjacentCountries;
    }

    /**
     * get the String of troops on this country
     * @return  the String of troops on this country
     */
    public String printState() {
        // print name and troops
        String s = "";
        s += countryName + " (" + troopsNum + " troops)";
        return s;
    }

    /**
     * get the string of adjacent enemy countries.
     * @return a string of adjacent enemy countries.
     */
    public String printEnemyCountry() {
        String s = "";
        for (Country c : this.getAdjacentCountries()) {


            if (c.getOwner().equals(this.getOwner())) continue;
            s += c.printState();
            s += "\n";
        }
        return s;
    }

    @Override
    public String toString() {
        return countryName;
    }
}
