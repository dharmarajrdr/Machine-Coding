
import java.util.HashMap;
import java.util.Map;

public class Board {

    private int boardSize;
    private char[][] cells;

    public int getBoardSize() {
        return boardSize;
    }

    public char[][] getCells() {
        return cells;
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.cells = new char[boardSize][boardSize];
    }

    public void print() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (j > 0) {
                    System.out.print(" | ");
                }
                char symbol = this.cells[i][j];
                System.out.print(symbol != '\u0000' ? symbol : ' ');
            }
            System.out.println();
        }
    }

    public Map<String, Integer> getRowAndColumn(int number) {
        Map<String, Integer> map = new HashMap<>();
        int row = number / boardSize;
        int column = number % boardSize;
        map.put("ROW", row);
        map.put("COLUMN", column);
        return map;
    }
}
