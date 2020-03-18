package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import blackjack.domain.Name;
import blackjack.domain.Names;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void create() {
        assertThatThrownBy(() -> new Participants(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
    }

    @DisplayName("플레이어 최대인원 초과시 예외 발생")
    @Test
    void validMaximumPlayerCount() {
        assertThatThrownBy(() -> new Participants(new Names("jamie1,jamie2,jamie3,jamie4,jamie5,jamie6,jamie7,jamie8")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참여 인원");
    }

    @DisplayName("플레이어들의 이름들을 받아오는 테스트")
    @Test
    void getPlayerNames() {
        Participants participants = new Participants(new Names(("jamie,ravie")));
        assertThat(participants.getPlayerNames().get(0)).isEqualTo("jamie");
        assertThat(participants.getPlayerNames().get(1)).isEqualTo("ravie");
    }
}
