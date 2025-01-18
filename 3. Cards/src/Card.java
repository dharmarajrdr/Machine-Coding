
public class Card {

    private Rank rank;
    private Suit suit;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public int getCardRank() {
        return this.rank.ordinal();
    }
}
