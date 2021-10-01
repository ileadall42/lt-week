package week11;

import java.math.BigDecimal;

public class TT13 {

    public static void main(String[] args) {
        int[] arr = {1,1,100000};
        double hour = 2.01;
        TT13 tt13 = new TT13();
        int ans = tt13.minSpeedOnTime(arr, hour);
        System.out.println("the ans is :" + ans);
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int l = 1;
        int r = (int)1e7 + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(dist, hour, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l == (1e7 + 1)) {
            return -1;
        }
        return l;
    }

    private boolean check(int[] dist, double hour, int speed) {
        double cnt = 0.0;
        // 对除了最后一个站点以外的时间进行向上取整累加
        for (int i = 0; i < dist.length - 1; ++i) {
            // 除法的向上取整
            cnt += (dist[i] + speed - 1) / speed;
        }
        // 加上最后一个站点所需的时间
        cnt += (double) dist[dist.length - 1] / speed;
        return cnt <= hour;
    }

}
