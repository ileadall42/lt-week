package week9;

import java.util.*;

public class TT4 {

    public static void main(String[] args) {
        TT4 tt4 = new TT4();
        int n = 5;
        int[][] arr = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        int k = 3;
        System.out.println("the ans is: " + tt4.numWays(n, arr, k));

    }

    Map<Integer, List<Integer>> transferMap;
    int finalN;
    int[] memo;

    public int numWays(int n, int[][] relation, int k) {
        this.transferMap = new HashMap<>();
        this.memo = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            this.memo[i] = -1;
        }
        for (int i = 0; i < relation.length; i++) {
            int source = relation[i][0];
            int target = relation[i][1];
            if (this.transferMap.get(source) == null) {
                this.transferMap.put(source, new ArrayList<>());
            }
            this.transferMap.get(source).add(target);
        }
        this.finalN = n - 1;
        int ans = dp(0, k, "0");
        //这种就是会有重复 需要剪纸
//        for (Integer source : this.transferMap.keySet()) {
//            ans += dp(source, k, String.valueOf(source));
//        }
        return ans;
    }

    public int numWays1(int n, int[][] relation, int k) {
        //状态压缩后的dp
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            int[] next = new int[n];
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                next[dst] += dp[src];
            }
            dp = next;
        }
        return dp[n - 1];
    }

    public int numWays2(int n, int[][] relation, int k) {
        //状态压缩后的dp
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            int[] next = new int[n];
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                next[dst] += dp[src];
            }
            dp = next;
        }
        return dp[n - 1];
    }

    public int numWays4(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1; // 表示第0轮传递到编号为0的小朋友的总方案数
        for (int round = 1; round <= k; round++) {
            for (int[] r : relation) {
                int source = r[0];
                int target = r[1];
                dp[round][target] += dp[round - 1][source];
            }
        }
        return dp[k][n - 1];
    }

    /**
     * 图的做法
     *
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public int numWays3(int n, int[][] relation, int k) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        while (!queue.isEmpty() && k > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                for (int j = 0; j < relation.length; j++) {
                    if (relation[j][0] == node) {
                        queue.offer(relation[j][1]);
                    }
                }
            }
            k--;
        }

        int count = 0;
        if (k == 0) {
            while (!queue.isEmpty()) {
                if (queue.poll() == n - 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int dp(int source, int k, String path) {
        if (k == 0) {
            if (source == this.finalN) {
                System.out.println("the path is:" + path);
                return 1;
            }
            return 0;
        }

        int ans = 0;
        List<Integer> targetLis = this.transferMap.get(source);
        if (targetLis != null) {
            for (Integer ss : targetLis) {
                ans += dp(ss, k - 1, path + "_" + ss);
            }
        }
        this.memo[k] = ans;

        return ans;
    }
}
