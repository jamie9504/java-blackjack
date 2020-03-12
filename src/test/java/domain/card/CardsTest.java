package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;

    @BeforeEach
    void resetVariable() {
        cards = new Cards();
    }

    @DisplayName("같은 카드 추가시 예외 발생")
    @Test
    void add() {
        Card card = new Card(Type.DIAMOND, Symbol.ACE);
        cards.add(card);
        assertThatThrownBy(() -> cards.add(card)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 숫자 합계")
    @Test
    void getSum() {
        cards.add(new Card(Type.DIAMOND, Symbol.TWO));
        cards.add(new Card(Type.DIAMOND, Symbol.THREE));
        cards.add(new Card(Type.DIAMOND, Symbol.ACE));
        cards.add(new Card(Type.CLUB, Symbol.JACK));

        assertThat(cards.getScore()).isEqualTo(16);
    }

    @DisplayName("카드가 21을 넘는지 확인")
    @Test
    void isOverBlackJack() {
        cards.add(new Card(Type.DIAMOND, Symbol.TWO));
        cards.add(new Card(Type.DIAMOND, Symbol.THREE));
        cards.add(new Card(Type.DIAMOND, Symbol.JACK));
        assertThat(cards.isBust()).isFalse();

        cards.add(new Card(Type.CLUB, Symbol.JACK));
        assertThat(cards.isBust()).isTrue();
    }

    @DisplayName("카드가 21인지 확인")
    @Test
    void isBlackJack() {
        Cards cards = new Cards();
        cards.add(new Card(Type.DIAMOND, Symbol.TWO));
        cards.add(new Card(Type.DIAMOND, Symbol.THREE));
        cards.add(new Card(Type.DIAMOND, Symbol.JACK));
        assertThat(cards.isBlackJack()).isFalse();

        cards.add(new Card(Type.CLUB, Symbol.SIX));
        assertThat(cards.isBlackJack()).isTrue();
    }
}