import javax.swing.*;
import java.awt.*;

public class AdjacentCountriesTextArea extends JTextArea implements RiskModelListener {
     /**
     * Constructor of AdjacentCountriesTextArea
     * set the text area of the adjacent countries 
     */
    public AdjacentCountriesTextArea() {
        super("",5,1);

        this.setFont(new Font("Consolas", Font.BOLD, 15));
        this.setEditable(false);

    }

    /**
     * upload the situation of the adjacent countries at the text area 
     */
    @Override
    public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {

        RiskModel model = (RiskModel)updateEvent.getSource();
        String s = model.getSelectedCountryInfo();
        this.setText(s);
    }
}
