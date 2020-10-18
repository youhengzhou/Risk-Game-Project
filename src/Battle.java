public class Battle {
    private Country countryAttack;
    private Country countryDefend;
//    private Dice attackDice;
//    private Dice defendDice;

    public Battle(Country attack, Country defend){
        this.countryAttack = attack;
        this.countryDefend = defend;

//        this.attackDice = new Dice(countryAttack.getTroopsNum());
//        this.defendDice = new Dice(countryDefend.getTroopsNum());

//        if(countryAttack.getTroopsNum()>3){
//            this.attackDice = new Dice(3);
//        }
//
//        if(countryDefend.getTroopsNum() >= 3) {
//            this.defendDice = new Dice(3);
//        } else {
//            this.defendDice = new Dice(countryDefend.getTroopsNum());
//        }
    }

    //this is for quick fight, just to make sure the game is up
    public Boolean fight(){
        while(countryAttack.getTroopsNum() != 0 && countryDefend.getTroopsNum() != 0){
            Dice attackDice = new Dice(countryAttack.getTroopsNum());
            Dice defendDice = new Dice(countryDefend.getTroopsNum());

            while(!attackDice.isempty() && !defendDice.isempty()){
                    if(attackDice.getNexdtHighest() > defendDice.getNexdtHighest()){
                        countryDefend.removetroop(1);

                    } else {
                        countryAttack.removetroop(1);
                    }
            }

            if(countryDefend.getTroopsNum() ==0) return true;
        }
        return false;
    }
    public static void main (String[] args){
        Country canada = new Country(3, new Player("ben"), "canada");
        Country China = new Country(4, new Player("jay"), "china");

        System.out.print(new Battle(China, canada).fight());
    }
}
