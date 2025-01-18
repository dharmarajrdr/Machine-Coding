
import java.util.List;

public interface ScoringRule {

    Player determineWinner(List<Player> players);
}
