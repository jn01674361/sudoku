import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;

public class SudokuGUI extends Frame implements ActionListener{
   public TextField tf = new TextField();
    // private variables
    private JPanel mainpanel;
    private SudokuPart[][] panelArr = new SudokuPart[3][3];

    public void initMainpanel(){
        this.mainpanel = new JPanel();
    }
    public SudokuPart[][] panelArr(){
        return this.panelArr;
    }
    public SudokuPart getPanel(int i, int j){
        return this.panelArr[i][j];
    }

   // Constructor to setup the GUI components
   public SudokuGUI() { 
        initMainpanel();
        

        mainpanel.setLayout(new GridLayout(3, 3, 10, 10));    
        //create components  
        for(int i = 0; i<3; i++){
            for(int j=0; j<3; j++){
                SudokuPart part = new SudokuPart();
                panelArr[i][j] = part;
                mainpanel.add(panelArr[i][j].part());
                // TextField tf = new TextField();
                // add(tf);
            }
        }

        mainpanel.setSize(1000,1000);  
        mainpanel.setVisible(true);  
        mainpanel.setBackground(Color.BLACK);

        add(mainpanel);
        setSize(1000,1000);  
        setVisible(true);  
        
        Integer[][] matrix = getMatrix();

        // for(int i = 0; i<matrix.length;i++){
        //     for(int j =0 ; j< matrix[0].length; j++){
        //         System.out.println("Values at arr["+i+"]["+j+"] is "+matrix[i][j]);
        //     }
        // }

    }

    public void actionPerformed(ActionEvent e){  
        tf.setText("Welcome");
    }
    public Integer[][] getMatrix(){
        Integer[][]mat = new Integer[9][9];
        for(int i =0;i<3;i++){
            for(int j = 0; j<3;j++){
                //i,j loop through the 3x3 sudokuboxes in the 3x3 sudokuboard 
                for(int k=0; k<3;k++){
                    for(int l=0;l<3;l++){
                        //k,l loop through the fields of the 3x3 sudokubox
                        mat[k+3*i][l+3*j] = Integer.parseInt(getPanel(i,j).getText(k,l));

                    }
                }
            }
        }

        // how to add stuff to the matrix?


        return mat;
    }
   public static void main(String[] args) {
      // Invoke the constructor (to setup the GUI) by allocating an instance
      new SudokuGUI();
      

   }
}