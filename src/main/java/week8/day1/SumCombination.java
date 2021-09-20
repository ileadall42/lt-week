package week8.day1;

import java.util.*;

public class SumCombination {


    public static void main(String[] args) {
        SumCombination sumCombination = new SumCombination();
        int[] nums = {4, 2, 1};
        int target = 32;
        long begin = System.currentTimeMillis();
        int ans = sumCombination.combinationSum4(nums, target);
        long end = System.currentTimeMillis();
        System.out.println("it cost: " + (end - begin));
        System.out.println(ans);
//        int[] nn = {1, 1, 2};
//        HashSet<String> alreadyShow = new HashSet<>();
//        DFS(Arrays.asList(1, 1, 2), "", alreadyShow);
//        System.out.println(alreadyShow.size());
    }

    int[] nums;
    int target;
    int finalAns;

    public int combinationSum4(int[] nums, int target) {
        //正整数排序
        Arrays.sort(nums);
        this.nums = nums;
        this.target = target;
        List<Integer> currPath = new ArrayList<>();
        this.finalAns = 0;

        dfs(0, 0, currPath);

        return this.finalAns;
    }

    public void dfs(int idx, int currSum, List<Integer> currPath) {
        if (currSum > this.target) {
            return;
        }
        if (currSum == target) {
//            System.out.println(currPath);
            int res = getAllCombination(currPath);
            this.finalAns += res;
            return;
        }
        //选择自身元素加入
        currPath.add(nums[idx]);
//        currPath.addLast(nums[idx]);
        dfs(idx, currSum + nums[idx], currPath);
        currPath.remove(currPath.size() - 1);
//        currPath.removeLast();

        //自身不加入，则直接跳下个元素
        if ((idx + 1) < this.nums.length) {
            dfs(idx + 1, currSum, currPath);
        }
    }

    int[] swapNum;

    /**
     * 获取其元素组合
     *
     * @param currPath
     * @return
     */
    private int getAllCombination(List<Integer> currPath) {
//        System.out.println(currPath);
        this.ansOfSwapWay = 0;
        int[] arr = new int[currPath.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = currPath.get(i);
        }
        this.swapNum = arr;
        dfsSwapWithDuplicate(0, currPath);
        return this.ansOfSwapWay;
    }

    int ansOfSwapWay = 0;

    public void dfsSwapWithDuplicate(int start, List<Integer> currPath) {
        if (start == currPath.size() - 1) {
//            System.out.println(Arrays.toString(this.swapNum));
            this.ansOfSwapWay += 1;
            return;
        }
        List<Integer> visitedValue = new ArrayList<>();
        for (int i = start; i < currPath.size(); i++) {
            if (visitedValue.contains(this.swapNum[i])) {
                continue;
            }
            visitedValue.add(this.swapNum[i]);
            swap(this.swapNum, i, start);
            //start已经确定了 剩下的是start+1的部分
            dfsSwapWithDuplicate(start + 1, currPath);
            swap(this.swapNum, i, start);
        }
    }

    public void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }


}
