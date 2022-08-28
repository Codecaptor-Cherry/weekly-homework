package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 어두운길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int count = 0;
        long total = 0;
        List<Vertex> vList = new ArrayList<>();
        int[] parents = new int[N];

        //parents를 자기 자신으로 초기화
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        //vertex받아오기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            total+=weight; //total = 절약할 수 있는 금액
            vList.add(new Vertex(nodeA, nodeB, weight));
        }

        //크루스칼을 위하 ㄴ정렬
        Collections.sort(vList);

        //간선이 N-1개 될 떄 까지 반복
        for(int i=0;i<M;i++){
            Vertex v = vList.get(i);
            if (find(v.nodeA, parents) != find(v.nodeB, parents)) {
                union(v.nodeA, v.nodeB, parents);
                count++;
                total -= v.weight;
//                System.out.println("UNON=> "+v.nodeA+" "+v.nodeB+" weight: "+v.weight);
            }
            if(count==N-1) break;
        }
        System.out.println(total);
    }

    //find
    static int find(int x, int[] parents) {
        if (x != parents[x]) {
            parents[x] = find(parents[x], parents);
        }
        return parents[x];
    }

    //union
    static void union(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);
        if(x<y) parents[y] = parents[x];
        else parents[x] = parents[y];
    }

    //정렬가능한 Vertex클래스
    static class Vertex implements Comparable<Vertex>{
        int nodeA; //a 와 b의 번호 from to 하면 단방향 노드 같아 보이니깐
        int nodeB;
        long weight; //가중치

        public Vertex(int nodeA, int nodeB, long weight){
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return (this.weight<o.weight?-1:1);
        }
    }
}
