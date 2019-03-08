import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

public class SudokuPart{

    private JPanel part;
    private JPanel[][] partArray;
    private int height = 3, width = 3;


    public JPanel part(){
        return this.part;
    }

    public String getText(int i, int j){
        JPanel panel = getPart(i, j);
        Component[] components = panel.getComponents();
        JTextPane pane;

        if(components[0] instanceof JTextPane){
            pane = (JTextPane)components[0];
        }
        else{
            pane = new JTextPane();
        }
        

        String text = pane.getText();
        return text;
    }

    public JPanel[][] partArray(){
        return this.partArray;
    }

    public void setPart(JPanel part){
        this.part = part;
    }

    public void initPartArray(){
        this.partArray = new JPanel[this.width][this.height];
    }
    public void addToArray(JPanel panel, int i, int j){
        this.partArray[i][j] = panel;
    }
    public JPanel getPart(int i, int j){
        return this.partArray[i][j];
    }

    //get textfield value

    //set textfield value

    public SudokuPart(){
        initPartArray();
        setPart(new JPanel());
        
        this.part.setLayout(new GridLayout(this.width,this.height,3,3));

        for(int i = 0; i< this.width; i++){
            for(int j = 0; j< this.height; j++){
                JTextPane jtp = new JTextPane();
                // jtp.setText(((Integer) i).toString());
                JPanel tpPanel = new JPanel();
                jtp.setPreferredSize(new Dimension(100, 100));
                jtp.setFont(new Font("Akzidenz Grotesk", Font.PLAIN, 80));
                tpPanel.add(jtp);
                jtp.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
                jtp.setAlignmentY(JTextPane.CENTER_ALIGNMENT);
                tpPanel.setBackground(Color.WHITE);
                
                
                addToArray(tpPanel, i, j);
                
                this.part.add(partArray()[i][j]);
            }
        }
        this.part.setSize(300,300);  
        this.part.setVisible(true);  
        this.part.setBackground(Color.BLACK);
    }
}