
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class RiskView extends JFrame {

    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension screenSize = tk.getScreenSize();

    private CustomPanel imagePanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JTextArea countriesOwnText;
    private JTextArea adjacentCountriesText;
    private JTextArea consoleText;
    private JTextPane namePane;
    private JButton attackButton;
    private JButton passButton;
    private JButton helpButton;
    private JButton confirmButton;

    private JButton testButton;
    private JPanel testPanel;

    private ArrayList<JButton> buttonList;

    public RiskView (){
        super("view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.buttonList = new ArrayList<>();
        this.imagePanel = new CustomPanel();
        this.textPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.countriesOwnText = new JTextArea("",10,1);
        this.adjacentCountriesText = new JTextArea("",5,1);
        this.consoleText = new JTextArea();
        this.attackButton = new JButton("ATTACK");
        this.passButton = new JButton("PASS");
        this.helpButton = new JButton("HELP");
        this.confirmButton = new JButton("CONFIRM");
        int frameSize_Width = 1350;
        int frameSize_Height = 800;

        //setup for console
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                consoleText.append(String.valueOf((char) b));
            }
        }));
        consoleText.setBackground(Color.BLACK);
        consoleText.setDisabledTextColor(Color.WHITE);
        JScrollPane consoleScrollPane = new JScrollPane(consoleText);
        consoleScrollPane.setPreferredSize(new Dimension(frameSize_Width/7, frameSize_Height/7));

        //setup for textPanel
        countriesOwnText.setFont(new Font("Arial", Font.BOLD, 15));
        countriesOwnText.setEditable(false);
        adjacentCountriesText.setFont(new Font("Arial", Font.BOLD, 15));
        adjacentCountriesText.setEditable(false);
        textPanel.setLayout(new BorderLayout());
        JScrollPane countriesOwnSP = new JScrollPane(countriesOwnText);
        JScrollPane adjacentCountriesSP = new JScrollPane(adjacentCountriesText);
        adjacentCountriesSP.setPreferredSize(new Dimension(frameSize_Width/6,350));
        namePane = new JTextPane();
        namePane.setFont(new Font("Arial", Font.BOLD, 15));
        namePane.setPreferredSize(new Dimension(200,30));

        textPanel.add(namePane,BorderLayout.NORTH);
        textPanel.add(countriesOwnSP, BorderLayout.CENTER);
        textPanel.add(adjacentCountriesSP, BorderLayout.SOUTH);
        adjacentCountriesText.setText("Click on country \nto see its information");
        textPanel.setPreferredSize(new Dimension(frameSize_Width/6, frameSize_Height/7));

        //setup for buttonPanel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(helpButton,FlowLayout.LEFT);
        buttonPanel.add(attackButton);
        buttonPanel.add(passButton);
        buttonPanel.add(confirmButton);



        //setup test Button
        imagePanel.setLayout(null);




        //North America buttons
        JButton alaskaButton = new JButton("");
        imagePanel.add(alaskaButton);
        alaskaButton.setBounds(23,190,15,8);
        alaskaButton.setActionCommand("alaska");
        buttonList.add(alaskaButton);

        JButton albertaButton = new JButton("");
        imagePanel.add(albertaButton);
        albertaButton.setBounds(100,210,15,8);
        albertaButton.setActionCommand("alberta");
        buttonList.add(albertaButton);

        JButton centralamericaButton = new JButton("");
        imagePanel.add(centralamericaButton);
        centralamericaButton.setBounds(170,315,15,8);
        centralamericaButton.setActionCommand("centralamerica");
        buttonList.add(centralamericaButton);

        JButton easternunitedstatesButton = new JButton("");
        imagePanel.add(easternunitedstatesButton);
        easternunitedstatesButton.setBounds(187,290,15,8);
        easternunitedstatesButton.setActionCommand("easternunitedstates");
        buttonList.add(easternunitedstatesButton);

        JButton greenlandButton = new JButton("");
        imagePanel.add(greenlandButton);
        greenlandButton.setBounds(305,145,15,8);
        greenlandButton.setActionCommand("greenland");
        buttonList.add(greenlandButton);

        JButton northwestterritoryButton = new JButton("");
        imagePanel.add(northwestterritoryButton);
        northwestterritoryButton.setBounds(145,167,15,8);
        northwestterritoryButton.setActionCommand("northwestterritory");
        buttonList.add(northwestterritoryButton);

        JButton ontarioButton = new JButton("");
        imagePanel.add(ontarioButton);
        ontarioButton.setBounds(187,210,15,8);
        ontarioButton.setActionCommand("ontario");
        buttonList.add(ontarioButton);

        JButton quebecButton = new JButton("");
        imagePanel.add(quebecButton);
        quebecButton.setBounds(260,240,15,8);
        quebecButton.setActionCommand("quebec");
        buttonList.add(quebecButton);

        JButton westernunitedstatesButton = new JButton("");
        imagePanel.add(westernunitedstatesButton);
        westernunitedstatesButton.setBounds(100,275,15,8);
        westernunitedstatesButton.setActionCommand("westernunitedstates");
        buttonList.add(westernunitedstatesButton);

        //South America Buttons
        JButton argentinaButton = new JButton("");
        imagePanel.add(argentinaButton);
        argentinaButton.setBounds(193,487,15,8);
        argentinaButton.setActionCommand("argentina");
        buttonList.add(argentinaButton);

        JButton brazilButton = new JButton("");
        imagePanel.add(brazilButton);
        brazilButton.setBounds(263,481,15,8);
        brazilButton.setActionCommand("brazil");
        buttonList.add(brazilButton);

        JButton peruButton = new JButton("");
        imagePanel.add(peruButton);
        peruButton.setBounds(208,440,15,8);
        peruButton.setActionCommand("peru");
        buttonList.add(peruButton);

        JButton venezuelaButton = new JButton("");
        imagePanel.add(venezuelaButton);
        venezuelaButton.setBounds(230,399,15,8);
        venezuelaButton.setActionCommand("venezuela");
        buttonList.add(venezuelaButton);

        //Europe Buttons
        JButton greatbritainButton = new JButton("");
        imagePanel.add(greatbritainButton);
        greatbritainButton.setBounds(370,230,15,8);
        greatbritainButton.setActionCommand("greatbritain");
        buttonList.add(greatbritainButton);

        JButton icelandButton = new JButton("");
        imagePanel.add(icelandButton);
        icelandButton.setBounds(365,185,15,8);
        icelandButton.setActionCommand("iceland");
        buttonList.add(icelandButton);

        JButton northerneuropeButton = new JButton("");
        imagePanel.add(northerneuropeButton);
        northerneuropeButton.setBounds(480,207,15,8);
        northerneuropeButton.setActionCommand("northerneurope");
        buttonList.add(northerneuropeButton);

        JButton scandinaviaButton = new JButton("");
        imagePanel.add(scandinaviaButton);
        scandinaviaButton.setBounds(440,160,15,8);
        scandinaviaButton.setActionCommand("scandinavia");
        buttonList.add(scandinaviaButton);

        JButton southerneuropeButton = new JButton("");
        imagePanel.add(southerneuropeButton);
        southerneuropeButton.setBounds(510,280,15,8);
        southerneuropeButton.setActionCommand("southerneurope");
        buttonList.add(southerneuropeButton);

        JButton ukraineButton = new JButton("");
        imagePanel.add(ukraineButton);
        ukraineButton.setBounds(530,207,15,8);
        ukraineButton.setActionCommand("ukraine");
        buttonList.add(ukraineButton);

        JButton westerneuropeButton = new JButton("");
        imagePanel.add(westerneuropeButton);
        westerneuropeButton.setBounds(455,250,15,8);
        westerneuropeButton.setActionCommand("westerneurope");
        buttonList.add(westerneuropeButton);

        //Africa Buttons
        JButton congoButton = new JButton("");
        imagePanel.add(congoButton);
        congoButton.setBounds(382,418,15,8);
        congoButton.setActionCommand("congo");
        buttonList.add(congoButton);

        JButton eastafricaButton = new JButton("");
        imagePanel.add(eastafricaButton);
        eastafricaButton.setBounds(460,419,15,8);
        eastafricaButton.setActionCommand("eastafrica");
        buttonList.add(eastafricaButton);

        JButton egyptButton = new JButton("");
        imagePanel.add(egyptButton);
        egyptButton.setBounds(450,355,15,8);
        egyptButton.setActionCommand("egypt");
        buttonList.add(egyptButton);

        JButton madagascarButton = new JButton("");
        imagePanel.add(madagascarButton);
        madagascarButton.setBounds(499,521,15,8);
        madagascarButton.setActionCommand("madagascar");
        buttonList.add(madagascarButton);

        JButton northafricaButton = new JButton("");
        imagePanel.add(northafricaButton);
        northafricaButton.setBounds(385,338,15,8);
        northafricaButton.setActionCommand("northafrica");
        buttonList.add(northafricaButton);

        JButton southafricaButton = new JButton("");
        imagePanel.add(southafricaButton);
        southafricaButton.setBounds(380,518,15,8);
        southafricaButton.setActionCommand("southafrica");
        buttonList.add(southafricaButton);

        //Asia Buttons
        JButton afghanistanButton = new JButton("");
        imagePanel.add(afghanistanButton);
        afghanistanButton.setBounds(599,265,15,8);
        afghanistanButton.setActionCommand("afghanistan");
        buttonList.add(afghanistanButton);

        JButton chinaButton = new JButton("");
        imagePanel.add(chinaButton);
        chinaButton.setBounds(630,235,15,8);
        chinaButton.setActionCommand("china");
        buttonList.add(chinaButton);

        JButton indiaButton = new JButton("");
        imagePanel.add(indiaButton);
        indiaButton.setBounds(633,326,15,8);
        indiaButton.setActionCommand("india");
        buttonList.add(indiaButton);

        JButton irkutskButton = new JButton("");
        imagePanel.add(irkutskButton);
        irkutskButton.setBounds(736,202,15,8);
        irkutskButton.setActionCommand("irkutsk");
        buttonList.add(irkutskButton);

        JButton japanButton = new JButton("");
        imagePanel.add(japanButton);
        japanButton.setBounds(830,284,15,8);
        japanButton.setActionCommand("japan");
        buttonList.add(japanButton);

        JButton kamchatkaButton = new JButton("");
        imagePanel.add(kamchatkaButton);
        kamchatkaButton.setBounds(805,185,15,8);
        kamchatkaButton.setActionCommand("kamchatka");
        buttonList.add(kamchatkaButton);

        JButton middleeastButton = new JButton("");
        imagePanel.add(middleeastButton);
        middleeastButton.setBounds(583,310,15,8);
        middleeastButton.setActionCommand("middleeast");
        buttonList.add(middleeastButton);

        JButton mongoliaButton = new JButton("");
        imagePanel.add(mongoliaButton);
        mongoliaButton.setBounds(758,237,15,8);
        mongoliaButton.setActionCommand("mongolia");
        buttonList.add(mongoliaButton);

        JButton siamButton = new JButton("");
        imagePanel.add(siamButton);
        siamButton.setBounds(688,316,15,8);
        siamButton.setActionCommand("siam");
        buttonList.add(siamButton);

        JButton siberiaButton = new JButton("");
        imagePanel.add(siberiaButton);
        siberiaButton.setBounds(640,175,15,8);
        siberiaButton.setActionCommand("siberia");
        buttonList.add(siberiaButton);

        JButton uralButton = new JButton("");
        imagePanel.add(uralButton);
        uralButton.setBounds(608,129,15,8);
        uralButton.setActionCommand("ural");
        buttonList.add(uralButton);

        JButton yakutskButton = new JButton("");
        imagePanel.add(yakutskButton);
        yakutskButton.setBounds(717,173,15,8);
        yakutskButton.setActionCommand("yakutsk");
        buttonList.add(yakutskButton);

        //Australia Buttons

        //set up easterAustraliaButton
        JButton easterAustraliaButton = new JButton("");
        imagePanel.add(easterAustraliaButton);
        easterAustraliaButton.setBounds(800,600,15,8);
        easterAustraliaButton.setBackground(Color.GREEN);


        imagePanel.setPreferredSize(new Dimension(900, 750));

        //seting background
