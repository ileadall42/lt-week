package week11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class TT8 {

    public static void main(String[] args) {
        TT8 tt8 = new TT8();
//        int[][] arr = {{0, 1, 1, 0, 0}, {0, 1, 1, 0, 0}, {0, 1, 0, 0, 1}, {1, 1, 1, 1, 0}, {1, 0, 0, 1, 0}};
//        int[][] hopeAns = {{0, 1, 1, 0, 0}, {0, 1, 1, 0, 0}, {0, 1, 0, 0, 1}, {1, 1, 1, 1, 0}, {1, 0, 0, 1, 0}};
        int tTime = 10;
        for (int pi = 0; pi < tTime; pi++) {
            int[][] arr = getRandomMat(10,10);
            int[][] hopeAns = tt8.updateMatrix(arr);
            int[][] ans2 = tt8.updateMatrix2(arr);
            for (int i = 0; i < ans2.length; i++) {
                for (int j = 0; j < ans2[0].length; j++) {
                    if (ans2[i][j] != hopeAns[i][j]) {
                        System.out.println("it's not the ans:");

                        System.out.println("the arr is:");
                        for (int[] an : arr) {
                            System.out.println(Arrays.toString(an));
                        }

                        System.out.println("the hope ans  is:");
                        for (int[] an : hopeAns) {
                            System.out.println(Arrays.toString(an));
                        }

                        System.out.println("the ans is:");
                        for (int[] an : ans2) {
                            System.out.println(Arrays.toString(an));
                        }
                        break;
                    }
                }
            }
        }

    }

    private static int[][] getRandomMat(int r, int c) {
        int[][] arr = new int[r][c];
        Random random = new Random(10);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i][j] = random.nextInt() % 2 == 0 ? 0 : 1;
            }
        }
        arr[0][0] = 0;
        return arr;
    }


    public int[][] updateMatrix(int[][] mat) {

        int row = mat.length;
        int col = mat[0].length;
        int[][] ans = new int[row][col];
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] != 0) {
                    ans[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                LinkedList<Integer> queue = new LinkedList<>();
                boolean[] visited = new boolean[row * col];
                queue.add(i * col + j);
                visited[i * col + j] = true;
                int step = 0;
                boolean zeroFind = false;
                while (!queue.isEmpty()) {
                    int count = queue.size();
                    if (zeroFind) {
                        break;
                    }
                    for (int k = 0; k < count; k++) {
                        if (zeroFind) {
                            break;
                        }
                        Integer rcn = queue.poll();
                        int r = rcn / col;
                        int c = rcn % col;
                        for (int[] direction : directions) {
                            int dx = r + direction[0];
                            int dy = c + direction[1];
                            if (dx > -1 && dy > -1 && dx < row && dy < col && !visited[dx * col + dy]) {
                                visited[dx * col + dy] = true;
                                if (mat[dx][dy] == 0) {
                                    zeroFind = true;
                                    break;
                                } else {
                                    queue.add(dx * col + dy);
                                    visited[dx * col + dy] = true;
                                }
                            }
                        }
                    }
                    step++;
                }
                ans[i][j] = step;
            }
        }
        return ans;
    }

    public int[][] updateMatrix2(int[][] mat) {

        int row = mat.length;
        int col = mat[0].length;
        int[][] ans = new int[row][col];
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) {
                    //记忆优化
                    queue.add(i * col + j);
                } else {
                    //初始化非1的为-1
                    ans[i][j] = -1;
                }
            }
        }

        while (!queue.isEmpty()) {
            Integer rcn = queue.poll();
            int r = rcn / col;
            int c = rcn % col;
            for (int[] direction : directions) {
                int dx = r + direction[0];
                int dy = c + direction[1];
                //把所有0旁边的染色成功了，再去染色其他人 多源 第一层是所有的0
                if (dx > -1 && dy > -1 && dx < row && dy < col && ans[dx][dy] == -1) {
                    ans[dx][dy] = ans[r][c] + 1;
                    queue.add(dx * col + dy);
                }
            }

        }
        return ans;
    }
}
