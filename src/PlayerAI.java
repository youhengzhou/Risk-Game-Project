import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    /**
     * calculate total emeny troops number around the country
     * @param country
     * @return count, enemy troop numbers
     */
    public int calculateEnemiesNum(Country country){
        int count = 0;
        for(Country c: country.getAdjacentCountries()){ //loop though all adj countries
            if(!isFriendlyCountry(c)){
                count += c.getTroopsNum();
            }
        }
        return count;
    }

    /**
     * loop through the player's country list and find the desire country of AttackTo and AttackFrom
     * @return  true if countries are found and able to perfrom Attack. false when no more country to attack
     */
    public boolean calculateAttack()
    {
        int attackTime = 3; //test
        ArrayList<Country> countries = (ArrayList)getCountriesOwn();
        for(Country c:countries)
        {

            double troopNum = c.getTroopsNum();
            if(troopNum==1) continue;
            double attackTroop = troopNum-1;
            ArrayList<Country> adjEnemyCountries = c.getEnemyCountry();
            for(Country enemyCountry:adjEnemyCountries)
            {
                double enemyTroop = enemyCountry.getTroopsNum();
                if(attackTroop/enemyTroop > 1.5)
                {
                    attackFrom = c;
                    attackTo = enemyCountry;
                    troopNeedToAttack = (enemyTroop==1)? 3 : (int)Math.floor(enemyTroop*(1.5));
                    troopNeedToAttack = (troopNeedToAttack == troopNum) ? (int)attackTroop : troopNeedToAttack;
                    attackTime--;//test
                    if(attackTime ==0) return false; //test
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

    public boolean calculateMove(){
        moveFrom =null;
        moveTo = null;
        for(Country country: getCountriesOwn()){

            //System.out.println("in dat for loop");
            if(country.noAdjFriendCountry() ||country.getTroopsNum() < 2 || !country.isAStrongCountry())continue;
            //if country is not suitable to support any other country, continue;
            moveFrom = country;
           // System.out.println("potential move from: "+moveFrom.getCountryName());
            lookForMoveToCountry(country); //this line of code will modify found, found if there's country need help and can be helped.
            if(found){
              //  System.out.println("country found to move");
                found = false;
                return true;
            }
            loopedCountries.clear();
        }
        return false;
    }



     public String AIMove()
     {
         String s = "Move Phase: ";
        // System.out.println("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
         int count = new Random().nextInt(3)+4;
         while(calculateMove() )
         {
             if(count == 0) break;
             count--;
              int offer = calculateSupportCanOffer(moveFrom);

              int need = calculateSupportNeeded(moveTo);

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
    public void lookForMoveToCountry(Country startAtCountry){
        //System.out.println("here to LookForMoveTo");

        loopedCountries.add(startAtCountry);
        boolean allFriendlyCountryVisited = startAtCountry.getFriendlyCountry().stream().allMatch(e -> loopedCountries.contains(e));
        if(allFriendlyCountryVisited) return;   //dead end

        for(Country c: startAtCountry.getFriendlyCountry()){// loop through all adj countries
            //System.out.println("looping for "+c.getCountryName());

            if(loopedCountries.contains(c)) continue;

            if(c.hasStrongEnemyCountryNearBy()) { //c needs help
                moveTo = c;
                calculateSupportNeeded(c);
                found = true;
                return;
            }
            else
            {
                //System.out.println("recursive "+c.getCountryName());
                lookForMoveToCountry(c);
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
    public int calculateSupportCanOffer(Country country)
    {

        int min = 999;
        int totalTroops = country.getTroopsNum();
        if(country.noAdjacentEnemy())
        {
            supportCanOffer = country.getTroopsNum()-1;
            return supportCanOffer;
        }
        for(Country c: country.getEnemyCountry())
        {
            int curr = totalTroops - c.getTroopsNum();

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
    public int calculateSupportNeeded(Country country)
    {
        supportNeed = 0;
        int troops = country.getTroopsNum();
        int max = 0 ;
        for(Country c : country.getEnemyCountry())
        {
            int enemyTroops = c.getTroopsNum();
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

    public boolean findRecruitTo()
    {
        for(Country c: this.getCountriesOwn())
        {
            if(c.hasStrongEnemyCountryNearBy())
            {
                recruitTo = c;
                supportNeed = calculateSupportNeeded(c);
                return true;
            }
        }
        return false;
    }

    /**
     * Perform recruit for AI
     * @return the recruitLog containing text information about what AIplayer did in the recruit round.
     */
    public String AIrecruit()
    {

        recruitLog = this.getName()+"\nRecruit Phase: \n";
        while(newTroops!=0 && findRecruitTo()) //if we still have new troops to assign and still countries need help
        {

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
