package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 탑승구 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine()); //탑승구 수
        int P = Integer.parseInt(br.readLine()); // 비행기 수
        int cnt = 0;
        int[] parent = new int[G+1];
        int[] airplanes = new int[P];
        //airplanes 초기화
        for (int i = 0; i < P; i++) {
            airplanes[i] = Integer.parseInt(br.readLine());//탑승구번호는 1번부터이고 ports배열은 0번부터니까 -1해주고 저장
        }
        //parent초기화
        for (int i = 0; i <= G; i++) {
            parent[i] = i;
        }
        //순서대로 airplne배열 탐색
        for (int x : airplanes) {
            //p의 부모를 찾고
            int p = find_parent(x,parent);
            //만약 0이면 더이상 놓을 곳이 없으니 break
            if(p==0) break;
            //아니면 p의 바로 왼쪽칸과 합침(도킹 했다는 뜻) 그리고 cnt++
            union_parent(x,p-1,parent);
            cnt++;
        }
        System.out.print(cnt);

    }

    static int find_parent(int x,int[] parent){
        if(parent[x] != x){
            parent[x] = find_parent(parent[x],parent );
        }
        return parent[x];
    }

    static void union_parent(int a, int b, int[] parent) {
        a = find_parent(a, parent);
        b = find_parent(b, parent);
        if(a>b) parent[a] = b;
        else parent[b] = a;
    }
}
