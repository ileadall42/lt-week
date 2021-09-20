package week9;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class TT2 {

    public static void main(String[] args) {
        TT2 tt = new TT2();
        for (int i = 0; i < 20; i++) {
            int[] arr = getRandomArraySizeOf(12);
//        int[] arr = {41, 32, 33, 33, 26, 86, 63, 96, 31, 84, 45, 65, 7, 74, 97, 81, 35, 81, 30, 2, 25, 18, 81};

            boolean ans1 = tt.canPartition(arr);
            boolean ans2 = tt.canPartition2(arr);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr));
                System.out.println("result not equal!");
            }
        }


        int[] arr = {5, 61, 4, 97, 13, 57, 40, 65, 79, 49};


        boolean ans1 = tt.canPartition(arr);
        boolean ans2 = tt.canPartition2(arr);
        if (ans1 != ans2) {
            System.out.println(Arrays.toString(arr));
            System.out.println("result not equal!");
        }


    }

    private static int[] getRandomArraySizeOf(int size) {
        int[] arr = new int[size];
        Random random = new Random();//默认构造方法
        for (int j = 0; j < size; j++) {
            int i = random.nextInt() % 100;
            arr[j] = i > 0 ? i : -i;
        }
        return arr;
    }

    HashMap<String, Boolean> memo;

    public boolean canPartition(int[] arr) {
        this.memo = new HashMap<>();
        if (arr.length == 1) {
            return false;
        }
        int sum = Arrays.stream(arr).sum();
        if ((sum & 1) == 1) {
            return false;
        }
        return dp(arr, 0, sum / 2);
    }


    private boolean dp(int[] arr, int idx, int res) {

        String key = idx + "_" + res;
        //对应正向校验
        if (res < 0) {
            this.memo.put(key, false);
            return false;
        }
        //对应正向初始化
        if (res == 0) {
            this.memo.put(key, true);
            return true;
        }
        //对应需要idx
        if (idx == arr.length) {
            this.memo.put(key, false);
            return false;
        }

        if (this.memo.get(key) != null) {
            return this.memo.get(key);
        }
        //实际已经回溯了 这就dp
        boolean result = dp(arr, idx + 1, res - arr[idx]) || dp(arr, idx + 1, res);
        //如果for循环则不记忆 且不过滤 重复选择的话 那就是回溯 只可以选一次 与只可以选多次的区别


        //记忆
        this.memo.put(key, result);
        //选与不选的回溯 可以不用记忆
        return result;
    }

    public boolean canPartition2(int[] arr) {
        this.memo = new HashMap<>();
        if (arr.length == 1) {
            return false;
        }
        int sum = Arrays.stream(arr).sum();
        if ((sum & 1) == 1) {
            return false;
        }
        sum = sum / 2;
        boolean[][] dp = new boolean[arr.length + 1][sum + 1];
        //初始化
        for (int i = 0; i < arr.length + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < arr.length + 1; i++) {
            for (int cus = 1; cus < sum + 1; cus++) {
                if (cus - arr[i - 1] < 0) {
                    dp[i][cus] = dp[i - 1][cus];
                } else {
                    dp[i][cus] = dp[i - 1][cus] || dp[i - 1][cus - arr[i - 1]];
                }
            }
        }
        return dp[arr.length][sum];
    }


}
