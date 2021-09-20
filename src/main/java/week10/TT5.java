package week10;

public class TT5 {

    public static void main(String[] args) {
        TT5 tt5 = new TT5();
        System.out.println(tt5.firstBadVersion(10
                , 7));
    }

    public int binarySearch(int[] arr, int target) {
        //找到第一个出现target的地方
        int l = 0;
        int r = arr.length;
        while (l < r) {
            //[l,r) 左闭右开，等于号也是要找的
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) {
                r = mid;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                //相等的时候的话就mid不动，已经踩到点了 r过去就好了，搜索的要在左边
                r = mid;
            }
        }
        if (r >= 0 && r < arr.length && arr[r] == target) {
            return r;
        }
        return -1;
    }

    int bad;

    public int firstBadVersion(int n, int bad) {
        //循环遍历就不说了
        this.bad = bad;
        int l = 1;
        int r = n ;
        while (l < r) {
            int mid = (l + (r - l) / 2);
            boolean wrong = isBadVersion(mid);
            if (wrong) {
                r = mid;  //左闭右闭
            } else {
                //mid没错，mid之后的都没问题
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean isBadVersion(int mid) {
        return mid >= this.bad;
    }

}
