package week11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TT11 {

    String s;
    String p;

    public int maximumRemovals(String s, String p, int[] removable) {
        //前缀记忆
        this.s = s;
        this.p = p;
        //找到第一个满足的l
        int l = 0;
        int r = removable.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (checkOk(mid, removable)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public boolean checkOk(int k, int[] removeable) {
        int pl = 0;
        int sl = 0;
        Set<Integer> removableKIdX = new HashSet<>();
        for (int i = 0; i < k + 1; i++) {
            removableKIdX.add(removeable[i]);
        }
        while (pl < this.p.length() && sl < this.s.length()) {
            if (removableKIdX.contains(sl)) {
                sl++;
                continue;
            }
            if (this.s.charAt(sl) == this.p.charAt(pl)) {
                sl++;
                pl++;
                continue;
            }
            sl++;
        }
        return pl == this.p.length();
    }
}
