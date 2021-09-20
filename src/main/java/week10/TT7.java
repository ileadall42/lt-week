package week10;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TT7 {

    public static void main(String[] args) {
        TT7 tt7 = new TT7();
        int[] arr = {5, 7, 7, 8, 8, 8, 10};
        int target = 9;
        int[] ans = tt7.searchRange(arr, target);
        System.out.println("the ans is:" + Arrays.toString(ans));
    }

    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        //todo 对二分的左开右闭 还有待理解
        while (l < r && nums[l] != nums[r - 1]) {
            int mid = l + (r - l) / 2;

            if (nums[mid] > target) {
                r = mid; //抛去mid自身了
            } else if (nums[mid] < target) {
                l = mid + 1; //抛去mid自身了
            } else if (nums[mid] == target) {
                //所以mid 左边 和 mid右边都有可能 这个时候只有移动r--;
                //相当于再次二分，去查找 右半段 最后一个target位置，左半段去查找第一个target的位置
                if (nums[mid] != nums[l]) {
                    l++;
                } else if (nums[mid] != nums[r - 1]) {
                    r--;
                }
            }
        }
        int[] res = {l, r - 1};
        if (l < nums.length && nums[l] == target) {
            return res;
        }

        int[] res2 = {-1, -1};

        return res2;
    }
}
