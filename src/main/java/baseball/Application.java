package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Application {
    private static final int INIT_NUMBER = 0;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;
    private static final int MAX_NUMBER_SIZE = 3;
    private static final int WINNING_STRIKE_COUNT = 3;

    private static final String RESTART_KEYWORD = "1";
    private static final String EXIT_KEYWORD = "2";

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[1-9]+$");

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        while (true) {
            playGame();
            if (!restartOrExitGame()) {
                break;
            }
        }
    }

    private static void playGame() {
        List<Integer> computer = new ArrayList<>();
        List<Integer> numbers;
        int ballCount;
        int strikeCount;

        System.out.println("숫자 야구 게임을 시작합니다.");

        while (true) {
            generateRandomNumber(computer);
            System.out.println(computer);
            String input = getUserInput();

            numbers = convertStringToIntList(input);

            strikeCount = getStrikeCount(computer, numbers);
            ballCount = getBallCount(computer, numbers);

            printBallAndStrikeCount(strikeCount, ballCount);

            if (isGameOver(strikeCount)) {
                handleGameOver(computer);
                return;
            }
        }
    }

    private static List<Integer> convertStringToIntList(String input) {
        List<Integer> numbers;
        numbers = Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return numbers;
    }

    private static String getUserInput() {
        System.out.print("숫자를 입력해주세요 : ");
        String input = Console.readLine();
        validateInput(input);
        return input;
    }

    private static void validateInput(String input) {
        validateIsNumber(input);
        validateNumberSize(input);
        validateNoDuplicate(input);
    }

    private static void validateIsNumber(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }

    private static void validateNumberSize(String input) {
        if (input.length() != MAX_NUMBER_SIZE) {
            throw new IllegalArgumentException(MAX_NUMBER_SIZE + "자리의 수를 입력해주세요.");
        }
    }

    private static void validateNoDuplicate(String input) {
        long distinctCount = input.chars()
                .distinct()
                .count();
        if (distinctCount < input.length()) {
            throw new IllegalArgumentException("서로 다른 3자리의 수를 입력해주세요.");
        }
    }

    private static void generateRandomNumber(List<Integer> computer) {
        while (computer.size() < MAX_NUMBER_SIZE) {
            int randomNumber = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    private static int getStrikeCount(List<Integer> computerNumber, List<Integer> userNumber) {
        int strike = INIT_NUMBER;
        for (int i = INIT_NUMBER; i < MAX_NUMBER_SIZE; i++) {
            if (computerNumber.get(i) == userNumber.get(i)) {
                strike++;
            }
        }
        return strike;
    }

    private static int getBallCount(List<Integer> computerNumber, List<Integer> userNumber) {
        int ball = INIT_NUMBER;
        for (int i = INIT_NUMBER; i < MAX_NUMBER_SIZE; i++) {
            if (computerNumber.contains(userNumber.get(i))) {
                ball++;
            }
        }
        return ball - getStrikeCount(computerNumber, userNumber);
    }

    private static String getBaseballResult(int strikeCount, int ballCount) {
        if (strikeCount > INIT_NUMBER && ballCount > INIT_NUMBER) {
            return ballCount + "볼 " + strikeCount + "스트라이크";
        }

        if (strikeCount > INIT_NUMBER) {
            return strikeCount + "스트라이크";
        }

        if (ballCount > INIT_NUMBER) {
            return ballCount + "볼";
        }

        return "낫싱";
    }

    private static void printBallAndStrikeCount(int strikeCount, int ballCount) {
        System.out.println(getBaseballResult(strikeCount, ballCount));
    }

    private static boolean isGameOver(int strikeCount) {
        return strikeCount == WINNING_STRIKE_COUNT;
    }

    private static void handleGameOver(List<Integer> computer) {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        computer.clear();
    }

    private static boolean restartOrExitGame() {
        System.out.println("게임을 새로 시작하려면 " + RESTART_KEYWORD + ", 종료하려면 " + EXIT_KEYWORD + "를 입력하세요.");
        String userChoice = Console.readLine();

        if (RESTART_KEYWORD.equals(userChoice)) return true;
        if (EXIT_KEYWORD.equals(userChoice)) return false;

        throw new IllegalArgumentException(RESTART_KEYWORD + " 또는 " + EXIT_KEYWORD + " 중 하나만 선택하세요.");
    }
}
