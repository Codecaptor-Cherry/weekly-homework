package com.kim.algoStudy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11404_플로이드_김건현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int city[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(city[i],Integer.MAX_VALUE); //우선 모두 무한대

        }

        //자기 자신으로 가는 비용은 0으로 설정
        for (int i = 0; i < N; i++) {
            city[i][i] = 0;
        }

        //초기화 넣을 때 부터 중복되는 노선이 있을 수 있으므로 최소값을 넣어줌
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            //from to index접근이니 -1해서 저장 cost
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());
            city[from][to] = Math.min(city[from][to], cost); //기존에 있던 값과 새로운 값 비교
        }

        //로직. 플로이드는 N^3의 시간 복잡도 a에서 b로 가는데 k를 거쳤을때 기존값과 비교한다~~
        for (int k = 0; k < N; k++) {
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    city[a][b] = Math.min(city[a][b], city[a][k]+city[k][b]);
                }
            }
        }

        //출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(city[i][j] == Integer.MAX_VALUE)
                    bw.write(0+" ");
                else
                    bw.write(city[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
