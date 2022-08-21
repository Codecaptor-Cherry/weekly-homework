package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//배열의 중간에 가까운 집?
//집들의 중간에 있는집?
//배열에 하면 1~100,000크기의 배열. -> 좀 그래
public class 안테나 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Home> homes = new ArrayList<>();
        //list에 집의 위치를 저장한home
        for (int i = 0; i < N; i++) {
            homes.add(new Home(Integer.parseInt(st.nextToken())));
        }

        //집의 위치를 기준으로 오름차순 정렬.
        Collections.sort(homes, new Comparator<Home>() {
            @Override
            public int compare(Home o1, Home o2) {
                return o1.pos-o2.pos;
            }
        });

        //집들중 가운데 집의 위치를 출력
        int middleHomePos = homes.get((N-1)/2).pos;
        System.out.print(middleHomePos);
    }

    static class Home{
        int pos;

        public Home(int index) {
            this.pos = index;
        }
    }
}
