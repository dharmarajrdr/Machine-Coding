
import java.util.List;

public class Game {

    private Board board;
    private int currentTurn;
    private List<Player> players;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.currentTurn = 0;
        this.players = players;
        for (int i = 0; i < players.size(); i++) {
            this.board.getPlayersCurrentPosition().put(i, 0);
        }
    }

    public boolean play() {

        String currentPlayer = players.get(currentTurn).getName();
        System.out.print(currentPlayer + "'s turn => ");
        int currentPositionOfCurrentPlayer = this.board.getPlayersCurrentPosition().get(this.currentTurn);
        int diceValue = this.board.getDice().rollDice();

        System.out.print("Rolled " + diceValue + ". ");

        int newPosition = currentPositionOfCurrentPlayer;

        if (currentPositionOfCurrentPlayer + diceValue <= this.board.getBoardSize()) {
            System.out.print("Moved from " + newPosition);
            newPosition += diceValue;   // ensure that we are moving within the boundary
            System.out.print(" to " + newPosition + ". ");

            if (newPosition == this.board.getBoardSize()) {
                System.out.println("\n" + currentPlayer + " won the Game...");
                return true;
            }

            // Check whether we are landing under the ladder
            if (this.board.getLadders().containsKey(newPosition)) {
                System.out.print("Got the ladder at " + newPosition);
                newPosition = this.board.getLadders().get(newPosition);
                System.out.print(" to " + newPosition + ". ");
            }

            // Check whether we are landing on snake's mouth
            if (this.board.getSnakes().containsKey(newPosition)) {
                System.out.print("Bitten by snake at " + newPosition);
                newPosition = this.board.getSnakes().get(newPosition);
                System.out.print(" moved to " + newPosition);
            }

        } else {
            System.out.print("Better luck next time.");
        }

        // Line break
        System.out.println();

        this.board.getPlayersCurrentPosition().put(this.currentTurn, newPosition);
        nextTurn();

        return false;
    }

    private void nextTurn() {
        this.currentTurn = (this.currentTurn + 1) % this.players.size();
    }
}
