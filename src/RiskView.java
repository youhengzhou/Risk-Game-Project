import jdk.internal.util.xml.impl.Input;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class RiskView extends JFrame {

    private CustomPanel contentPane;
//    private JButton button;
    private JPanel textPanel;
    private JList<Country> countriesOwn;
    private JList<Country> adjacentCountries;
    private JTextField countriesOwnText;
    private JTextField adjacentCountriesText;

    public RiskView (){
        super("view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.contentPane = new CustomPanel();
        this.textPanel = new JPanel();
        this.countriesOwnText = new JTextField();
        this.countriesOwn = new JList<>();
        this.adjacentCountries = new JList<>();
        this.adjacentCountriesText = new JTextField();

        adjacentCountriesText.setFont(new Font("Arial", Font.BOLD, 15));
        countriesOwnText.setFont(new Font("Arial", Font.BOLD, 15));
        countriesOwnText.setEditable(false);
        countriesOwnText.setSize(50,20);

        countriesOwn.setSize(textPanel.getWidth(),300);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(countriesOwnText,BorderLayout.NORTH);
        textPanel.add(countriesOwn);
        textPanel.add(adjacentCountriesText, FlowLayout.TRAILING);

        countriesOwn.setSize(textPanel.getSize());
        countriesOwn.setSelectionMode(ListSelectionModel.);
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
        this.add(contentPane, BorderLayout.WEST);
        this.add(textPanel);

       // this.setContentPane(contentPane);
       // this.pack();
        this.setSize(1300,800);
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

    public JList<Country> getList(){
        return this.countriesOwn;
    }

    //used to update the color of the textField when Player switch
    public void upDateTextFieldColor(Color color){
        this.countriesOwnText.setBackground(color);
    }
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