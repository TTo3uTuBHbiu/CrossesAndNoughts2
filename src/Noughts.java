/**
 * Created by z on 19.04.2017.
 */

import java.util.HashMap;
import java.util.Map;

public class Noughts {


    public enum Sign {
        NOUGHT,
        CROSS;

        public static String StringBuilder(Sign x) {
            if (x == NOUGHT) return ("O ");
            else if (x == CROSS) return ("X ");
            else return ("- ");

    }


    }


    public final static class Cell {
        int x;

        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Cell plus(Cell other) {
            return new Cell(x + other.x, y + other.y);
        }

        public boolean equals(Object other) {
            if (this == other) return true;
            if (other instanceof Cell) {
                Cell cell = (Cell) other;
                return x == cell.x && y == cell.y;
            }
            return false;
        }

        public int hashCode() {
            int result = 11;
            result = 19 * result + x;
            return 19 * result + y;
        }
    }


    public final static class Board {

        int width;
        int height;

        public Map<Cell, Sign> signs = new HashMap<>();

        public Board(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public Sign get(int x, int y) {
            return get(new Cell(x, y));
        }

        public Sign get(Cell cell) {
            return signs.get(cell);
        }


        public void addCross(int t, int i) {
            if ((t < 0 || t >= width) || (i < 0 || i >= height)) throw new IllegalArgumentException();
            Cell cell = new Cell(t, i);
            if (!signs.containsKey(cell)) signs.put(cell, Sign.CROSS);
            else return;

        }

        public void addNought(int t, int i) {
            if ((t < 0 || t >= width) || (i < 0 || i >= height)) throw new IllegalArgumentException();
            Cell cell = new Cell(t, i);
            if (!signs.containsKey(cell)) signs.put(cell, Sign.NOUGHT);
            else return;

        }

        public void clearCell(int t, int i) {
            if ((t < 0 || t >= width) || (i < 0 || i >= height)) throw new IllegalArgumentException();
            Cell cell = new Cell(t, i);
            if (!signs.containsKey(cell)) signs.put(cell, null);
            else return;

        }

        public boolean noughtsExist() {
            for (Sign value : signs.values()) {
                if (value == Sign.NOUGHT) return true;
            }
            return false;
        }


        public boolean crossesExist() {
            for (Sign value : signs.values()) {
                if (value == Sign.CROSS) return true;
            }
            return false;
        }

        static private final Cell[] DIRECTIONS = new Cell[]{
                new Cell(0, 1), new Cell(1, 0),
                new Cell(1, 1), new Cell(1, -1)
        };

        public boolean correct(Cell cell) {
            return cell.getX() >= 0 && cell.getX() < width && cell.getY() >= 0 && cell.getY() < height;
        }


        public int maxInRow() {
            int max = 1;
                for (int t = 0; t < width; ++t) {

                    for (int i = 0; i < height - 1; ++i) {

                        Cell cell = new Cell(t, i);

                        Sign startSign = signs.get(cell);


                        if (startSign == null) continue;
                        for (Cell dir : DIRECTIONS) {
                            Cell current = cell;
                            int length = 1;
                            for (; ; length++) {
                                current = current.plus(dir);
                                if (!correct(current)) break;
                                if (get(current) != startSign) break;
                            }
                            if (length > max) max = length;

                        }
                    }
                }
                return max;

        }

        public int maxInRowNought() {
            int max = 1;
            if (noughtsExist() == false) return 0;
            else {
                for (int t = 0; t < width; ++t) {

                    for (int i = 0; i < height - 1; ++i) {

                        Cell cell = new Cell(t, i);

                        Sign startSign = signs.get(cell);


                        if ((startSign == null) || (startSign == Sign.CROSS)) continue;
                        for (Cell dir : DIRECTIONS) {
                            Cell current = cell;
                            int length = 1;
                            for (; ; length++) {
                                current = current.plus(dir);
                                if (!correct(current)) break;
                                if (get(current) != startSign) break;
                            }
                            if (length > max) max = length;

                        }
                    }
                }
                return max;

            }
        }



        public int maxInRowCross() {
            int max = 1;
            if (crossesExist() == false) return 0;
            else {
                for (int t = 0; t < width; ++t) {

                    for (int i = 0; i < height - 1; ++i) {

                        Cell cell = new Cell(t, i);

                        Sign startSign = signs.get(cell);


                        if ((startSign == null) || (startSign == Sign.NOUGHT)) continue;
                        for (Cell dir : DIRECTIONS) {
                            Cell current = cell;
                            int length = 1;
                            for (; ; length++) {
                                current = current.plus(dir);
                                if (!correct(current)) break;
                                if (get(current) != startSign) break;
                            }
                            if (length > max) max = length;

                        }
                    }
                }
                return max;

            }
        }


        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int y = height - 1; y >= 0; y--) {
                for (int x = 0; x < width; x++) {
                    Sign sign = get(x, y);
                    if (sign == null) {
                        sb.append("- ");
                        continue;
                    }
                   sb = sb.append(Sign.StringBuilder(sign));
                }
                sb.append("\n");
            }
            return sb.toString();
        }


        public static void main(String args[]) {
            Board demo = new Board(4, 4);
            demo.addNought(0, 0);
            demo.addCross(0, 1);
            demo.addCross(0, 2);
            demo.addNought(0, 3);
            demo.addNought(1, 0);
            demo.addCross(1, 1);
            demo.addCross(1, 2);
            demo.addCross(1, 3);
            demo.addCross(2, 0);
            demo.addCross(2, 1);
            demo.addNought(2, 2);
            demo.addCross(2, 3);
            demo.addCross(3, 0);
            demo.addNought(3, 1);
            demo.addCross(3, 2);
            demo.addNought(3, 3);


            System.out.println(demo.toString());
            System.out.println(demo.maxInRow());
            System.out.println(demo.maxInRowNought());
            System.out.println(demo.maxInRowCross());


        }


    }
}


