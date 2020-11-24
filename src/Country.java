import javax.swing.*;
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
    private JButton countryButton;

    /**
     * Constructor for Country,initializing troopsNum,owner and countryName
     */
    public Country(String name) {

        countryName = name;
        adjacentCountries = new ArrayList<>();
        owner = null;

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
    *Get the list of the countries' buttons
    *@return countries' button
    */
    public JButton getCountryButton(){
        return this.countryButton;
    }

    //for testing Attack method
    public void setTroopsNum(int num){this.troopsNum = num;}

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

    /**
     * get list of enemy country
     * @return
     */
   public ArrayList<Country> getEnemyCountry()
    {
        ArrayList<Country> enemyCountry = new ArrayList<>();
        for (Country c : this.getAdjacentCountries()) {


            if (c.getOwner().equals(this.getOwner())) continue;
            enemyCountry.add(c);

        }
        return enemyCountry;
    }

    /**
     * get list of country that having the same owner
     * @return
     */
    public ArrayList<Country> getFriendlyCountry()
    {
        ArrayList<Country> friendlyCountry = new ArrayList<>();
        for (Country c : this.getAdjacentCountries()) {


            if (!c.getOwner().equals(this.getOwner())) continue;
            friendlyCountry.add(c);

        }
        return friendlyCountry;
    }

    /**
     * check whether there is a stong enemy near by the country
     * @return true if there are strong enemy cuntries near by the country passed in.
     */
    public boolean hasStrongEnemyCountryNearBy()
    {
        int troopsOnC = this.getTroopsNum();
        for(Country country: getAdjacentCountries())
        {
            if(this.getOwner().getCountriesOwn().contains(country)) continue; //continue if it's a friendly country.
            int troopOfEnemyCountry = country.getTroopsNum();
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
    public boolean noAdjacentEnemy(){
        for(Country c: this.getAdjacentCountries()){
            if(this.getEnemyCountry().isEmpty()){
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
    public boolean noAdjFriendCountry()
    {
        for(Country c: this.getAdjacentCountries()){
            if(this.getFriendlyCountry().isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * check of the defence country has more troops than the attack country 
     */
    public boolean isAStrongCountry()
    {
        return this.getEnemyCountry().stream().allMatch(e->e.getTroopsNum()*1.2 < this.getTroopsNum());
    }

    /**
     * give the country's name and show out the troop number 
     */
    @Override
    public String toString() {
        return countryName + "(troops: "+ troopsNum + " )\n";
    }
}
