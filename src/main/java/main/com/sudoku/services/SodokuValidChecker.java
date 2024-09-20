package main.com.sudoku.services;

import java.util.HashSet;

public class SodokuValidChecker {
    HashSet<Integer>[] rows = new HashSet[9];
    HashSet<Integer>[] cols = new HashSet[9];
    HashSet<Integer>[][] subboxs = new HashSet[3][3];

    public SodokuValidChecker() {
        for(int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                subboxs[i][j] = new HashSet<>();
            }
        }
    }

    public boolean isValid(int x, int y, int n) {
        int bigX = x/3;
        int bigY = y/3;
        if(rows[x].contains(n) || cols[y].contains(n) || subboxs[bigX][bigY].contains(n)) {
            return false;
        } else {
            rows[x].add(n);
            cols[y].add(n);
            subboxs[bigX][bigY].add(n);
        }
        return true;
    }

    public void remove(int x, int y, Integer n) {
        int bigX = x/3;
        int bigY = y/3;
        rows[x].remove(n);
        cols[y].remove(n);
        subboxs[bigX][bigY].remove(n);
    }

    public void clear() {
        for(int i = 0; i < 9; i++) {
            rows[i].clear();
            cols[i].clear();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                subboxs[i][j].clear();
            }
        }
    }
}
