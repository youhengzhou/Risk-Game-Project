import jdk.internal.util.xml.impl.Input;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RiskView extends JFrame {

    private CustomPanel contentPane;

    public RiskView (){
        super("view");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        contentPane = new CustomPanel();

        this.add(contentPane, BorderLayout.WEST);
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