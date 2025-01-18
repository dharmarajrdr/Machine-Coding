
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultScoringRule implements ScoringRule {

    private boolean isWinner(Player player) {
        Map<Suit, List<Integer>> suitGroup = new HashMap<>();
        List<Card> cards = player.getHand();
        for (Card card : cards) {
            Suit suit = card.getSuit();
            Integer cardRank = card.getCardRank();
            if (!suitGroup.containsKey(suit)) {
                suitGroup.put(suit, new ArrayList<>());
            }
            suitGroup.get(suit).add(cardRank);
        }
        // TODO: Implement winner rule
        /**
         * {
         * "heart": [2,4,7],
         * "spade": [1]
         * }
         */
        return false;
    }

    @Override
    public Player determineWinner(List<Player> players) {
        for (Player player : players) {
            if (this.isWinner(player)) {
                return player;
            }
        }
        return null;
    }

}
