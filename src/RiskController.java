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
        System.out.println("Press on any country you want to \n" + "check their status, \nwhen you feel ready, press [attack]\n------------------------------------------------");

    }

    public void setButtonInfo(){
        for(JButton button: view.getButtonList()){
            model.assignButtonToCountry(button);
        }
    }

    public void setNumOfPlayer(){
        int num = 0;
        do{
        num = Integer.parseInt(new JOptionPane().showInputDialog("please insert the number of Player"));
        } while(num < 2 || num > 6);
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
                    if(model.getState() == RiskModel.Phase.ATTACK) {
                        model.setSelected(model.map.get(button.getActionCommand()));
                        System.out.println("Attacking Country : \n" + model.getFirstSelected().printState());
                        System.out.println("\nAttacking to: \n" + ((model.getSecondSelected() == null) ? "" : model.getSecondSelected().printState())+"\n--------------------------------------------");
                    }
                }
            });
        }
    }

    class passButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            model.pass();
            updatePlayerInfo(model.getPlayerOnGoing());
            System.out.println("Press on any country you want to \n" + "check their status, \nwhen you feel ready, press [attack]\n------------------------------------------------");

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
                if(model.getFirstSelected().getOwner() != model.getPlayerOnGoing())
                {
                    new JOptionPane().showMessageDialog(view, "You can only choose your own country to attack\n Please press [Attack] again and choose another one");
                    model.releaseSelected();
                    model.updateState(RiskModel.Phase.PENDING);

                }
                else if (model.getSecondSelected().getOwner() == model.getPlayerOnGoing())
                {
                    new JOptionPane().showMessageDialog(view, "You don't want to attack your own country\n Please press [Attack] again and choose another one\n");
                    model.releaseSelected();
                    model.updateState(RiskModel.Phase.PENDING);
                }
                else if (!model.getFirstSelected().getAdjacentCountries().contains(model.getSecondSelected()))
                {
                    new JOptionPane().showMessageDialog(view, "The two country you chosen is not adjacent to each other\n Please press [Attack] again and choose another one\n");
                    model.releaseSelected();
                    model.updateState(RiskModel.Phase.PENDING);
                }
                else if(model.getFirstSelected().getTroopsNum() < 2){
                    new JOptionPane().showMessageDialog(view, "the country you select has no enough troops to attack\n Please press [Attack] again and choose another one");
                    model.releaseSelected();
                    model.updateState(RiskModel.Phase.PENDING);
                    return;
                }
                num = 999;
                while(num> model.getFirstSelected().getTroopsNum()-1)
                {
                    num = Integer.parseInt(new JOptionPane().showInputDialog("please input the number of troops you want to send (1-"+ (model.getFirstSelected().getTroopsNum()-1) + ")"));
                }
                model.setAttackTroops(num);
                boolean result = model.attack();
                new JOptionPane().showMessageDialog(view, model.printBattleResult());
                if(result){
                    num = 999;
                    while(num>model.getSurvivedTroops() || num<1) {
                        num = Integer.parseInt(new JOptionPane().showInputDialog("how many survived troops you want to send to "+ model.getSecondSelected().getCountryName()+"\n maximum "+model.getSurvivedTroops()+
                                "minimum 1 \n The remaining troops will be sent back to their home land"+model.getFirstSelected().getCountryName()));
                    }

                    model.handleSurvivedTroops(num);

                }
                model.releaseSelected();

            }
            updatePlayerInfo(model.getPlayerOnGoing());
            model.releaseSelected();

        }
    }

    class attackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            model.updateState(RiskModel.Phase.ATTACK);
            new JOptionPane().showMessageDialog(view, "Now please choose two countries\n First one being the country you want to use to Attack\n" +
                    "Second one being the country you are intending to attack\n The country you are using to Attack need to have more than 1 troops on it\n " +
                    "You can not attack your own country\n Press [Confirm] after selecting Attacking and Defending countries\n Good Luck");
        }
    }
}


