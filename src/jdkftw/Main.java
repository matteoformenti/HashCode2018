package jdkftw;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Slice> slices = new ArrayList<>();
    public static ArrayList<Cell> cells = new ArrayList<>();
    public static Cell[][] pizza;

    public static int L;
    public static int H;
    public static int R;
    public static int C;

    public static CoordCombinations[] coordCombinations;

    public static void main(String[] args) {
        //Load dataset
        LoadDataset.loadDataset(Main.class.getResourceAsStream("in/big.in"));

        //Vector combinations
        long coordCalcTime0 = System.nanoTime();
        coordCombinations = new CoordCombinations[H - (2 * L) + 1];
        for (int i = 0; i < H - (2 * L) + 1; i++)
            coordCombinations[i] = new CoordCombinations(H - i);
        long coordCalcTime1 = System.nanoTime();
        System.out.println("Coord calculation done in " + toMillis(coordCalcTime1 - coordCalcTime0) + "ms");

        //Start slicing
        long slicingTime0 = System.nanoTime();
        for (Cell c : cells) {
            if (!c.owned) {
                Slice slice = new Slice(c);
                if (slice.start())
                    slices.add(slice);
            }
        }
        long slicingTime1 = System.nanoTime();
        System.out.println("Slicing done in " + toMillis(slicingTime1 - slicingTime0) + "ms");

        //Calc score
        int tot = 0;
        for (Slice slice : slices) {
            tot += slice.cells.size();
        }
        System.out.println("Total points: " + tot);
        System.out.println("Total slices: " + slices.size());
        System.out.println("Max points: " + (R * C));
        System.out.println("Efficency: " + ((tot) * 100.0 / (R * C)));

        for (Slice slice : slices) {
            tot += slice.cells.size();
        }
    }

    public static double toMillis(long nanoSeconds) {
        return nanoSeconds / 1000000.0;
    }
}
