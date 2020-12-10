import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RiskController {
    private RiskModel model;
    private RiskView view;
    public static int ORIGINAL = 1;
    public static int STAR = 2;

    /**
     * Constructor for RiskController
     * initializing view, model, playerinfo, buttonlistener, buttoninfo
     */
    public RiskController(RiskModel model, RiskView view) {
        this.view = view;
        this.model = model;

        this.updateView();
        this.countryButtonConnect();
        this.addButtonListener();
        view.addHelpButtonListener(new helpButtonListener());
        view.addConfirmButtonListener(new confirmButtonListener());
        view.addAttackButtonListener(new attackButtonListener());
        view.addPassButtonListener(new passButtonListener());
        view.addFortifyButtonListener(new fortifyButtonListener());
        view.addSaveButtonListener(new saveButtonListener());
        view.addLoadButtonListener(new loadButtonListener());
        enterRecruitState(model.getPlayerOnGoing());

    }

    /**
     * This is the main function
     *
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        int mode = 999;
        RiskModel testingModel;
        while (mode != ORIGINAL && mode != STAR) {
            String numberStr = new JOptionPane().showInputDialog("                       Choose a MAP                    \n \"1\" for original world map, \"2\" for star war map ");
            if (!isNumeric(numberStr)) continue;
            mode = Integer.parseInt(numberStr);
            testingModel = new RiskModel(mode);
            if(!testingModel.checkMap()){
                System.out.println("this map is invalid");
                mode = 999;
            }
        }
        RiskView view = new RiskView(mode);
        RiskModel riskModel = new RiskModel(view.getNumOfPlayer(), view.getNumOfAiPlayerPlayer(), mode);
        riskModel.addRiskModelListener(view.getadjacentCountriesText());
        riskModel.addRiskModelListener(view.getButtonListAsRiskModelListener());
        riskModel.addRiskModelListener(view.getNamePane());
        riskModel.addRiskModelListener(view.getCountriesOwnText());
        riskModel.addRiskModelListener(view.getImagePanel());

        view.showHelp(riskModel.printHelp());

        RiskController controller = new RiskController(riskModel, view);


        System.out.println("Press on your own country, then \npress confirm to add troops\n------------------------------------------------");

    }

    /**
     * Set buttoninfo, show the button list to the player so they can see it in the window
     */

    public void countryButtonConnect() {
        for (JButton button : view.getButtonList()) {
            model.assignButtonToCountry(button);
        }
        model.updateModelListeners();
    }


    /**
     * Show out the player's information in the window
     * player's name and the contries they own
     */
    public void updateView() {
        model.updateModelListeners();
    }

    /**
     * Add button listener to attack countries
     * print out the information of the country that get attacked
     */
    public void addButtonListener() {
        for (JButton button : view.getButtonList()) {
            button.setFont(new Font("Arial", Font.PLAIN, 10));

            button.setMargin(new Insets(0, 0, 0, 0));

//            button.setText(Integer.toString(model.getCountry(button.getActionCommand()).getTroopsNum()));
            button.addActionListener(e -> {
                model.setSelected(model.getCountry(button.getActionCommand()));
                model.setSelectedCountryInfo(button.getActionCommand());


                updateView();
                if (model.getState() == RiskModel.Phase.AIATTACK) {
                    // AI Placholder

                }
                if (model.getState() == RiskModel.Phase.ATTACK) {
                    model.setSelected(model.gameMap.getMap().get(button.getActionCommand()));
                    System.out.println("Attacking Country : \n" + model.getFirstSelected().printTrumpNumState());
                    System.out.println("\nAttacking to: \n" + ((model.getSecondSelected() == null) ? "" : model.getSecondSelected().printTrumpNumState()) + "\n--------------------------------------------");
                } else if (model.getState().equals(RiskModel.Phase.FORTIFY)) {
                    model.setSelected(model.gameMap.getMap().get(button.getActionCommand()));
                    System.out.println("Fortifying from Country : \n" + model.getFirstSelected().printTrumpNumState());
                    System.out.println("\nFortifying to: \n" + ((model.getSecondSelected() == null) ? "" : model.getSecondSelected().printTrumpNumState()) + "\n--------------------------------------------");
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


            if (model.getState().equals(RiskModel.Phase.RESIGN)) {
                new JOptionPane().showMessageDialog(view, "you have to finish resign to get to next step");
                //model.updateState(RiskModel.Phase.ATTACK);

            } else if (model.getState().equals(RiskModel.Phase.ATTACK)) {
                new JOptionPane().showMessageDialog(view, "now, you are at Fortify phase. when you done with Fortify, just press PASS again");
                model.updateState(RiskModel.Phase.FORTIFY);
            } else {


                //if(model.getPlayerOnGoing().isAi()) view.clickPassButton();
                if (model.getPlayerComing().isAi()) {

                    model.pass();
                    updateView();
                    String AiRoundInfo = model.getAiPlayInfo();
                    new JOptionPane().showMessageDialog(view, AiRoundInfo);

                    //System.out.println(aiRecruitInfo);
                    //let view to displaythose info and then pass on to the next player.

                    view.clickPassButton();
                    return;

                }
                model.pass();
                updateView();
                System.out.println("Press on any country you want to \n" + "check their status, \nwhen you feel ready, press [attack]\n------------------------------------------------");
                enterRecruitState(model.getPlayerOnGoing());
                System.out.println(model.getState());

            }
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
            if (model.getState().equals(RiskModel.Phase.RESIGN)) {

                Country c = model.getFirstSelected();
                if (!model.getPlayerOnGoing().getCountriesOwn().contains(c)) {
                    new JOptionPane().showMessageDialog(view, "You can only choose your own country to assign new troops, do it again");
                    return;
                }
                assignNewTroops(c);

                return;

            }
            if (model.getState().equals(RiskModel.Phase.ATTACK)) {

                if (model.getFirstSelected() == (null) || model.getFirstSelected() == (null)) {
                    new JOptionPane().showMessageDialog(view, "Please select attack and defend countries first");
                    return;
                }
                if (model.getFirstSelected().getOwner() != model.getPlayerOnGoing()) {
                    new JOptionPane().showMessageDialog(view, "You can only choose your own country to attack\n Please press [Attack] again and choose another one");
                    model.releaseSelected();
                    return;
                } else if (model.getSecondSelected().getOwner() == model.getPlayerOnGoing()) {
                    new JOptionPane().showMessageDialog(view, "You don't want to attack your own country\n Please press [Attack] again and choose another one\n");
                    model.releaseSelected();
                    return;
                } else if (!model.getFirstSelected().getAdjacentCountries(model.getGameMap()).contains(model.getSecondSelected())) {
                    new JOptionPane().showMessageDialog(view, "The two country you chosen is not adjacent to each other\n Please press [Attack] again and choose another one\n");
                    model.releaseSelected();
                    return;
                } else if (model.getFirstSelected().getCountryTroopsNumber() < 2) {
                    new JOptionPane().showMessageDialog(view, "the country you select has no enough troops to attack\n Please press [Attack] again and choose another one");
                    model.releaseSelected();
                    return;
                }
                int num = 999;
                boolean isNumeric;
                do {
                    String numberStr = new JOptionPane().showInputDialog("please input the number of troops you want to send (1-" + (model.getFirstSelected().getCountryTroopsNumber() - 1) + ")");

                    if (!isNumeric(numberStr)) continue;
                    num = Integer.parseInt(numberStr);
                } while (num > model.getFirstSelected().getCountryTroopsNumber() - 1 || num < 1); //keep looping until the num satisfies the requirement.

                model.setAttackTroops(num);
                model.attack();
                updateView();
                new JOptionPane().showMessageDialog(view, model.printBattleResult());
                if (model.getAttackWin()) {

                    num = 999;
                    do {
                        String numberStr = new JOptionPane().showInputDialog("how many survived troops you want to send to " + model.getSecondSelected().getCountryName() + "\n maximum " + model.getSurvivedTroops() +
                                "minimum 1 \n The remaining troops will be sent back to their home land " + model.getFirstSelected().getCountryName());
                        //                 isNumeric = numberStr.chars().allMatch(Character :: isDigit);

                        if (!isNumeric(numberStr)) continue;
                        num = Integer.parseInt(numberStr);
                    } while (num > model.getSurvivedTroops() || num < 1);
                    model.iniAttackWin();
                    model.handleSurvivedTroops(num);
                }
                updateView();

            }
            if (model.getState().equals(RiskModel.Phase.FORTIFY)) {
                if (model.availableToMove(model.getFirstSelected())) {
                    int num = 999;
                    while (num < 1 || num > (model.getFirstSelected().getCountryTroopsNumber() - 1)) {
                        String numberStr = new JOptionPane().showInputDialog("how many troops you want to move (1 to " + (model.getFirstSelected().getCountryTroopsNumber() - 1));
                        if (!isNumeric(numberStr)) continue;
                        num = Integer.parseInt(numberStr);

                    }
                    model.moveTroops(num);
                    model.clearPreCountries();
                    new JOptionPane().showMessageDialog(view, "move success, your turn is done ");
                    view.clickPassButton();

                } else {
                    new JOptionPane().showMessageDialog(view, "failed to move, make sure selcting your own country with enough troops on it");
                }
                updateView();
                return;
            }
            if (model.hasWinner()) {
                new JOptionPane().showMessageDialog(view, "The game is end, the WINNER is: " + model.getPlayerOnGoing());
                System.exit(0);
            }
            updateView();
            model.releaseSelected();
        }
    }


    public void enterRecruitState(Player player) {
        model.updateState(RiskModel.Phase.RESIGN);
        model.refreshNewArmy();
        int newTroops = model.getNewArmyLeft();


        new JOptionPane().showMessageDialog(view, "It's your turn " + player.getName() + ".\n you have " + newTroops + " new troops in this round,\n plase click on the country you want to assign troops\n when you ready, press confirm ");
    }

    public boolean assignNewTroops(Country country) {

        int newTroops = model.getNewArmyLeft();
        int num = 999;
        do {
            String numberStr = new JOptionPane().showInputDialog("how many troop do you want to add to " + country.getCountryName() + "\n Max " + newTroops);

            if (!isNumeric(numberStr)) continue;
            num = Integer.parseInt(numberStr);
        } while (num > newTroops || num < 0);
        country.addTroops(num);
        boolean noTroopsLeft = model.decrementNewArmy(num);
        System.out.println(num + " troops assign to " + country.getCountryName());
        updateView();
        if (noTroopsLeft) {
            new JOptionPane().showMessageDialog(view, "You have assigned all new army\n press Attack when you are ready to fight");
            model.updateState(RiskModel.Phase.ATTACK);
        } else {
            System.out.println("You still have " + model.getNewArmyLeft() + " troops left \nto be assign, please continue");

        }
        return noTroopsLeft;

    }

    public static boolean isNumeric(String s) {
        boolean b = false;
        try {
            b = s.chars().allMatch(Character::isDigit);
        } catch (Exception e) {

        }
        return b;
    }

    /**
     * Choose the country that the player wants to use to attack
     */
    class attackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.getState().equals(RiskModel.Phase.RESIGN)) {
                new JOptionPane().showMessageDialog(view, "Please assign all new troops before Attack");
                return;
            }
            if (model.getState().equals(RiskModel.Phase.FORTIFY)) {
                new JOptionPane().showMessageDialog(view, "Now is Fortify Phase, you can't attack now \n Please wait for the next round");
                return;
            }
            model.releaseSelected();
            model.updateState(RiskModel.Phase.ATTACK);
            new JOptionPane().showMessageDialog(view, "Now please choose two countries\n First one being the country you want to use to Attack\n" +
                    "Second one being the country you are intending to attack\n The country you are using to Attack need to have more than 1 troops on it\n " +
                    "You can not attack your own country\n PressConfirm] after selecting Attacking and Defending countries\n Good Luck");
        }

    }

    class fortifyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.getState().equals(RiskModel.Phase.RESIGN)) {
                new JOptionPane().showMessageDialog(view, "Please assign all new troops first");
                return;
            }
            if (model.getState().equals(RiskModel.Phase.ATTACK)) {
                new JOptionPane().showMessageDialog(view, "Now is Attack Phase, you can't, leave this alone");
                return;
            }
            model.releaseSelected();
            model.updateState(RiskModel.Phase.FORTIFY);
            new JOptionPane().showMessageDialog(view, "Fortify! Please choose two countries, From and To\n then press confirm to move your armies");

        }
    }

    class saveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fileName = new JOptionPane().showInputDialog("Please give a name for the saving point");
            try {
                model.exportToXMLFile(fileName);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    class loadButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fileName = new JOptionPane().showInputDialog("Which saving point would you like to access");
            model.importFromXmlFile(fileName);
            model.updateModelListeners();
        }
    }
}


