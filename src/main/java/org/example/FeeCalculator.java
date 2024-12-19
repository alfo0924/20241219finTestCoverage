package org.example;

public class FeeCalculator {
    private final GitHubService gitHubService;

    public FeeCalculator(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public int calculateFee(int grade, int absenceCount, int typingMinutes, int wpm, String gitHubRepo) {
        if (grade < 2) {
            throw new IllegalArgumentException("只有大二以上才能加入社團");
        }

        int baseFee = 500;
        int maxDiscount = 0;

        // 計算曠課折扣
        int absenceDiscount = (absenceCount < 5) ? 50 : 0;
        maxDiscount = Math.max(maxDiscount, absenceDiscount);

        // 計算打字速度折扣
        int wpmDiscount = 0;
        if (wpm > 100) {
            wpmDiscount = 150;
        } else if (wpm > 80) {
            wpmDiscount = 100;
        } else if (wpm > 60) {
            wpmDiscount = 50;
        }
        maxDiscount = Math.max(maxDiscount, wpmDiscount);

        // 計算深蹲打字折扣
        int typingDiscount = 0;
        int requiredMinutesForHighDiscount = (grade == 2) ? 10 : (grade == 3) ? 15 : 20;
        int requiredMinutesForLowDiscount = (grade == 2) ? 5 : (grade == 3) ? 10 : 15;

        if (typingMinutes >= requiredMinutesForHighDiscount) {
            typingDiscount = 200;
        } else if (typingMinutes >= requiredMinutesForLowDiscount) {
            typingDiscount = 100;
        }
        maxDiscount = Math.max(maxDiscount, typingDiscount);

        // 計算程式成癮折扣
        int programDiscount = 0;
        int lines = gitHubService.getLines(gitHubRepo);
        int wmc = gitHubService.getWMC(gitHubRepo);

        if (grade == 2) {
            programDiscount = Math.min((lines / 1000) * 50, 200);
        } else if (grade > 2 && wmc > 50) {
            programDiscount = Math.min((lines / 1000) * 50, 200);
        }
        maxDiscount = Math.max(maxDiscount, programDiscount);

        return baseFee - maxDiscount;
    }
}
