package jdkftw;

public class Cell {
    public int r;
    public int c;
    public boolean owned;
    public char type;
    public boolean analyzed;

    public Cell(int r, int c, char type) {
        this.r = r;
        this.c = c;
        owned = false;
        this.type = type;
    }

    public boolean canGoUp() {
        return (r != 0) && (!Main.pizza[r - 1][c].owned);
    }

    public boolean canGoDown() {
        return (r != Main.R - 1) && (!Main.pizza[r + 1][c].owned);
    }

    public boolean canGoLeft() {
        return (c != 0) && (!Main.pizza[r][c - 1].owned);
    }

    public boolean canGoRight() {
        return (c != Main.C - 1) && (!Main.pizza[r][c + 1].owned);
    }
}
