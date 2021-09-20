package weenk7;

public class Mt2 {
    public static void main(String[] args) {
        Mt2 mt2 = new Mt2();
        int[] ns = {0, 2, 1, 5, 3, 4};
        int[] ints = mt2.buildArray(ns);
        System.out.println(ints);

    }

    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++){
            int idx = nums[i];
            ans[i] = nums[idx];
        }

        return ans;

    }
}
