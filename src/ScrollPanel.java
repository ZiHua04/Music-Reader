import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Timer;
import javax.swing.JPanel;

public class ScrollPanel extends JPanel implements ActionListener{
    private int scrollFlag = config.SCROLL_FLAG_START;//
    private static double speed;
    private double positon = 0;
    private static Vector<ImgLabel> imgVector;
    private int imageHeight,imageWidth;

    public ScrollPanel(Vector<ImgLabel> ximgVector, double xspeed){
        imgVector = ximgVector;
        speed = xspeed;
        Timer timer = new Timer(1000/100, this);
        timer.start();

    }
    public void SetScrollFlag(int x){
        scrollFlag = x;
    }
    public void AddPositon(int x){
        positon += x;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(scrollFlag != config.SCROLL_FLAG_STOP)
            repaint();
        switch (scrollFlag){
            case config.SCROLL_FLAG_START:{
                positon -= speed;
                break;
            }
            case config.SCROLL_FLAG_STOP:{
                break;
            }
            case config.SCROLL_FLAG_NEXT:{
                positon -= 10;
                break;
            }
            case config.SCROLL_FLAG_BACK:{
                positon += 10;
                break;
            }

        }
        
        if(positon <= -imageHeight*imgVector.size()){
            positon = 0;
        }
        
    }
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();

        

        for(int i = 0; i  < imgVector.size(); i++){
            Image image = Toolkit.getDefaultToolkit().getImage(imgVector.get(i).getPath());
            imageHeight = image.getHeight(getFocusCycleRootAncestor());
            imageWidth = image.getWidth(getFocusCycleRootAncestor());
            int tempWidth = config.ShowFrameWidth-100;
            imageHeight = tempWidth*imageHeight/imageWidth;
            imageWidth = tempWidth;
            g2d.drawImage(image,0,(int) (positon+imageHeight*i),imageWidth,imageHeight,this);
            //System.out.println(imgVector.get(i).getPath());
        }

        g2d.dispose();

    }
    public void setSpeed(double xspeed){
        speed = xspeed;
    }


}
