import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskController {
    private RiskModel model;
    private RiskView view;

    public RiskController(RiskModel model, RiskView view){
        this.view = view;
        this.model = model;

        this.updatePlayerInfo(model.getPlayerOnGoing());
        this.addButtonListener();
        this.setButtonInfo();
        view.addHelpButtonListener(new helpButtonListener());
        view.addConfirmButtonListener(new confirmButtonListener());
        view.addAttackButtonListener(new attackButtonListener());
        view.addPassButtonListener(new passButtonListener());
    }

    public static void main(String[] args){
        RiskView view = new RiskView();
        RiskModel riskModel = new RiskModel();

        RiskController controller = new RiskController(riskModel, view);
        view.showHelp(riskModel.printHelp());

    }

    public void setButtonInfo(){
        for(JButton button: view.getButtonList()){
            model.assignButtonToCountry(button);
        }
    }

    public void updatePlayerInfo(Player player)
    {
        view.getNamePane().setBackground(player.getColor());

        view.getNamePane().setText("Current Player: "+player.getName());
        view.getCountriesOwnText().setText("Countries You Own:\n"+player.getAvailableCountries());
    }

    public void addButtonListener(){
        for(JButton button: view.getButtonList()){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.modifyAdjacentCountriesText(model.handleCountryButton(button.getActionCommand()));
                    model.setSelected(model.map.get(button.getActionCommand()));
                    System.out.println("\nAttack from: "+ model.getFirstSelected());
                    System.out.println("Attack to: "+ model.getSecondSelected());
                }
            });
        }
    }

    class passButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            model.pass();
            updatePlayerInfo(model.getPlayerOnGoing());
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
            int num = 0;
            if(model.getState().equals(RiskModel.Phase.ATTACK )){
                if(model.getFirstSelected().getTroopsNum() < 2){
                    new JOptionPane().showMessageDialog(view, "not enough troops to attack");
                    return;
                }
                num = Integer.parseInt(new JOptionPane().showInputDialog("please input the number of troops you want to send (1-"+ (model.getFirstSelected().getTroopsNum()-1) + ")"));
                model.setAttackTroops(num);
                if(model.attack()){
                   num = Integer.parseInt(new JOptionPane().showInputDialog("how many troops you want to send to this country (1 - "+ (model.getFirstSelected().getTroopsNum()-1) + ")"));
                   model.getFirstSelected().removeTroops(num);
                   model.getSecondSelected().addTroops(num);
                }

            }
            updatePlayerInfo(model.getPlayerOnGoing());
            model.releaseSelected();
        }
    }

    class attackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            model.updateState(RiskModel.Phase.ATTACK);
        }
    }
}


