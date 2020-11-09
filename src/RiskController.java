import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskController {
    private RiskModel model;
    private RiskView view;

    /**
     * Constructor for RiskController
     *initializing view, model, playerinfo, buttonlistener, buttoninfo
     */
    public RiskController(RiskModel model, RiskView view) {
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

    /**
     *This is the main function
     *@param args
     */
    public static void main(String[] args) {
        RiskView view = new RiskView();
        RiskModel riskModel = new RiskModel();

        RiskController controller = new RiskController(riskModel, view);
        view.showHelp(riskModel.printHelp());
        System.out.println("Press on any country you want to \n" + "check their status, \nwhen you feel ready, press [attack]\n------------------------------------------------");

    }

    /**
     * Set buttoninfo, show the button list to the player so they can see it in the window
     */
    public void setButtonInfo() {
        for (JButton button : view.getButtonList()) {
            model.assignButtonToCountry(button);
        }
    }

    /**
     * Set the bumber of the player
     * the number of the player should bigger than 2 and smaller that 6
     */
    public void setNumOfPlayer() {
        int num = 0;
        do {
            num = Integer.parseInt(new JOptionPane().showInputDialog("please insert the number of Player"));
        } while (num < 2 || num > 6);
    }

    /**
     * Show out the player's information in the window
     * player's name and the contries they own
     */
    public void updatePlayerInfo(Player player) {
        view.getNamePane().setBackground(player.getColor());

        view.getNamePane().setText("Current Player: " + player.getName());
        view.getCountriesOwnText().setText("Countries You Own:\n" + player.getAvailableCountries());
    }

    /**
     * Add button listener to attack countries
     * print out the information of the country that get attacked
     */
    public void addButtonListener() {
        for (JButton button : view.getButtonList()) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.modifyAdjacentCountriesText(model.handleCountryButton(button.getActionCommand()));
                    if (model.getState() == RiskModel.Phase.ATTACK) {
                        model.setSelected(model.map.get(button.getActionCommand()));
                        System.out.println("Attacking Country : \n" + model.getFirstSelected().printState());
                        System.out.println("\nAttacking to: \n" + ((model.getSecondSelected() == null) ? "" : model.getSecondSelected().printState()) + "\n--------------------------------------------");
                    }
                }
            });
        }
    }

    /**
     * Get player's action then print out the informaiton by using the updateplayerinfo
     */
    class passButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.pass();
            updatePlayerInfo(model.getPlayerOnGoing());
            System.out.println("Press on any country you want to \n" + "check their status, \nwhen you feel ready, press [attack]\n------------------------------------------------");

        }
    }

    /**
     * Print out the help information if the player press the help button
     */
    class helpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHelp(model.printHelp());
        }
    }

    /**
     * Check if the player choose the country match all the conditions print out a comment if don't match
     * if the chountry matchs every condition, choose the troops number to attack it, then the system will give the winer
     */
    class confirmButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int num = 0;
            if (model.getState().equals(RiskModel.Phase.ATTACK)) {
                if (model.getFirstSelected().getOwner() != model.getPlayerOnGoing()) {
                    new JOptionPane().showMessageDialog(view, "You can only choose your own country to attack\n Please press [Attack] again and choose another one");
                    model.releaseSelected();
                    model.updateState(RiskModel.Phase.PENDING);
                    return;
                } else if (model.getSecondSelected().getOwner() == model.getPlayerOnGoing()) {
                    new JOptionPane().showMessageDialog(view, "You don't want to attack your own country\n Please press [Attack] again and choose another one\n");
                    model.releaseSelected();
                    model.updateState(RiskModel.Phase.PENDING);
                    return;
                } else if (!model.getFirstSelected().getAdjacentCountries().contains(model.getSecondSelected())) {
                    new JOptionPane().showMessageDialog(view, "The two country you chosen is not adjacent to each other\n Please press [Attack] again and choose another one\n");
                    model.releaseSelected();
                    model.updateState(RiskModel.Phase.PENDING);
                    return;
                } else if (model.getFirstSelected().getTroopsNum() < 2) {
                    new JOptionPane().showMessageDialog(view, "the country you select has no enough troops to attack\n Please press [Attack] again and choose another one");
                    model.releaseSelected();
                    model.updateState(RiskModel.Phase.PENDING);
                    return;
                }

                num = Integer.parseInt(new JOptionPane().showInputDialog("please input the number of troops you want to send (1-" + (model.getFirstSelected().getTroopsNum() - 1) + ")"));
                model.setAttackTroops(num);
                model.attack();
                new JOptionPane().showMessageDialog(view, model.printBattleResult());
                if (model.getAttackWin()) {
                    num = 999;
                    while (num > model.getSurvivedTroops() || num < 1) {
                        num = Integer.parseInt(new JOptionPane().showInputDialog("how many survived troops you want to send to " + model.getSecondSelected().getCountryName() + "\n maximum " + model.getSurvivedTroops() +
                                "minimum 1 \n The remaining troops will be sent back to their home land" + model.getFirstSelected().getCountryName()));
                    }
                    model.iniAttackWin();
                    model.handleSurvivedTroops(num);
                }

            }
            if (model.hasWinner()) {
                new JOptionPane().showMessageDialog(view, "The game is end, the WINNER is: " + model.getPlayerOnGoing());
                System.exit(0);
            }
            updatePlayerInfo(model.getPlayerOnGoing());
            model.releaseSelected();
        }
    }

    /**
     * Choose the country that the player wants to use to attack 
     */
    class attackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.updateState(RiskModel.Phase.ATTACK);
            new JOptionPane().showMessageDialog(view, "Now please choose two countries\n First one being the country you want to use to Attack\n" +
                    "Second one being the country you are intending to attack\n The country you are using to Attack need to have more than 1 troops on it\n " +
                    "You can not attack your own country\n Press [Confirm] after selecting Attacking and Defending countries\n Good Luck");
        }
    }
}


