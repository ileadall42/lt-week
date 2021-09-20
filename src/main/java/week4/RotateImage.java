package week4;

public class RotateImage {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,1},{0,1}};
        int[][] target = new int[][]{{1,1},{1,0}};
        System.out.println(findRotation(arr, target));

    }

    //旋转矩阵的常用公式 https://www.cnblogs.com/chrischennx/p/4009376.html
    public static boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++){
            if (checkEqual(mat, target)){
                return true;
            }
            rotate(mat);
        }
        return false;
    }

    private static boolean checkEqual(int[][] mat, int[][] target){
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat.length; j++){
                if (mat[i][j] != target[i][j]){
                    return false;
                }
            }
        }
        return true;
    }



    private static void rotate(int[][] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        int tr = 0;
        int tc = 0;
        int dr = arr.length - 1;
        int dc = arr[0].length - 1;
        while (tr <= dr && tc <= dc) {
            rotateEdge(arr, tr++, tc++, dr--, dc--);
        }
    }

    private static void rotateEdge(int[][] arr, int tr, int tc, int dr, int dc) {
        int t = dc - tc;
        int temp;
        for (int i = 0; i < t; i++) {
            temp = arr[tr][tc + i];
            arr[tr][tc + i] = arr[dr - i][tc];
            arr[dr - i][tc] = arr[dr][dc - i];
            arr[dr][dc - i] = arr[tr + i][dc];
            arr[tr + i][dc] = temp;
        }
    }
}
