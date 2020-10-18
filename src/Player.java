<<<<<<< HEAD
public class Player {

    public void removeCountry(Country countryName)
    {

    }
=======
import java.util.ArrayList;
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

>>>>>>> e9f64e540d771f91d34512c1464a124584e8ebff
}
