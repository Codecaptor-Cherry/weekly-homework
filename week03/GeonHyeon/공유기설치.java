package com.kim.algoStudy;

import java.io.*;
import java.util.*;

public class 공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] home = new int[n];
        int ans = 0; //정답
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }
        //우선 정렬
        Arrays.sort(home);
        //left와 right는 간격을 의미함 이 사이에 정답이 있음.
        int left = 1;
        int right = home[n - 1] - home[0]; //총거리
        //left right 교차되면 종료
        while (left <= right) {
            int mid = (right + left) / 2;
            int count = 1; //일단 시작인덱스에서 하나 무조건 설치
            int prev = home[0];
            //공유기 개수 세기
            for (int i = 1; i < n; i++) {
                int dis = home[i] - prev; //집사이의 간격 구하기
                //집사이의 거리가 내가설정한 간격(mid) 이상이면 -> 공유기를 설치 한 것임 -> count++
                if (dis >= mid) {
                    count++; //개수늘리고
                    prev = home[i]; //이전 집의 위치를 prev에 저장.
                }
            }
            //개수가 더 많아버린다면 -> 간격을 더 넓힐 수 있음.
            if (count >= c) {
                left = mid + 1;
                ans = mid;
            }
            //개수가 더 적다면. -> 간격을 좁혀야 한다.
            else {
                right = mid - 1;
            }

        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}

