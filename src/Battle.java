/**
 * the Battle class performs the fight between two countries and display the battle result, modifies the state of country owner of the attack country and
 * defend country according to the result.
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-10-25
 *
 */
public class Battle {
    // the Battle class mainly treats the battle portion of the game, where country name and troop numbers are taken and used for battles
    private Country countryAttack;
    private Country countryDefend;
    private int AttackingTroops;
    private String battleResultString;
    private int troopSurvive;
    private boolean isAttackerWin;

    /**
     * Constructor of Battle
     * @param attack the attacking country
     * @param defend the defending country
     * @param troopSent number of troops bing sent from attacking country
     */
    public Battle(Country attack, Country defend, int troopSent) {
        battleResultString = "";
        isAttackerWin = false;
        this.countryAttack = attack;
        this.countryDefend = defend;
        AttackingTroops = troopSent;
        countryAttack.removeTroops(AttackingTroops);
    }

    /**
     * performs the fight, display battle result, change owner of the defend Country to the attacking country owner if
     * the attacker wins the battle.
     */

    public String fight() {
        int attackTroopDeath = 0; // counter for taking away troops later
        int defendTroopDeath = 0; // counter for taking away troops later
        Dice attackDice;
        Dice defendDice;

        while (AttackingTroops != 0 && countryDefend.getTroopsNum() != 0) {
            attackDice = new Dice(AttackingTroops); // attack with troops picked from user
            defendDice = new Dice(countryDefend.getTroopsNum()); // defend with all troops present from defender
            while (!attackDice.isEmpty() && !defendDice.isEmpty()) { // make sure it is possible to attack and defend
                int attackNum = attackDice.getNextHighest(); // choose the highest dice number to attack with
                int defendNum = defendDice.getNextHighest(); // choose the highest dice number to defend with
                if (attackNum > defendNum) {
                    countryDefend.removeTroops(1); // if attacks win once, removes defending troop once
                    defendTroopDeath++;
                } else {
                    AttackingTroops--; // if attacks lost once, removes attacking troop once
                    attackTroopDeath++;
                }
            }
        }
       battleResultString +=("\nBattle Result:\n");
       battleResultString +=(countryAttack.getCountryName() + " lost " + attackTroopDeath + " troops in this battle\n");
        battleResultString +=(countryDefend.getCountryName() + " lost " + defendTroopDeath + " troops in this battle\n");
        if (countryDefend.getTroopsNum() == 0) {
             troopSurvive = AttackingTroops;
            isAttackerWin = true;
            countryDefend.getOwner().removeCountry(countryDefend); //remove the country from the country defender countryOwner
            countryDefend.changeOwner(countryAttack.getOwner());//change owner of the defendCountry
            countryAttack.getOwner().addCountry(countryDefend);//add CountryDefend into the attacker CountryOwner
            battleResultString +=("You win! Now " + countryDefend.getCountryName() + "is yours." +"\n");
            battleResultString +=("you have "+troopSurvive+" troops survived in the battle.");
            countryDefend.getCountryButton().setBackground(countryAttack.getOwner().getColor());
            return battleResultString;
        }
       battleResultString +=("Unfortunately you lose the battle with " + countryDefend.getCountryName()+"\n");
        return battleResultString;
    }

    /*
    *Get the TroopSurvive number 
    *@return TroopSurvive
    */
    public int getTroopSurvive() {
        return troopSurvive;
    }

    /*
    *Check if the attacker win the battle, if true the attacker win, if false the attacker lose 
    *@return isAttackerWin the game
    */
    public boolean isAttackerWin()
    {
        return isAttackerWin;
    }
}
