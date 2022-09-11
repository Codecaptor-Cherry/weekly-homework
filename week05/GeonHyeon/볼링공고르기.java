package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 볼링공고르기 {
    //N말고 M도 준 이유?
    //1부터M까지 배열 생성 -> 각무게의 공 카운팅 -> 자기보다 큰 무게 곱해서 모두 더하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0; //정답 출력용
        int[] balls = new int[M+1]; //1부터 사용
        st = new StringTokenizer(br.readLine());

        //입력받기
        for (int i = 0; i < N; i++) {
            balls[Integer.parseInt(st.nextToken())]++;
        }

        //로직
        for (int i = 1; i <= M; i++) {
            for (int j = i + 1; j <= M; j++) {
                cnt+=balls[i]*balls[j];
            }
        }

        System.out.println(cnt);

    }
}
