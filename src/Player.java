import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player { // the Player class stores all the important details for the players, such as the list of countries they own, and their names
    private List<Country> countriesOwn;
    private String name;

    public Player(String name){
        this.name = name;
        this.countriesOwn = new ArrayList<>();
    }

    public void addCountry(Country country){
        this.countriesOwn.add(country);
    }

    public void removeCountry(Country country){
        this.countriesOwn.remove(country);
    }

    public String getStatus() {
        String s = "";
        s += "Player: " + this.name + " has countries:\n";
        for (Country country : countriesOwn) {
            s += "  " + country.printState() + "\n";
        }
        return s+"\n";
    }

    public String getAvaliableCountries() {
        String s = "";
        s += "Player: " + this.name + " has countries:\n";
        for (Country country : countriesOwn) {
            if (country.getTroopsNum() > 1) {
                s += "  " + country.printState() + "\n";
            }
        }
        return s;
    }

    public String getName() {
        return name;
    }

    public List<Country> getCountriesOwn() {
        return countriesOwn;
    }
}
