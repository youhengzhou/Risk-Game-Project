import javax.swing.*;
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
        this.updatePlayerInfo(model.getPlayerOnGoing());
        view.getCountriesOwnText().setText("Player 0 owns these countries: \n"+model.getPlayerOnGoing().getCountriesInString());
        view.addTestListener(new TestListener());
        view.addHelpButtonListener(new helpButtonListener());
        view.addConfirmButtonListener(new confirmButtonListener());
        view.addAttackButtonListener(new attackButtonListener());
    }

    public static void main(String[] args){
        RiskModel riskModel = new RiskModel();
        RiskView view = new RiskView();

        RiskController controller = new RiskController(riskModel, view);

    }

//    public void setButtonInfo(){
//        for(JButton button: view.getButtonList()){
//            model.assignButtonToCountry(button);
//        }
//    }

    public void modifyAdjCountryText(String s )
    {

        view.modifyAdjacentCountriesText(s);
    }

    public void updatePlayerInfo(Player player)
    {
        view.getCountriesOwnText().setBackground(player.getColor());

        view.getNamePane().setText("Current Player: "+player.getName());
        view.getCountriesOwnText().setText("Country Own:\n"+player.getAvailableCountries());
    }

    class TestListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class helpButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHelp(model.printHelp());
        }
    }

    class confirmButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.getState().equals(RiskModel.Phase.ATTACK)){
                int num = Integer.parseInt(new JOptionPane().showInputDialog("please input the number of troops you want to send (1-"+ (model.getFirstSelected().getTroopsNum()-1) + ")"));
                model.setAttackTroops(num);
                model.attack();
            }
        }
    }

    class attackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            model.updateState(RiskModel.Phase.ATTACK);
        }
    }
}


