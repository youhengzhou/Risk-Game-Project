
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class RiskView extends JFrame {

    private CustomPanel imagePanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private CountryOwnTextArea countriesOwnText;
    private JTextArea adjacentCountriesText;
    private JTextArea consoleText;
    private JTextPane namePane;
    private JButton attackButton;
    private JButton passButton;
    private JButton helpButton;



    private JButton confirmButton;
    private int NumOfPlayer;

    private Buttons buttonList;

    /**
     * Constructor for RiskView
     */
    public RiskView (){
        super("view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
//        this.buttonList = new ArrayList<>();
        this.imagePanel = new CustomPanel();
        this.buttonList = new Buttons(imagePanel);
        this.textPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.countriesOwnText = new CountryOwnTextArea();
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

        adjacentCountriesText.setFont(new Font("Consolas", Font.BOLD, 15));
        adjacentCountriesText.setEditable(false);
        textPanel.setLayout(new BorderLayout());
        JScrollPane countriesOwnSP = new JScrollPane(countriesOwnText);
        JScrollPane adjacentCountriesSP = new JScrollPane(adjacentCountriesText);
        adjacentCountriesSP.setPreferredSize(new Dimension(frameSize_Width/6,350));
        namePane = new JTextPane();
        namePane.setFont(new Font("Consolas", Font.BOLD, 15));
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

        //initializing buttons
//        initializeButtons(buttonList);

        //setup test Button
        imagePanel.setLayout(null);

        this.add(consoleScrollPane, BorderLayout.WEST);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(textPanel, BorderLayout.EAST);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(frameSize_Width,frameSize_Height));
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.pack();
        this.showNumOfPlayerDialog();
    }

    /**
     *get the number of player;
     * @return
     */
    public int getNumOfPlayer() {
        return NumOfPlayer;
    }

    /**
     * get countries information
     *@return counrties own text
     */
    public RiskModelListener getCountriesOwnText(){
        return this.countriesOwnText;
    }

    /**
     * get the user input of how many players are playing.
     * @return
     */
    public void showNumOfPlayerDialog(){
        int num = 0;
        String str = "";
        boolean isNumeric;
        do {
            String numberStr = new JOptionPane().showInputDialog("How many players in the game? (2-6players)");
            isNumeric = numberStr.chars().allMatch(Character :: isDigit);
            if(!isNumeric) continue;
            num = Integer.parseInt(numberStr);
        }while(num<2 || num>6);
        NumOfPlayer = num;
    }

    /**
     * get adjacent countries information
     */
    public void  modifyAdjacentCountriesText(String s){
        adjacentCountriesText.setText(s);
    }

    /**
     * get countries name
     *@return namepane
     */
    public JTextPane getNamePane() {
        return namePane;
    }

    /**
     * get the list of countries buttons 
     * @return countries buttons' list
     */
    public ArrayList<JButton> getButtonList(){return this.buttonList.getList();}

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
     * show help information
     */
    public void showHelp(String s){
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(this,s);
    }

}

     /**
     * insert the risk map in the system
     */
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

    /**
     * set the map image in the window
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(Inputimage,0,0,900,750,null);
//        g.dispose();
    }
}

