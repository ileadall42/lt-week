package week9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TT3 {

    public static void main(String[] args) {
        TT3 tt3 = new TT3();
        int[] arr = {1, 2, 3, 4, 5};
        int target = 10;
        System.out.println("the ans is :" + tt3.combinationSum4(arr, target));
        System.out.println("the ans is :" + tt3.combinationSum1(arr, target));

    }

    public int combinationSum1(int[] nums, int target) {
        if (target == 0) {
            return 0;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    HashMap<Integer, Integer> memo;

    public int combinationSum4(int[] nums, int target) {
        this.memo = new HashMap<>();
        return dp(nums, target, 0, "");
    }


    public int dp(int[] nums, int cus, int idx, String path) {


        if (cus < 0) {
            this.memo.put(cus, 0);
            return 0;
        }

        if (cus == 0) {
            this.memo.put(cus, 1);
            return 1;
        }
        if (idx == nums.length) {
            this.memo.put(cus, 0);
            return 0;
        }
        int ans = 0;

        if (this.memo.get(cus) != null) {
            System.out.println("hit map!" + cus + "-ans is:" + this.memo.get(cus));
            return this.memo.get(cus);
        }

        for (int i = 0; i < nums.length; i++) {
            //一直选自己的，利用减到达终值
            ans += dp(nums, cus - nums[i], i, path + "_" + nums[i]);
        }

        this.memo.put(cus, ans);
        return ans;

    }

}
