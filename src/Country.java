import java.util.ArrayList;

public class Country {
    private String countryName;
    private Player owner;
    private int troopsNum;
    private ArrayList<Country> adjacentCountries;

    public Country(String name) {
        //initializing troopsNum,owner and countryName
        countryName = name;
        adjacentCountries = new ArrayList<>();
        owner = null;

    }

    public void addTroops(int num) {
        //add new troops to the country
        troopsNum += num;
    }

    public void removeTroop(int num) {
        //remove troops in the country
        troopsNum -= num;
    }


    public int getTroopsNum() {
        //return total number of troops in the conutry
        return troopsNum;
    }

    public Player getOwner() {
        // return the name of the player that owns the Country
        return owner;
    }

    public String getCountryName() {
        // return name of Country
        return countryName;
    }

    public void changeOwner(Player newOwner) {
        if (owner == null) {
            owner = newOwner;
            return;
        }
        // change owner of the country
        owner.removeCountry(this);
        this.owner = newOwner;
    }

    public void addAdjacentCountry(Country adc) {
        // add adjacent countries to the AdjacentCountries list
        adjacentCountries.add(adc);
    }

    public ArrayList<Country> getAdjacentCountries() {
        // returns an ArrayList of all the adjacent countries
        return adjacentCountries;
    }

    public String printState() {
        // print name and troops
        String s = "";
        s += countryName + " (" + troopsNum + " troops)";
        return s;
    }

    public String printEnemyCountry() {
        String s = "";
        for (Country c : this.getAdjacentCountries()) {


            if (c.getOwner().equals(this.getOwner())) continue;
            s += c.printState();
            s += "\n";
        }
        return s;
    }
}