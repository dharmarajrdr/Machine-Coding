
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Range {

    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Game {

    private Board board;
    private List<Player> players;
    private int currentTurn;
    private Scanner scanner;
    private Range range;
    private Player winner;
    private int cellsFilled;

    public Game(Board board, List<Player> players) throws Exception {
        this.board = board;
        this.players = players;
        scanner = new Scanner(System.in);
        this.currentTurn = 0;
        this.range = new Range(0, (board.getBoardSize() * board.getBoardSize()) - 1);
        this.cellsFilled = 0;
        this.gameInfo();
        this.validatePlayers();
    }

    private void validatePlayers() throws Exception {
        Set<Symbol> uniqueSymbols = new HashSet<>();
        for (Player player : players) {
            if (uniqueSymbols.contains(player.getSymbol())) {
                throw new Exception("Each player must use a unique symbol to play the game.");
            }
            uniqueSymbols.add(player.getSymbol());
        }
    }

    private void gameInfo() {
        for (Player player : players) {
            System.out.println(player.getSymbol() + " : " + player.getName());
        }
    }

    private boolean isUserInputWithinRange(int userInput) {
        return this.range.getStart() <= userInput && userInput <= this.range.getEnd();
    }

    private void updateCurrentTurn() {
        this.currentTurn = (this.currentTurn + 1) % this.players.size();
    }

    private Player getPlayerHavingSymbol(Symbol symbol) {
        for (Player player : players) {
            if (player.getSymbol() == symbol) {
                return player;
            }
        }
        return null;
    }

    private void setWinnerIn(int i, int j) {
        this.winner = getPlayerHavingSymbol(Symbol.fromChar(this.board.getCells()[i][j]));
    }

    private boolean hasSameSymbolsInAnyRow() {
        int N = this.board.getBoardSize();
        char[][] cells = this.board.getCells();
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (cells[i][j] == '\u0000' || cells[i][j] != cells[i][j - 1]) {
                    break;
                }
                if (j == N - 1) {
                    setWinnerIn(i, j);
                    return true;    // all the symbols are same in the row 'i'
                }
            }
        }
        return false;
    }

    private boolean hasSameSymbolsInAnyColumn() {
        int N = this.board.getBoardSize();
        char[][] cells = this.board.getCells();
        for (int j = 0; j < N; j++) {
            for (int i = 1; i < N; i++) {
                if (cells[i][j] == '\u0000' || cells[i][j] != cells[i - 1][j]) {
                    break;
                }
                if (i == N - 1) {
                    setWinnerIn(i, j);
                    return true;    // all the symbols are same in the row 'i'
                }
            }
        }
        return false;
    }

    private boolean hasSameSymbolsInDiagonals() {
        int N = this.board.getBoardSize();
        char[][] cells = this.board.getCells();

        // top-left to bottom-right
        for (int i = 1; i < N; i++) {
            int row = i;
            int col = i;
            if (cells[row][col] == '\u0000' || cells[row - 1][col - 1] != cells[row][col]) {
                break;
            }
            if (i == N - 1) {
                setWinnerIn(i, i);
                return true;
            }
        }

        // top-right to bottom-left
        for (int i = 1; i < N; i++) {
            int row = i;
            int col = N - 1 - i;
            if (cells[row][col] == '\u0000' || cells[row - 1][col + 1] != cells[row][col]) {
                break;
            }
            if (i == N - 1) {
                setWinnerIn(row, col);
                return true;
            }
        }

        return false;
    }

    private boolean isGameOver() {
        return hasSameSymbolsInAnyRow() || hasSameSymbolsInAnyColumn() || hasSameSymbolsInDiagonals() || this.cellsFilled == (this.board.getBoardSize() * this.board.getBoardSize());
    }

    private boolean cellHasSymbol(Map<String, Integer> rowAndColumn) {
        int row = rowAndColumn.get("ROW");
        int column = rowAndColumn.get("COLUMN");
        return this.board.getCells()[row][column] != '\u0000';
    }

    private void updateCell(Map<String, Integer> rowAndColumn) {
        Symbol symbol = this.players.get(currentTurn).getSymbol();
        int row = rowAndColumn.get("ROW");
        int column = rowAndColumn.get("COLUMN");
        this.board.getCells()[row][column] = symbol.toString().charAt(0);
        this.cellsFilled++;
    }

    public boolean play() {
        Player currentPlayer = this.players.get(currentTurn);
        String message = "Enter a number from " + range.getStart() + "-" + range.getEnd() + ": ";
        System.out.print(currentPlayer.getName() + "'s turn. " + message);
        int userInput = this.scanner.nextInt();
        if (isUserInputWithinRange(userInput)) {
            Map<String, Integer> rowAndColumn = this.board.getRowAndColumn(userInput);
            if (cellHasSymbol(rowAndColumn)) {
                System.err.println("The selected position is already occupied.");
                return false;
            }

            this.updateCell(rowAndColumn);

            if (isGameOver()) {
                this.board.print();
                System.out.print("Game over!!! ");
                if (this.winner != null) {
                    System.out.println(this.winner.getName() + "(" + this.winner.getSymbol() + ") won the game.");
                } else {
                    System.out.println("It is a draw.");
                }
                return true;
            }

            this.updateCurrentTurn();
            this.board.print();

        } else {
            System.err.println("Invalid number.");
        }
        return false;
    }
}
