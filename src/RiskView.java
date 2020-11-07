
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class RiskView extends JFrame {

    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension screenSize = tk.getScreenSize();

    private CustomPanel imagePanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JTextArea countriesOwnText;
    private JTextArea adjacentCountriesText;
    private JTextArea consoleText;
    private JTextPane namepane;
    private JButton attackButton;
    private JButton passButton;
    private JButton helpButton;
    private JButton confirmButton;
    private RiskController control;
   // private JButton testButton;
    private JPanel testPanel;

    public RiskView (){
        super("view");
        RiskModel model = new RiskModel();
        control = new RiskController();
        control.addModel(model);
        control.addView(this);
        model.addcontrol(control);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.imagePanel = new CustomPanel();
        this.textPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.countriesOwnText = new JTextArea("",10,1);
        this.adjacentCountriesText = new JTextArea("",5,1);
        this.consoleText = new JTextArea();
        consoleText.setFont(new Font("TimesRoman", Font.BOLD, 18));
        this.attackButton = new JButton("ATTACK");
        attackButton.setActionCommand("attack");
        attackButton.addActionListener(control);
        this.passButton = new JButton("PASS");
        passButton.setActionCommand("pass");
        passButton.addActionListener(control);
        this.helpButton = new JButton("HELP");
        helpButton.setActionCommand("help");
        helpButton.addActionListener(control);
        this.confirmButton = new JButton("CONFIRM");
        confirmButton.setActionCommand("confirm");
        confirmButton.addActionListener(control);

        int frameSize_Width = 1350;
        int frameSize_Height = 800;

        //setup for console
       // System.setOut(new PrintStream(new OutputStream() {
          //  @Override
          //  public void write(int b) throws IOException {
          //      consoleText.append(String.valueOf((char) b));
        //    }
       // }));

        JScrollPane consoleScrollPane = new JScrollPane(consoleText);
        consoleText.setText("console is not yet implemented");
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



         namepane = new JTextPane();
        namepane.setFont(new Font("Arial", Font.BOLD, 15));

        namepane.setPreferredSize(new Dimension(200,30));
        control.updatePlayerInfo(model.getPlayerOnGoing());

        textPanel.add(namepane,BorderLayout.NORTH);
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

//        buttonPanel.setPreferredSize(new Dimension(screenX/20, screenY/20));

        //setup test Button
        //testButton = new JButton("");
       imagePanel.setLayout(null);
        //imagePanel.add(testButton);
       // testButton.setBounds(23,35,15,8);

        //set up alaskaButton
        JButton alaskaButton = new JButton("");
        imagePanel.add(alaskaButton);
        alaskaButton.setBounds(23,190,15,8);
        alaskaButton.addActionListener(control);
        alaskaButton.setActionCommand("alaska");
        control.correlateCountryButton(alaskaButton);

        //set up easterAustraliaButton
        JButton easterAustraliaButton = new JButton("");
        imagePanel.add(easterAustraliaButton);
        easterAustraliaButton.setBounds(800,600,15,8);
        easterAustraliaButton.addActionListener(control);
        easterAustraliaButton.setActionCommand("easternaustralia");
        control.correlateCountryButton(easterAustraliaButton);

        //set up northWestTerritoryButton
        JButton northWestTerritoryButton = new JButton("");
        imagePanel.add(northWestTerritoryButton);
        northWestTerritoryButton.setBounds(145,167,15,8);
        northWestTerritoryButton.addActionListener(control);
        northWestTerritoryButton.setActionCommand("northwestterritory");
        control.correlateCountryButton(northWestTerritoryButton);

        //set up albertaButton
        JButton albertaButton = new JButton("");
        imagePanel.add(albertaButton);
        albertaButton.setBounds(100,210,15,8);
        albertaButton.addActionListener(control);
        albertaButton.setActionCommand("alberta");
        control.correlateCountryButton(albertaButton);

        //set up ontarioButton
        JButton ontarioButton = new JButton(""); //create new button
        imagePanel.add(ontarioButton); // add the button to imagePanel
        ontarioButton.setBounds(187,210,15,8);
        //IMPORTANT set the location of the button IMPORTANT
        //x: find the x-axis position for the button.
        //y: find the y-axis positon for hte button.
        //width,height. Don't Change
        ontarioButton.addActionListener(control);//add linstner to it
        ontarioButton.setActionCommand("ontario");//give its name all lower case
        control.correlateCountryButton(ontarioButton);//let control respond to it






       // testButton.setVisible(true);

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



    public void  modifyAdjacentCountriesText(String s){
       adjacentCountriesText.setText(s);
    }

    public JTextPane getNamepane() {
        return namepane;
    }

    //used to update the color of the textField when Player switch
    public void upDateTextFieldColor(Color color){
        this.countriesOwnText.setBackground(color);
    }

    public JTextArea getConsoleText() {
        return consoleText;
    }

    public void showHelp(String s){
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(this,s);
    }

    public JTextArea getCountriesOwnText() {
        return countriesOwnText;
    }
}
class CustomPanel extends JPanel{
    private BufferedImage Inputimage;
    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension screenSize = tk.getScreenSize();

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
}

