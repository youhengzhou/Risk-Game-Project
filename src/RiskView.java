import jdk.internal.util.xml.impl.Input;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RiskView extends JFrame {
    private JButton[][] button;
    private CustomPanel contentPane;
//    private JButton button;
    private JPanel textPanel;
    private JList<Country> countriesOwn;
    private Game game;
    private RiskCountroller control;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    public RiskView (){

        super("view");
        game = new Game(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        contentPane = new CustomPanel();
        control = new RiskCountroller(game,this);
        textPanel = new JPanel();
        countriesOwn = new JList<>();
        textPanel.add(countriesOwn);
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300,200));
        textArea.setText("Please click on the country\n___________________");
        textArea.setFont(new Font("Courier", Font.BOLD, 20));
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
        this.add(textPanel, BorderLayout.EAST);
        this.add(scrollPane,BorderLayout.WEST);
        button = new JButton[100][100];
        for(int i =0; i<100;i++)
        {
            for(int j =0; j<100;j++) {

                button[i][j] = new JButton("" + i+" "+j);
                //button[i][j].setOpaque(false);
               // button[i][j].setContentAreaFilled(false);
               // button[i][j].setBorderPainted(false);
                if(i==9 ||i==19 ||i==29 ||i==39 ||i==49 ||i==59 ||i==69 ||i==79 ||i==89 ||i==99)
                {
                    button[i][j].setBackground(Color.red);
                }
                if(j==9 ||j==19 ||j==29 ||j==39 ||j==49 ||j==59 ||j==69 ||j==79 ||j==89 ||j==99)
                {
                    button[i][j].setBackground(Color.BLUE);
                }

                contentPane.add(button[i][j]);
            }
        }
        this.connectCountryAndButton();


       // this.setContentPane(contentPane);
       // this.pack();
        this.setSize(1300,800);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
    private void connectCountryAndButton()
    {
        //Correspond countries to List of buttons
        //North America
        //1. Alaska
        ArrayList AlaskaButton = new ArrayList<JButton>();
        for(int i =9; i<=18;i++)
        {
            for(int j =1; j<=11; j++)
            {
                button[i][j].setActionCommand("alaska");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.black);
                AlaskaButton.add(button[i][j]);
            }
        }

        //North West Territory
        ArrayList northWestTerritoryButton = new ArrayList<JButton>();
        for(int i =9; i<=18;i++)
        {
            for(int j =12; j<=27; j++)
            {
                button[i][j].setActionCommand("northwestterritory");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.red);
                northWestTerritoryButton.add(button[i][j]);
            }
        }
        //Alberta
        ArrayList AlbertaButton = new ArrayList<JButton>();
        for(int i =19; i<=28;i++)
        {
            for(int j =9; j<=21; j++)
            {
                button[i][j].setActionCommand("alberta");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.BLUE);
                AlbertaButton.add(button[i][j]);
            }
        }
        //Ontario
        ArrayList OntarioButton = new ArrayList<JButton>();
        for(int i=19; i<=28;i++)
        {
            for(int j =19; j<=26; j++)
            {
                button[i][j].setActionCommand("ontario");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.GREEN);
                OntarioButton.add(button[i][j]);
            }
        }
        //Western United State
        ArrayList westernUnitedState = new ArrayList<JButton>();
        for(int i=29; i<=38;i++)
        {
            for(int j =2; j<=11; j++)
            {
                button[i][j].setActionCommand("westernunitedstates");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.MAGENTA);
                westernUnitedState.add(button[i][j]);
            }
        }
        //Eastern United States
        ArrayList easternUnitedStatesButton = new ArrayList<JButton>();
        for(int i=29; i<=39;i++)
        {
            for(int j =12; j<=23; j++)
            {
                button[i][j].setActionCommand("easternunitedstates");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.ORANGE);
                easternUnitedStatesButton.add(button[i][j]);
            }
        }
        //Quebec
        ArrayList QuebecButton = new ArrayList<JButton>();
        for(int i=19; i<=28;i++)
        {
            for(int j =27; j<=34; j++)
            {
                button[i][j].setActionCommand("quebec");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.pink);
                easternUnitedStatesButton.add(button[i][j]);
            }
        }
        //Central America
        ArrayList centralAmericaButton = new ArrayList<JButton>();
        for(int i=39; i<=45;i++)
        {
            for(int j =7; j<=15; j++)
            {
                button[i][j].setActionCommand("centralamerica");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.yellow);
                easternUnitedStatesButton.add(button[i][j]);
            }
        }
        for(int i=46; i<=49;i++)
        {
            for(int j =15; j<=22; j++)
            {
                button[i][j].setActionCommand("centralamerica");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.yellow);
                easternUnitedStatesButton.add(button[i][j]);
            }
        }
        for(int i=50; i<=53;i++)
        {
            for(int j =17; j<=21; j++)
            {
                button[i][j].setActionCommand("centralamerica");
                button[i][j].addActionListener(control);
                button[i][j].setBackground(Color.yellow);
                easternUnitedStatesButton.add(button[i][j]);
            }
        }
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
    public void countrySelectedNotify(Country c)
    {
        textArea.append("\n"+c.getCountryName()+" is selected\nOwner: "+c.getOwner().getName()+" \nTroops: "+c.getTroopsNum()+"\n_________________________");
        c.changeColor();
    }

    public void setUpList(DefaultListModel model){
        this.countriesOwn = new JList<>(model);
        this.contentPane.add(countriesOwn);
    }

    public JList<Country> getList(){
        return this.countriesOwn;
    }
}

class CustomPanel extends JPanel{
    private BufferedImage Inputimage;

    //private BufferedImage outputimage;

    public CustomPanel(){
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black,5));

        try{
            Inputimage = ImageIO.read(getClass().getResource("Risk.jpg"));
//          outputimage = new BufferedImage(1,1, BufferedImage.TYPE_INT_ARGB);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setLayout(new GridLayout(100,100));


    }

    @Override
    public Dimension getPreferredSize(){
        return(new Dimension(800, 600));
    }

   @Override
    protected void paintComponent(Graphics g)
    {
       super.paintComponent(g);
        //g.drawImage(image, 0, 0, this);
        g.drawImage(Inputimage,0,0,1000,700,null);
        //g.dispose();
    }
}