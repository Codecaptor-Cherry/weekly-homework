package codingtestproblem.p3.day0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 못생긴수 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int x2 = 0;
        int x3 = 0;
        int x5 = 0;

        int next2 = 2;
        int next3 = 3;
        int next5 = 5;

        int[] arr = new int[N];
        arr[0] = 1;

        for(int i = 1; i < N; i++) {
            arr[i] = Math.min(next2, Math.min(next3, next5));
            if(arr[i] == next2) {
                x2++;
                next2 = arr[x2] * 2;
            }
            if(arr[i] == next3) {
                x3++;
                next3 = arr[x3] * 3;
            }
            if(arr[i] == next5) {
                x5++;
                next5 = arr[x5] * 5;
            }
        }
        System.out.println(arr[N-1]);
    }

}
