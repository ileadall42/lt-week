package week1;

import java.util.Stack;

public class PopStack {
    Stack<Integer> popStack;
    Stack<Integer> pushStack;

    public PopStack() {
        popStack = new Stack();
        pushStack = new Stack();
    }

    public void appendTail(int value) {
        pushStack.push(value);
    }

    public int deleteHead() {
        if (popStack.empty()) {
            while (!pushStack.empty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    public static void main(String[] args) {
        PopStack tt = new PopStack();
        tt.appendTail(3);
        int i = tt.deleteHead();
        System.out.println(i);
    }

    public boolean checkZeroOnes(String s) {
        int[] tmpAns = new int[]{0, 0};
        int maxOne = 0;
        int maxZero = 0;
        char[] chs = s.toCharArray();
        int i = 0;
        while (i < chs.length) {
            if (i == 0) {
                tmpAns[0] = chs[i] == '0' ? 1 : 0;
                maxZero = Math.max(tmpAns[0], maxZero);

                tmpAns[1] = chs[i] == '1' ? 1 : 0;
                maxOne = Math.max(tmpAns[1], maxOne);
                i++;
                continue;
            }

            if (chs[i] == chs[i - 1]) {
                tmpAns[0] = chs[i] == '0' ? tmpAns[0] + 1 : 0;
                maxZero = Math.max(tmpAns[0], maxZero);

                tmpAns[1] = chs[i] == '1' ? tmpAns[1] + 1 : 0;
                maxOne = Math.max(tmpAns[1], maxOne);

            } else {
                tmpAns[0] = chs[i] == '0' ? 1 : 0;
                maxZero = Math.max(tmpAns[0], maxZero);

                tmpAns[1] = chs[i] == '1' ? 1 : 0;
                maxOne = Math.max(tmpAns[1], maxOne);
            }
            i += 1;
        }
        return maxOne > maxZero;

    }
}
