package week10;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class TT1 {

    public static void main(String[] args) {
        String as = "[[7,8,9],[9,7,6],[7,2,3]]";
        int[][] arr = getArrFromString(as);
        System.out.println(Arrays.deepToString(arr));
        TT1 tt1 = new TT1();
        int ans = tt1.longestIncreasingPath(arr);
        System.out.println("the ans is:" + ans);
    }

    private static int[][] getArrFromString(String as) {
        String[] split = as.split("],");
        String[] split2 = split[0].split(",");
        int[][] arr = new int[split.length][split2.length];
        for (int i = 0; i < split.length; i++) {
            String[] replace = split[i].replace("[", "")
                    .replace("]", "").split(",");
            for (int j = 0; j < replace.length; j++) {
                Integer num = Integer.valueOf(replace[j]);
                arr[i][j] = num;
            }

        }
        return arr;
    }

    int ans;
    int m;
    int n;
    int[][] mm;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] memo;

    public int longestIncreasingPath(int[][] matrix) {
        this.ans = 1;
        int m = matrix.length;
        int n = matrix[0].length;
        this.m = m;
        this.n = n;
        this.mm = matrix;
        this.memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.memo[i][j] = -1;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int dpAns = dp(i, j) + 1;
                ans = Math.max(ans, dpAns);
            }
        }
        return ans;
    }

    /**
     * 返回的是到这个点为止，的长度是多少
     * BFS 不可行，因为同一次层数中 会交叉并吞没，比如
     * 7,8,9
     * 9,7,6
     * 7,2,3
     * 这个记忆顺序有问题
     *
     * @param i
     * @param j
     * @return
     */
    public int dp(int i, int j) {
        //任意一点的最长递增路径长度，选最大的那个点

        if (this.memo[i][j] != -1) {
            return this.memo[i][j];
        }
        int temp = 0;
        for (int[] direction : this.directions) {
            int dx = i + direction[0];
            int dy = j + direction[1];

            if (dx < 0 || dy < 0 || dy >= this.n || dx >= this.m) {
                continue;
            }
            if (this.mm[i][j] < this.mm[dx][dy]) {
                int pointAns = dp(dx, dy) + 1;
                //用记忆化搜索解决了 重复访问的问题
                if (pointAns > temp) {
                    temp = pointAns;
                }
            }
        }
        if (this.memo[i][j] == -1) {
            this.memo[i][j] = temp;
        }

        return temp;
    }

    private String getMemoKey(int i, int j) {
        return "(" + i + "," + j + ")_[" + this.mm[i][j] + "] ";
    }
}
