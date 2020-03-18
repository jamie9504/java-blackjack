package blackjack.view;

import blackjack.domain.result.CardsResult;
import blackjack.domain.result.Outcome;
import blackjack.domain.result.ParticipantsResult;
import blackjack.domain.gambler.Participants;
import blackjack.domain.gambler.User;
import java.util.Map;

public class OutputView {

    public static void printCardDistribution(Participants participants) {
        System.out.println();
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다."
            , participants.getDealerName()
            , String.join(", ", participants.getPlayerNames()));
        System.out.println();
    }

    public static void printUsersCards(Participants participants) {
        User dealer = participants.getDealer();
        System.out.printf("%s: %s", dealer.getName(), String.join(",", dealer.getFirstCardInfo()));
        System.out.println();
        for (User player : participants.getPlayers()) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    public static void printPlayerCards(User player) {
        System.out.println(getUserCards(player));
    }

    private static String getUserCards(User user) {
        return String.format("%s: %s", user.getName()
            , String.join(", ", user.getCardsInfos()));
    }

    public static void printDealerOneMoreCard(User dealer) {
        System.out.println();
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println();
    }

    public static void printUsersCardsAndScore(Participants participants) {
        System.out.println();
        for (User user : participants.getParticipants()) {
            printUserCardsAndScore(user);
        }
    }

    public static void printUserCardsAndScore(User user) {
        CardsResult userCardsResult = user.getScore();
        System.out.printf("%s - 결과: %s", getUserCards(user), userCardsResult.getResult());
        System.out.println();
    }

    public static void printFinalResult(Participants participants,
        ParticipantsResult participantsResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerFinalResult(participants.getDealer(),
            participantsResult.getDealerResultsNoZero());
        printPlayerFinalResult(participantsResult.getPlayersResult());
    }

    private static void printDealerFinalResult(User dealer, Map<Outcome, Integer> gameResult) {
        System.out.printf("%s: ", dealer.getName());
        for (Outcome outcome : gameResult.keySet()) {
            System.out.print(gameResult.get(outcome) + outcome.getConverseName() + " ");
        }
        System.out.println();
    }

    private static void printPlayerFinalResult(Map<User, Outcome> playersResult) {
        for (User player : playersResult.keySet()) {
            Outcome outcome = playersResult.get(player);
            System.out.printf("%s: %s", player.getName(), outcome.getName());
            System.out.println();
        }
    }

    public static void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
