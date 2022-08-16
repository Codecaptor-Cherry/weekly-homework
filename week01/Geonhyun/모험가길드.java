package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 모험가길드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] people = new int[N];
        int count =0; //답
        int sum = 0; //사람의수
        for (int i = 0; i < N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(people);

        for (int i = 0; i < N; i++) {
            sum++; //모험가 한명 추가
            if(sum/people[i]>=1){ //만약 공포도로 나눠진다면 모험가 수의 합을 초기화 하고 count 증가
                sum=0;
                count++;
            }
        }
        System.out.println(count);

    }
}
