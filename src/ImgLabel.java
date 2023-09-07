import javax.swing.JLabel;

import java.awt.Image;

import javax.swing.ImageIcon;
public class ImgLabel extends JLabel{
    private String path;
    public ImgLabel(String path){
        this.path = path;
        ImageIcon img = new ImageIcon(path);
        Image scaledImage = img.getImage().getScaledInstance(config.imgWidth, config.imgHigh, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        this.setIcon(scaledIcon);

    }
    public String getPath(){
        return path;
    }
  
}
