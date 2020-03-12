package domain;

import java.util.ArrayList;
import java.util.List;
import util.BlackJackRule;

public class Cards {

    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "Duplicate card exception.";
    private List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getValue();
        }
        return addAceWeight(score);
    }

    private int addAceWeight(int score) {
        for (Card card : cards) {
            if (BlackJackRule.isBust(score + Symbol.getAceWeight())) {
                break;
            }
            if (card.isAce()) {
                score += Symbol.getAceWeight();
            }
        }
        return score;
    }

    public boolean isBust() {
        return BlackJackRule.isBust(getScore());
    }

    public boolean isBlackJack() {
        return BlackJackRule.isBlackJack(getScore());
    }

    public List<Card> getCards() {
        return cards;
    }
}
