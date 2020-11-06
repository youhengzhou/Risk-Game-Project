
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
    private JButton attackButton;
    private JButton passButton;
    private JButton helpButton;
    private JButton confirmButton;

    private JButton testButton;
    private JPanel testPanel;

    public RiskView (){
        super("view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
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
        int screenX = (int) screenSize.getWidth();
        int screenY = (int) screenSize.getHeight();

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
        consoleScrollPane.setPreferredSize(new Dimension(screenX/8, screenY/8));

        //setup for textPanel
        countriesOwnText.setFont(new Font("Arial", Font.BOLD, 15));
        countriesOwnText.setEditable(false);
        adjacentCountriesText.setFont(new Font("Arial", Font.BOLD, 15));
        adjacentCountriesText.setEditable(false);
        textPanel.setLayout(new BorderLayout());
        JScrollPane countriesOwnSP = new JScrollPane(countriesOwnText);
        JScrollPane adjacentCountriesSP = new JScrollPane(adjacentCountriesText);
        textPanel.add(countriesOwnSP, BorderLayout.CENTER);
        textPanel.add(adjacentCountriesSP, BorderLayout.SOUTH);
        textPanel.setPreferredSize(new Dimension(screenX/8, screenY/8));

        //setup for buttonPanel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(helpButton,FlowLayout.LEFT);
        buttonPanel.add(attackButton);
        buttonPanel.add(passButton);
        buttonPanel.add(confirmButton);

//        buttonPanel.setPreferredSize(new Dimension(screenX/20, screenY/20));

        //setup test Button
        testButton = new JButton("test");
        imagePanel.setLayout(null);
        imagePanel.add(testButton);
        testButton.setBounds(23,50,50,50);
        testButton.setVisible(true);

        imagePanel.setPreferredSize(new Dimension(screenX/2, screenY/2));

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

        this.setPreferredSize(new Dimension(3*screenX/4,3 * screenY/4));
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.pack();
    }

    public JTextArea getCountriesOwnText(){
        return this.countriesOwnText;
    }

    public JTextArea getAdjacentCountriesText(){return this.adjacentCountriesText;}

    //used to update the color of the textField when Player switch
    public void upDateTextFieldColor(Color color){
        this.countriesOwnText.setBackground(color);
    }

    public void addTestListener(ActionListener actionListener){this.testButton.addActionListener(actionListener);}

    public void addPassButtonListener(ActionListener al){this.passButton.addActionListener(al);}

    public void addHelpButtonListener(ActionListener al){this.helpButton.addActionListener(al);}

    public void addConfirmButtonListener(ActionListener al){this.confirmButton.addActionListener(al);}

    public void showHelp(String s){
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(this,s);
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
        g.drawImage(Inputimage,0,0,(int)screenSize.getWidth()/2,(int)screenSize.getHeight()/2,null);
//        g.dispose();
    }
}

