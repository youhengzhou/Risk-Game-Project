import jdk.internal.util.xml.impl.Input;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class RiskView extends JFrame {

    private CustomPanel contentPane;
    private JPanel textPanel;
    private JPanel adjacentTextPanel;
    private JPanel buttonPanel;
    private JTextArea countriesOwnText;
    private JTextArea adjacentCountriesText;
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
        this.adjacentTextPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.countriesOwnText = new JTextArea("",10,1);
//        this.countriesOwn = new JList<>();
//        this.ADJCountries = new JList<>();
        this.adjacentCountriesText = new JTextArea("",5,1);
        this.attackButton = new JButton("ATTACK");
        this.passButton = new JButton("PASS");
        this.helpButton = new JButton("HELP");
        this.quitButton = new JButton("QUIT");

        //setup for textPanel
        countriesOwnText.setFont(new Font("Arial", Font.BOLD, 15));
        countriesOwnText.setEditable(false);
        adjacentCountriesText.setFont(new Font("Arial", Font.BOLD, 15));
        adjacentCountriesText.setEditable(false);
        textPanel.setLayout(new BorderLayout());
//        textPanel.add(countriesOwnText, BorderLayout.NORTH);
//        textPanel.add(adjacentCountriesText, BorderLayout.SOUTH);
        JScrollPane js = new JScrollPane(countriesOwnText);
        js.setPreferredSize(new Dimension(350,350));
        JScrollPane jp = new JScrollPane(adjacentCountriesText);
        jp.setPreferredSize(new Dimension(350,350));
        textPanel.add(js, BorderLayout.NORTH);
        textPanel.add(jp, BorderLayout.SOUTH);


        //setup for buttonPanel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(helpButton,FlowLayout.LEFT);
        buttonPanel.add(quitButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(passButton);

       // this.add(new JScrollPane(countriesOwnText,JScrollPane.VERTICAL_SCROLLBAR));
        this.add(contentPane, BorderLayout.CENTER);
//        this.add(adjacentTextPanel, BorderLayout.WEST);
        this.add(textPanel, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1400,800);
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
    //private BufferedImage outputimage;

    public CustomPanel(){
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black,1));

        try{
            Inputimage = ImageIO.read(getClass().getResource("/RiskMap.jpg"));
//            outputimage = new BufferedImage(1,1, BufferedImage.TYPE_INT_ARGB);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize(){
        return(new Dimension(1000, 700));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //g.drawImage(image, 0, 0, this);
        g.drawImage(Inputimage,0,0,1000,700,null);
        g.dispose();
    }
}