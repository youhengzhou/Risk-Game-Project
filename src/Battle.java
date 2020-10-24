public class Battle { // the Battle class mainly treats the battle portion of the game, where country name and troop numbers are taken and used for battles.
    private Country countryAttack;
    private Country countryDefend;
    private int AttackingTroops;

    public Battle(Country attack, Country defend,int troopSent){ // initialize battle
        this.countryAttack = attack;
        this.countryDefend = defend;
        AttackingTroops=troopSent;
        countryAttack.removetroop(AttackingTroops);
    }

    //this is for quick fight, just to make sure the game is up
    public Boolean fight() {
        int attackTroopDeath = 0; // counter for taking away troops later
        int defendTroopDeath=0; // counter for taking away troops later
        Dice attackDice = new Dice(1);
        Dice defendDice=new Dice(1) ;

        while(AttackingTroops!=0 && countryDefend.getTroopsNum()!=0) {
            attackDice = new Dice(AttackingTroops); // attack with troops picked from user
            defendDice = new Dice(countryDefend.getTroopsNum()); // defend with all troops present from defender

            while (!attackDice.isempty() && !defendDice.isempty()) { // make sure it is possible to attack and defend
                int attacknum = attackDice.getNextHighest(); // choose the highest dice number to attack with
                int defendnum = defendDice.getNextHighest(); // choose the highest dice number to defend with
                if (attacknum > defendnum) {
                    countryDefend.removetroop(1); // if attacks win once, removes defending troop once
                    defendTroopDeath++;
                } else {
                    AttackingTroops--; // if attacks lost once, removes attacking troop once
                    attackTroopDeath++;
                }
            }
        }
        System.out.println("\nBattle Result:");
        System.out.println(countryAttack.getCountryName()+" lost "+attackTroopDeath+" troops in this battle");
        System.out.println(countryDefend.getCountryName() +" lost "+defendTroopDeath+" troops in this battle");
        if (countryDefend.getTroopsNum() == 0) {
            int troopSurvive =AttackingTroops;
            countryDefend.addtroops(troopSurvive);
            countryDefend.getOwner().removeCountry(countryDefend); //remove the country from the country defender countryOwner
            countryDefend.changeOwner(countryAttack.getOwner());//change owner of the defendCountry
            countryAttack.getOwner().addCountry(countryDefend);//add CountryDefend into the attaker CountryOwner
            System.out.println("You win! Now "+countryDefend.getCountryName()+"is yours.");
            System.out.println("Surviving "+troopSurvive+" troops has moved from "+countryAttack.getCountryName()+" to "+countryDefend.getCountryName()+"\n\n");
            return true;
        }
        System.out.println("Unfortunately you lose the battle with "+countryDefend.getCountryName());
        return false;
    }

    /*public static void main(String[] args) { // sample demonstrating how the code can be used to initiate a potential battle
        Country northPole = new Country("north pole");
        Country southPole = new Country("south pole");
        northPole.addtroops(10);
        southPole.addtroops(10);
       // System.out.print(new Battle(northPole, southPole).fight());
    }*/
}
