package codingtestproblem.p3.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정렬된배열에서특정수의개수구하기 {

    static int[] arr;
    static int x;
    static int cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        cnt = 0;
        arr = new int[N];
        int ans = 0;
        st = new StringTokenizer(in.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = binarySearch(x,0, arr.length);
        if(ans == 0){
            System.out.println(-1);
        }else {
            System.out.println(ans);
        }

    }

    static int binarySearch(int x, int start, int end) {    // 이진 탐색 시작

        int left = low(x, start, end);  // x의 시작좌표
        int right = high(x, start, end);    // x의 마지막 좌표 + 1
        return right - left;

    }

    static int low(int x, int start, int end) { // 중간값에서 낮은 값들 중에 x를 탐색하여 시작좌표를 구한다.

        while(start < end){
            int mid = (start + end) / 2;
            if(arr[mid] >= x) {
                end = mid;
            }else {
                start = mid + 1;
            }
        }
        return end;
    }
    static int high(int x, int start, int end) {    // 중간값에서 높은 값들 중에 x를 탐색하여 마지막좌표 + 1 값을 구한다.

        while(start < end) {

            int mid = (start + end) / 2;
            if (arr[mid] > x) {
                end = mid;
            } else {
                start = mid + 1;
            }

        }
        return end;
    }

}
