package main.com.sudoku.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private int resultArray[][] = new int[9][9];
    private int initialArr[] = new int[9];

    private int randomRow = 0;

    Utilities util = new Utilities();
    SodokuValidChecker checker = new SodokuValidChecker();
    // initialize array to first row with random number
    private void createArr() {
        checker.clear();
        int randomArr[] = util.shuffle(9);
        // initialize the whole 2d arr
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(i == randomRow) {
                    resultArray[i][j] = randomArr[j];
                } else {
                    resultArray[i][j] = -1;
                }
            }
        }

        // add all of number the first row into checker
        for(int i = 0; i < 9; i++) {
            checker.isValid(randomRow, i, randomArr[i]);
        }
        // backTracking to generate whole 2d arr
        backTracking(0, 0);
        // takeaway 60% cells (for playing purpose)
        removeRandomly(5);

    }
    private boolean backTracking(int x, int y) {
        // if x == 9 the backTracking has reach the end => it solve the problem => return everything back to true
        if(x == 9) {
            return true;
        }
        if(x == randomRow) {
            backTracking(x+1,y);
        }
        for(int i = 1; i <= 9; i++) {
            boolean checkValid = checker.isValid(x, y, i);
            boolean returnCheck;
            if(checkValid) {
                resultArray[x][y] = i;
                if(y == resultArray.length - 1) {
                    returnCheck = backTracking(x+1,0);
                } else {
                    returnCheck = backTracking(x,y+1);
                }
            } else {
                continue;
            }
            if(returnCheck) {
                return true;
            } else {
                // if return back is false => undo everything and iterate to next branch of tree
                int n = resultArray[x][y];
                resultArray[x][y] = -1;
                checker.remove(x, y, n);
            }
        }
        return false;
    }

    private void removeRandomly(float percent) {
        int cellsToBeDeleted = (int) ((int) 81*(percent / 100));
        Random r = new Random();
        List<Integer> cells = new ArrayList<>();
        // Initialize the list with -1 using a loop
        for (int i = 0; i < 81; i++) {
            cells.add(i);
        }
        for(int i = 0; i < cellsToBeDeleted; i++) {
            int index = r.nextInt(cells.size());
            int row = cells.get(index) / 9;
            int col = cells.get(index) % 9;
            resultArray[row][col] = -1;
            cells.remove(index);
        }
    }

    public int[][] getArray() {
        createArr();
        return resultArray;
    }

}
