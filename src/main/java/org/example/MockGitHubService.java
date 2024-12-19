package org.example;

public class MockGitHubService implements GitHubService {
    private final int lines;
    private final int wmc;

    public MockGitHubService(int lines, int wmc) {
        this.lines = lines;
        this.wmc = wmc;
    }

    @Override
    public int getLines(String repo) {
        return lines;
    }

    @Override
    public int getWMC(String repo) {
        return wmc;
    }

    // 假設的 GitHub 分析類別
    static class GitHubAnalyzer {
        private String repoUrl;

        public GitHubAnalyzer(String repoUrl) {
            this.repoUrl = repoUrl;
        }

        public int getCodeLines() {
            // 實際應用中需要實作 GitHub API 來獲取程式碼行數
            return 0;
        }

        public int getWMC() {
            // 實際應用中需要實作計算 WMC (Weighted Methods per Class)
            return 0;
        }
    }
}
