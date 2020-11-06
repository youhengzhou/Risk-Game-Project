import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskController {
    private RiskModel model;
    private RiskView view;

    public RiskController(RiskModel model, RiskView view){
        this.model = model;
        this.view = view;
       // model.updateList(playerOnGoing);
//        view.getCountriesOwnList().setModel(model.getList());
//        view.getAdjacentCountriesList().setModel(model.getList());

        view.getCountriesOwnText().setText("Player 0 owns these countries: \n"+model.getPlayerOnGoing().getCountriesInString());
        view.getAdjacentCountriesText().setText(model.getPlayerOnGoing().getCountriesInString());
        view.addTestListener(new TestListener());
        view.addHelpButtonListener(new helpButtonListener());
        view.addConfirmButtonListener(new quitButtonListener());
    }

    public static void main(String[] args){
        RiskModel riskModel = new RiskModel();
        RiskView view = new RiskView();

        RiskController controller = new RiskController(riskModel, view);

    }

    class TestListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("sadasdsadasddasdsd");
        }
    }

    class helpButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHelp(model.printHelp());
        }
    }

    class quitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}


