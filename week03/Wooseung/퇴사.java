package codingtestproblem.p3.day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사 {

    static int ans;
    static int N;
    static int[] T;
    static int[] P;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        T = new int[N + 1];
        P = new int[N + 1];

        for(int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());

        }
        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int idx, int total) {

        if(idx >= N) {
            ans = Math.max(ans, total);
            return;
        }else {

            if(idx + T[idx] <= N) { // 상담을 선택한 경우
                dfs(idx + T[idx], total + P[idx]);
            }
            dfs(idx + 1, total);    // 상담을 선택하지 않은 경우
        }
    }

}
