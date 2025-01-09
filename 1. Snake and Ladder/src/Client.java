
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {

    public static void main(String[] args) {

        Dice dice = new Dice(2);

        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(30, 3);
        snakes.put(88, 11);

        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(22, 79);
        ladders.put(43, 91);

        int boardSize = 100;

        List<Player> players = List.of(
                new Player(1, "Dharma", "dharmaraj.rd@gmail.com"),
                new Player(2, "Mohan", "mohan.rmk@gmail.com"),
                new Player(3, "Parimalam", "parimalam.rp@gmail.com"),
                new Player(4, "Rathinavel", "rathinavel.d@gmail.com")
        );

        Board board = new Board(dice, snakes, ladders, boardSize);

        Game game = new Game(board, players);
        while (true) {
            boolean gameEnd = game.play();
            if (gameEnd) {
                break;
            }
        }
    }
}
