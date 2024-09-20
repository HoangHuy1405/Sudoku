package main.com.sudoku.services;

public class SodokuSubmittedChecker {
    SodokuValidChecker checker = new SodokuValidChecker();
    // for sure that the submitted 2d array has been all filled in
    public boolean checkSodokuSubmitted(int[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(!checker.isValid(i,j,board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
