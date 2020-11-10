import org.junit.Test;

import static org.junit.Assert.*;

public class RiskModelTest {

    private RiskModel model = new RiskModel(2);


    @Test
    public void randomAssignCountry() {
       for(Country c:  model.map.values()){
           assertEquals(true,c.hasOwner());
       }
    }

    @Test
    public void randomAssignTroops() {
        for(Country c:  model.map.values()){
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
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");

        model.setPlayerOnGoing(p1);
        Country country1 = model.map.get("india");
        p1.addCountry(country1);
        country1.setTroopsNum(1);
        Country country2 = model.map.get("siam");
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

        Country country3 = model.map.get("japan");
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
}