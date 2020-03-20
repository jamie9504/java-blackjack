package blackjack.domain.card;

import blackjack.util.NullChecker;

public final class Card {

    private final Type type;
    private final Symbol symbol;

    public Card(Type type, Symbol symbol) {
        NullChecker.validateNotNull(type, symbol);
        this.type = type;
        this.symbol = symbol;
    }

    public boolean isAce() {
        return this.symbol == Symbol.ACE;
    }

    public int getValue() {
        return symbol.getValue();
    }

    @Override
    public String toString() {
        return symbol.getName() + type.getName();
    }
}
