package week10;

public class TT9 {

    public static void main(String[] args) {
        TT9 tt9 = new TT9();
        int x = 8;
        System.out.println("the ans is:" + tt9.mySqrt(x));
        System.out.println(1 + 2 + 1 >> 2);

    }

    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        if (x <= 1) {
            return x;
        }
        //当右闭时 l + (r -l) / 2 和 （l + r） / 2 和 l + (r - l)>>1 或 (l+r) >> 1 偶数时都是选下中位，奇数都是该点
        //当右开时 l + (r -l) / 2 和 （l + r） / 2 和 l + (r - l)>>1 或 (l+r) >> 1 偶数时都是选上中位，奇数就是该点

        //(l+r+l)
        int res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            //就没见过这种写法
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                //和小，值在右边，但是自身也可能是答案被截取的，记录一下
                res = Math.max(res, (int) mid);
                l = mid + 1;
            } else if (mid > x / mid) {
                //平方大，值在左边，肯定不包括mid
                r = mid - 1;
            }
        }

        return res;
    }
}
