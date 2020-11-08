import org.junit.Test;

import java.util.List;

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
        country1.setTroopsNum(1);
        Country country2 = model.map.get("siam");
        country1.changeOwner(p1);
        country2.changeOwner(p2);
        model.setSelected(country1);
        model.updateState(RiskModel.Phase.ATTACK);
        model.setSelected(country2);
        assertEquals(false,model.attack());

        country1.setTroopsNum(5);
        model.setAttackTroops(3);
        assertEquals(true,model.attack());
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