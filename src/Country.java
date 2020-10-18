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
        troopsNum=troopsnum;
        owner=player;
        countryName=name;
        adjacentCountries = new ArrayList<>();

    }
    public void addtroops(int num)
    {
        troopsNum+=num;
    }

    public void removetroop(int num)
    {
        troopsNum-=num;
    }

    public int getTroopsNum()
    {
        return troopsNum;
    }


}
