package codingtestproblem.p3.day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {    // 간선의 정보를 담은 Edge클래스 생성

    int distance;
    int nodeA;
    int nodeB;

    public Edge(int distance, int nodeA, int nodeB) {
        this.distance = distance;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }
    
    // 정렬시에 거리의 값이 오름차순으로 정렬되어야지 크루스칼 가능
    @Override
    public int compareTo(Edge other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}

public class 어두운길 {

    // 노드의 개수와 간선의 개수
    public static int N, M;
    public static int[] parent; // 부모 테이블 초기화하기
    // 모든 간선을 담을 리스트와, 최종 비용을 담을 변수
    public static ArrayList<Edge> edges = new ArrayList<>();
    public static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        // 모두 자기 자신을 루트 노드로 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 간선의 정보 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            edges.add(new Edge(z, x, y));
        }

        // 간선을 비용순으로 정렬
        Collections.sort(edges);
        int total = 0; // 전체 가로등 비용

        // 간선을 하나씩 확인하며
        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).distance;
            int a = edges.get(i).nodeA;
            int b = edges.get(i).nodeB;
            total += cost;
            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }
        System.out.println(total - result); // 모든 가로등을 켰을 때에서 최소 신장 트리 값을 빼주면 절약하는 금액을 알 수 있다.
    }
    
    public static int findParent(int x) {   // 루트 노드를 찾기 위한 find메서드
        if (x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }
    
    public static void unionParent(int a, int b) {  // 두 노드를 합치기 위한 union 메서드
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
    
}