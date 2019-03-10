package com.sudoku;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Tests {

    /**
     * This class is used to run desired tests on an example instantiation of the SudokuGUI class.
     */

    /**
    * Check that the sudoku validator works correctly
    *
    * @param sudoku     SudokuGUI object for testing.
    * @return       true if the tests passed, false otherwise.
    */
    public static boolean validatesCorrectly(SudokuGUI sudoku) {

        SudokuLoader loader = new SudokuLoader();

        File puzzleFolder = new File("txt/puzzles");
        File[] puzzleList = puzzleFolder.listFiles();

        File solutionFolder = new File("txt/raw_solutions");
        File[] solutionList = solutionFolder.listFiles();

        Integer[][] mat;

        for(int i =0; i<puzzleList.length;i++){
            if(!puzzleList[i].toString().substring(0, 1).equals("s")){
                continue;
            }
            loader.setPathToSudoku(puzzleList[i].toString());
            loader.loadSudokuFromTxt();
            mat = loader.sudokuMatrix();
            sudoku.setTestMat(mat);
            if(sudoku.validAll(true)){

                return(false);
            }
        }

        for(int i =0; i<solutionList.length;i++){
            if(!puzzleList[i].toString().substring(0, 1).equals("s")){
                continue;
            }
            loader.setPathToSudoku(solutionList[i].toString());
            loader.loadSudokuFromTxt();
            mat = loader.sudokuMatrix();
            sudoku.setTestMat(mat);
            if(!sudoku.validAll(true)){
                return(false);
            }
            
        }
        return(true);
    }
}