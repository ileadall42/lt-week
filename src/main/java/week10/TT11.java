package week10;

public class TT11 {
    public static void main(String[] args) {
        TT11 tt11 = new TT11();
        int[] arr = {1, 2, 3, 4, 5};
        int target = 11;
        int ans = tt11.minSubArrayLen(target, arr);
        System.out.println("the ans is :" + ans);

    }
    public int minSubArrayLen(int target, int[] nums) {
        int ansMinLen = nums.length + 1;

        for (int i = 0; i < nums.length; i++){
            for (int j = i; j < nums.length; j++){
                int cus = 0;
                for (int k = i; k <= j; k++){
                    cus += nums[k];
                }
                if (cus >= target){
                    ansMinLen = Math.min(ansMinLen, j - i + 1);
                }
            }
        }
        return ansMinLen > nums.length ? 0 : ansMinLen;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        //开始窗口左边
        int l = 0;
        //开始窗口右边
        int r = 0;
        int cus = 0;
        int cusMinLen = nums.length + 1;
        //只要右边窗口还没到顶
        while (r < nums.length){
            cus += nums[r];

            //窗口收缩的条件
            while (cus >= target){
                //窗口大小
                cusMinLen = Math.min(r - l + 1, cusMinLen);
                if (cusMinLen == 1){
                    return 1;
                }
                //收缩同时操作
                cus -= nums[l];
                l++;
            }
            //窗口右扩
            r++;
        }
        return cusMinLen > nums.length ? 0 : cusMinLen;
    }

    public int minSubArrayLen3(int target, int[] nums) {
        //前缀数组 + 二分 ： 利用前缀数组求出前i个字符的和，在搜target
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] += preSum[i - 1] + nums[i];
        }

        //todo 二分的解法太难理解了..
        return -1;
    }
}
