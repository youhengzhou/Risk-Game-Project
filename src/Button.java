import javax.swing.*;
import java.awt.*;
public class Button extends JPanel
{

    public void button() {
        pan p =new pan();
        this.add(p);

        JPanel south = new JPanel(new GridLayout(6,3,200,2));
        south.add(new JButton ("Attack"));
        south.add(new JButton ("Pass"));
        south.add(new JButton ("Move"));
        south.add(new JButton ("Back"));
        south.add(new JButton ("Help"));

        this.add(south, BorderLayout.SOUTH);
    }

    class pan extends JPanel{

        public void print(Graphics countryInfo) {
        countryInfo.setColor(new Color(0,100,0));
        countryInfo.drawString("owner", 0, 50);
        countryInfo.drawString("countries", 0, 70);
        countryInfo.drawString("troops number", 0, 90);
        }
    }

}


