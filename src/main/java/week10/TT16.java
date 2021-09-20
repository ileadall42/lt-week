package week10;

public class TT16 {

    int[] arr;
    int right;

    public static void main(String[] args) {
        TT16 tt16 = new TT16();
        System.out.println("tt16.getRankOfNumber(1) =" + tt16.getRankOfNumber(1));
        tt16.track(0);
        tt16.track(10);
        tt16.track(20);
        tt16.track(5);
        tt16.track(5);
        tt16.track(30);
        System.out.println("tt16.getRankOfNumber(5) =" + tt16.getRankOfNumber(5));


    }

    public TT16() {
        this.arr = new int[2001];
        this.right = 0;
    }

    public void track(int x) {
        int insertIndex = getRankOfNumber(x);
        moveOnArr(insertIndex, x);
    }

    public int getRankOfNumber(int x) {
        int l = 0;
        int r = this.right;
        while (l <  r){
            int mid = l + (r - l) / 2;
            if (this.arr[mid] > x){
                r = mid;
            }else if (this.arr[mid] <= x){
                l = mid + 1;
            }
        }
        return l;
    }

    public void moveOnArr(int idx, int x){
        int rIndex = this.right;
        while (rIndex > idx){
            this.arr[rIndex] = this.arr[rIndex - 1];
            rIndex--;
        }
        this.arr[rIndex] = x;
        this.right++;
    }
}
