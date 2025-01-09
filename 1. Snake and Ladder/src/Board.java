
import java.util.HashMap;
import java.util.Map;

public class Board {

    private Dice dice;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    private Map<Integer, Integer> playersCurrentPosition;
    private int boardSize;

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public void setSnakes(Map<Integer, Integer> snakes) {
        this.snakes = snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public void setLadders(Map<Integer, Integer> ladders) {
        this.ladders = ladders;
    }

    public Map<Integer, Integer> getPlayersCurrentPosition() {
        return playersCurrentPosition;
    }

    public void setPlayersCurrentPosition(Map<Integer, Integer> playersCurrentPosition) {
        this.playersCurrentPosition = playersCurrentPosition;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public Board(Dice dice, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, int boardSize) {
        this.dice = dice;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playersCurrentPosition = new HashMap<>();
        this.boardSize = boardSize;
    }
}
