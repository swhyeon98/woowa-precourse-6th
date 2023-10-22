package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        List<Integer> computer = new ArrayList<>();
        List<Integer> numbers;
        int ballCount;
        int strikeCount;

        System.out.println("숫자 야구 게임을 시작합니다.");

        while (true) {
            generateRandomNumber(computer);

            String input = printInputNumber();

            numbers = convertStringToIntList(input);

            validateDuplicate(numbers.size());

            strikeCount = getStrikeCount(computer, numbers);
            ballCount = getBallCount(computer, numbers);

            if (strikeCount > 0 && ballCount > 0) {
                System.out.println(ballCount + "볼 " + strikeCount + "스트라이크");
                continue;
            }

            if (strikeCount > 0) {
                System.out.println(strikeCount + "스트라이크");
            }

            if (ballCount > 0) {
                System.out.println(ballCount + "볼");
            }

            if (strikeCount == 0 && ballCount == 0) {
                System.out.println("낫싱");
            }

            if (strikeCount == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                String userChoice = Console.readLine();

                if (userChoice.equals("1")) {
                    computer = new ArrayList<>();
                    continue;
                }
                if (userChoice.equals("2")) {
                    return;
                }

                throw new IllegalArgumentException("1 또는 2 중 하나만 선택하세요.");
            }
        }
    }

    private static List<Integer> convertStringToIntList(String input) {
        List<Integer> numbers;
        numbers = Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .distinct()
                .collect(Collectors.toList());
        return numbers;
    }

    private static String printInputNumber() {
        System.out.print("숫자를 입력해주세요 : ");
        String input = Console.readLine();
        return input;
    }

    private static void generateRandomNumber(List<Integer> computer) {
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    private static void validateDuplicate(int size) {
        if (size < 3) {
            throw new IllegalArgumentException("서로 다른 3자리의 수를 입력해주세요.");
        }
    }

    private static int getStrikeCount(List<Integer> computerNumber, List<Integer> userNumber) {
        int strike = 0;
        for (int i = 0; i < 3; i++) {
            if (computerNumber.get(i) == userNumber.get(i)) {
                strike++;
            }
        }
        return strike;
    }

    private static int getBallCount(List<Integer> computerNumber, List<Integer> userNumber) {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < 3; i++) {
            if (computerNumber.get(i) == userNumber.get(i)) {
                strike++;
            }
            if (computerNumber.contains(userNumber.get(i))) {
                ball++;
            }
        }
        return ball - strike;
    }
}
