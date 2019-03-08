import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

public class SudokuUtil{

    public static JPanel formattedPanel(String text){
        JTextPane jtp = new JTextPane();

        if(!text.equals("")){
            jtp.setText(text);
        }
        JPanel tpPanel = new JPanel();
        jtp.setPreferredSize(new Dimension(100, 100));
        jtp.setFont(new Font("Akzidenz Grotesk", Font.PLAIN, 80));
        tpPanel.add(jtp);
        jtp.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
        jtp.setAlignmentY(JTextPane.CENTER_ALIGNMENT);
        tpPanel.setBackground(Color.WHITE);
        return tpPanel;
    }
    
}