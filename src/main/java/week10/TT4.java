package week10;

/**
 * 自上而下的，不需要返回的结果，但是也没办法记忆
 */
public class TT4 {

    public static void main(String[] args) {
        TT4 tt3 = new TT4();
        int[] coins = {2, 5, 10, 1};
        int amount = 27;
        int result = tt3.coinChange(coins, amount);
        System.out.println("the ans is:" + result);

    }

    int[] coins;
    int[] memo;

    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        this.memo = new int[amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            this.memo[i] = -2;
        }
        int ans = dp(amount, 0, "");

        return ans;
    }


    public int dp(int resAmount, int startIdx, String visited) {
        //表示当前到了startIdx 选择的时候，还有resAmount需要抉择 需要的最少数目
        //递归也要选对含义
        System.out.println("visited:[" + visited + " ]restAmount: " + resAmount);

        if (resAmount < 0) {
            return -1;
        }
        if (resAmount == 0) {
            return 0;
        }

        if (this.memo[resAmount] != -2) {
            return this.memo[resAmount];
        }

        int curAns = -1;

        for (int i = startIdx; i < this.coins.length; i++) {
            String k = visited + "_" + this.coins[startIdx];
            //选择硬币 可下一个 可当前 所以直接当前就可以了
            int pickAns = dp(resAmount - this.coins[startIdx], startIdx, k);
            if (pickAns >= 0) {
                curAns = pickAns + 1;
            }

            //不选硬币，直接下一个
            int nonPickAns = dp(resAmount, startIdx + 1, visited);
            if (nonPickAns >= 0) {
                if (curAns == -1) {
                    curAns = nonPickAns;
                }
                curAns = Math.min(nonPickAns, curAns);
            }
        }

        this.memo[resAmount] = curAns;

        return curAns;

    }

}
