package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;

public class LottoView {

    public String input(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    public String inputPurchaseAmount() {
        System.out.println();
        return input("구입금액을 입력해 주세요.");
    }

    public String inputWinningNumber() {
        System.out.println();
        return input("당첨 번호를 입력해 주세요.");

    }

    public String inputBonusNumber() {
        System.out.println();
        return input("보너스 번호를 입력해 주세요.");
    }

    public void printLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto -> System.out.println(lotto));
    }

    public void printResult(Map<Rank, Long> results, double profitRate) {
        System.out.println();
        System.out.println("당첨 통계\n---------");
        printRankResult(results, Rank.FIFTH, "3개 일치 (5,000원) - %d개\n");
        printRankResult(results, Rank.FOURTH, "4개 일치 (50,000원) - %d개\n");
        printRankResult(results, Rank.THIRD, "5개 일치 (1,500,000원) - %d개\n");
        printRankResult(results, Rank.SECOND, "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n");
        printRankResult(results, Rank.FIRST, "6개 일치 (2,000,000,000원) - %d개\n");
        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitRate);
    }

    private void printRankResult(Map<Rank, Long> results, Rank rank, String format) {
        System.out.printf(format, results.getOrDefault(rank, 0L));
    }

    public void printError(String message) {
        System.out.println();
        System.out.println(message);
    }
}
