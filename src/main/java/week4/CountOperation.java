package week4;

import java.util.Map;
import java.util.TreeMap;

public class CountOperation {
    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,2,3,3,4};
        System.out.println(reductionOperations(arr));
    }

    public static int reductionOperations(int[] nums) {
        //1. 有序字典
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (int num : nums) {
            Integer numScan = countMap.getOrDefault(num, 0);
            countMap.put(num, ++numScan);
        }
        int ans = 0;
        while (countMap.size() > 1) {
            Map.Entry<Integer, Integer> maxNumCnt = countMap.pollLastEntry();
            Integer operationTime = maxNumCnt.getValue();
            ans += operationTime;
            Integer max2ndNum = countMap.lastKey();
            Integer max2ndNumNewCount = countMap.lastEntry().getValue() + operationTime;
            countMap.put(max2ndNum, max2ndNumNewCount);
            if (countMap.size() == 1) {
                return ans;
            }
        }

        return ans;
    }

}
