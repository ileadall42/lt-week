package week9;

import java.util.Arrays;

public class TT {

    public static void main(String[] args) {
        TT tt = new TT();
        int[] arr = {1, 5, 11, 5};
        System.out.println("the ans is :" + tt.canPartition(arr));

    }

    public boolean canPartition(int[] arr) {
        if (arr.length == 1) {
            return false;
        }
        int sum = Arrays.stream(arr).sum();
        if ((sum & 1) == 1) {
            return false;
        }
        return dp(arr, 0, sum / 2);
    }

    private boolean dp(int[] arr, int idx, int s) {

        if (s < 0) {
            return false;
        }
        if (s == 0) {
            return true;
        }

        if (idx == arr.length) {
            return false;
        }

        //不可重复选的，就不用for循环dp了，也就是只往前推进 只选一次的
        //可以重复选的就for循环dp
        return dp(arr, idx + 1, s - arr[idx]) || dp(arr, idx + 1, s);
    }
}
