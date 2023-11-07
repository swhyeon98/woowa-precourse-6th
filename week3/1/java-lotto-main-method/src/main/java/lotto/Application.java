package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        List<Lotto> lottos = new ArrayList<>();

        System.out.println("구입금액을 입력해 주세요.");
        int price = Integer.parseInt(Console.readLine());

        int ticketCount = price / 1000;
        System.out.println("\n" + ticketCount + "개를 구매했습니다.");

        for (int i = 0; i < ticketCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }

        lottos.forEach(lotto -> System.out.println(lotto));

        System.out.println("\n당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        List<Integer> winningLottoNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("\n보너스 번호를 입력해 주세요.");
        input = Console.readLine();
        int bonusNumber = Integer.parseInt(input);

        int th5 = 0;
        int th4 = 0;
        int th3 = 0;
        int th2 = 0;
        int th1 = 0;

        for (Lotto lotto : lottos) {
            int winningCount = (int) lotto.getNumbers()
                    .stream()
                    .filter(winningLottoNumbers::contains)
                    .count();

            if (winningCount == 5 && lotto.getNumbers().contains(bonusNumber)) {
                th2++;
                continue;
            }

            switch (winningCount) {
                case 3:
                    th5++;
                    continue;
                case 4:
                    th4++;
                    continue;
                case 5:
                    th3++;
                    continue;
                case 7:
                    th1++;
            }
        }

        long totalPrize = 0;
        totalPrize += th1 * 2_000_000_000;
        totalPrize += th2 * 30_000_000;
        totalPrize += th3 * 1_500_000;
        totalPrize += th4 * 50_000;
        totalPrize += th5 * 5_000;

        System.out.println(totalPrize);

        double profit = ((double) totalPrize / (ticketCount * 1000)) * 100;

        System.out.println("\n당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (5,000원) - %d개\n", th5);
        System.out.printf("4개 일치 (50,000원) - %d개\n", th4);
        System.out.printf("5개 일치 (1,500,000원) - %d개\n", th3);
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", th2);
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", th1);
        System.out.printf("총 수익률은 %.1f%%입니다.", profit);

    }
}
