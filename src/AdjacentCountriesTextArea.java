import javax.swing.*;
import java.awt.*;

public class AdjacentCountriesTextArea extends JTextArea implements RiskModelListener {

    public AdjacentCountriesTextArea() {
        super("",5,1);

        this.setFont(new Font("Consolas", Font.BOLD, 15));
        this.setEditable(false);

    }

    @Override
    public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {

        RiskModel model = (RiskModel)updateEvent.getSource();
        String s = model.getSelectedCountryInfo();
        this.setText(s);
    }
}