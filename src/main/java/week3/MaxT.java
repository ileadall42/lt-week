package week3;

import java.util.LinkedList;

public class MaxT {

    public static void main(String[] args) {
        MaxT maxT = new MaxT();
        System.out.println(maxT.maxValue("-132", 3));
    }

    public String maxValue(String n, int x) {
        StringBuilder sb = new StringBuilder();
        char[] result = n.toCharArray();
        int start = 0;
        boolean negative = false;
        boolean xAppend = false;
        if (result[0] == '-') {
            start = 1;
            negative = true;
            sb.append('-');
        }
        while (start < result.length) {
            if (!negative) {
                if (result[start] - '0' > x) {
                    sb.append(result[start]);
                } else {
                    sb.append(x);
                    break;
                }
            }
            if (negative) {
                if (result[start] - '0' > x) {
                    sb.append(x);
                    break;
                } else {
                    sb.append(result[start]);
                    xAppend = true;
                }
            }
            start++;
        }
        while (start < result.length) {
            sb.append(result[start]);
            start++;
        }
        return sb.toString();

    }
}
