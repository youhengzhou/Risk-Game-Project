import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RiskModelTest {

    private RiskModel model = new RiskModel();


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

//    @Test
//    public void attackTest() {
//        model.setSelected(model.map.get("India"));
//        model.updateState(RiskModel.Phase.ATTACK);
//        model.setSelected(model.map.get("Siam"));
//        assertEquals(true,model.attack());
//
//    }

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