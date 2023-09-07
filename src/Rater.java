import java.awt.Font;
import java.awt.Shape;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.annotation.XmlElement.DEFAULT;

import javafx.scene.media.Track;
public class Rater {
    private static Timer rateTimer;
    private int counter = 0;
    private RaterPanel raterPanel = new RaterPanel();
    private static JFrame raterFrame = new JFrame("Rater");

    Rater(int xNotes, int BPM){
        rateTimer = new Timer();
        rateTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                if(counter >= xNotes){
                    counter = 1;
                    raterPanel.clearPic();
                    raterPanel.addPic();
                    System.out.println("clear");
                }
                else{
                    counter++;
                    raterPanel.addPic();
                    System.out.println(counter);
                }
            }
            
        },0 , 60*1000/BPM);
        raterFrame.setSize(200, 200);
        raterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        raterFrame.add(raterPanel);
        raterFrame.setVisible(true);
    }
    public class RaterPanel extends JPanel{
        public void addPic(){
            JLabel tempLabel = new JLabel(" 拍");
            tempLabel.setFont(new Font("宋体", Font.BOLD, 40));
            this.add(tempLabel);
            this.revalidate();
            this.repaint();
        }
        public void clearPic(){
            this.removeAll();
            this.revalidate();
            this.repaint();
        }
    }
    // public static void main(String[] args) {
    //     new Rater(4, 120);
        
    // }
    
}
