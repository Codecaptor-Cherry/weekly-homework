package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 만들수없는금액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //정렬
        Arrays.sort(arr);
        //target-1까지는 만들 수 있다는 가정
        int target = 1;

        for (int i = 0; i < N; i++) {
            //예를들어 target=5, arr[i]=7이면 6은 만들 수 없는 금액이 된다.
            if(target<arr[i]) break;
            target += arr[i];
        }
        //출력
        System.out.println(target);



    }

}
