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
public class PlayerAI {
    //playerAI as a field in player, when player is an AI, when it is his turn to attack, he will invoke the ai method,
    //and pass in himself to perform the action.
    private Player p;
    private Country attackFrom;
    private Country attackTo;

    public  PlayerAI(Player p)
    {
        this.p = p;
        attackFrom = null;
        attackTo = null;
    }

    /**
     * loop through the player's country list and find the desire country of AttackTo and AttackFrom
     */
    public boolean calculateAttack()
    {

        ArrayList<Country> countries = (ArrayList)p.getCountriesOwn();
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
}
