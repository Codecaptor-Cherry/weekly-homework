package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum =0;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int tmp,tmpB;
        //초기화
        for (int i = 0; i < N; i++) {
            tmp = Integer.parseInt(br.readLine());
            pq.offer(tmp);
        }

        //작은거부터
        while (true) {
            //종료조건
            if(pq.size()==1) break;
            //두개 꺼내서 더하기->총합에 누적시키기-> 합을 다시 우선순위 큐에 넣기
            tmp = pq.poll();
            tmpB = pq.poll();
            sum+= tmp+tmpB;
            pq.offer(tmp + tmpB);
        }

        //출력
        System.out.println(sum);

    }}
