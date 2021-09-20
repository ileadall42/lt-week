package week10;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class TT2 {

    public static void main(String[] args) {
        String as = "[[7,8,9],[9,7,6],[7,2,3]]";
        int[][] arr = getArrFromString(as);
        System.out.println(Arrays.deepToString(arr));
        TT2 tt1 = new TT2();
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
    int maxLen = 1;
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        /*memory[i][j]表示以(i,j)作为起始位置最长的递增路径的长度
         * 初始化为-1表示未计算的状态*/
        int[][] memory = new int[row][column];
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                memory[i][j] = -1;
            }
        }
        for(int i = 0; i< row; i++){
            for(int j = 0; j< column; j++) {
                dfs(matrix,memory,i,j);
            }
        }
        return maxLen;
    }
    public int dfs(int[][] matrix,int[][] memory, int i, int j) {
        //优先从memory中取出答案，若不存在则计算，计算完成后填入memory中
        if(memory[i][j] != -1)
            return memory[i][j];
        else{
            int up=0,right =0,down=0,left =0;
            if(i-1>=0 && matrix[i-1][j]>matrix[i][j]) {
                up = dfs(matrix, memory, i - 1, j);
            }
            if(j+1<matrix[0].length && matrix[i][j+1]>matrix[i][j]) {
                right = dfs(matrix,memory,i,j+1);
            }
            if(i+1<matrix.length && matrix[i+1][j] > matrix[i][j]) {
                down = dfs(matrix,memory,i+1,j);
            }
            if(j-1>=0 && matrix[i][j-1] > matrix[i][j]) {
                left = dfs(matrix,memory,i,j-1);
            }
            //从当前结点向四个方向都扩展完，之后取四个方向的最大值 + 1作为当前结点为起始的最长递增路劲
            int max = Math.max(up, right);
            max = Math.max(max,down);
            max = Math.max(max,left)+1;
            memory[i][j] = max;
            maxLen = Math.max(memory[i][j],maxLen);
            return memory[i][j];
        }
    }

}
