package week4;

import java.util.Arrays;

public class MinOperationForString {
    public static void main(String[] args) {
        String s = "111000";
        System.out.println(minFlips(s));
    }

    public static int minFlips(String s) {
        String newString = s + s;
        int n = s.length();
        char[] arr = newString.toCharArray();
        int minOp = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - n; i++) {
            int subResult = findSubSetMinOp(Arrays.copyOfRange(arr, i, i + n));
            minOp = Math.min(minOp, subResult);
        }
        return minOp;
    }

    private static int findSubSetMinOp(char[] subArr) {
        int cnt = 0;
        char[] f = new char[]{'0', '1'};
        //换言之奇数位不是0的，或者奇数位不是1的，都要变，看变那个方便点
        for (int i = 0; i < subArr.length; i++) {
            if (subArr[i] != f[i % 2]) {
                cnt++;
            }
        }

        return Math.min(cnt, subArr.length - cnt);
    }


}
