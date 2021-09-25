package week11;

import java.util.Arrays;
import java.util.Objects;

public class TT3 {

    public static void main(String[] args) {
        TT3 tt3 = new TT3();
        int[] a = {-2147483648, 1};
        int[] b = {2147483647, 0};
        int ans = tt3.smallestDifference(a, b);
        System.out.println("the ans is: " + ans);
    }
    public int smallestDifference(int[] a, int[] b) {
        //O(n^2) 暴力求解
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < b.length; j++){
                long diff = (long)a[i] - (long)b[j];
                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE){
                    continue;
                }
                ans = Math.min(ans, Math.abs((int)diff));
            }
        }
        return ans;
    }

    public int smallestDifference2(int[] a, int[] b) {
        //O(n^2) 暴力求解
        Arrays.sort(a);
        Arrays.sort(b);

        long mans = Math.abs((long)a[0] - (long)b[0]);
        int l1 = 0;
        int l2 = 0;
        while (l1 < a.length && l2 < b.length){
            long diff = Math.abs((long)a[l1] - (long)b[l2]);
            if (diff < mans){
                mans = diff;
            }
            if (a[l1] - b[l2] == 0){
                return 0;
            }
            //排序之后，双指针，从0开始的
            if (a[l1] - b[l2] < 0){
                l1++;
            }else{
                l2++;
            }
        }

        return (int) mans;
    }
}
