import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

public class SudokuGUI extends Frame implements ActionListener{
    TextField tf;  
   // private variables
 
   // Constructor to setup the GUI components
   public SudokuGUI() { 
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(3, 3, 10, 10));    
        //create components  
        for(int i = 0; i<3; i++){
            for(int j=0; j<3; j++){
                JPanel part = sudokuPart();
                mainpanel.add(part);
                // TextField tf = new TextField();
                // add(tf);
            }
        }

        mainpanel.setSize(1000,1000);  
        mainpanel.setVisible(true);  
        add(mainpanel);
        setSize(1000,1000);  
        setVisible(true);  


    }

    public void actionPerformed(ActionEvent e){  
        tf.setText("Welcome");  
        }
    public JPanel sudokuPart(){
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3,3,3,3));

        for(int i = 0; i<3; i++){
            for(int j=0; j<3; j++){
                TextField tf = new TextField();
                panel.add(tf);
            }
        }
        panel.setSize(300,300);  
        panel.setVisible(true);  

        return panel;
    }
   public static void main(String[] args) {
      // Invoke the constructor (to setup the GUI) by allocating an instance
      new SudokuGUI();
   }
}