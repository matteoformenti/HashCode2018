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
        add(startCell);
    }

    public boolean start() {
        for (int i = 0; i < Main.coordCombinations.length; i++){
            for (Coords coord : Main.coordCombinations[i].getCoords(startCell.canGoUp(), startCell.canGoRight(), startCell.canGoDown(), startCell.canGoLeft())) {
                if (fill(coord.getR(), coord.getC()))
                    if (isValid()) {
                        //System.out.println(coord.getR()+","+coord.getC());
                        validateSlice();
                        return true;
                    }
            }
        }
        return false;
    }

    private boolean fill(int r, int c) {
        int sR = startCell.r, sC = startCell.c;
        if (sR+r < 0 || sC+c < 0 || sR+r > Main.R-1 || sC+c > Main.C-1)
            return false;
        for (int nR = 0; nR < r; nR++)
            for (int nC =  0; nC < c; nC++) {
                if (startCell.c != sC+nC || startCell.r != sR+nR) {
                    Cell cell = Main.pizza[sR + nR][sC + nC];
                    if (cell.owned)
                        return false;
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

    public void reset() {
        cells.clear();
        countT = 0;
        countM = 0;
        tot = 0;
        add(startCell);
    }

    private boolean isValid() {
        int countLT = 0;
        int countLM = 0;
        int minC = 0, minR = 0, maxC = 0, maxR = 0;
        for (Cell c : cells) {
            if (c.type == 'T')
                countLT++;
            else
                countLM++;
            if (c.c < minC)
                minC = c.c;
            if (c.c > maxC)
                maxC = c.c;
            if (c.r < minR)
                minR = c.r;
            if (c.r > maxR)
                maxR = c.r;
        }
        boolean valid = true;
        for (Cell c : cells)
            if ((c.c < minC || c.c > maxC) || (c.r < minR || c.r > maxR))
                valid = false;
        return countLM >= Main.L && countLT >= Main.L && cells.size() <= Main.H && valid;
    }

    private void validateSlice() {
        for (Cell c : cells)
            c.owned = true;
    }
}
