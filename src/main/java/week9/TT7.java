package week9;

public class TT7 {
    int m;
    int n;
    int[][] memo;

    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;
        this.memo = new int[m][n];
        for (int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                this.memo[i][j] = -1;
            }
        }
        return dp(0, 0);
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = m - 1; i > -1; i--) {
            for (int j = n - 1; j > -1; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = 1;
                }
                if (i + 1 < m) {
                    dp[i][j] += dp[i + 1][j];
                }
                if (j + 1 < n) {
                    dp[i][j] += dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public int dp(int i, int j) {

        if (i == this.m) {
            return 0;
        }
        if (j == this.n) {
            return 0;
        }
        //base case
        if ((i == this.m - 1) && (j == this.n - 1)){
            return 1;
        }
        //记忆
        if (this.memo[i][j] != -1){
            return this.memo[i][j];
        }
        //状态推进转移，
        int ans = dp(i+1, j) + dp(i, j+1);
        this.memo[i][j] = ans;
        return ans;

    }
}
