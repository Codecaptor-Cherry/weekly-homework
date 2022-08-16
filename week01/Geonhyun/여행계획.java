package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행계획 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        String ans = "YES";
        int[] parent = new int[N]; //도시의 parent들

        //parent자기자신 가리키도록 초기화
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        int[] trip = new int[M];// 여행갈 도시 목록
        int [][] map = new int[N][N]; //map

        //맵초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //trip초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            trip[i] = Integer.parseInt(st.nextToken())-1;
        }
        //로직
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i>j) continue;
                if(map[i][j] == 1){
                    union_parent(parent,i,j);
                }
            }
        }


        int tmp1 = find_parent(parent,trip[0]);
        int tmp2;
        for (int i = 1; i < M; i++) {
            tmp2 = find_parent(parent,trip[i]);
            if(tmp1 != tmp2){
                ans = "NO";
                break;
            }
            tmp1 = tmp2;
        }
        System.out.println(ans);
    }

    static int find_parent(int[] parent, int x){
        if(parent[x]!= x)
            return parent[x] = find_parent(parent, parent[x]);
        return parent[x];
    }

    static void union_parent(int[] parent, int a,int b){
        a = find_parent(parent, a);
        b = find_parent(parent, b);
        if(a>b) parent[b] = a;
        else parent[a] = b;
    }
}
