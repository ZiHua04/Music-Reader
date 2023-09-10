import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class RatePanel extends JPanel{
    private static App app;
    private static JButton confirmButton;
    private static JTextField BPMField;
    private static JTextField notesField;
    private static JTextField beatesField;
    public boolean isEmpty(){
        if(notesField.getText().isEmpty() || beatesField.getText().isEmpty() || BPMField.getText().isEmpty()){
            return true;
        }
        else return false;
    }
    private static class ConfirmAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String noteString = notesField.getText();
            String beatString = beatesField.getText();
            String BPMString = BPMField.getText();
            if(noteString.isEmpty() || beatString.isEmpty() || BPMString.isEmpty()){
                JOptionPane confirmOptionPane = new JOptionPane();
                confirmOptionPane.showConfirmDialog(null, "输入为空", "警告", JOptionPane.DEFAULT_OPTION);
            }
            else{
                try {
                    int Notes = Integer.parseInt(noteString);
                    int Beats = Integer.parseInt(beatString);
                    int BPM = Integer.parseInt(BPMString);
                    app.setRate(Beats, Notes, BPM);
                } catch (Exception exception) {
                    JOptionPane confirmOptionPane = new JOptionPane();
                    confirmOptionPane.showConfirmDialog(null, "输入不合法", "警告", JOptionPane.DEFAULT_OPTION);
                }
                
            }
            
        }
    }
    public RatePanel(GridLayout gridLayout, App a){
        app = a;
        this.setLayout(gridLayout);
        BPMField = new JTextField();
        BPMField.setToolTipText("请输入BPM");
        BPMField.setOpaque(false);
        notesField = new JTextField();
        notesField.setToolTipText("一小节几拍");
        notesField.setOpaque(false);
        beatesField = new JTextField();
        beatesField.setToolTipText("一页多少小节");
        beatesField.setOpaque(false);
        confirmButton = new JButton("确认");
        confirmButton.setOpaque(false);
        confirmButton.addActionListener(new ConfirmAction());
        this.add(new JLabel("请输入BPM"));
        this.add(BPMField);
        this.add(new Label("一小节几拍"));
        this.add(notesField);
        this.add(new Label("一页多少小节"));
        this.add(beatesField);
        this.add(confirmButton);
        
    }
}
