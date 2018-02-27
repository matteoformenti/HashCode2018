package jdkftw;

import java.util.ArrayList;

public class Slice {
    public ArrayList<Cell> cells = new ArrayList<>();
    private Cell startCell;

    private int countM = 0;
    private int countT = 0;
    private int tot = 0;

    Slice(Cell startCell) {
        this.startCell = startCell;
        startCell.analyzed = true;
        add(startCell);
    }

    public boolean start() {
        if (startCell.c == 6 && startCell.r == 0)
            System.out.println("eccomi");
        for (int i = 0; i < Main.coordCombinations.length; i++) {
            for (Coords coord : Main.coordCombinations[i].getCoords(startCell.canGoUp(), startCell.canGoRight(), startCell.canGoDown(), startCell.canGoLeft())) {
                if (fill(coord.getR(), coord.getC()))
                    if (isValid()) {
                        validateSlice();
                        return true;
                    }
            }
        }
        return false;
    }

    private boolean fill(int r, int c) {
        int sR = startCell.r, sC = startCell.c;
        if (sR + r < 0 || sC + c < 0 || sR + r > Main.R || sC + c > Main.C)
            return false;
        for (int nR = 0; nR < r; nR++)
            for (int nC = 0; nC < c; nC++) {
                if (startCell.c != sC + nC || startCell.r != sR + nR) {
                    Cell cell = Main.pizza[sR + nR][sC + nC];
                    if (cell.owned) {
                        reset();
                        return false;
                    }
                    add(cell);
                }
            }
        return true;
    }

    private void add(Cell c) {
        if (c.type == 'T')
            countT++;
        else
            countM++;
        tot++;
        cells.add(c);
    }

    private void reset() {
        cells.clear();
        cells.forEach(cell -> {
            cells.remove(cell);
        });
        countT = 0;
        countM = 0;
        tot = 0;
        add(startCell);
    }

    public boolean isValid() {
        int countLT = 0;
        int countLM = 0;
        for (Cell c : cells) {
            if (c.type == 'T')
                countLT++;
            else
                countLM++;
        }
        return countLM >= Main.L && countLT >= Main.L && cells.size() <= Main.H;
    }

    private void validateSlice() {
        for (Cell c : cells)
            c.owned = true;
    }

    public int[] getMaxMinC() {
        int cMin = Main.C;
        int cMax = -1;
        for (Cell c : cells) {
            if (c.c < cMin)
                cMin = c.c;
            if (c.c > cMax)
                cMax = c.c;
        }
        return new int[]{cMin, cMax};
    }

    public int[] getMaxMinR() {
        int rMin = Main.R;
        int rMax = -1;
        for (Cell c : cells) {
            if (c.r < rMin)
                rMin = c.r;
            if (c.r > rMax)
                rMax = c.r;
        }
        return new int[]{rMin, rMax};
    }
}
