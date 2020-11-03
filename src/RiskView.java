import jdk.internal.util.xml.impl.Input;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class RiskView extends JFrame {

    private CustomPanel contentPane;
//    private JButton button;
    private JPanel textPanel;
    private JPanel adjacentTextPanel;
    private JPanel buttonPanel;
    private JList<Country> countriesOwn;
    private JList<Country> ADJCountries;
    private JTextField countriesOwnText;
    private JTextField adjacentCountriesText;
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
        this.countriesOwnText = new JTextField();
        this.countriesOwn = new JList<>();
        this.ADJCountries = new JList<>();
        this.adjacentCountriesText = new JTextField();
        this.attackButton = new JButton("ATTACK");
        this.passButton = new JButton("PASS");
        this.helpButton = new JButton("HELP");
        this.quitButton = new JButton("QUIT");

        //setup for adjacentTextPanel
        adjacentTextPanel.setPreferredSize(new Dimension(160,this.getHeight()));
        adjacentCountriesText.setFont(new Font("Arial", Font.BOLD, 15));
        adjacentCountriesText.setText("AdjacentCountries: ");
        adjacentCountriesText.setEditable(false);
        adjacentTextPanel.add(adjacentCountriesText, BorderLayout.NORTH);
        adjacentTextPanel.add(ADJCountries,BorderLayout.CENTER);


        //setup for textPanel
        countriesOwnText.setFont(new Font("Arial", Font.BOLD, 15));
        countriesOwnText.setEditable(false);
        textPanel.setLayout(new BorderLayout());
        textPanel.add(countriesOwnText, BorderLayout.PAGE_START);
        textPanel.add(countriesOwn);

        //setup for buttonPanel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(helpButton,FlowLayout.LEFT);
        buttonPanel.add(quitButton,FlowLayout.RIGHT);
        buttonPanel.add(attackButton);
        buttonPanel.add(passButton);

//        textPanel.add(countriesOwn);
//        button = new JButton("hello,testing");
//        contentPane.setLayout(null);
//        contentPane.add(button);
//        button.setBounds(250,100,200,200);
//        button.addActionListener(this);
//        button.setOpaque(false);
//        button.setContentAreaFilled(false);
//        button.setBorderPainted(false);
//        textPanel.add(button);
//        button.setSize(textPanel.getMaximumSize());
        this.add(contentPane, BorderLayout.CENTER);
        this.add(adjacentTextPanel, BorderLayout.WEST);
        this.add(textPanel, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1400,800);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    public static void main(String[] args){
       Runnable runnable = new Runnable(){
           @Override
           public void run() {
               new RiskView();
           }
        };
       EventQueue.invokeLater(runnable);
    }

    public JTextField getCountriesOwnText(){
        return this.countriesOwnText;
    }

    public JList<Country> getCountriesOwnList(){
        return this.countriesOwn;
    }

    public JList<Country> getAdjacentCountriesList(){return this.ADJCountries;}

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