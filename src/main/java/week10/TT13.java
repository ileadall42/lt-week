package week10;

public class TT13 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3};
        TT13 tt13 = new TT13();
        int day = 2;
        int ans = tt13.minTime(arr, day);
        System.out.println("the ans is:" + ans);

    }

    public int minTime(int[] time, int m) {
        if (m >= time.length) {
            return 0;
        }
        int l = 0;
//        int r = Integer.MAX_VALUE;
        int r = 10;

        while (l < r) {
//            int mid = l + (r - l) / 2;
            int mid = (l + r) >> 1;
            boolean checkOk = check(time, m, mid);
            if (checkOk) {
                //说明这个数值可以完成刷题，否则不行
                r = mid;
            } else {
                l = mid + 1;
            }

        }
        return l;
    }


    public boolean check(int[] time, int m, int limit) {
        int cur = 0, sum = 0, max = 0, day = 1; //当前遍历到的题目，当前组的总耗时，当前组的最大耗时，需要的天数
        while (cur < time.length) {
            sum += time[cur];
            max = Math.max(max, time[cur]);
            if (sum - max > limit) { //当前组总耗时减去组内最大耗时仍超出限制，则需要开启额外一天
                day++;
                if (day > m) { //超出总天数m，无法完成分配
                    return false;
                }
                sum = time[cur]; //sum和max更新为新组的值
                max = time[cur];
            }
            cur++;
        }
        return true; //能遍历完所有题目即完成了分配
    }

}
