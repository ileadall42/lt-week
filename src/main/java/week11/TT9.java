package week11;

import java.util.LinkedList;

public class TT9 {

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        TT9 tt9 = new TT9();
        int ans = tt9.findCircleNum(arr);
        System.out.println("the ans is: " + ans);

    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(i);
            visited[i] = true;
            ans += 1;
            while (!queue.isEmpty()) {
                Integer friend = queue.poll();
                for (int j = 0; j < n; j++) {
                    //friend 与 j 是好友
                    if (isConnected[friend][j] == 1 && !visited[j]) {
                        visited[j] = true;
                        queue.add(j);
                    }
                }
            }
        }

        return ans;

    }
}
