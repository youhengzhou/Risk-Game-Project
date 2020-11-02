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
    private Game game;

    public RiskView (){
        super("view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        contentPane = new CustomPanel();

        textPanel = new JPanel();
        countriesOwn = new JList<>();
        textPanel.add(countriesOwn);
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
        this.add(textPanel, BorderLayout.EAST);

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