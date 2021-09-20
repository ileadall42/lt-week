package week9;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TT9 {

    public static void main(String[] args) {
        TT9 tt8 = new TT9();
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
        updateMemo(m, n);
        this.ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Set<String> visited = new LinkedHashSet<>();
                visited.add(getMemoKey(i, j));
                this.ans[i][j] = dp(i, j, visited);
            }

        }
        return this.ans;

    }
    private String getMemoKey(int i, int j) {
        return "(" + i + "," + j + ")_";
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

        if (this.mat[i][j] == 0) {
            return 0;
        }
        if (this.memo[i][j] != -1) {
            return this.memo[i][j];
        }

        int ans = 0;
        for (int[] dpoint : this.direction) {
            int dx = dpoint[0] + i;
            int dy = dpoint[1] + j;

            if (dx < 0 || dy < 0 || dy >= this.n || dx >= this.m) {
                continue;
            }

            if (visited.contains(getMemoKey(dx, dy))) {
                continue;
            }
            visited.add(getMemoKey(dx, dy));
            int temp = dp(dx, dy, visited) + 1;
            visited.remove(getMemoKey(dx, dy));
            ans = Math.min(ans, temp);

        }

        this.memo[i][j] = ans;
        return this.memo[i][j];
    }
}
