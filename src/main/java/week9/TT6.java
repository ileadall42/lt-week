package week9;

import java.util.Arrays;

public class TT6 {

    int targetRound;
    int targetNum;
    int[] arr;


    public static void main(String[] args) {
        TT6 tt5 = new TT6();
        int[] arr = {5, 3, 4, 5};
        boolean result = tt5.stoneGame(arr);
        System.out.println("the ans is:" + result);
    }

    public boolean stoneGame(int[] piles) {
        //轮次
        this.targetRound = piles.length / 2;

        //大于等于这个和就能赢
        this.targetNum = (Arrays.stream(piles).sum() + 1) / 2;

        this.arr = piles;


        return dp(-1, piles.length, 0, 0);
    }

    public boolean dp(int pl, int pr, int cus, int round) {
        if (cus >= this.targetNum) {
            return true;
        }

        if (round >= this.targetRound) {
            return false;
        }

//        //都选左边
//        boolean win1 = dp(pl + 2, pr, cus + this.arr[pl + 1], round + 1, path + "_" + (pl + 1));
//
//        //都选右边
//        boolean win2 = dp(pl, pr - 2, cus + this.arr[pr - 1], round + 1, path + "_" + (pr - 1));
//
//        //alex选左，lee选右
//        boolean win3 = dp(pl + 1, pr - 1, cus + this.arr[pl + 1], round + 1, path + "_" + (pl));
//
//        //alex选右，lee选左
//        boolean win4 = dp(pl + 1, pr - 1, cus + this.arr[pr - 1], round + 1, path + "_" + (pr));
//
        //贪心，让lee每次拿小的，alex每次拿大的
        //其实我这个也是博弈了
        // https://leetcode-cn.com/problems/stone-game/solution/gong-shui-san-xie-jing-dian-qu-jian-dp-j-wn31/
        //限制了后手
        if (this.arr[pl + 1] > this.arr[pr - 1]) {
            //选左边的，且lee拿小的
            if (this.arr[pr - 1] >= this.arr[pl + 2]) {
                return dp(pl + 2, pr, cus + this.arr[pl + 1], round + 1);
            }
            return dp(pl + 1, pr - 1, cus + this.arr[pl + 1], round + 1);
        } else {
            //选了右边的之后，如果右边的继续大，则
            if (this.arr[pr - 2] >= this.arr[pl + 1]) {
                return dp(pl + 1, pr - 1, cus + this.arr[pr - 1], round + 1);
            }
            //选了右边的之后，如果右边小，则
            return dp(pl, pr - 2, cus + this.arr[pr - 1], round + 1);
        }
    }
}
