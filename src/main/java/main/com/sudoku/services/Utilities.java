package main.com.sudoku.services;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Random;

public class Utilities {
    int[] shuffleArr = {1,2,3,4,5,6,7,8,9};
    // ArrayToJson
    public static String arrayToJson(int[][] arr) {
        JSONArray jsonArray = new JSONArray(arr);
        return jsonArray.toString();
    }

    // shuffle Array using Fisher–Yates shuffle Algorithm
    public int[] shuffle(int n) {
        // Creating a object for Random class
        Random r = new Random();

        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = n-1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i+1);

            // Swap arr[i] with the element at random index
            int temp = shuffleArr[i];
            shuffleArr[i] = shuffleArr[j];
            shuffleArr[j] = temp;
        }
        // Prints the random array
        return shuffleArr;
    }
}
