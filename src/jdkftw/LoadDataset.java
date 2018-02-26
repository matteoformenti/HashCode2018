package jdkftw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadDataset {
    public static void loadDataset(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        long time0 = System.nanoTime();
        try {
            String firstLine = reader.readLine();
            Main.R = Integer.parseInt(firstLine.split(" ")[0]);
            Main.C = Integer.parseInt(firstLine.split(" ")[1]);
            Main.L = Integer.parseInt(firstLine.split(" ")[2]);
            Main.H = Integer.parseInt(firstLine.split(" ")[3]);
            Main.pizza = new Cell[Main.R][Main.C];
            for (int r = 0; r < Main.R; r++) {
                char[] line = reader.readLine().toCharArray();
                for (int c = 0; c < line.length; c++) {
                    Cell cell = new Cell(r, c, line[c]);
                    Main.pizza[r][c] = cell;
                    Main.cells.add(cell);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time1 = System.nanoTime();
        System.out.println("Dataset loaded in " + Main.toMillis(time1 - time0) + "ms");
    }
}
