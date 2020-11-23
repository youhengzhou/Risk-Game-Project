import org.junit.Test;

import static org.junit.Assert.*;

public class RiskModelTest {

    private RiskModel model = new RiskModel(2);


    @Test
    public void randomAssignCountry() {
       for(Country c:  model.gameMap.map.values()){
           assertEquals(true,c.hasOwner());
       }
    }

    @Test
    public void randomAssignTroops() {
        for(Country c:  model.gameMap.map.values()){
            assertEquals(true,c.getTroopsNum() != 0);
        }
    }

    @Test
    public void removePlayerWithNoCountry() {
        Player player = model.getPlayerOnGoing();
        player.clear();
        assertEquals(0,player.getCountriesOwn().size());
        model.removePlayerWithNoCountry();
        assertEquals(1,model.getNumOfPlayer());
    }

    @Test
    public void attackTest() {
        Player p1 = new Player("p1", false);
        Player p2 = new Player("p2", false);

        model.setPlayerOnGoing(p1);
        Country country1 = model.gameMap.map.get("india");
        p1.addCountry(country1);
        country1.setTroopsNum(1);
        Country country2 = model.gameMap.map.get("siam");
        p2.addCountry(country2);

        country1.changeOwner(p1);
        country2.changeOwner(p2);

        assertEquals(false,model.attack());// test when the firstSelected or secondSelected is null

        model.updateState(RiskModel.Phase.ATTACK);
        model.setSelected(country1);
        model.setSelected(country2);
        assertEquals(false,model.attack()); // test when the troopsNum in attackCountry < 2

        p1.removeCountry(country1);
        assertEquals(false,model.attack()); // test when the player doesn't contains the Country

        p1.addCountry(country1);
        country1.setTroopsNum(5);
        country2.setTroopsNum(5);
        model.setAttackTroops(3);
        assertEquals(true,model.attack());
        System.out.println(model.printBattleResult());

        Country country3 = model.gameMap.map.get("japan");
        p2.addCountry(country3);
        model.setSelected(country3);
        assertEquals(false,model.attack()); // test when the Country you are attacking is not your adjacentCountry

    }

    @Test
    public void setSelectedTest() {
        Country India = new Country("India");
        Country Siam = new Country("Siam");
        Country Ontario = new Country("Ontario");
        model.setSelected(India);
        assertEquals("India", model.getFirstSelected().getCountryName());
        model.setSelected(Siam);
        assertEquals("Siam", model.getFirstSelected().getCountryName());

        model.updateState(RiskModel.Phase.ATTACK);
        model.setSelected(Ontario);
        assertEquals("Siam", model.getFirstSelected().getCountryName());
        assertEquals("Ontario", model.getSecondSelected().getCountryName());
        model.setSelected(India);
        assertEquals("India", model.getSecondSelected().getCountryName());

    }

    @Test
    public void availableToMoveTest(){
        Player p1 = new Player("p1",false);
        model.setPlayerOnGoing(p1);
        Country country1 = model.gameMap.map.get("india");
        p1.addCountry(country1);
        country1.setTroopsNum(1);
        Country country2 = model.gameMap.map.get("china");
        p1.addCountry(country2);
        Country country3 = model.gameMap.map.get("japan");
        p1.addCountry(country3);
        Country country4 = model.gameMap.map.get("mongolia");
        p1.addCountry(country4);

        model.updateState(RiskModel.Phase.FORTIFY);
        model.setSelected(country1);
        model.setSelected(country3);
        assertEquals(true, model.availableToMove(model.getFirstSelected()));

        Country country5 = model.gameMap.map.get("congo");
        p1.addCountry(country5);
        model.setSelected(country5);
        assertEquals(false, model.availableToMove(model.getFirstSelected()));

//        model.releaseSelected();
//        model.setSelected(country1);
//        model.setSelected(model.gameMap.map.get("china"));
//        assertEquals(false,  model.availableToMove(model.getFirstSelected(), null));
    }
    @Test
    public void resignTest() {
        Player p1 = new Player("p1",false);
        model.setPlayerOnGoing(p1);
        Country country1 = model.getCountry("easternaustralia");
        p1.addCountry(country1);
        country1.setTroopsNum(1);
        Country country2 = model.gameMap.map.get("indonesia");
        p1.addCountry(country2);
        Country country3 = model.gameMap.map.get("newguinea");
        p1.addCountry(country3);
        Country country4 = model.gameMap.map.get("westernaustralia");
        p1.addCountry(country4);
        //p1 owns entire australia, and owning four countries in total.
        model.setPlayerOnGoing(p1);
        model.updateState(RiskModel.Phase.RESIGN);
        model.refreshNewArmy();
        assertEquals(3,model.getNewArmyLeft());
        model.decrementNewArmy(2);
        assertEquals(1,model.getNewArmyLeft());
        //now we add 3 more counties to p1.
        Country country5 = model.getCountry("kamchatka");
        p1.addCountry(country5);
        country1.setTroopsNum(1);
        Country country6 = model.gameMap.map.get("middleeast");
        p1.addCountry(country6);
        Country country7 = model.gameMap.map.get("siberia");
        p1.addCountry(country7);
        model.refreshNewArmy();//refresh the total new army
        assertEquals(4,model.getNewArmyLeft());
        //should return true when there's no more troops left to assign
        assertEquals(true,model.decrementNewArmy(4));

    }
}