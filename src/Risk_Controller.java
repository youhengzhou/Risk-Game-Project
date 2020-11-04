import javax.swing.*;
import java.util.List;

public class Risk_Controller {
    private Risk_Model model;
    private RiskView view;

    public Risk_Controller(Risk_Model model, RiskView view){
        this.model = model;
        this.view = view;
       // model.updateList(playerOnGoing);
//        view.getCountriesOwnList().setModel(model.getList());
//        view.getAdjacentCountriesList().setModel(model.getList());

        view.getCountriesOwnText().setText("Player 0 owns these countries: \n"+model.getPlayerOnGoing().getCountriesInString());
        view.getAdjacentCountriesText().setText(model.getPlayerOnGoing().getCountriesInString());
    }

    public static void main(String[] args){
        Risk_Model riskModel = new Risk_Model();
        RiskView view = new RiskView();

        Risk_Controller controller = new Risk_Controller(riskModel, view);

    }
}
