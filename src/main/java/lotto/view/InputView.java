package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lotto.grobal.LottoConstants.NUMBER_PATTERN;
import static lotto.grobal.LottoConstants.LOTTO_PRICE;
import static lotto.grobal.LottoConstants.MIN_LOTTO_NUMBER;
import static lotto.grobal.LottoConstants.MAX_LOTTO_NUMBER;

public class InputView {

    public String input(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    public int inputPurchaseAmount() {
        String input = input("구입금액을 입력해 주세요.");
        validateIsNumber(input);
        int purchaseAmount = Integer.parseInt(input);
        validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }

    public List<Integer> inputWinningNumber() {
        String input = input("\n당첨 번호를 입력해 주세요.");
        return parseAndValidateNumbers(input);
    }

    public int inputBonusNumber() {
        String input = input("\n보너스 번호를 입력해 주세요.");
        validateIsNumber(input);
        int bonusNumber = Integer.parseInt(input);
        validateSingleNumberRange(bonusNumber);
        return bonusNumber;
    }

    private List<Integer> parseAndValidateNumbers(String input) {
        List<Integer> numbers = Stream.of(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return numbers;
    }

    private void validateIsNumber(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요.");
        }
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        validateIsPositiveNumber(purchaseAmount);
        validateUnit(purchaseAmount);
    }

    private void validateIsPositiveNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("[ERROR] 양수만 입력해주세요.");
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    private void validateSingleNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}
