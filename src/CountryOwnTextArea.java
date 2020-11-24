import javax.swing.*;
import java.awt.*;

public class CountryOwnTextArea extends JTextArea implements RiskModelListener {
    /**
     * Constructor of CountryOwnTextArea
     * set the text area of the countries owned by player
     */
    public  CountryOwnTextArea()
    {
        super("",10,1);
        this.setFont(new Font("Consolas", Font.BOLD, 15));
        this.setEditable(false);

    }

    /**
     * upload the situation of the countries that player own at the text area 
     */
    @Override
    public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {

        RiskModel model = (RiskModel)updateEvent.getSource();
        Player player = model.getPlayerOnGoing();
        this.setText("Countries You Own:\n" + player.getAvailableCountries());

    }
}
