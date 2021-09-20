package weenk7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Mt1 {

    public static void main(String[] args) {
        int[] dist =  {46,33,44,42,46,36,7,36,31,47,38,42,43,48,48,25,28,44,49,47,29,32,30,6,42,9,39,48,22,26,50,34,40,22,10,45,7,
                43,24,18,40,44,17,39,36};


        int[] speed = {1, 2, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7, 1, 1, 3, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 8, 1, 1, 1, 3, 6, 1, 3, 1, 1};

        Mt1 mt1 = new Mt1();
        System.out.println(mt1.eliminateMaximum(dist, speed));

    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        int ans = 0;
        double[] time = new double[dist.length];

        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == 0) {
                return 0;
            }
            time[i] = (double) dist[i] / speed[i];
        }
        Arrays.sort(time);

        for (int i = 0; i < dist.length; i++) {
            if (i == 0 && time[i] == 0) {
                ans += 1;
            } else if (time[i] > ans) {
                ans += 1;
            } else {
                return ans;
            }
        }
        return ans;
        
    }

}
