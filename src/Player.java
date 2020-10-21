
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class Player {
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

    public String printStatus(){
        String s = "";
        s += "Player: "+ this.name + " has countries:\n";
        for(Country country: countriesOwn){
            s += "  "+ country.printState() + "\n";
        }
        return s;
    }


    public List<Country> getCountriesOwn() {
        return countriesOwn;
    }
}
