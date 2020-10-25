import java.util.ArrayList;

public class Country { // the Country class stores information about the country's name, and its owner, the troops present on the country, and an array list of adjacent countries
    private String countryName;
    private Player owner;
    private int troopsNum;
    private ArrayList<Country> adjacentCountries;

    public Country(String name) {
        //initializing troopnum, owner and countryName
        countryName=name;
        adjacentCountries = new ArrayList<>(); // uses an ArrayList to store the adjacent countries
        owner=null;

    }
    public void addtroops(int num) {
        // add new troops to the Country
        troopsNum+=num;
    }

    public void removetroop(int num) {
        // remove troops in the Country
        troopsNum -= num;
    }


    public int getTroopsNum() {
        // return total number of troops in the Country
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

    public void changeOwner(Player newowner) {
        if (owner == null) {
            owner = newowner;
            return;
        }
        // change owner of the country
        owner.removeCountry(this);
        this.owner = newowner;
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

    //public void printAdjacentCountries() { // method for printing adjacent countries, is obsolete for the current milestone since printEnemyCountry was used, and move is not in place yet
     //   String s = "";
    //    for (Country c : this.getAdjacentCountries()) {
    //        s += c.printState();
    //         s += "\n";
    //    }
    //    System.out.println(s);
    //    }

    public String printEnemyCountry() // prints enemy country, by storing the countries to a list, and only continuing if the owner of the adjacent country is those given by the owner
    {
        String s = "";
        for (Country c : this.getAdjacentCountries()) {
            if (c.getOwner().equals(this.getOwner())) continue;
            s += c.printState();
            s += "\n";
        }
        return s;

    }
}
