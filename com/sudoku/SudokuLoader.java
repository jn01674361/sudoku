package com.sudoku;


import java.util.Random;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SudokuLoader{
    private Integer[][] sudokuMatrix;
    private String pathToSudoku = "";
    private final String puzzleFolder = "txt/puzzles";

    public void setPathToSudoku(String path){
        this.pathToSudoku = path;
    }
    public String pathToSudoku(){
        return this.pathToSudoku;
    }

    public Integer[][] sudokuMatrix(){
        return this.sudokuMatrix;
    }
    public void loadSudokuFromTxt(){
        try{
            Integer[][] mat = new Integer[9][9];
            if(pathToSudoku.equals("")){
                File puzzleFile = new File(puzzleFolder);
                File[] fileList = puzzleFile.listFiles();
                Random rand = new Random(); 
                File randFile = fileList[rand.nextInt(fileList.length)];
                setPathToSudoku(randFile.toString());
            }
            BufferedReader br = new BufferedReader(new FileReader(pathToSudoku()));
            String line = br.readLine();
            int i =0;
            while(line!=null)
            {   
                int j=0;
                int k = 0;
                while(j<9 && k<line.length()){

                    char c = line.charAt(k);
                    if(c != ' '){
                        System.out.print(i);
                        System.out.print(j);
                        mat[i][j]=Integer.parseInt(String.valueOf(c));
                        j++;
                    }
                    k++;
                    
                }     
                line = br.readLine();
                i++;
            }
            br.close(); 
            this.sudokuMatrix = mat;
        }
        catch(FileNotFoundException e){
            System.err.println("SudokuLoader: "+e);
        }
        catch(IOException e){
            System.err.println("SudokuLoader: "+e);
        }
        
    }
}