package week10;

public class TT6 {
    int pick;

    public static void main(String[] args) {
        TT6 tt6 = new TT6();
        tt6.pick = 6;
        System.out.println(tt6.guessNumber(10));

    }

    public int guessNumber(int n) {
        int l = 1;
        int r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int ans = this.guess(mid);
            if (ans == 0) {
                return mid;
            } else if (ans == 1) {
                //mid选小了
                l = mid + 1;
            } else if (ans == -1) {
                //mid选大了
                r = mid - 1;
            }
        }
        return -1;
    }

    int guess(int num) {
        if (num > this.pick) {
            return -1;
        }
        if (num < this.pick) {
            return 1;
        }
        return 0;
    }
}
