package codingtestproblem.p3.day0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 볼링공고르기 {

    static int N, M;
    static int[] balls;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        balls = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            balls[x] += 1;
        }

        int ans = 0;

        for(int i = 1; i <= M; i++) {
            N -= balls[i];
            ans += balls[i] * N;
        }
        System.out.println(ans);
    }

}
