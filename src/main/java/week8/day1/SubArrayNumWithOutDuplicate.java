package week8.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集-- 无重复元素
 */
public class SubArrayNumWithOutDuplicate {

    public static void main(String[] args) {
        int[] arr = getRandomArraySizeOf(3);
        SubArrayNumWithOutDuplicate allCombinationWithNoDuplicate = new SubArrayNumWithOutDuplicate();
        long begin1 = System.currentTimeMillis();
        System.out.println("全排列有重复元素，回溯法实现结果：" +
                allCombinationWithNoDuplicate.getNumOfAllCombinationByBackTrace(arr));
        long backTraceCost = System.currentTimeMillis();
        System.out.println("全排列有重复回溯元素耗时：" + (backTraceCost - begin1));

        long begin2 = System.currentTimeMillis();
        System.out.println("全排列有重复元素，邻里交换法实现结果：" +
                allCombinationWithNoDuplicate.getNumOfAllCombinationBySwapWay(arr));
        long swapCost = System.currentTimeMillis();
        System.out.println("全排列有重复交换元素耗时：" + (swapCost - begin2));
    }

    private static int[] getRandomArraySizeOf(int size) {
        int[] arr = new int[size * 2];
        for (int j = 0; j < size; j++) {
            arr[j] = j;
            arr[j / 2] = j;
        }
        return arr;
    }

    int ansOfBackTraceWay = 0;
    int ansOfSwapWay = 0;
    int[] nums;

    public int getNumOfAllCombinationByBackTrace(int[] num) {
        //保证字典序
        Arrays.sort(num);
        this.nums = num;
        boolean[] show = new boolean[num.length];
        List<Integer> curLis = new ArrayList<>();
        dfsSearchWithBackTrace(show, curLis);
        return this.ansOfBackTraceWay;
    }

    /**
     * 邻里交换法，避免列表的频繁插入删除
     *
     * @param num
     * @return
     */
    public int getNumOfAllCombinationBySwapWay(int[] num) {
        //保证字典序
        Arrays.sort(num);
        this.nums = num;

        dfsSwapWithoutDuplicate(0);
        return this.ansOfSwapWay;
    }

    /**
     * 深度优先搜索
     * 利用一个临时列表 curLis
     * 利用一个布尔数组表示是否出现过回溯 《--》 一般的做法是加入哈希表
     *
     * @param show
     * @param curLis
     */
    public void dfsSearchWithBackTrace(boolean[] show, List<Integer> curLis) {
        if (curLis.size() == show.length) {
            //已经到了
//            System.out.println(curLis);
            //可以在这里去重 todo1
            this.ansOfBackTraceWay += 1;
            return;
        }

        for (int i = 0; i < show.length; i++) {
            //如果前面的数字没有取过，后面的数字一定不要取，保证相同数字的原始顺序一致就不会出现重复【相当于当成了不同的数字处理】
            if (show[i] || (i > 0 && this.nums[i] == this.nums[i - 1] && !show[i - 1])) {
                continue;
            }
            //设置为true
            show[i] = true;
            curLis.add(this.nums[i]);
            dfsSearchWithBackTrace(show, curLis);
            //回溯设置归位
            show[i] = false;
            //移除最后一个元素
            curLis.remove(curLis.size() - 1);
        }

    }


    /**
     * 将数组分为确定部分和不确定部分，从下标0开始
     * 每次交换不确定部分的数据，start的位置依次与后续位置交换并恢复，得到排列组合
     */
    public void dfsSwapWithoutDuplicate(int start) {
        if (start == this.nums.length - 1) {
//            System.out.println(Arrays.toString(this.nums));
            //也可以在这里去重 todo hashSet方式
            this.ansOfSwapWay += 1;
            return;
        }
        List<Integer> visitedValue = new ArrayList<>();
        for (int i = start; i < this.nums.length; i++) {
            if (visitedValue.contains(this.nums[i])) {
                continue;
            }
            visitedValue.add(this.nums[i]);
            swap(this.nums, i, start);
            //start已经确定了 剩下的是start+1的部分
            dfsSwapWithoutDuplicate(start + 1);
            swap(this.nums, i, start);
        }
    }

    public void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
