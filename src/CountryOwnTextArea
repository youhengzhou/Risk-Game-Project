import javax.swing.*;
import java.awt.*;

public class CountryOwnTextArea extends JTextArea implements RiskModelListener {
    public  CountryOwnTextArea()
    {
        super("",10,1);
        this.setFont(new Font("Consolas", Font.BOLD, 15));
        this.setEditable(false);

    }

    @Override
    public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {

        RiskModel model = (RiskModel)updateEvent.getSource();
        Player player = model.getPlayerOnGoing();
        this.setText("Countries You Own:\n" + player.getAvailableCountries());

    }
}
