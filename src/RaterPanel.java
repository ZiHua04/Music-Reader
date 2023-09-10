import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

import java.util.Timer;
import java.util.TimerTask;
public class RaterPanel extends JPanel{
    private Timer rateTimer;
    private int counter = 1;
    public RaterPanel(int xNotes, int BPM){
        rateTimer = new Timer();
        rateTimer.schedule(new BitTask(this,xNotes),0 , 60*1000/BPM);
        
    }
    public void stopTimer(){
        rateTimer.cancel();
    }
    public void addPic(){
            
            try {
                MusicPlayer.playmusic("music\\newDrum.mp3");
            } catch (Exception e) {
                // TODO: handle exception
            }
            JLabel tempLabel = new JLabel(" 拍");
            tempLabel.setFont(new Font("宋体",Font.BOLD,40));
            this.add(tempLabel);
            this.revalidate();
            this.repaint();
    }
    public void clearPic(){
        this.removeAll();
        
    }
    private class BitTask extends TimerTask{
        private RaterPanel tempPanel;
        private int xNotes;
        BitTask(RaterPanel raterPanel, int Notes){
            tempPanel = raterPanel;
            xNotes = Notes;
        }
        @Override
            public void run() {
                if(counter >= xNotes){
                    counter = 1;
                    tempPanel.clearPic();
                    tempPanel.addPic();
                    System.out.println("clear");
                }
                else{
                    counter++;
                    tempPanel.addPic();
                    System.out.println(counter);
                }
            }

    }
}
