import javax.swing.*;
import java.awt.*;

public class PlayerNameTextPane extends JTextPane implements RiskModelListener {
    public PlayerNameTextPane() {
        super();
        this.setFont(new Font("Consolas", Font.BOLD, 15));
        this.setPreferredSize(new Dimension(200,30));
    }

    @Override
    public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {
        RiskModel model = (RiskModel) updateEvent.getSource();
        Player player = model.getPlayerOnGoing();
        this.setBackground(player.getColor());
        this.setText("Current Player: " + player.getName());

    }
}
