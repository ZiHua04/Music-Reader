import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ShowFrame extends JFrame{
    private static int controlFlag = config.SCROLL_FLAG_START;
    private static JPanel controlPanel = new JPanel(new GridLayout(1, 3));
    private static Vector<ImgLabel> imgVector;
    private static double speed;
    private static JPanel speedPanel = new JPanel(new GridLayout(6,1));
    private static JLabel textLabel;
    private static ScrollPanel scrollPanel;
    public static void setSpeed(double xspeed){
        speed = xspeed;
         scrollPanel.setSpeed(speed);
    }
    private static class SetSpeedAction implements ActionListener{
        private ShowFrame frame;
        private double xspeed;
        public SetSpeedAction(double xxspeed, ShowFrame showFrame){
            frame = showFrame;
            xspeed = xxspeed;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            speed = xspeed;
            scrollPanel.setSpeed(speed);
            textLabel.setText("当前速度"+ Double.toString(speed));
            frame.requestFocus();
        }
        
    }
    private static class ControlAction implements ActionListener{
        private int flag;
        private ShowFrame frame;
        public ControlAction(int x, ShowFrame showFrame){
            flag = x;
            frame = showFrame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(flag == config.SCROLL_FLAG_STOP){
                if(controlFlag == config.SCROLL_FLAG_START){
                    controlFlag = config.SCROLL_FLAG_STOP;
                }
                else if(controlFlag == config.SCROLL_FLAG_STOP){
                    controlFlag = config.SCROLL_FLAG_START;
                }
                else{
                    controlFlag = flag;
                }
            }
            else{
                controlFlag = flag;
                //scrollPanel.SetScrollFlag(controlFlag);
            }

            scrollPanel.SetScrollFlag(controlFlag);
            frame.requestFocus();
        }
        
    }
    private class MyKeyListener extends KeyAdapter{
            private ShowFrame frame;
            public MyKeyListener(ShowFrame showFrame){
                frame = showFrame;
            }
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_SPACE:{
                        ControlAction controlAction = new ControlAction(config.SCROLL_FLAG_STOP,frame);
                        controlAction.actionPerformed(null);
                        System.out.println("Stop");
                        break;
                    }
                    case KeyEvent.VK_UP:{
                        scrollPanel.AddPositon(30);
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        scrollPanel.AddPositon(-25);
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
    }
    
    public ShowFrame(Vector<ImgLabel> ximgVector, double xspeed){
        imgVector = ximgVector;
        speed = xspeed;
        this.setSize(config.ShowFrameWidth, config.ShowFrameHigh);

        scrollPanel = new ScrollPanel(imgVector, speed);
        this.add(scrollPanel);

        JButton stopButton = new JButton("播放/暂停");
        //stopButton.setContentAreaFilled(false);
        //stopButton.setBorderPainted(false); // 设置按钮边框不可见
        JButton nextButton = new JButton("快进");
        JButton backButton = new JButton("后退");
        stopButton.addActionListener(new ControlAction(config.SCROLL_FLAG_STOP,this));
        nextButton.addActionListener(new ControlAction(config.SCROLL_FLAG_NEXT,this));
        backButton.addActionListener(new ControlAction(config.SCROLL_FLAG_BACK,this));
        controlPanel.removeAll();
        controlPanel.add(backButton);
        controlPanel.add(stopButton);
        controlPanel.add(nextButton);

        textLabel = new JLabel("当前速度"+ Double.toString(speed));
        textLabel.setFont(new Font("楷体", Font.BOLD,  20));
        speedPanel.removeAll();
        speedPanel.add(textLabel);
        JButton[] speedButtons = new JButton[5];
        for(int i = 0; i < 5; i++){
            if(0 == i){
                JButton speedButton = new JButton(Double.toString(0.5));
                speedButtons[i] = speedButton;
                speedButtons[i].addActionListener(new SetSpeedAction(0.5,this));
                speedPanel.add(speedButtons[i]);
            }
            else{
                JButton speedButton = new JButton(Double.toString(i));
                speedButtons[i] = speedButton;
                speedButtons[i].addActionListener(new SetSpeedAction(i,this));
                speedPanel.add(speedButtons[i]);
            }
            
        }

        
       RaterPanel raterPanel = new RaterPanel(App.getNotes(), App.getBPM());
       this.add(raterPanel,BorderLayout.SOUTH);
       
        this.add(speedPanel,BorderLayout.EAST);
        this.add(controlPanel,BorderLayout.NORTH);

        Toolkit toolkit = this.getToolkit();
        Image icon = toolkit.getImage(config.HomeIconPath);
        this.setIconImage(icon);

        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new MyKeyListener(this));
    }
    
}
