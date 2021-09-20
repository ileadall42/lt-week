package week10;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class TT17 {

    public static void main(String[] args) {
        TT17 tt17 = new TT17();
        for (int i = 3; i < 10e4; i++) {
            int[] arr = getRandomCase(i);
            int ans = tt17.sumOfBeauties2(arr);
            int ans1 = tt17.sumOfBeauties(arr);
            if (ans != ans1) {
                System.out.println("the arr is" + Arrays.toString(arr));
                System.out.println("the O(n) is:" + ans);
                System.out.println("the real O(n^2) is:" + ans1);
            }

        }

//        int[] arr = {1428, 2418, 7912};
//        int ans = tt17.sumOfBeauties2(arr);
//        int ans1 = tt17.sumOfBeauties(arr);
//        if (ans != ans1) {
//            System.out.println("the arr is" + Arrays.toString(arr));
//            System.out.println("the O(n) is:" + ans);
//            System.out.println("the real O(n^2) is:" + ans1);
//        }


    }

    public static int[] getRandomCase(int len) {
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt((int) 100);

        }
//        int[] arr = {2, 4, 6, 4};
        return arr;
    }

    public int sumOfBeauties(int[] nums) {
        //o（n^2）暴力解法
        int[] ans = new int[nums.length];
        for (int i = 1; i < nums.length - 1; i++) {
            ans[i] = checkOk(nums, i);
        }
        return Arrays.stream(ans).sum();
    }


    public int sumOfBeauties2(int[] nums) {
        //记录 i 位置之前的最大值坐标 maxValue[i] = max(i-1,i-2...0)
        int[] maxValueIndex = new int[nums.length];
        //minValueIndex[i]  表示 i+1，i+2,.. nums.length - 1 的最小值
        int[] minValueIndex = new int[nums.length];

        int[] ans = new int[nums.length];
        ans[0] = 0;
        ans[nums.length - 1] = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                maxValueIndex[i] = nums[i];
                continue;
            }
            maxValueIndex[i] = nums[i - 1] >= maxValueIndex[i - 1] ? nums[i - 1] : maxValueIndex[i - 1];
        }

        for (int i = nums.length - 1; i > -1; i--) {
            if (i == nums.length - 1) {
                minValueIndex[i] = nums[i];
                continue;
            }
            minValueIndex[i] = nums[i + 1] < minValueIndex[i + 1] ? nums[i + 1] : minValueIndex[i + 1];
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > maxValueIndex[i] && nums[i] < minValueIndex[i]) {
                ans[i] = 2;
                continue;
            } else {
                if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                    ans[i] = 1;
                    continue;
                }
            }
        }
        return Arrays.stream(ans).sum();

    }


    private int checkOk(int[] nums, int idx) {
        int lflag = 0;
        int rflag = 0;
        boolean lflagNotOk = false;
        boolean rflagNotOk = false;
        int l = 0;
        while (l < idx) {
            if (nums[l] >= nums[idx]) {
                lflagNotOk = true;
            } else {
                if (l == idx - 1) {
                    lflag = 1;
                }
            }
            l++;
        }

        int r = idx + 1;
        while (r < nums.length) {
            if (nums[r] <= nums[idx]) {
                rflagNotOk = true;
            } else {
                if (r == idx + 1) {
                    rflag = 1;
                }
            }
            r++;
        }
        int ans = 0;
        if (lflag == 1 && rflag == 1) {
            ans = 1;
        }
        if (!rflagNotOk && !lflagNotOk) {
            ans = 2;
        }

        return ans;
    }
}
