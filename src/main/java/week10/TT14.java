package week10;

import java.util.Arrays;

public class TT14 {

    public static void main(String[] args) {
        int[] arr = {7, 7, 7, 7, 12, 7, 7};
        TT14 tt14 = new TT14();
        int m = 2;
        int k = 3;
        int ans = tt14.minDays(arr, m, k);
        System.out.println("the ans is:" + ans);
        System.out.println(7 / 2);
    }

    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length / m < k) {
            return -1;
        }
        int l = 1;
        int maxNum = Arrays.stream(bloomDay).max().getAsInt();
        int r = maxNum;
        int ans = r + 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (canMakeFlower(bloomDay, mid, m, k)) {
                r = mid - 1;
                ans = Math.min(mid, ans);
            } else {
                l = mid + 1;
            }

            /**
             * 把答案留在区间内，且返回的是left left = right 会终止
             * while (left < right) {
             *             int mid = left + right >>> 1;
             *             if (!flowerable(bloomDay, m, k, mid)) {
             *                 left = mid + 1;
             *             } else {
             *                 right = mid;
             *             }
             *         }
             *         return left
             */
        }
        return ans == maxNum + 1 ? -1 : ans;
    }

    private boolean canMakeFlower(int[] bloomDay, int mid, int m, int k) {
        boolean[] canTake = new boolean[bloomDay.length];
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= mid) {
                canTake[i] = true;
            }
        }
        int flowerNum = 0;
        int i = 0;
        while (i < canTake.length) {
            int cus = k;
            while (cus > 0 && i < canTake.length && canTake[i]) {
                i++;
                cus--;
            }
            if (cus == 0) {
                flowerNum++;
            } else {
                i++;
            }
        }
        return flowerNum >= m;
    }
}
