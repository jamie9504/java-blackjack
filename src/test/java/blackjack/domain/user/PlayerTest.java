package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private CardDeck cardDeck;
    private Player player;

    @BeforeEach
    void resetVariable() {
        cardDeck = new CardDeck();
        player = new Player("Jamie&Ravie");
    }

    @DisplayName("이름 비었거나 Null일 때, 에러 발생")
    @Test
    void validName() {
        assertThatThrownBy(() -> new Player(null)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("공백이어선");
        assertThatThrownBy(() -> new Player("")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("공백이어선");
    }

    @DisplayName("드로우 가능여부 - 가능")
    @Test
    void canDrawCard() {
        player.drawCard(cardDeck);
        assertThat(player.canDrawCard()).isTrue();

        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        assertThat(player.canDrawCard()).isFalse();
    }
}
