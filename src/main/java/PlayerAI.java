import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the PlayerAI class stores all the important details for the AI Player, such as the list of countries they own, and their names
 *
 * @author Avengers
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
    private Country recruitTo = null;
    private List<Country> loopedCountries;
    private boolean found = false;
    private int supportCanOffer = 0;
    private int supportNeed = 0;
    private int newTroops = 0;
    private String recruitLog = "";
    private int testNumber = 3;



    private int troopNeedToAttack;

    public  PlayerAI(String name)
    {
        super(name , true);
        loopedCountries = new ArrayList<>();

    }



    public Country calculateResignTroops(WorldMap map){
        Country country =getCountriesOwn().get(0);
        int enemiesNum = calculateEnemiesNum(country,map);
        for(Country c: getCountriesOwn()){
            int count = calculateEnemiesNum(c,map);
            if(count > enemiesNum){
                country = c;
                enemiesNum = count;
            }
        }
        return country;
    }

    /**
     * calculate total emeny troops number around the country
     * @param country
     * @return count, enemy troop numbers
     */
    public int calculateEnemiesNum(Country country,WorldMap map){
        int count = 0;
        for(Country c: country.getAdjacentCountries(map)){ //loop though all adj countries

            if(!isFriendlyCountry(c)){
                count += c.getCountryTroopsNumber();
            }
        }
        return count;
    }

    /**
     * loop through the player's country list and find the desire country of AttackTo and AttackFrom
     * @return  true if countries are found and able to perfrom Attack. false when no more country to attack
     */
    public boolean calculateAttack(WorldMap map)
    {

        ArrayList<Country> countries = (ArrayList)getCountriesOwn();
        for(Country c:countries)
        {
            double troopNum = c.getCountryTroopsNumber();
            if(troopNum==1) continue;
            double attackTroop = troopNum-1;
            ArrayList<Country> adjEnemyCountries = c.getEnemyCountry(map);
            for(Country enemyCountry:adjEnemyCountries)
            {
                double enemyTroop = enemyCountry.getCountryTroopsNumber();
                if(attackTroop/enemyTroop > 1.5)
                {
                    attackFrom = c;
                    attackTo = enemyCountry;
                    troopNeedToAttack = (enemyTroop==1)? 3 : (int)Math.floor(enemyTroop*(1.5));
                    troopNeedToAttack = (troopNeedToAttack == troopNum) ? (int)attackTroop : troopNeedToAttack;
                    return true; //return true if countries are found and able to perfrom Attack.
                }
            }
        }
     return false;//return false if there are no country suitable to attack
    }

    /**
     * this much troops need in order to win
     * @return
     */
    public int getTroopNeedToAttack() {
        return troopNeedToAttack;
    }

    /**
     * get the AttackToCountry
     * @return attactTo
     */
    public Country getAttackToCountry()
    {
        return attackTo;
    }

    /**
     * get the attackFromCountry
     * @return attackFrom
     */
    public Country getAttackFromCountry() {
        return attackFrom;
    }

    public boolean calculateMove(WorldMap map){
        moveFrom =null;
        moveTo = null;
        for(Country country: getCountriesOwn()){

            //System.out.println("in dat for loop");
            if(country.noAdjFriendCountry(map) ||country.getCountryTroopsNumber() < 2 || !country.isAStrongCountry(map))continue;
            //if country is not suitable to support any other country, continue;
            moveFrom = country;
           // System.out.println("potential move from: "+moveFrom.getCountryName());
            lookForMoveToCountry(country,map); //this line of code will modify found, found if there's country need help and can be helped.
            if(found){
              //  System.out.println("country found to move");
                found = false;
                return true;
            }
            loopedCountries.clear();
        }
        return false;
    }



     public String AIMove(WorldMap map)
     {
         String s = "Move Phase: \n";
        // System.out.println("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
         int count = 1;
         while(calculateMove(map) )
         {
             if(count == 0) break;
             count--;
               calculateSupportCanOffer(moveFrom,map);
               calculateSupportNeeded(moveTo,map);

             s+="moved from "+moveFrom.getCountryName();
             s+= " to "+moveTo.getCountryName();

             int numberToMove = (supportCanOffer>supportNeed)? supportNeed : supportCanOffer;
             moveFrom.removeTroops(numberToMove);
             moveTo.addTroops(numberToMove);
             s+=" for "+numberToMove+" troops."+"\n";
             //s+= getName() +" moved " + numberToMove+" troops from "+moveFrom.getCountryName()+" to "+moveTo.getCountryName()+"\n";
         }

         return s;
     }


    /**
     * recursively look for the country that need support and then assign Country to MoveTo, modify "found" value if country is found.
     * @param startAtCountry
     */
    public void lookForMoveToCountry(Country startAtCountry,WorldMap map){
        //System.out.println("here to LookForMoveTo");

        loopedCountries.add(startAtCountry);
        boolean allFriendlyCountryVisited = startAtCountry.getFriendlyCountry(map).stream().allMatch(e -> loopedCountries.contains(e));
        if(allFriendlyCountryVisited) return;   //dead end

        for(Country c: startAtCountry.getFriendlyCountry(map)){// loop through all adj countries
            //System.out.println("looping for "+c.getCountryName());

            if(loopedCountries.contains(c)) continue;

            if(c.hasStrongEnemyCountryNearBy(map)) { //c needs help
                moveTo = c;
                calculateSupportNeeded(c,map);
                found = true;
                return;
            }
            else
            {
                //System.out.println("recursive "+c.getCountryName());
                lookForMoveToCountry(c,map);
            }

        }

    }



    /**
     * check if the country is own by us, "friendly country"
     * @param c
     * @return true if the country is our own country.
     */
    public boolean isFriendlyCountry(Country c)
    {
        return getCountriesOwn().contains(c);
    }

    /**
     * Pass in the moveFrom and see how much it can help.
     * @param country
     * @return
     */
    public int calculateSupportCanOffer(Country country,WorldMap map)
    {

        int min = 999;
        int curr = 999;
        int maxCanGive = country.getCountryTroopsNumber()-1;
        if(country.noAdjacentEnemy(map))
        {
            supportCanOffer = maxCanGive;
            return supportCanOffer;
        }
        for(Country c: country.getEnemyCountry(map))
        {
             curr = country.getCountryTroopsNumber() - c.getCountryTroopsNumber();
            min = (curr<min) ? curr: min;
        }

        supportCanOffer = min;
        return supportCanOffer; //find the minimum of number we can offer(we don't want the helpping country to be unsafe)
    }


    /**
     * find out how much troops this country need inorder to stay safe.
     * @param country
     * @return
     */
    public int calculateSupportNeeded(Country country,WorldMap map)
    {
        supportNeed = 0;
        int troops = country.getCountryTroopsNumber();
        int max = 0 ;
        for(Country c : country.getEnemyCountry(map))
        {
            int enemyTroops = c.getCountryTroopsNumber();
            int curr = enemyTroops-troops;
            max = (curr>max) ? curr:max; //find the maximum number of troops needed.

        }
        supportNeed = max;
        return max;

    }

    public void setNewTroops(int num)
    {
        newTroops = num;
        //System.out.println("newTroops is set to" + newTroops);
    }

    public boolean findRecruitTo(WorldMap map)
    {
        for(Country c: this.getCountriesOwn())
        {
            if(c.hasStrongEnemyCountryNearBy(map))
            {
                recruitTo = c;
                supportNeed = calculateSupportNeeded(c,map);
                return true;
            }
        }
        return false;
    }

    /**
     * Perform recruit for AI
     * @return the recruitLog containing text information about what AIplayer did in the recruit round.
     */
    public String AIrecruit(WorldMap map)
    {
        boolean findRecruit = false;
        recruitLog = this.getName()+"\nRecruit Phase: \n";
        while(newTroops!=0 && findRecruitTo(map)) //if we still have new troops to assign and still countries need help
        {
            findRecruit =true;
            int troopGive = 0;
            if(supportNeed<newTroops)
            {
                troopGive = supportNeed;

            }
            else
            {

                troopGive = newTroops;//give what ever left
            }

            recruitTo.addTroops(troopGive);
            newTroops-=troopGive;
            //recruitLog += "\n assign "+recruitTo +" with "+troopGive+" troops";

            recruitLog+="added "+troopGive+" troops to "+recruitTo.getCountryName()+"\n";

        }
        if(!findRecruit || newTroops!=0)
        {
            for(Country c: this.getCountriesOwn())
            {
                if(!c.noAdjacentEnemy(map))
                {
                    c.addTroops(newTroops);
                    recruitLog+="added "+newTroops+" troops to "+c.getCountryName()+"\n";
                    return recruitLog;
                }
            }
        }
        return recruitLog;
    }








    public static void main(String[] args){
        PlayerAI ai = new PlayerAI("aa");
        ai.addCountry(new Country("canada"));
        ai.addCountry(new Country("china"));
        for(Country c : ai.getCountriesOwn()){
            System.out.println(c.getCountryName());
        }
    }

    /**
     * get move from country
     * @return moveFrom
     */
    public Country getMoveFrom() {
        return moveFrom;
    }

    /**
     * get the country of MoveTo
     * @return
     */
    public Country getMoveTo()
    {
        return moveTo;
    }


}
