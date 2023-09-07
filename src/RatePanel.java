import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RatePanel extends JPanel{
    private static App app;
    private static JButton confirmButton;
    private static JTextField BPMField;
    private static JTextField notesField;
    private static JTextField beatesField;
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
                int Notes = Integer.parseInt(noteString);
                int Beats = Integer.parseInt(beatString);
                int BPM = Integer.parseInt(BPMString);
                app.setRate(Beats, Notes, BPM);
            }
            
        }
    }
    public RatePanel(GridLayout gridLayout, App a){
        app = a;
        this.setLayout(gridLayout);
        BPMField = new JTextField();
        notesField = new JTextField();
        notesField.setOpaque(false);
        beatesField = new JTextField();
        beatesField.setOpaque(false);
        confirmButton = new JButton("确认");
        confirmButton.setOpaque(false);
        confirmButton.addActionListener(new ConfirmAction());
        this.add(BPMField);
        this.add(notesField);
        this.add(beatesField);
        this.add(confirmButton);
        
    }
}
