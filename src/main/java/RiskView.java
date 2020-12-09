
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.*;
import java.util.ArrayList;

public class RiskView extends JFrame implements RiskModelListener
{
    private int mode;
    private CustomPanel imagePanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private CountryOwnTextArea countriesOwnText;
    private JTextArea adjacentCountriesText;
    private JTextArea consoleText;
    private PlayerNameTextPane namePane;
    private JButton attackButton;
    private JButton passButton;
    private JButton helpButton;
    private JButton confirmButton;
    private JButton fortifyButton;
    private JButton saveButton;
    private JButton loadButton;
    private int NumOfTotalPlayer;
    private int NumOfAiPlayer;
    private Buttons buttonList;
    private ArrayList<RiskModelListener> listners;
    /**
     * Constructor for RiskView
     */
    public RiskView (int mode) throws FileNotFoundException {
        super("view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.mode = mode;
        this.imagePanel = new CustomPanel(mode);
        iniButtons();
        this.textPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.countriesOwnText = new CountryOwnTextArea();
        this.adjacentCountriesText = new AdjacentCountriesTextArea();
        this.consoleText = new JTextArea();
        this.attackButton = new JButton("ATTACK");
        this.passButton = new JButton("PASS");
        this.helpButton = new JButton("HELP");
        this.confirmButton = new JButton("CONFIRM");
        this.fortifyButton = new JButton("FORTIFY");
        this.saveButton = new JButton("SAVE");
        this.loadButton = new JButton("LOAD");
        this.listners = new ArrayList<>();
        int frameSize_Width = 1350;
        int frameSize_Height = 800;
        this.mode = mode;

        //setup for console
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                consoleText.append(String.valueOf((char) b));
            }
        }));
        consoleText.setBackground(Color.WHITE);
        consoleText.setDisabledTextColor(Color.GREEN);
        JScrollPane consoleScrollPane = new JScrollPane(consoleText);

        consoleScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });
        consoleScrollPane.setPreferredSize(new Dimension(frameSize_Width/7, frameSize_Height/7));

        //setup for textPanel

        textPanel.setLayout(new BorderLayout());
        JScrollPane countriesOwnSP = new JScrollPane(countriesOwnText);
        JScrollPane adjacentCountriesSP = new JScrollPane(adjacentCountriesText);
        adjacentCountriesSP.setPreferredSize(new Dimension(frameSize_Width/6,350));
        namePane = new PlayerNameTextPane();

        textPanel.add(namePane,BorderLayout.NORTH);
        textPanel.add(countriesOwnSP, BorderLayout.CENTER);
        textPanel.add(adjacentCountriesSP, BorderLayout.SOUTH);
        textPanel.setPreferredSize(new Dimension(frameSize_Width/6, frameSize_Height/7));

        //setup for buttonPanel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(helpButton,FlowLayout.LEFT);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(fortifyButton);
        buttonPanel.add(passButton);
        buttonPanel.add(confirmButton);

        imagePanel.setLayout(null);

        listners.add(countriesOwnText);
        listners.add(namePane);
        listners.add(buttonList);
        listners.add((RiskModelListener)adjacentCountriesText);

        this.add(consoleScrollPane, BorderLayout.WEST);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(textPanel, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(frameSize_Width,frameSize_Height));
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.pack();
        this.showNumOfTotalPlayerDialog();
        this.showNumOfAiPlayerDialog();
    }

    /**
     *get the number of total player;
     * @return
     */
    public int getNumOfPlayer() {
        return NumOfTotalPlayer;
    }

    /**
     *get the number of ai player;
     * @return
     */
    public int getNumOfAiPlayerPlayer() {
        return NumOfAiPlayer;
    }

    /**
     * get countries information
     *@return counrties own text
     */
    public RiskModelListener getCountriesOwnText(){
        return this.countriesOwnText;
    }

    public CustomPanel getImagePanel()
    {
        return imagePanel;
    }

    /**
     * get the user input of how many players are playing.
     * @return
     */
    public void showNumOfTotalPlayerDialog(){
    int num = 0;
    String str = "";
    boolean isNumeric;
    do {
        String numberStr = new JOptionPane().showInputDialog("How many players in the game? (2-6players)");
        isNumeric = numberStr.chars().allMatch(Character :: isDigit);
        if(!isNumeric) continue;
        num = Integer.parseInt(numberStr);
    }while(num<2 || num>6);
    NumOfTotalPlayer = num;
}
    public void showNumOfAiPlayerDialog(){
        int num = 0;
        String str = "";
        boolean isNumeric;
        do {
            String numberStr = new JOptionPane().showInputDialog("How many AI players in the game? (0 -"+ (NumOfTotalPlayer-1)+" )");
            isNumeric = numberStr.chars().allMatch(Character :: isDigit);
            if(!isNumeric) continue;
            num = Integer.parseInt(numberStr);
        }while(num<0 || num>NumOfTotalPlayer-1);
        NumOfAiPlayer = num;
    }

    public void clickPassButton()
    {
        passButton.doClick();
    }


    public RiskModelListener getadjacentCountriesText()
    {
        return (RiskModelListener)adjacentCountriesText;
    }

    public void iniButtons()
    {
        if(mode == RiskController.ORIGINAL)
        {
            this.buttonList = Buttons.build(ButtonInfoStore.loadFromXML("worldMapButtons.xml"),imagePanel);
        }
        else
        {
            this.buttonList = Buttons.build(ButtonInfoStore.loadFromXML("starMapButtons.xml"),imagePanel);
        }
    }

    /**
     * get countries name
     *@return namepane
     */
    public RiskModelListener getNamePane() {
        return namePane;
    }

    /**
     * get the list of countries buttons 
     * @return countries buttons' list
     */
    public ArrayList<JButton> getButtonList(){return this.buttonList.getList();}

    public RiskModelListener getButtonListAsRiskModelListener()
    {
        return buttonList;
    }

    /**
     * add pass button listener
     */
    public void addPassButtonListener(ActionListener al){this.passButton.addActionListener(al);}

    /**
     * add help button listener
     */
    public void addHelpButtonListener(ActionListener al){this.helpButton.addActionListener(al);}

    /**
     * add confirm button listener
     */
    public void addConfirmButtonListener(ActionListener al){this.confirmButton.addActionListener(al);}

    /**
     * add attack button listener
     */
    public void addAttackButtonListener(ActionListener al){this.attackButton.addActionListener(al);}

    /**
     * add fortify button listener
     */
    public void addFortifyButtonListener(ActionListener al){this.fortifyButton.addActionListener(al);}

    /**
     * add saveButton listener
     * @param al
     */
    public void addSaveButtonListener(ActionListener al){this.saveButton.addActionListener(al);}

    /**
     * add loadButton listener
     * @param al
     */
    public void addLoadButtonListener(ActionListener al){this.loadButton.addActionListener(al);}

    /**
     * show help information
     */
    public void showHelp(String s){
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(this,s);
    }

    @Override
    public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {

        for(RiskModelListener rl:listners)
        {
            rl.handleRiskModelUpdate(updateEvent);
        }
        RiskModel model = (RiskModel)updateEvent.getSource();
//        for(Player p: model.getPlayers()){
//            for(Country c: p.getCountriesOwn()){
//                c.setColor(p.getColor());
//            }
//        }



        for(JButton b:buttonList.getList()) {
            Country c = model.getCountry(b.getActionCommand());
            b.setForeground(c.getOwner().getColor());
            b.setText(Integer.toString(model.getCountry(b.getActionCommand()).getCountryTroopsNumber()));
        }
    }
}

     /**
     * insert the risk map in the system
     */
class CustomPanel extends JPanel implements RiskModelListener{
    private Image im;
    int mode;
    private String mapImagePath = "D:/work/3110/Risk-Game-Project/src/main/java/NEW STAR MAP.png";
    public CustomPanel(int mode) throws FileNotFoundException {
        setLayout(null);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black,1));
        mode = mode;
        if(mode==RiskController.ORIGINAL)
        {
            mapImagePath = "NEW MAP.png";
        }
        else
        {
            mapImagePath = "NEW STAR MAP.png";
        }
        try{
            im = ImageIO.read(new File(mapImagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
         /**
          * set the map image in the window
          */
         @Override
         protected void paintComponent(Graphics g)
         {
             super.paintComponent(g);
             g.drawImage(im,0,0,900,750,null);
         }

         public static void main(String[] args) throws FileNotFoundException {

         }

         @Override
         public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {
             RiskModel model =(RiskModel) updateEvent.getSource();
             if(model.getFileNotFound())new JOptionPane().showMessageDialog(this, "No such a saving point is found for this map");
         }
     }