//        testPanel = new JPanel();
//        testPanel.setLayout(null);
//        ImageIcon img=new ImageIcon("Risk-Game-Project\\src\\risk_map_withname.png");
//        JLabel backGround=new JLabel("",img,JLabel.CENTER);
//        backGround.setBounds(0,0,1000,1000);
//        testPanel.add(backGround);
//        this.add(testPanel,BorderLayout.CENTER);
//        setLayout(null);
//        setSize(1000,1000);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        ImageIcon img = new ImageIcon("C:\\Users\\finnh\\Desktop\\retake SYSC 3110\\Risk-Game-Project\\src\\RiskMap.jpg");
//        JLabel back=new JLabel("",img,JLabel.CENTER);
//        back.setBounds(0,0,1000,1000);;

        this.add(consoleScrollPane, BorderLayout.WEST);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(textPanel, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(frameSize_Width,frameSize_Height));
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.pack();
    }

    public JTextArea getCountriesOwnText(){
        return this.countriesOwnText;
    }

    public void  modifyAdjacentCountriesText(String s){
        adjacentCountriesText.setText(s);
    }

    public JTextPane getNamePane() {
        return namePane;
    }

    public ArrayList<JButton> getButtonList(){return this.buttonList;}

    //public void addTestListener(ActionListener actionListener){this.testButton.addActionListener(actionListener);}

    public void addPassButtonListener(ActionListener al){this.passButton.addActionListener(al);}

    public void addHelpButtonListener(ActionListener al){this.helpButton.addActionListener(al);}

    public void addConfirmButtonListener(ActionListener al){this.confirmButton.addActionListener(al);}

    public void addAttackButtonListener(ActionListener al){this.attackButton.addActionListener(al);}

    public void showHelp(String s){
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(this,s);
    }
}
class CustomPanel extends JPanel{
    private BufferedImage Inputimage;

    public CustomPanel(){
        setLayout(null);
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
        g.drawImage(Inputimage,0,0,900,750,null);
//        g.dispose();
    }

    public ArrayList<JButton> getButtonList() {
        ArrayList buttonList = new ArrayList<>();
        return buttonList;
    }
}

