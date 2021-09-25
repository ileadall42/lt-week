package week11;

import java.util.*;

public class TT4 {

    public static void main(String[] args) {
        TT4 tt4 = new TT4();
        int[][] cc = {{1, 2}, {2, 1}, {3, 3}, {2, 2}, {1, 1}, {1, 3}, {2, 3}, {3, 2}, {3, 1}};
        int row = 3;
        int col = 3;
        int ans = tt4.latestDayToCross(row, col, cc);
        System.out.println("the ans is :" + ans);
    }

    int[][] cells;
    int row;
    int col;

    public int latestDayToCross(int row, int col, int[][] cells) {
        //二分搜索i ，结果是
        //l 是天数 - 1 下标
        //找到第一个不可行的l 也就是答案
        this.cells = cells;
        this.row = row;
        this.col = col;
        int l = 0;
        int r = cells.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (checkOk(mid)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l == 0 ? 1 : l;
    }

    private boolean checkOk(int mid) {
        int[][] matrix = new int[this.row][this.col];
//        //初始化矩阵
        for (int i = 0; i < mid + 1; i++) {
            int pr = this.cells[i][0] - 1;
            int pc = this.cells[i][1] - 1;
            matrix[pr][pc] = 1;
        }

        return canAchieve(matrix);
    }

    private boolean canAchieve(int[][] matrix) {
        //O(M*N) BFS 空间复杂度最大搜索完了所有
        //去重的
        HashSet<String> visited = new HashSet<>();
        //队列
        LinkedList<int[]> queue = new LinkedList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //把第一个元素加到队列
        for (int i = 0; i < this.col; i++) {
            if (matrix[0][i] != 1) {
                queue.add(new int[]{0, i});
                //标识已访问过
                visited.add(getVisitedKey(0, i));
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            //广度有优先每层
            for (int k = 0; k < size; k++) {

                int[] point = queue.poll();
                int curX = point[0];
                int curY = point[1];
                if (matrix[curX][curY] == 1) {
                    continue;
                }
                if (curX == this.row - 1) {
                    return true;
                }
                for (int[] direction : directions) {
                    int dx = direction[0] + curX;
                    int dy = direction[1] + curY;
                    if (dx > -1 && dx < this.row && dy > -1 && dy < this.col) {
                        int[] newPoint = {dx, dy};
                        if (visited.contains(getVisitedKey(dx, dy))) {

                            continue;
                        }
                        visited.add(getVisitedKey(dx, dy));
                        queue.add(newPoint);
                    }
                }
            }
        }
        return false;
    }

    private String getVisitedKey(int i, int j) {
        return "(" + i + "," + +j + ")_";
    }


//    /**
//     * 失败的case
//     * @param target
//     * @return
//     */
//    private boolean checkOk(int target) {
//        int[][] matrix = new int[this.row][this.col];
//        //初始化矩阵
//        boolean[][] dp = new boolean[this.row][this.col];
//        for (int i = 0; i < target + 1; i++) {
//            int pr = this.cells[i][0] - 1;
//            int pc = this.cells[i][1] - 1;
//            matrix[pr][pc] = 1;
//            dp[pr][pc] = false;
//            if (target == 33) {
//                System.out.println("cord: " + Arrays.toString(this.cells[i]));
//            }
//        }
//        if (target == 33) {
//            System.out.println("the arr: \n");
//            for (int[] a1 : matrix) {
//
//            System.out.println(Arrays.toString(a1));
//            }
//        }
//
//        for (int j = 0; j < this.col; j++) {
//            if (matrix[0][j] == 0) {
//                dp[0][j] = true;
//            }
//        }
//        for (int i = 1; i < this.row; i++) {
//            //第一轮
//            for (int j = 0; j < this.col; j++) {
//                if (matrix[i][j] == 1) {
//                    continue;
//                }
//                //染色失败，往上走的时候失败了，想复杂了
//
//                if (dp[i - 1][j]) {
//                    dp[i][j] = true;
//
//                    int lidx = j;
//                    while (lidx - 1 > -1 && matrix[i][lidx - 1] != 1) {
//                        dp[i][lidx - 1] = dp[i][j];
//                        lidx--;
//                    }
//
//                    int ridx = j;
//                    while (ridx + 1 < this.col && matrix[i][ridx + 1] != 1) {
//                        dp[i][ridx + 1] = dp[i][j];
//                        ridx++;
//                    }
//                }
//
//            }
//
//
//        }
//        for (int j = 0; j < this.col; j++) {
//            if (dp[this.row - 1][j]) {
//                return true;
//            }
//        }
//        return false;
//    }

}
