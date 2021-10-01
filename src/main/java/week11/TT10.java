package week11;

import java.util.Arrays;

public class TT10 {
    public static void main(String[] args) {
        TT10 tt10 = new TT10();
        int[] arr = {3, 1, 5, 6, 4, 2};
        int[] ans = tt10.longestObstacleCourseAtEachPosition(arr);
        System.out.println("the ans is: " + Arrays.toString(ans));
    }

    public int[] longestObstacleCourseAtEachPosition2(int[] obstacles) {
        int[] dp = new int[obstacles.length];
        Arrays.fill(dp, 1);
        //未包含i自身的
        for (int i = 0; i < dp.length; i++) {
            //从前搜索，0..j i 找到i比他大，直接加一，不比他大则取
            for (int j = 0; j < i; j++) {
                if (obstacles[j] <= obstacles[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] dp = new int[n];
        int[] ans = new int[n];
        int endp = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[endp] = obstacles[i];
                endp++;
                ans[i] = endp;
            } else {
                int l = 0;
                int r = endp;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (obstacles[i] >= dp[mid]) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                dp[l] = obstacles[i];
                if (l == endp) {
                    endp += 1;
                }
                //答案是二分找第一个比nums[i]大的下标
                dp[l] = obstacles[i];
                if (l == endp) {
                    endp += 1;
                }
                ans[i] = l + 1;
            }
        }
        return ans;
    }


}
