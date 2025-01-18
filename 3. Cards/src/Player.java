
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void receiveCards(Card card) {
        this.hand.add(card);
    }

    public List<Card> showCards() {
        return this.hand;
    }

    public int calculateScore() {
        return this.hand.stream().mapToInt(Card::getCardRank).sum();
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getHand() {
        return this.hand;
    }
}
