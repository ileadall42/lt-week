package week11;

public class TT2 {

    public static void main(String[] args) {
        TT2 tt2 = new TT2();
        int[] arr = {1};
        int target = 2;
        int ans = tt2.search(arr, target);
        System.out.println("the ans is:" + ans);
    }

    public int search(int[] nums, int target) {
        //暴力做法 直接统计
        //二分做法 l,r 为区间 l - r 则为元素个数
        //左边找到第一个小于target的值，右边找到第一个大于target的index

        int l = indexOfLeast(nums, target - 1);
        int r = indexOfLeast(nums, target);
//        System.out.println("the r index is:" + r);
//        System.out.println("the l index is:" + l);

        return r - l;
    }

    /**
     * 找到第一个大于target的
     */
    public int indexOfLeast(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (target < nums[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


}
