import javax.imageio.ImageIO;
import javax.swing.*;

///import com.oracle.jrockit.jfr.FlightRecorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
class App{
    private static Vector<ImgLabel> imgVector = new Vector<ImgLabel>();
    private static int Beats;
    private static int Notes;
    private static int BPM;
    private static RatePanel ratePanel = new RatePanel(new GridLayout(7, 1),new App());
    private static JLabel textLabel;
    private static JFrame myFrame;
    private static JPanel imgPanel;
    protected static ShowFrame showFrame;
    private static JPanel speedPanel = new JPanel(new GridLayout(6,1));
    private static double speed = 0.5;
    public static void saveFile(File file, String folderPath) throws IOException {
        File destinationFolder = new File(folderPath);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        File destinationFile = new File(destinationFolder.getPath() + File.separator + file.getName());
        Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    public void setRate(int xBeats, int xNotes, int xBPM){
        System.out.println("Rate has been set");
        Beats = xBeats;
        Notes = xNotes;
        BPM = xBPM;
    }
    public static int getBPM(){
        return BPM;
    }
    public static int getNotes(){
        return Notes;
    }
    public static int getBeats(){
        return Beats;
    }
    private static class addAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser("C:\\Users\\zzzh\\Pictures\\");
            int result = chooser.showOpenDialog(myFrame);

            if(result == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();

                try {
                    saveFile(file, config.loadedPicturePath);
                    ImgLabel tempImgLabel = new ImgLabel(config.loadedPicturePath+"\\"+file.getName());
                    imgVector.add(tempImgLabel);
                    imgPanel.removeAll();
                    for(int i = 0; i < imgVector.size(); i++){
                        imgPanel.add(imgVector.get(i));
                    }
                    imgPanel.revalidate();
                    imgPanel.repaint();
                } catch (Exception exception) {
                    // TODO: handle exception
                    exception.printStackTrace();
                }
            }
            
        }
        
    }
    private static boolean loadPicture(){
        File loadedFLie = new File(config.loadedPicturePath);
        File[] loadedPicture = loadedFLie.listFiles();
        if(loadedPicture.length > 0){
            for(int i = 0; i < loadedPicture.length; i++){
                ImgLabel tempImgLabel = new ImgLabel(loadedPicture[i].getAbsolutePath());
                imgVector.add(tempImgLabel);
            }
            return true;
        }
        else{
            return false;
        }
    }
    
    private static class deleteAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String path = imgVector.get(imgVector.size()-1).getPath();
            if(deleteFile(path)){
                System.out.println("已删除");
            }else{
                System.out.println("删除失败");
            }
            imgVector.remove(imgVector.size()-1);
            imgPanel.removeAll();
            for(int i = 0; i < imgVector.size(); i++){
                imgPanel.add(imgVector.get(i));
            }
            imgPanel.revalidate();
            imgPanel.repaint();
        }
        
    }
    private static class deleteAllAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            File loadedPicture = new File(config.loadedPicturePath);
            File[] Pictures = loadedPicture.listFiles();
            for(int i = 0; i < Pictures.length; i++){
                Pictures[i].delete();
            }
            imgVector.clear();
            imgPanel.removeAll();
            imgPanel.revalidate();
            imgPanel.repaint();
        }
        
    }
    private static class startAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(imgVector.size() <= 0){
                System.out.println("未输入图片");
                int result = JOptionPane.showConfirmDialog(null, "是否打开选择框", "未选择图片", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    addAction action = new addAction();
                    action.actionPerformed(e);
                }
            }
            else if(ratePanel.isEmpty()){
                JOptionPane confirmOptionPane = new JOptionPane();
                confirmOptionPane.showConfirmDialog(null, "请输入节奏", "警告", JOptionPane.DEFAULT_OPTION);
            }
            else{
                showFrame = new ShowFrame(imgVector, speed);
                //new Rater(Notes, BPM);
                System.out.println("开始播放");
            }
        }
        
    }
    
    private static class SetSpeedAction implements ActionListener{
        private double xspeed;
        public SetSpeedAction(double xxspeed){
            xspeed = xxspeed;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            speed = xspeed;
            try {
                showFrame.setSpeed(speed);
            } catch (Exception expException) {
                
            }
            textLabel.setText("当前速度"+ Double.toString(speed));
        }
        
    }
    
    public static boolean deleteFile(String path){
        File file = new File(path);
        if(!file.exists()){
            return false;
        }else{
            file.delete();
            return true;
        }
    }
    public static void main(String[] args) {


        loadPicture();
        
        myFrame = new JFrame(config.homeName, null);
        myFrame.setLayout(new BorderLayout());
        myFrame.setSize(config.homeWidth,config.homeHigh);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textLabel = new JLabel("当前速度"+ Double.toString(speed));
        speedPanel.add(textLabel);
        JButton[] speedButtons = new JButton[5];
        for(int i = 0; i < 5; i++){
            if(i == 0){
                JButton speedButton = new JButton(Double.toString(0.5));
                speedButtons[i] = speedButton;
                speedButtons[i].addActionListener(new SetSpeedAction(0.5));
                speedPanel.add(speedButtons[i]);
            }else{
                JButton speedButton = new JButton(Double.toString(i));
                speedButtons[i] = speedButton;
                speedButtons[i].addActionListener(new SetSpeedAction(i));
                speedPanel.add(speedButtons[i]);
            }
            
        }
        

        imgPanel = new JPanel(new FlowLayout());

        
        for(int i = 0; i < imgVector.size();i++){
            imgPanel.add(imgVector.get(i));
        }
        JButton addButton = new JButton("添加");
        JButton deleteAllButton = new JButton("全部删除");
        JButton deleteButton = new JButton("删除");
        JButton startButton = new JButton("开始播放");
        startButton.addActionListener(new startAction());
        addButton.addActionListener(new addAction());
        deleteAllButton.addActionListener(new deleteAllAction());
        deleteButton.addActionListener(new deleteAction());
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(deleteAllButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(startButton);
        myFrame.add(speedPanel,BorderLayout.EAST);
        myFrame.add(imgPanel,BorderLayout.NORTH);
        myFrame.add(buttonsPanel,BorderLayout.SOUTH);
        myFrame.add(ratePanel,BorderLayout.WEST);
       
        Toolkit toolkit = myFrame.getToolkit();
        Image icon = toolkit.getImage(config.HomeIconPath);
        myFrame.setIconImage(icon);
       
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        
    }
    
}