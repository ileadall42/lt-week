package week11;

import java.util.Arrays;

public class TT1 {

    public static void main(String[] args) {
        int[] h = {2868, 5485, 1356, 1306, 6017, 8941, 7535, 4941, 6331, 6181};
        int[] w = {5042, 3995, 7985, 1651, 5991, 7036, 9391, 428, 7561, 8594};
        TT1 tt1 = new TT1();
        int ans = tt1.bestSeqAtIndex(h, w);
        System.out.println("the ans is :" + ans);

    }

    public int bestSeqAtIndex(int[] height, int[] weight) {
        if (height.length == 0) {
            return 0;
        }

        //n 个人的 weight , height
        int[][] people = new int[height.length][2];
        for (int i = 0; i < height.length; i++) {
            people[i][0] = weight[i];
            people[i][1] = height[i];
        }
        Arrays.sort(people, (p1, p2) -> {
            if (p1[0] == p2[0]) {
                //体重相同，身高降序，此时不可以了
                return p2[1] - p1[1];
            }
            //体重升序
            return p1[0] - p2[0];
        });
        //体重升序之后，体重相同，身高降序（故意不满足条件），则变成求体重的最
        //求升高的最长升子序列
        System.out.println("deep: " + Arrays.deepToString(people));

        //dp[i] 表示到第i个元素（包含），最多多少个
        int[] dp = new int[height.length];
        System.out.println();
        for (int i = 0; i < weight.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (people[i][1] > people[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int bestSeqAtIndex2(int[] height, int[] weight) {
        if (height.length == 0) {
            return 0;
        }

        //n 个人的 weight , height
        int[][] people = new int[height.length][2];
        for (int i = 0; i < height.length; i++) {
            people[i][0] = weight[i];
            people[i][1] = height[i];
        }
        Arrays.sort(people, (p1, p2) -> {
            if (p1[0] == p2[0]) {
                //体重相同，身高降序，此时不可以了
                return p2[1] - p1[1];
            }
            //体重升序
            return p1[0] - p2[0];
        });
        //体重升序之后，体重相同，身高降序（故意不满足条件），则变成求体重的最
        //求升高的最长升子序列

        //dp[i] 表示到第i个元素（包含），最多多少个
        int[] dp = new int[height.length];
        int end = 0;
        dp[0] = people[0][1];

        for (int i = 1; i < people.length; i++){
            if (people[i][1] > dp[end]){
                end++;
                dp[end] = people[i][1];
            }else{
                int l = 0;
                int r = end;
                while (l < r){
                    int mid = l + (r - l) / 2;
                    if (people[i][1] >= dp[mid] ){
                        l = mid + 1;
                    }else{
                        r = mid;
                    }
                }
                if (l - 1 >= 0 && dp[l-1] == people[i][1]){
                    continue;
                }
                dp[l] = people[i][1];
            }
        }
        return end + 1;
    }

    public static void main2(String[] args) {

    }

}
