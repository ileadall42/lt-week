package week2;

public class MinT {
    boolean[] ans;
    char[] chs;
    int minJump;
    int maxJump;

    public static void main(String[] args) {
        MinT minT = new MinT();

        System.out.println(1);
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        this.chs = s.toCharArray();
        this.ans = new boolean[chs.length];
        this.minJump = minJump;
        this.maxJump = maxJump;

        return search(chs.length - 1);
    }

    public boolean search(int index) {
        if (index < 0) {
            return false;
        }
        if (this.chs[index] == '1') {
            return false;
        }
        if (index == 0) {
            return true;
        }
        if (this.ans[index] == true) {
            return true;
        }
        boolean tmpAns = false;
        int end = index - minJump < 0 ? -1 : index - minJump;
        if (end < 0) {
            return false;
        }
        int start = index - maxJump < 0 ? 0 : index - maxJump;

        while (start <= end) {
            this.ans[start] = search(start);
            if (this.ans[start] == true) {
                tmpAns = true;
                break;
            }
            start++;
        }
        return tmpAns;
    }
}
