package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 고정점찾기 {
    public static void main(String[] args) throws IOException {
        //기본 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int start = 0;
        int end = N - 1;
        int mid;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(BinarySearch(start, end, arr));
    }

    static int BinarySearch(int start, int end, int[] arr) {
        int mid;
        //로직
        while (start<=end){
            //mid값 갱신
            mid = (start + end) / 2;
            //인덱스가 값보다크면 -> 오른쪽을 찾아야함.
            if (arr[mid] < mid) {
                start = mid+1;
            } else if (arr[mid] > mid) { //인덱스가 값보다 작으면 -> 왼쪽을 찾야아함
                end = mid-1;
            } else { //정답조건
                return arr[mid];
            }
        }
        return -1;
    }
}
