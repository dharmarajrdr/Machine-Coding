
import java.util.List;

public class Game {

    private ScoringRule scoringRule;
    private Deck deck;
    private GameStatus gameStatus = GameStatus.NOT_STARTED;
    private List<Player> players;
    Card jokerCard;

    public Game(List<Player> players, ScoringRule scoringRule) {
        this.players = players;
        this.scoringRule = scoringRule;
        this.deck = new Deck();
    }

    public void start() {
        this.gameStatus = GameStatus.IN_PROGRESS;
        deck.suffle();
        distributeCards();
        this.jokerCard = deck.dealCard();
    }

    public void distributeCards() {
        for (int i = 0; i < 13; i++) {
            for (Player player : players) {
                Card card = deck.dealCard();
                player.receiveCards(card);
            }
        }
    }

    public Player declareWinner() {
        if (gameStatus != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is not in progress.");
        }
        Player winner = scoringRule.determineWinner(players);
        gameStatus = GameStatus.COMPLETED;
        return winner;
    }
}
