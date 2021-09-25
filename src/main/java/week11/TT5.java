package week11;

import java.util.HashSet;
import java.util.LinkedList;

public class TT5 {

    public static void main(String[] args) {
        TT5 tt5 = new TT5();
        int[][] arr = {{1, 2, 2, 5}, {3, 8, 4, 5}, {5, 3, 5, 5}};
        int ans = tt5.minimumEffortPath2(arr);
        System.out.println("the ans is: " + ans);

    }

    int row;
    int col;
    int[][] matrix;
    int ans;
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minimumEffortPath2(int[][] heights) {
        this.row = heights.length;
        this.col = heights[0].length;
        this.matrix = heights;
        this.ans = 100;
        String path = getPathKey(0, 0);
        dfs(0, 0, 0, path);
        return this.ans;
    }

    private String getPathKey(int x, int y) {
        return "(" + x + "," + y + ") -> ";
    }

    private void dfs(int i, int j, int curMaxDiff, String path) {
        if (i == this.row - 1 && j == this.col - 1) {
            this.ans = Math.min(this.ans, curMaxDiff);
            System.out.println("cur path:" + path);
            System.out.println("cur max：" + curMaxDiff);
            return;
        }
        if (curMaxDiff >= this.ans) {
            return;
        }

        for (int[] direction : this.directions) {
            int dx = direction[0] + i;
            int dy = direction[1] + j;
            String pathKey = getPathKey(dx, dy);
            if (path.contains(pathKey)) {
                continue;
            }
            if (dx > -1 && dy > -1 && dx < row && dy < col) {
                int diff = Math.abs(this.matrix[dx][dy] - this.matrix[i][j]);
                dfs(dx, dy, Math.max(curMaxDiff, diff), path + pathKey);
            }
        }
    }


    public int minimumEffortPath(int[][] heights) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        LinkedList<int[]> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        int row = heights.length;
        int col = heights[0].length;

        int[] startPoint = {0, 0};
        queue.add(startPoint);
        visited.add(getPointKey(startPoint));
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int[] headPoint = queue.poll();
                for (int[] direction : directions) {
                    int dx = direction[0] + headPoint[0];
                    int dy = direction[1] + headPoint[1];
                    int[] nextPoint = {dx, dy};
                    String pointKey = getPointKey(nextPoint);
                    if (dx > -1 && dy > -1 && dx < row && dy < col) {
                        if (dx == row - 1 && dy == col - 1) {
                            //答案到了要看路径信息
                        }


                        if (!visited.contains(pointKey)) {
                            visited.add(pointKey);
                            queue.add(nextPoint);
                        }
                    }
                }
            }
        }
        return 0;
    }

    private String getPointKey(int[] point) {
        int x = point[0];
        int y = point[1];
        return "(" + x + "," + y + ")";
    }
}
