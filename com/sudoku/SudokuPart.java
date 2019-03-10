package com.sudoku;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import java.util.ArrayList;

public class SudokuPart{

    /**
     * This class represents a 3 by 3 box on the sudoku board with a 2d array of JPanels.
     */

    private JPanel part;
    private JPanel[][] partArray;
    private int height = 3, width = 3;

    public JPanel part(){
        return this.part;
    }

    /**
    * Get the text from one of the fields in the box.
    *
    * @param  i   row number
    * @param  j   column number
    * @return text  string holding the text in the JPanel.
    */
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

    /**
    * Add text to one of the fields.
    *
    * @param  i   row number
    * @param  j   column number
    * @param text  text to add
    */
    public void setText(int i, int j, String text){
        JPanel panel = getPart(i, j);
        Component[] components = panel.getComponents();
        JTextPane pane;

        if(components[0] instanceof JTextPane){
            pane = (JTextPane)components[0];
        }
        else{
            pane = new JTextPane();
        }
        pane.setText(text);
        panel.add(pane);
        addToArray(panel, i, j);
    }

    /**
    * Lock a textfield with some text so that it cannot be edited by the user.
    *
    * @param  i   row number
    * @param  j   column number
    * @return text  text to add
    */
    public void nonEditable(int i, int j, String text){
        JPanel panel = getPart(i, j);
        Component[] components = panel.getComponents();
        JTextPane pane;

        if(components[0] instanceof JTextPane){
            pane = (JTextPane)components[0];
        }
        else{
            pane = new JTextPane();
        }
        pane.setText(text);
        pane.setBackground(Color.GRAY);
        pane.setEditable(false);
        panel.add(pane);
        panel.setBackground(Color.GRAY);
        addToArray(panel, i, j);
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

    /**
    * Get the values of the fields as an ArrayList.
    *
    * @return ints  ArrayList representation of the box.
    */
    public ArrayList<Integer> partAsIntList(){
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for(int i=0; i< 3; i++){
            for(int j =0; j<3;j++){
                if(getText(i,j).equals("")){
                    ints.add(0);
                }
                else{
                    ints.add(Integer.parseInt(getText(i,j)));
                }
            }
        }
        return ints;

    }

    /**
    * Generate a 2d array with JPanels holding JTextPanes to represent a 3 by 3 box.
    */
    public SudokuPart(){
        initPartArray();
        setPart(new JPanel());
        
        this.part.setLayout(new GridLayout(this.width,this.height,3,3));

        for(int i = 0; i< this.width; i++){
            for(int j = 0; j< this.height; j++){
                JTextPane jtp = new JTextPane();
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