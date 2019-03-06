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

    //get textfield value

    //set textfield value

    public SudokuPart(){
        initPartArray();
        setPart(new JPanel());
        
        this.part.setLayout(new GridLayout(this.width,this.height,3,3));

        for(int i = 0; i< this.width; i++){
            for(int j = 0; j< this.height; j++){
                JTextField jtf = new JTextField("0", 9);
                JPanel tfPanel = new JPanel();
                tfPanel.add(jtf);

                addToArray(tfPanel, i, j);
                
                this.part.add(partArray()[i][j]);
            }
        }
        this.part.setSize(300,300);  
        this.part.setVisible(true);  
        this.part.setBackground(Color.BLACK);
    }
}