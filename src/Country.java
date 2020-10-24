import java.util.ArrayList;

public class Country {
    private String countryName;
    private Player owner;
    private int troopsNum;
    private ArrayList<Country> adjacentCountries;

    public Country(String name){
        //initializing troopnum,owner and countryName
        countryName=name;
        adjacentCountries = new ArrayList<>(); // uses an ArrayList to store the adjacent countries
        owner=null;

    }
    public void addtroops(int num)
    {
        //add new troops to the country
        troopsNum+=num;
    }

    public void removetroop(int num) {
        //remove troops in the country
        troopsNum -= num;
    }


    public int getTroopsNum() {
        //return total number of troops in the conutry
        return troopsNum;
    }

    public Player getOwner() {
        return owner;
    }

    public String getCountryName() {
        //return name of Country
        return countryName;
    }

    public void changeOwner(Player newowner) {
        if (owner == null) {
            owner = newowner;
            return;
        }
        //change owner of the country
        owner.removeCountry(this);
        this.owner = newowner;
    }

    public void addAdjacentCountry(Country adc) {
        //add adjacent countries to the AdjacentCountries list
        adjacentCountries.add(adc);
    }

    public ArrayList<Country> getAdjacentCountries() {
        return adjacentCountries;
    }

    public String printState() {
        //print name and troops
        String s = "";
        s += countryName + " (" + troopsNum + " troops)";
        return s;
    }

    //public void printAdjacentCountries() { // method for printing adjacent countries, is obsolete since printEnemyCountry was used
     //   String s = "";
    //    for (Country c : this.getAdjacentCountries()) {
    //        s += c.printState();
    //         s += "\n";
    //    }
    //    System.out.println(s);
    //    }

    public String printEnemyCountry() // prints enemy country, by storing the countries to a list
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
