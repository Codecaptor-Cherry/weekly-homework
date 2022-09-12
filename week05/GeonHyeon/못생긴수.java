package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//now를1부터 늘려간다. -> 못생긴 수이면 cnt++ -> cnt가 N에 도달하면 출력, 검사방법은 now/2 now/3 now/5의 인덱스가 true인지 -> dp
public class 못생긴수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int now = 1; //지금 탐색하는 수
        int cnt = 1; //n번째 수 라는 뜻
        boolean[] isUgly = new boolean[100000000]; //배열 범위를 어떻게 해야할지 모르겠음.
        isUgly[1] = true;


        while(true){
            if(now%2 == 0 || now%3 == 0|| now%5 == 0){
                if(isUgly[now/2]){
                    isUgly[now] = true;
                    cnt++;
                }else if(isUgly[now/3]){
                    isUgly[now] = true;
                    cnt++;
                }else if(isUgly[now/5]){
                    isUgly[now] = true;
                    cnt++;
                }
            }
            if(cnt == N) break; //종료조건
            now++;
        }
        System.out.println(now);
    }
}
