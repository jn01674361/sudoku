import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class SudokuGUI extends Frame implements ActionListener{
   public TextField tf = new TextField();
   public JButton load = new JButton("New puzzle"), submit = new JButton("Submit"), exit = new JButton("Exit");
   
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
    public SudokuPart addTextToPart(SudokuPart part, int ifield, int jfield, String text, boolean editable){
        

        if(part!=null){
            if(!editable){
                part.nonEditable(ifield, jfield, text);
            }
            else{
                part.setText(ifield, jfield, text);
            }
        }
        else{
            JPanel jp = SudokuUtil.formattedPanel("");
            part = new SudokuPart();

            if(!editable){
                part.nonEditable(ifield, jfield, text);
            }

            part.addToArray(jp, ifield, jfield);

        }

        
        return part;
    }

   // Constructor to setup the GUI components


   public SudokuGUI() { 


        SudokuLoader loader = new SudokuLoader();
        loader.loadSudokuFromTxt();
        loadPanelsFromMatrix(loader.sudokuMatrix());
        // initMainpanel();
        

        mainpanel.setLayout(new GridLayout(3, 3, 10, 10));    
        // //create components  
        // for(int i = 0; i<3; i++){
        //     for(int j=0; j<3; j++){
        //         SudokuPart part = new SudokuPart();
        //         panelArr[i][j] = part;
        //         mainpanel.add(panelArr[i][j].part());
        //         // TextField tf = new TextField();
        //         // add(tf);
        //     }
        // }

        mainpanel.setSize(1000,1000);  
        mainpanel.setVisible(true);  
        mainpanel.setBackground(Color.BLACK);

        JPanel btnPanel = new JPanel();
        
        btnPanel.add(this.load);
        btnPanel.add(this.submit);
        btnPanel.add(this.exit);
        
        btnPanel.setLayout(new GridLayout(1,3));
        btnPanel.setSize(1000,100);
        btnPanel.setVisible(true);
        btnPanel.setBackground(Color.BLACK);
        
        setLayout(new BorderLayout());

        add(btnPanel, BorderLayout.PAGE_END);
        add(mainpanel, BorderLayout.CENTER);
        setSize(1100,1100);  
        setVisible(true);  
        
        Integer[][] matrix = getMatrix();

        // for(int i = 0; i<matrix.length;i++){
        //     for(int j =0 ; j< matrix[0].length; j++){
        //         System.out.println("Values at arr["+i+"]["+j+"] is "+matrix[i][j]);
        //     }
        // }

        // System.out.println();
        // System.out.println("valid? "+validAll());

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
                        if(getPanel(i,j).getText(k,l).equals("")){
                            mat[k+3*i][l+3*j] = 0;
                        }
                        else{
                            mat[k+3*i][l+3*j] = Integer.parseInt(getPanel(i,j).getText(k,l));
                        }
                    }
                }
            }
        }

        // how to add stuff to the matrix?
        return mat;
    }

    public void loadPanelsFromMatrix(Integer[][] mat){
        initMainpanel();
        for(int i =0;i<3;i++){
            for(int j = 0; j<3;j++){
                //i,j loop through the 3x3 sudokuboxes in the 3x3 sudokuboard
                SudokuPart part = new SudokuPart();
                for(int k=0; k<3;k++){
                    for(int l=0;l<3;l++){
                        //k,l loop through the fields of the 3x3 sudokubox
                        if(mat[k+3*i][l+3*j]==0){
                            // getPanel(i,j).setText(k,l,"");
                            part = addTextToPart(part, k, l, "", true);
                            // System.err.println("0");
                            // setPanelText(i, j, k, l, "");
                        }
                        else{
                            part = addTextToPart(part, k, l, Integer.toString(mat[k+3*i][l+3*j]), false);

                            // setPanelText(i, j, k, l, Integer.toString(mat[k+3*i][l+3*j]));
                            // getPanel(i,j).setText(k, l, Integer.toString(mat[k+3*i][l+3*j]));
                        }
                        panelArr[i][j] = part;
                        mainpanel.add(panelArr[i][j].part());

                        // mat[k+3*i][l+3*j] = Integer.parseInt(getPanel(i,j).getText(k,l));

                    }
                }
            }
        }
    }

    public Integer[] getMatRow(int i){
        Integer[] row = new Integer[9];

        for(int j =0; j<row.length;j++){
            row[j] = this.getMatrix()[i][j];
        }
        return row;

    }

    public Integer[] getMatCol(int j){
        Integer[] col = new Integer[9];

        for(int i =0; i<col.length;i++){
            col[i] = this.getMatrix()[i][j];
        }
        return col;

    }

    public boolean validSeq(Integer[] seq){
        boolean valid = true;
        Set<Integer> mySet = new HashSet<Integer>(Arrays.asList(seq));
        for(int i =1; i<10;i++){
            if(!mySet.contains(i)){
                valid = false;
            }
        }
        return valid;
    }

    public Integer[][] getBox(int i, int j){
        // 00 01 02
        // 10 11 12
        // 20 21 22
        Integer[][] box = new Integer[3][3];
        for(int k =0; k< 3; k++){
            for(int l=0; l< 3; l++){
                box[k][l] = getMatrix()[k+3*i][l+3*j];
            }
        }
        return box;
        
    }

    public boolean validBox(Integer[][] box){
        boolean valid = true;
        Set<Integer> mySet = new HashSet<Integer>();
        for(int g =0; g<3;g++){
            for(int h=0; h<3;h++){
                mySet.add(box[g][h]);
            }
        }
        for(int i =1; i<10;i++){
            if(!mySet.contains(i)){
                valid = false;
            }
        }
        return valid;
    }

    public boolean validAll(){
        boolean valid = true;

        for(int i = 0; i< 9; i++){
            if(!validSeq(getMatRow(i))){
                valid = false;
            }
        }

        for(int j = 0; j< 9; j++){
            if(!validSeq(getMatCol(j))){
                valid = false;
            }
        }

        for(int k = 0; k<3;k++){
            for(int l=0; l<3; l++){
                if(!validBox(getBox(k,l))){
                    valid = false;
                }
            }
        }

        return valid;
    }

   public static void main(String[] args) {
      // Invoke the constructor (to setup the GUI) by allocating an instance
      new SudokuGUI();
      

   }
}