import java.util.ArrayList;

public class Country {
    private String countryName;
    private Player owner;
    private int troopsNum;
    private ArrayList<Country> adjacentCountries;

    public Country(String name){
        //initializing troopnum,owner and countryName
        countryName=name;
        adjacentCountries = new ArrayList<>();
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

    public void printAdjacentCountries() {
        String s = "";
        for (Country c : this.getAdjacentCountries()) {
            s += c.printState();
            s += "\n";
        }
    }
}
