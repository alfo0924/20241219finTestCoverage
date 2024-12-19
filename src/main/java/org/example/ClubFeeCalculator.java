package org.example;

public class ClubFeeCalculator {
    private static final int BASE_FEE = 500;

    public int calculateFee(int grade, int absenceCount, int typingMinutes, int wpm, String gitHubRepo) {
        // 檢查年級限制
        if (grade < 2) {
            throw new IllegalArgumentException("只有大二以上才能加入社團");
        }

        int totalFee = BASE_FEE;

        // 計算曠課折扣
        if (absenceCount < 5) {
            totalFee -= 50;
        }

        // 計算打字速度折扣
        if (wpm > 100) {
            totalFee -= 150;
        } else if (wpm > 80) {
            totalFee -= 100;
        } else if (wpm > 60) {
            totalFee -= 50;
        }

        // 計算深蹲打字持續時間折扣
        int requiredMinutesForHighDiscount;
        int requiredMinutesForLowDiscount;

        switch (grade) {
            case 2:
                requiredMinutesForHighDiscount = 10;
                requiredMinutesForLowDiscount = 5;
                break;
            case 3:
                requiredMinutesForHighDiscount = 15;
                requiredMinutesForLowDiscount = 10;
                break;
            case 4:
                requiredMinutesForHighDiscount = 20;
                requiredMinutesForLowDiscount = 15;
                break;
            default:
                throw new IllegalArgumentException("無效的年級");
        }

        if (typingMinutes >= requiredMinutesForHighDiscount) {
            totalFee -= 200;
        } else if (typingMinutes >= requiredMinutesForLowDiscount) {
            totalFee -= 100;
        }

        // 計算 GitHub 程式碼折扣
        MockGitHubService.GitHubAnalyzer analyzer = new MockGitHubService.GitHubAnalyzer(gitHubRepo);
        int codeLines = analyzer.getCodeLines();
        int wmc = analyzer.getWMC();

        int gitHubDiscount = 0;
        if (grade == 2) {
            gitHubDiscount = (codeLines / 1000) * 50;
        } else {
            if (wmc > 50) {
                gitHubDiscount = (codeLines / 1000) * 50;
            }
        }
        gitHubDiscount = Math.min(gitHubDiscount, 200);
        totalFee -= gitHubDiscount;

        return Math.max(totalFee, 0);
    }
}

