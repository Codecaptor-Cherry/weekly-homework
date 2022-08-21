package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정확한순위 {
    public static void main(String[] args) throws IOException {
        //기본세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int INF = 1000000000;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ans = 0;
        int[][] arr = new int[N+1][N+1]; //1번부터 시작이니 N+1

        //모두 INF로 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = INF;
            }
        }
        //자기 자신으로 가는 비용은 0
        for (int i = 1; i < N + 1; i++) {
            arr[i][i] = 0;
        }
        //해당 길만 1로 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]); //k를거쳐서 가냐 vs  그냥 가냐
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            int cnt = 0;
            for (int j = 1; j < N + 1; j++) {
                if(arr[i][j]!=INF || arr[j][i]!=INF)cnt++; //얘보다 위인지 아래인지 알 수 있다면,
            }
            if(cnt==N) ans++; //알수있는 정보가 자신포함 N개라면 -> 순위를 알 수 있다.
        }
        System.out.print(ans);
    }
}
