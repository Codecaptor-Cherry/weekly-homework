package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정렬된배열에서특정수의개수구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        //배열 초기화
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = findStartIdx(arr, X);
        int end = findEndIdx(arr, X);

        if(start>end) System.out.println(-1); // 없으면 start>end가 됨
        else System.out.println(end-start+1);//개수니까 +1
    }
    static int findStartIdx(int[] arr,int x){
        int start = 0;
        int end = arr.length-1;
        int mid = (start+end)/2;
        if(arr[0]==x) return 0;

        while(start<=end){
            //mid 설정
            mid = (start+end)/2;

            //찾고자 하는 수보다 arr[mid]가 더 작다면 -> mid가 더 오른쪽으로 이동해야하니 start를 오른쪽으로 이동
            if(arr[mid]<x) {
                start = mid+1;
                System.out.println("start = "+start);
            }
            //찾고자 하는 수보다 arr[mid]가 더 크다면 -> mid가 더 왼쪽으로 이동해야하니 end를 왼쪽으로 이동
            else if(arr[mid]>x){
                end = mid-1;
                System.out.println("end = "+end);
            }
            //같은수라면 end를 하나씩 줄여주면서 mid를 왼쪽으로 이동시킨다.
            else if (arr[mid]==x){
                end--;
                System.out.println("end-- "+end);
            }
        }
        return start;
    }

    static int findEndIdx(int[] arr, int x){
        int start = 0;
        int end = arr.length-1;
        int mid = (start+end)/2;

        while(start<=end){
            mid = (start+end)/2;

            if(arr[mid]<x) {
                start = mid+1;
                System.out.println("start = "+start);
            }else if(arr[mid]>x){
                end = mid-1;
                System.out.println("end = "+end);
            }else if(arr[mid]==x){
                start++;
                System.out.println("start++ "+start);
            }
        }
        return end;

    }
}
