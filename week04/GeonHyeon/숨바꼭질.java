package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 숨바꼭질 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int INF = (int)1e9;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int d[] = new int[N+1]; //최단거리 정보 저장을 위한d
        ArrayList<ArrayList<Node>> nodes=new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N + 1; i++) {
            nodes.add(new ArrayList<>());
        }
        //노드초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes.get(a).add(new Node(b,1));//노드 추가
            nodes.get(b).add(new Node(a,1));//노드 추가
        }


        //d초기화
        for (int i = 0; i < N + 1; i++) {
            d[i] = INF;
        }
        d[1] = 0;//시작점

        //다익스트라로직
        pq.offer(new Node(1,0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cost = node.cost;
            if (d[node.idx] < cost) {
                continue;
            }
            //꺼낸 곳에서 갈 수 있는 곳들을 모두 탐색&갱신
            for (int i = 0; i < nodes.get(node.idx).size(); i++) {
                //현재 뽑은 노드를 거쳐서 해당하는 곳으로 가는 비용이 더 적다면
                if(d[node.idx]+nodes.get(node.idx).get(i).cost < d[nodes.get(node.idx).get(i).idx]){
                    d[nodes.get(node.idx).get(i).idx] = d[node.idx]+nodes.get(node.idx).get(i).cost;
                    pq.offer(new Node(nodes.get(node.idx).get(i).idx,d[node.idx]+nodes.get(node.idx).get(i).cost));
                }
            }

        }

        //출력을위한 복수답 개수찾기.
        int idx =0;
        int dis = 0;
        int count =0;
        for (int i = 1; i < N + 1; i++) {
            //갱신
            if(d[i]>dis){
                count =0;
                dis = d[i];
                idx = i;
                count++;
            }else if(dis == d[i]){
                count++;
            }
        }

        System.out.println(idx + " " + dis + " " + count);

    }

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int to, int weight) {
            this.idx = to;
            this.cost = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }
}
