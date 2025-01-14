
import java.util.List;

public class Client {

    public static void main(String[] args) throws Exception {

        int boardSize = 5;
        List<Player> players = List.of(
                new Player("Dharmaraj", Symbol.O),
                new Player("Mohankumar", Symbol.X),
                new Player("Santhanakumar", Symbol.A)
        );

        Board board = new Board(boardSize);

        Game game = new Game(board, players);

        while (true) {
            boolean gameEnd = game.play();
            if (gameEnd) {
                break;
            }
        }
    }
}
