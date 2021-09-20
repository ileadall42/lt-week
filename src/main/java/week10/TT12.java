package week10;

public class TT12 {

    public static void main(String[] args) {
    }

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        //O(n) 暴力解法超时了，有些不可能的也被遍历了
        int l = 0;
        int r = drinks.length - 1;
        int ans = 0;
        int mod = (int)1e9 + 7;
        while (l < staple.length && r > 0){
            while (r > 0 && staple[l] + drinks[r] > x){
                r--;
            }
            if (r > 0){
                ans = (ans + r + 1) % mod;
            }
            l++;
        }
        return ans;
    }
}
