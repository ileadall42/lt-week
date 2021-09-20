package week10;

public class TT15 {
    public static void main(String[] args) {
        int n = 10;
        TT15 tt15 = new TT15();
        int ans = tt15.getMoneyAmount2(n);
        System.out.println("the ans is:" + ans);
    }

    public int getMoneyAmount(int n) {
        /**
         * 拥有的钱
         */
        int l = 1;
        int r = ((n - 1) * n) / 2;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (moneyEnough(n, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int getMoneyAmount2(int n) {
        /**
         * 拥有的钱
         */
        int l = 1;
        int r = 10;
        int money = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            money += mid;
            l = mid + 1;
        }
        return money;
    }

    private boolean moneyEnough(int n, int money) {

        int l = 1;  //l means the smallest guessNum
        int r = n;  //r means the largest guessNum

        while (l < r) {
            int mid = l + (r - l) / 2;
            money -= mid;
            if (money < 0) {

                return false;
            }
            l = mid + 1;
        }
        return money >= 0;
    }
}
