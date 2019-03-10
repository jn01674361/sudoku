package com.sudoku;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class SudokuGUI extends Frame implements ActionListener{
   public TextField tf = new TextField();
   public JButton load = new JButton("New puzzle"), submit = new JButton("Submit"), exit = new JButton("Exit");
   private boolean validFormat = true;
    private JPanel mainpanel;
    private SudokuPart[][] panelArr = new SudokuPart[3][3];
    private Integer[][] testMat;

    public void initMainpanel(){
        this.mainpanel = new JPanel();
    }
    public SudokuPart[][] panelArr(){
        return this.panelArr;
    }
    public void valid(){
        this.validFormat = true;
    }
    public void nonvalid(){
        this.validFormat = false;
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

    public void loadBoard(SudokuLoader loader, boolean show){
        loader.loadSudokuFromTxt();
        loadPanelsFromMatrix(loader.sudokuMatrix());
        if(show){
            mainpanel.setLayout(new GridLayout(3, 3, 10, 10));    
            mainpanel.setSize(1000,1000);  
            mainpanel.setVisible(true);  
            mainpanel.setBackground(Color.BLACK);
        }
    }
   public SudokuGUI(boolean test) { 
        if(!test){
            SudokuLoader loader = new SudokuLoader();
            loadBoard(loader, true);

            JPanel btnPanel = new JPanel();
        
            this.load.setActionCommand("load");
            this.submit.setActionCommand("submit");
            this.exit.setActionCommand("exit");                
            
            this.load.addActionListener(this);
            this.submit.addActionListener(this);
            this.exit.addActionListener(this);
    
            btnPanel.add(this.load);
            btnPanel.add(this.submit);
            btnPanel.add(this.exit);
            
            btnPanel.setLayout(new GridLayout(1,3));
            btnPanel.setSize(1000,200);
            btnPanel.setVisible(true);
            btnPanel.setBackground(Color.BLACK);
            
            setLayout(new BorderLayout());
    
            add(btnPanel, BorderLayout.PAGE_END);
            add(mainpanel, BorderLayout.CENTER);
            setSize(1200,1000);  
            setVisible(true); 
        }
        else{



        }
 
    }

    public void actionPerformed(ActionEvent e){  
        if("load".equals(e.getActionCommand())){
            remove(mainpanel);
            SudokuLoader loader = new SudokuLoader();
            loadBoard(loader, true);

            add(mainpanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
        else if("submit".equals(e.getActionCommand())){
            getMatrix();
            String result;
            if(!validFormat()){
                result = "Found invalid characters!";
            }
            else{
                boolean val = validAll(false);
                
                if(val){
                    result = "Good job! Click \'New Puzzle\' for another Sudoku challenge!";
                }
                else{
                    result = "Sorry, that is incorrect.";
                }
            }
            
            JOptionPane.showMessageDialog(null, result);
        }
        else if("exit".equals(e.getActionCommand())){
            System.exit(0);
        }
    }
    public Integer[][] getMatrix(){
        valid();
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
                            try{
                                mat[k+3*i][l+3*j] = Integer.parseInt(getPanel(i,j).getText(k,l));
                            }
                            catch(NumberFormatException e){
                                nonvalid();
                                mat[k+3*i][l+3*j] = 0;

                            }
                            
                        }
                    }
                }
            }
        }
        return mat;
    }
    public void setTestMat(Integer[][] mat){
        this.testMat = mat;
    }
    public Integer[][] testMat(){
        return this.testMat;
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
                            part = addTextToPart(part, k, l, "", true);
                        }
                        else{
                            part = addTextToPart(part, k, l, Integer.toString(mat[k+3*i][l+3*j]), false);
                        }
                        panelArr[i][j] = part;
                        mainpanel.add(panelArr[i][j].part());
                    }
                }
            }
        }
    }

    public Integer[] getMatRow(int i, boolean test){
        Integer[] row = new Integer[9];

        Integer[][] mat;
        if(test){
            mat = this.testMat();
        }
        else{
            mat = this.getMatrix();
        }

        for(int j =0; j<row.length;j++){
            row[j] = mat[i][j];
        }
        return row;
    }

    public Integer[] getMatCol(int j, boolean test){
        Integer[] col = new Integer[9];

        Integer[][] mat;
        if(test){
            mat = this.testMat();
        }
        else{
            mat = this.getMatrix();
        }

        for(int i =0; i<col.length;i++){
            col[i] = mat[i][j];
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

    public Integer[][] getBox(int i, int j, boolean test){
        // 00 01 02
        // 10 11 12
        // 20 21 22

        Integer[][] mat;
        if(test){
            mat = this.testMat();
        }
        else{
            mat = this.getMatrix();
        }

        Integer[][] box = new Integer[3][3];
        for(int k =0; k< 3; k++){
            for(int l=0; l< 3; l++){
                box[k][l] = mat[k+3*i][l+3*j];
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

    public boolean validFormat(){
        return this.validFormat;
    }

    public boolean validAll(boolean test){
        boolean valid = true;

        for(int i = 0; i< 9; i++){
            if(!validSeq(getMatRow(i, test))){
                valid = false;
            }
        }
        for(int j = 0; j< 9; j++){
            if(!validSeq(getMatCol(j, test))){
                valid = false;
            }
        }
        for(int k = 0; k<3;k++){
            for(int l=0; l<3; l++){
                if(!validBox(getBox(k,l, test))){
                    valid = false;
                }
            }
        }
        return valid;
    }

   public static void main(String[] args) {
       if(args.length>0){
        // run wih tests
        if(!args[0].equals("-t")){
            System.err.println("Add flag \'-t\' to run with tests. TO run without test, leave out cmd arguments.");
            System.exit(0);
        }
        else{
            if(Tests.validatesCorrectly(new SudokuGUI(true))){
                System.err.println("All tests passed!");
                new SudokuGUI(false);
            }
            else{
                System.err.println("Tests did not pass!");
                System.exit(0);
            }
        }
       }
       else{
        // run without tests
        new SudokuGUI(false); 
       }     
   }
}