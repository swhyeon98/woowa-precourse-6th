package baseball;

import java.util.List;

public class Umpire {

    private static final int INIT_NUMBER = 0;
    private static final int MAX_NUMBER_SIZE = 3;

    public int getStrikeCount(List<Integer> computerNumber, List<Integer> userNumber) {
        int strike = INIT_NUMBER;
        for (int i = INIT_NUMBER; i < MAX_NUMBER_SIZE; i++) {
            if (computerNumber.get(i) == userNumber.get(i)) {
                strike++;
            }
        }
        return strike;
    }

    public int getBallCount(List<Integer> computerNumber, List<Integer> userNumber) {
        int ball = INIT_NUMBER;
        for (int i = INIT_NUMBER; i < MAX_NUMBER_SIZE; i++) {
            if (computerNumber.contains(userNumber.get(i))) {
                ball++;
            }
        }
        return ball - getStrikeCount(computerNumber, userNumber);
    }

    public String getBaseballResult(int strikeCount, int ballCount) {
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
}
