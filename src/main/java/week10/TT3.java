package week10;

/**
 * 自上而下的，不需要返回的结果，但是也没办法记忆
 */
public class TT3 {

    public static void main(String[] args) {
        TT3 tt3 = new TT3();
        int[] coins = {1, 2, 5};
        int amount = 11;
        int result = tt3.coinChange(coins, amount);
        System.out.println("the ans is:" + result);

    }

    int[] coins;
    int ans;

    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        this.ans = Integer.MAX_VALUE;

        dp(0, amount, 0);

        if (this.ans == Integer.MAX_VALUE) {
            return -1;
        }

        return ans;
    }


    public void dp(int curNum, int resAmount, int startIdx) {
        if (resAmount == 0) {
            this.ans = Math.min(this.ans, curNum);
            return;
        }
        if (resAmount < 0) {
            return;
        }

        //重点在于选和不选 一直往后走，由于不是要求组合种类，所以和一定是一样的
        for (int i = startIdx; i < this.coins.length; i++) {
            //选这个硬币
            dp(curNum + 1, resAmount - this.coins[i], i);

            //不选这个硬币
            dp(curNum, resAmount, i + 1);
        }

    }

    /**
     * 动态规划的base case 要做好解 同时要注意边界 没有计算过的不能选
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        //表示 amount值最少需要多少个硬币
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i < coin) {
                    continue;
                }

                if (dp[i - coin] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);  //选硬币（钱减少、硬币数加多）  和不选硬币之间的最小

            }
        }
        return dp[amount];
    }
}
