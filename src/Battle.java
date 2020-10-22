public class Battle {
    private Country countryAttack;
    private Country countryDefend;

    public Battle(Country attack, Country defend){
        this.countryAttack = attack;
        this.countryDefend = defend;
    }

    //this is for quick fight, just to make sure the game is up
    public Boolean fight() {
        while (countryAttack.getTroopsNum() > 1 && countryDefend.getTroopsNum() != 0) {
            Dice attackDice = new Dice(countryAttack.getTroopsNum());
            Dice defendDice = new Dice(countryDefend.getTroopsNum());
            while (attackDice.hasMoreThanOne() && !defendDice.isempty()) {
                if (attackDice.getNexdtHighest() > defendDice.getNexdtHighest()) {
                    countryDefend.removetroop(1);
                } else {
                    countryAttack.removetroop(1);
                }
            }
            if (countryDefend.getTroopsNum() == 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Country canada = new Country("canada");
        Country China = new Country("china");
        canada.addtroops(10);
        China.addtroops(14);
        System.out.print(new Battle(China, canada).fight());
    }
}
