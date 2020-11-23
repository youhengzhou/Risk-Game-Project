import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * the PlayerAI class stores all the important details for the AI Player, such as the list of countries they own, and their names
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-11-18
 *
 */
public class PlayerAI extends Player {
    //playerAI as a field in player, when player is an AI, when it is his turn to attack, he will invoke the ai method,
    //and pass in himself to perform the action.
    private Country attackFrom = null;
    private Country attackTo = null;
    private Country moveFrom = null;
    private Country moveTo = null;
    private List<Country> loopedCountries;
    private boolean found = false;

    public  PlayerAI(String name)
    {
        super(name , true);
        loopedCountries = new ArrayList<>();

    }

    public Country calculateResignTroops(){
        Country country =getCountriesOwn().get(0);
        int enemiesNum = calculateEnemiesNum(country);
        for(Country c: getCountriesOwn()){
            int count = calculateEnemiesNum(c);
            if(count > enemiesNum){
                country = c;
                enemiesNum = count;
            }
        }
        return country;
    }

    public int calculateEnemiesNum(Country country){
        int count = 0;
        for(Country c: country.getAdjacentCountries()){
            if(!getCountriesOwn().contains(c)){
                count += c.getTroopsNum();
            }
        }
        return count;
    }

    /**
     * loop through the player's country list and find the desire country of AttackTo and AttackFrom
     */
    public boolean calculateAttack()
    {
        ArrayList<Country> countries = (ArrayList)getCountriesOwn();
        for(Country c:countries)
        {
            double troopNum = c.getTroopsNum();
            double attackTroop = troopNum-1;
            if(troopNum==1) continue;
            ArrayList<Country> adjEnemyCountries = c.getEnemyCountry();
            for(Country enemyCountry:adjEnemyCountries)
            {
                double enemyTroop = enemyCountry.getTroopsNum();
                if(attackTroop/enemyTroop > 1.5)
                {
                    attackFrom = c;
                    attackTo = enemyCountry;
                    return true; //return true if countries are found and able to perfrom Attack.
                }
            }
        }
     return false;//return false if there are no country suitable to attack
    }

    public Country getAttackToCountry()
    {
        return attackTo;
    }

    public Country getAttackFrom() {
        return attackFrom;
    }

    public boolean calculateMove(){
        for(Country country: getCountriesOwn()){
            if(!noAdjacentEnemy(country) && country.getTroopsNum() < 2)continue;
            moveFrom = country;
            if(loopCountries(country)){
                return true;
            }
            loopedCountries.clear();
        }
        return false;
    }

    public boolean noAdjacentEnemy(Country country){
        for(Country c: country.getAdjacentCountries()){
            if(!getCountriesOwn().contains(c)){
                return false;
            }
        }
        return true;
    }

    public boolean loopCountries(Country startAtCountry){

        for(Country c: startAtCountry.getAdjacentCountries()){
            if(getCountriesOwn().contains(c) && !loopedCountries.contains(c)) {
                if (!noAdjacentEnemy(c)) {
                    moveTo = c;
                    found = true;
                    break;
                }
                if(found) break;
                loopedCountries.add(startAtCountry);
                loopCountries(c);
            }
        }
        return found;
    }

    public static void main(String[] args){
        PlayerAI ai = new PlayerAI("aa");
        ai.addCountry(new Country("canada"));
        ai.addCountry(new Country("china"));
        for(Country c : ai.getCountriesOwn()){
            System.out.println(c.getCountryName());
        }
    }


//    public Player getPlayerSource() {
//        return player;
//    }
}
