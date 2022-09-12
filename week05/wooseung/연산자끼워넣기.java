package codingtestproblem.p3.day0912;

import java.util.Scanner;

public class 연산자끼워넣기 {
    static StringBuilder sb = new StringBuilder();
    static int N, max, min;
    static int[] nums, operators, order;

    static int calculator() {
        int value = nums[1];

        for (int i=1; i<= N-1; i++) {
            if(order[i] == 1) {
                value = value + nums[i+1];
            }
            if(order[i] == 2) {
                value = value - nums[i+1];
            }
            if(order[i] == 3) {
                value = value * nums[i+1];
            }
            if(order[i] == 4) {
                value = value / nums[i+1];
            }
        }
        return value;

    }

    static void rec_func(int k) {
        if (k == N) {
            int value = calculator();
            max = Math.max(max, value);
            min = Math.min(min, value);
        } else {
            for(int cand = 1; cand <= 4; cand++) {
                if(operators[cand] >= 1) {
                    operators[cand]--;
                    order[k] = cand;
                    rec_func(k+1);
                    operators[cand]++;
                    order[k] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        nums = new int[N + 1];
        operators = new int[5];
        order = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = sc.nextInt();
        }
        for (int i = 1; i <= 4; i++) {
            operators[i] = sc.nextInt();
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        rec_func(1);
        sb.append(max).append('\n').append(min);
        System.out.println(sb.toString());

    }

}