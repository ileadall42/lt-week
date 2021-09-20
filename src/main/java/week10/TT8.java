package week10;

import java.util.Arrays;

public class TT8 {

    public static void main(String[] args) {
        TT8 tt8 = new TT8();
        int[] arr = {332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184};
        int h = 823855818;
        int ans = tt8.minEatingSpeed(arr, h);
        System.out.println("the ans is:" + ans);
    }

    public int minEatingSpeed(int[] piles, int h) {
        //1. 最高的K = max(piles)    时间足够的情况下，最小的应该是1 故1-max(piles) 做搜索
        //2. 查看是否能吃完，能吃完则记录当前k，不能吃完则往回搜索
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt() + 1;
        int ans = r;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (canFinish(mid, piles, h)) {
                r = mid;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
    //超级耗时
//    private boolean canFinish(int mid, int[] piles, int h) {
//        int i = 0;
//        while (h > 0 && i < piles.length) {
//            piles[i] = piles[i] - mid;
//            if (piles[i] <= 0) {
//                i++;
//            }
//            h--;
//        }
//        return i == piles.length;
//    }

    /**
     * 要选用耗时小的arr作为遍历条件
     * @param speed
     * @param piles
     * @param h
     * @return
     */
    private boolean canFinish(int speed, int[] piles, int h) {
        int needHour = 0;
        for (int shit : piles) {
            //可以向上取整  只要有小数位
//            cnt += Math.ceil((double)p / speed);
            if (shit % speed == 0) {
                needHour += shit / speed;
            } else {
                needHour += shit / speed + 1;
            }
        }
        return needHour <= h;
    }
}
