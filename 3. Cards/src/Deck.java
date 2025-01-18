
import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> cards;

    public Deck() {
        this.cards = new Stack<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.push(new Card(suit, rank));
            }
        }
    }

    public void suffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No more cards in the deck.");
        }
        return cards.pop();
    }
}
