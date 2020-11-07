import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskController implements ActionListener {
    private RiskModel model;
    private RiskView view;

    public RiskController(){
    }

    public static void main(String[] args)
    {



        RiskView view = new RiskView();



    }

    public void addView(RiskView view)
    {
        this.view = view;
    }

    public void addModel(RiskModel model)
    {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(model.isCountryButton(e.getActionCommand()))
        {
            model.handleCountryButton(e.getActionCommand());

        }
        if(e.getActionCommand() == "attack")
        {
            System.out.println("attackButton is pressed");
        }
        if(e.getActionCommand() == "confirm")
        {
            System.out.println("confirmButton is pressed");
        }
        if(e.getActionCommand() == "pass")
        {
            model.pass() ;
        }
        if(e.getActionCommand() == "help")
        {
            view.showHelp(model.printHelp());
        }

    }

    public void correlateCountryButton(JButton b)
    {

        model.assignButtonToCountry(b);
    }

    public void modifyAdjCountryText(String s )
    {

        view.modifyAdjacentCountriesText(s);
    }

    public void updatePlayerInfo(Player player)
    {
        JTextArea textArea = view.getCountriesOwnText();
        JTextPane namepane = view.getNamepane();

        namepane.setBackground(player.getColor());
        namepane.setText("Current Player: "+player.getName());

       // playNameArea.setText("Current Player:\n"+player.getName());
        textArea.setText("Country Own:\n"+player.getAvailableCountries());
    }

}


