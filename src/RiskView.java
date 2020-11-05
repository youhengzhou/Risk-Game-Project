
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class RiskView extends JFrame {

    private CustomPanel contentPane;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JTextArea countriesOwnText;
    private JTextArea adjacentCountriesText;
    private JTextArea consoleText;
    private JButton attackButton;
    private JButton passButton;
    private JButton helpButton;
    private JButton quitButton;

    public RiskView (){
        super("view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.contentPane = new CustomPanel();
        this.textPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.countriesOwnText = new JTextArea("",10,1);
        this.adjacentCountriesText = new JTextArea("",5,1);
        this.consoleText = new JTextArea();
        this.attackButton = new JButton("ATTACK");
        this.passButton = new JButton("PASS");
        this.helpButton = new JButton("HELP");
        this.quitButton = new JButton("QUIT");

        //setup for console
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                consoleText.append(String.valueOf((char) b));
            }
        }));
        consoleText.setBackground(Color.BLACK);
        consoleText.setCaretColor(Color.WHITE);
        JScrollPane consoleScrollPane = new JScrollPane(consoleText);
        consoleScrollPane.setPreferredSize(new Dimension(400,700));

        //setup for textPanel
        countriesOwnText.setFont(new Font("Arial", Font.BOLD, 15));
        countriesOwnText.setEditable(false);
        adjacentCountriesText.setFont(new Font("Arial", Font.BOLD, 15));
        adjacentCountriesText.setEditable(false);
        textPanel.setLayout(new BorderLayout());
        JScrollPane countriesOwnSP = new JScrollPane(countriesOwnText);
        countriesOwnSP.setPreferredSize(new Dimension(400,700));
        JScrollPane adjacentCountriesSP = new JScrollPane(adjacentCountriesText);
        adjacentCountriesSP.setPreferredSize(new Dimension(400,700));
        textPanel.add(countriesOwnSP, BorderLayout.NORTH);
        textPanel.add(adjacentCountriesSP, BorderLayout.SOUTH);

        //setup for buttonPanel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(helpButton,FlowLayout.LEFT);
        buttonPanel.add(quitButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(passButton);

        this.add(consoleScrollPane, BorderLayout.WEST);
        this.add(contentPane, BorderLayout.CENTER);
        this.add(textPanel, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setSize(2000,1500);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    public JTextArea getCountriesOwnText(){
        return this.countriesOwnText;
    }

    public JTextArea getAdjacentCountriesText(){return this.adjacentCountriesText;}

    //used to update the color of the textField when Player switch
    public void upDateTextFieldColor(Color color){
        this.countriesOwnText.setBackground(color);
    }

    public void addPassButtonListener(ActionListener ac){this.passButton.addActionListener(ac);}
}

class CustomPanel extends JPanel{
    private BufferedImage Inputimage;

    public CustomPanel(){
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black,1));

        try{
            Inputimage = ImageIO.read(getClass().getResource("/risk_map_withname.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(Inputimage,0,0,1200,1400,null);
//        g.dispose();
    }
}