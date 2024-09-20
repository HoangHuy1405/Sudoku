package main.com.sudoku.controller;

import main.com.sudoku.services.SodokuSubmittedChecker;
import main.com.sudoku.services.SodokuValidChecker;
import main.com.sudoku.services.SudokuGenerator;
import main.com.sudoku.services.Utilities;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/api")
public class SudokuController {

    SudokuGenerator sudokuGenerator = new SudokuGenerator();
    Utilities util = new Utilities();

    @GetMapping("/generateArr")
    @ResponseBody
    public String generateArr() {
        int[][] arr = sudokuGenerator.getArray();
        String json = Utilities.arrayToJson(arr);
        return json;
    }

    @PostMapping("/generateArr/submit")
    public ResponseEntity<Boolean> recieveArr(@RequestBody int[][] board) {
        System.out.println("Received array:");
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        // Return a response
        System.out.println("Array received successfully!");

        SodokuSubmittedChecker checker = new SodokuSubmittedChecker();
        if(checker.checkSodokuSubmitted(board)) {
            System.out.println("Winner winner chicken dinner!");
            return ResponseEntity.ok(true);
        } else {
            System.out.println("Lost!");
            return ResponseEntity.ok(false);
        }
    }
}
