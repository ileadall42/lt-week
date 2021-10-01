package week11;

import java.util.Arrays;

import com.sun.org.apache.xerces.internal.dom.AttrNSImpl;

public class TT12 {

    public static void main(String[] args) {

    }

    public int minWastedSpace2(int[] packages, int[][] boxes) {
        int ans = Integer.MAX_VALUE;
        Arrays.sort(packages);

        long[] preSum = new long[packages.length + 1];
        for (int i = 0; i < packages.length; ++i) {
            preSum[i + 1] = preSum[i] + packages[i];
        }

        for (int i = 0; i < boxes.length; i++) {
            if (packages[packages.length - 1] > boxes[i][boxes[i].length - 1]) {
                continue;
            }
            Arrays.sort(boxes[i]);
            //箱子有序了，包裹有序了，二分查找第一个箱子可以装多少包裹
            //指向
            long waste = 0;
            int pp = 0;
            int pb = 0;
            while (pp < packages.length && pb < boxes[i].length) {
                int ppS = pp;
                int r = packages.length;
                while (pp < r) {
                    int mid = pp + (r - pp) / 2;
                    if (packages[mid] <= boxes[i][pb]) {
                        pp = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                //for (int pq = ppS; pq < pp; pq++) {
                //    //waste += (int)((boxes[i][pb] - packages[pq]) % (1e9 + 7));
                //
                //}
                //waste += (boxes[i][pb] * (pp - ppS) - (preSum[pp] - preSum[ppS])) % (1e9 + 7);
                pb++;
            }

            if (pp == packages.length) {
                ans = Math.min((int)waste, ans);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        Arrays.sort(packages);
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + packages[i];
        }

        long res = Long.MAX_VALUE;
        for (int[] box : boxes) {
            Arrays.sort(box);
            if (packages[n - 1] > box[box.length - 1]) {
                continue;
            }
            long t = 0;
            int low = 0;
            for (int b : box) {
                int idx = searchRight(packages, b, low);
                // 这里需要手动转 long
                t += ((idx - low) * (long)b - (preSum[idx] - preSum[low]));
                low = idx;
            }
            res = Math.min(res, t);
        }
        return res == Long.MAX_VALUE ? -1 : (int)(res % 1000000007);
    }

    private int searchRight(int[] packages, int target, int low) {
        int high = packages.length;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (packages[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
