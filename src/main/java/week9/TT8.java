package week9;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TT8 {

    public static void main(String[] args) {
        TT8 tt8 = new TT8();
        int[][] mat = {{0, 0, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 1, 0, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 0, 0, 0, 1, 1}, {1, 0, 1, 0, 1, 1, 1, 0, 1, 1}, {0, 0, 1, 1, 1, 0, 1, 1, 1, 1}, {1, 0, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 1, 0, 0, 1, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 0, 1}, {1, 0, 1, 1, 1, 0, 1, 1, 1, 0}};
        int[][] res = tt8.updateMatrix(mat);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
        int[][] result = {{0, 0, 1, 0, 1, 2, 1, 0, 1, 2}, {1, 1, 2, 1, 0, 1, 1, 1, 2, 3}, {2, 1, 2, 1, 1, 0, 0, 0, 1, 2}, {1, 0, 1, 0, 1, 1, 1, 0, 1, 2}, {0, 0, 1, 1, 1, 0, 1, 1, 2, 3}, {1, 0, 1, 2, 1, 1, 1, 2, 1, 2}, {1, 1, 1, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 1, 0, 0, 1, 2}, {1, 1, 1, 0, 1, 1, 0, 1, 0, 1}, {1, 0, 1, 1, 1, 0, 1, 2, 1, 0}};

        System.out.println("the right ans is:");
        for (int[] re : result) {
            System.out.println(Arrays.toString(re));
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (result[i][j] != res[i][j]) {
                    System.out.println("the ans is not eaqual! (" + i + "," + j + ")");
                }
            }
        }
//        System.out.println("the ans is ：" + Arrays.deepToString(tt8.updateMatrix(res)));


    }

    int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] mat;
    int m;
    int n;
    int[][] memo;
    int[][] ans;


    public int[][] updateMatrix(int[][] mat) {
        this.mat = mat;
        int m = mat.length;
        int n = mat[0].length;
        this.m = m;
        this.n = n;


        this.ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //这个每次memo就很费时todo ,不每次更新的话，缓存结果有误，路径搜索问题
                //bfs 会每个数开始 不是0 就+1，到0 为之
                updateMemo(m, n);
                if (mat[i][j] == 0) {
                    ans[i][j] = 0;
                    continue;
                }
                this.ans[i][j] = dp(i, j, new HashSet<>());
            }

        }
        return this.ans;

    }

    private void updateMemo(int m, int n) {
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        this.memo = memo;
    }

    public int dp(int i, int j, Set<String> visited) {

        String key = "(" + i + "," + j + ")-";

        if (i < 0 || i >= m) {
            return Integer.MAX_VALUE;
        }

        if (j < 0 || j >= n) {
            return Integer.MAX_VALUE;
        }

        if (this.mat[i][j] == 0) {
            this.memo[i][j] = 0;
            return 0;
        }
        if (this.memo[i][j] != -1) {
            return this.memo[i][j];
        }
        if (this.ans[i][j] != 0) {
            return this.ans[i][j];
        }

        int ans = Integer.MAX_VALUE;
        visited.add(key);

        for (int[] dpoint : this.direction) {
            int dx = dpoint[0] + i;
            int dy = dpoint[1] + j;
            String deltaKey = "(" + dx + "," + dy + ")-";
            if (visited.contains(deltaKey)) {
                continue;

            }
            int temp = dp(dx, dy, visited);
            ans = Math.min(ans, temp);

        }

        visited.remove(key);
        if (ans != Integer.MAX_VALUE) {
            this.memo[i][j] = ans + 1;
            return ans + 1;
        } else {
            return ans;
        }
    }

}
