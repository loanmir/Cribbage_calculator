import java.util.Objects;

public class Card {
    public final Rank rank;
    public final Suit suit;

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank(){
        return rank;
    }

    public Suit getSuit(){
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false; // Simplified type check
        Card card = (Card) o;
        return rank.equals(card.rank) && suit.equals(card.suit); // Use equals for Rank and Suit
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return rank + " of " + suit; // Optional: for better readability
    }
}
