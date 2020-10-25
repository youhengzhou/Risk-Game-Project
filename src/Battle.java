public class Battle {
    private Country countryAttack;
    private Country countryDefend;
    private int AttackingTroops;

    public Battle(Country attack, Country defend,int troopSent){
        this.countryAttack = attack;
        this.countryDefend = defend;
        AttackingTroops=troopSent;
        countryAttack.removetroop(AttackingTroops);
    }

    //this is for quick fight, just to make sure the game is up
    public Boolean fight() {
        int attackTroopDeath = 0;
        int defendTroopDeath=0;

            Dice attackDice = new Dice(1);
            Dice defendDice=new Dice(1) ;


            while(AttackingTroops!=0 && countryDefend.getTroopsNum()!=0) {
                attackDice = new Dice(AttackingTroops);
                defendDice = new Dice(countryDefend.getTroopsNum());


                while (!attackDice.isempty() && !defendDice.isempty()) {

                    int attacknum = attackDice.getNexdtHighest();
                    int defendnum = defendDice.getNexdtHighest();


                        if (attacknum > defendnum) {
                        countryDefend.removetroop(1);

                        defendTroopDeath++;
                    } else {
                        AttackingTroops--;
                        attackTroopDeath++;
                    }

                }
            }
            System.out.println("\nBattle Result:");
            System.out.println(countryAttack.getCountryName()+" lost "+attackTroopDeath+" troops in this battle");
            System.out.println(countryDefend.getCountryName() +" lost "+defendTroopDeath+" troops in this battle");
            if (countryDefend.getTroopsNum() == 0)
            {

                int troopSurvive =AttackingTroops;
                countryDefend.addtroops(troopSurvive);
                countryDefend.getOwner().removeCountry(countryDefend); //remove the country from the country defender countryOwner
                countryDefend.changeOwner(countryAttack.getOwner());//change owner of the defendCountry
                countryAttack.getOwner().addCountry(countryDefend);//add CountryDefend into the attaker CountryOwner
                System.out.println("You win! now "+countryDefend.getCountryName()+"is yours");
                System.out.println("Survived "+troopSurvive+" troops moved from "+countryAttack.getCountryName()+" to "+countryDefend.getCountryName()+"\n\n");
                return true;
            }

        System.out.println("Unfortunately you lose the battle with "+countryDefend.getCountryName());
        return false;
    }

    public static void main(String[] args) {
        Country canada = new Country("canada");
        Country China = new Country("china");
        canada.addtroops(10);
        China.addtroops(14);
       // System.out.print(new Battle(China, canada).fight());
    }
}
