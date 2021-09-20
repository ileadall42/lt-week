package week5;

import java.util.HashSet;
import java.util.Set;

public class Mt2 {

    public static void main(String[] args) {
        int rs = Integer.MAX_VALUE + Integer.MAX_VALUE;
        System.out.println(rs);
        System.out.println(Integer.MAX_VALUE);
        Mt2 mt2 = new Mt2();
        int[] removable = new int[]{3, 1, 0};
        System.out.println(mt2.maximumRemovals("abcacb", "ab", removable));
    }

    char[] schs;
    char[] pchs;

    public int maximumRemovals(String s, String p, int[] removable) {
        //二分法
        //k -> [0,removable.length]
        this.schs = s.toCharArray();
        this.pchs = p.toCharArray();

        int left = 0;
        int right = removable.length - 1;
        int maxK = 0;

        while (left <= right) {
            int mid = (right - left) / 2 + left;  //防止溢出
            boolean isOk = isOk(mid, removable);
            if (isOk) {
                //继续往右半段寻找
                maxK = Math.max(maxK + 1, mid + 1);
                left = mid + 1;
            } else {
                //往左半段寻找
                right = mid - 1;
            }
        }

        return maxK;
    }

    /**
     * * 返回移除前k个是否满足 则
     **/
    public boolean isOk(int maxIndex, int[] removable) {
        Set<Integer> removeIdx = new HashSet<Integer>();
        for (int i = 0; i <= maxIndex; i++) {
            removeIdx.add(removable[i]);
        }
        return isSubSequence(removeIdx);
    }

    public boolean isSubSequence(Set<Integer> removeIdx) {
        int pl = 0;
        for (int i = 0; i < this.schs.length; i++) {
            if (removeIdx.contains(i)) {
                continue;
            }
            if (this.pchs[pl] == this.schs[i]) {
                pl++;
            }
            if (pl >= this.pchs.length) {
                return true;
            }
        }
        return pl == this.pchs.length;
    }
}
