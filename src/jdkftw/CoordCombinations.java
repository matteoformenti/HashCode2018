package jdkftw;

import java.util.ArrayList;

public class CoordCombinations {
    private ArrayList<Coords> coords = new ArrayList<>();
    private int dimension;

    CoordCombinations(int dimensions) {
        this.dimension = dimensions;
        createCoords();
    }

    private void createCoords() {
        for (int r = 1; r <= dimension; r++)
            for (int c = 1; c <= dimension; c++)
                if (r * c == dimension) {
                    coords.add(new Coords(r, c));
                    coords.add(new Coords(r, -1 * c));
                    coords.add(new Coords(-1 * r, -1 * c));
                    coords.add(new Coords(-1 * r, c));
                }
    }

    public int getDimension() {
        return dimension;
    }

    public ArrayList<Coords> getCoords(boolean upwards, boolean rightwards, boolean downwards, boolean leftwards) {
        ArrayList<Coords> out = new ArrayList<>();
        for (Coords coord : coords) {
            if (coord.getR() < 0 && coord.getC() == 1) {
                if (upwards)
                    out.add(coord);
            } else if (coord.getR() < 0 && coord.getC() > 0) {
                if (upwards && rightwards)
                    out.add(coord);
            } else if (coord.getR() == 1 && coord.getC() > 0) {
                if (rightwards)
                    out.add(coord);
            } else if (coord.getR() > 0 && coord.getC() > 0) {
                if (downwards && rightwards)
                    out.add(coord);
            } else if (coord.getR() > 0 && coord.getC() == 0) {
                if (downwards)
                    out.add(coord);
            } else if (coord.getR() < 0 && coord.getC() > 0) {
                if (downwards && leftwards)
                    out.add(coord);
            } else if (coord.getR() == 1 && coord.getC() < 0) {
                if (leftwards)
                    out.add(coord);
            } else if (coord.getR() < 0 && coord.getC() < 0) {
                if (upwards && leftwards)
                    out.add(coord);
            }
        }
        return out;
    }
}
