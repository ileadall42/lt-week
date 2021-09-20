package week1;

import java.util.HashMap;
import java.util.Map;

public class FindPairs {
    //k: nums2[j]   v : num of nums2[j];
    Map<Integer, Integer> nums2Count = new HashMap<Integer,Integer>();
    int[] nums1;
    int[] nums2;
    public void FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for(int j = 0; j < this.nums2.length; j++){
            Integer cnt = this.nums2Count.getOrDefault(this.nums2[j], 0);
            cnt += 1;
            this.nums2Count.put(this.nums2[j], cnt);
        }
    }

    public void add(int index, int val) {
        //remove the old num
        Integer oldCnt = this.nums2Count.get(this.nums2[index]);
        oldCnt -= 1;
        nums2Count.put(this.nums2[index], oldCnt);

        //add the new num
        this.nums2[index] += val;
        Integer newCnt = this.nums2Count.getOrDefault(this.nums2[index], 0);
        newCnt += 1;
        this.nums2Count.put(this.nums2[index], newCnt);
    }

    public int count(int tot) {
        int ans = 0;
        for (int i = 0; i < this.nums1.length; i++){
            Integer needNum2 = tot - nums1[i];
            Integer newCnt = this.nums2Count.getOrDefault(needNum2, 0);
            ans += newCnt;
        }
        return ans;
    }
}
