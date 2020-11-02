import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RiskCountroller implements ActionListener {
    private Game game;
    private RiskView view;

    public RiskCountroller(Game g, RiskView v)
    {
        game = g;
        view = v;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String countryname = e.getActionCommand();
        game.countrySelected(countryname);
    }
}
