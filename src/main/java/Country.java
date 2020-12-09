import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * the Country class is used to store information of Country instance
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-10-25
 *
 */
@XmlRootElement(name = "Country")

public class Country extends DefaultHandler implements Serializable {
    @XmlElement(name = "CountryName")
    private String countryName;
    @XmlElement(name = "Owner")
    private Player owner;
    //@XmlElement(name = "troopNumber")
    private int countryTroopsNumber;
    @XmlElement(name = "adjCountry")
    private ArrayList<String> adjacentCountries;
    //@XmlElement(name = "CountryButton")
    private JButton countryButton;


    /**
     * Constructor for Country,initializing troopsNum,owner and countryName
     */
    public Country(String name) {

        countryName = name;
        adjacentCountries = new ArrayList<>();
        owner = null;
    }

    public Country()
    {}

    /**
     * add troops to the country
     * @param num the number of troops to be add.
     */
    public void addTroops(int num) {
        countryTroopsNumber += num;
    }

    /**
     * Remove troops from the country.
     * @param num number of troops to be removed
     */
    public void removeTroops(int num) {

        countryTroopsNumber -= num;
    }

    /**
     * add country buttons in JButton
     */
    public void addButton(JButton b)
    {
        countryButton = b;
    }


    /**
     * get number of troops on the country
     * @return troopsNum number of troops on the country
     */
    public int getCountryTroopsNumber() {
          return countryTroopsNumber;
    }


//    /**
//     * set the owner of the country
//     * @param player
//     */
//    public void setOwner(Player player){
//        this.owner = player;
//    }

    /**
     * get the owner of the country
     * @return  the owner of the country
     */
    public Player getOwner() {

        return owner;
    }

    /**
     * check if the country has a owner
     */
    public boolean hasOwner(){
        return !owner.equals(null);
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
     * @param countryName the adjacent country to be add.
     */
    public void addAdjacentCountry(String countryName) {

        adjacentCountries.add(countryName);
    }

    /**
     * get the list of the adjacent countries
     * @return adjacentCountries Arraylist of the adjacent counties.
     */
    public ArrayList<Country> getAdjacentCountries(WorldMap map) {
        ArrayList<Country>  adjs= new ArrayList<Country>();
        for(String c: adjacentCountries)
        {
            adjs.add(map.getCountry(c));
        }
        return adjs;
    }

    /**
    *Get the list of the countries' buttons
    *@return countries' button
    */
    public JButton getCountryButton(){
        return this.countryButton;
    }

    //for testing Attack method
    public void setCountryTroopsNumber(int num){this.countryTroopsNumber = num;}

    /**
     * get the String of troops on this country
     * @return  the String of troops on this country
     */
    public String printTrumpNumState() {
        // print name and troops
        String s = "";
        s += countryName + " (" + countryTroopsNumber + " troops)";
        return s;
    }

    /**
     * get the string of adjacent enemy countries.
     * @return a string of adjacent enemy countries.
     */
    public String printEnemyCountry(WorldMap map) {
        String s = "";
        for (Country c : this.getAdjacentCountries(map)) {

            if (c.getOwner().equals(this.getOwner())) continue;
            s += c.printTrumpNumState();
            s += "\n";
        }
        return s;
    }

    /**
     * get list of enemy country
     * @return
     */
   public ArrayList<Country> getEnemyCountry(WorldMap map)
    {
        ArrayList<Country> enemyCountry = new ArrayList<>();
        for (Country c : this.getAdjacentCountries(map)) {
            if (c.getOwner().equals(this.getOwner())) continue;
            enemyCountry.add(c);

        }
        return enemyCountry;
    }

    /**
     * get list of country that having the same owner
     * @return
     */
    public ArrayList<Country> getFriendlyCountry(WorldMap map)
    {
        ArrayList<Country> friendlyCountry = new ArrayList<>();
        for (Country c : this.getAdjacentCountries(map)) {
            if (!c.getOwner().equals(this.getOwner())) continue;
            friendlyCountry.add(c);

        }
        return friendlyCountry;
    }

    /**
     * check whether there is a stong enemy near by the country
     * @return true if there are strong enemy cuntries near by the country passed in.
     */
    public boolean hasStrongEnemyCountryNearBy(WorldMap map)
    {
        int troopsOnC = this.getCountryTroopsNumber();
        for(Country country: getAdjacentCountries(map))
        {
            if(this.getOwner().getCountriesOwn().contains(country)) continue; //continue if it's a friendly country.
            int troopOfEnemyCountry = country.getCountryTroopsNumber();
            if((double)troopOfEnemyCountry / troopsOnC > 1.5 )
            {
                return true; //there is a strong enemy near by

            }
        }
        return false; //country is safe, don't need recruit, don' need friend country to Move and help.
    }

    /**
     *
     * @param
     * @return true false all countries near by are not Enemy. False if any ennemy countries are found.
     */
    public boolean noAdjacentEnemy(WorldMap map){
        for(Country c: this.getAdjacentCountries(map)){
            if(this.getEnemyCountry(map).isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param
     * @return true if there are no friendly countries near by.
     */
    public boolean noAdjFriendCountry(WorldMap map)
    {

            if(this.getFriendlyCountry(map).isEmpty()){
                return true;
            }

        return false;
    }

    /**
     * check of the defence country has more troops than the attack country 
     */
    public boolean isAStrongCountry(WorldMap map)
    {

        return this.getEnemyCountry(map).stream().allMatch(e->e.getCountryTroopsNumber()*1.2 < this.getCountryTroopsNumber());
    }

    public void setColor(Color color){
        countryButton.setForeground(color);
    }

    /**
     * give the country's name and show out the troop number 
     */
    @Override
    public String toString() {
        return countryName + "(troops: "+ countryTroopsNumber + " )\n";
    }

    public String toXML(){
        String s = "<Country>\n";
        s += "<owner>" + this.getOwner().getName() + "</owner>\n";
        s += "<countryName>" + countryName + "</countryName>\n";
        s += "<troopsNum>" + countryTroopsNumber + "</troopsNum>\n";
        s += "</Country>\n";
        return s;
    }



}
