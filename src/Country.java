import java.util.ArrayList;

public class Country {
    /**1st Edition Youheng Zhou
     * Oct 17th
     * Implemented Country,battle()
     *
     * 2nd Edition Shaopeng Liu
     * Oct 18th
     * removed battle()
     * modified Country()
     * Implemented addtroops(num),removetroop(int num),getTroopsNum()
     */
    private String countryName;
    private Player owner;
    private int troopsNum;
    private ArrayList<Country> adjacentCountries;

    public Country(int troopsnum, Player player,String name){
        //initializing troopnum,owner and countryName
        troopsNum=troopsnum;
        owner=player;
        countryName=name;
        adjacentCountries = new ArrayList<>();

    }
    public void addtroops(int num)
    {
        //add new troops to the country
        troopsNum+=num;
    }

    public void removetroop(int num)
    {
        //remove troops in the country
        troopsNum-=num;
    }

    public int getTroopsNum()
    {
        //return total number of troops in the conutry
        return troopsNum;
    }

    public void changeOwner(Player newowner)
    {
        //change owner of the country
        owner.removeCountry(this);
        this.owner=newowner;
    }

    public void addAdjacentCountry(Country adc)
    {
        //add adjacent countries to the AdjacentCountries list
        adjacentCountries.add(adc);
    }

    public String printState() {
        //print the country state
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", owner=" + owner +
                ", troopsNum=" + troopsNum +
                ", adjacentCountries=" + adjacentCountries +
                '}';
    }
}
