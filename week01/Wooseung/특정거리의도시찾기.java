package codingtestproblem.p3.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 특정거리의도시찾기 {

    static int[] visited;
    static int N, M, K, X;
    static ArrayList<ArrayList<Integer>> city = new ArrayList<ArrayList<Integer>>();
    static int[] d;
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Queue<Integer> q = new LinkedList<Integer>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        d = new int[N+1];   // 최단 거리를 담을 배열

        
        for (int i = 0; i <= N; i++) {  //  그래프와 최단 거리 초기화
            city.add(new ArrayList<Integer>());
            d[i] = -1;
        }

        for(int i = 0; i < M; i++) {    // 도로 정보 입력
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            city.get(a).add(b);
        }
        d[X] = 0;   // 출발 도시까지의 거리 0으로 초기화

        q.add(X);   // BFS 시작
        while(!q.isEmpty()){

            int current = q.poll();
            // 이동할 수 있는 모든 도시를 확인
            for(int i = 0; i < city.get(current).size(); i++) {
                int next = city.get(current).get(i);

                if(d[next] == -1) { // 방문한 도시가 아니라면 최단거리 설정

                    d[next] = d[current] + 1;
                    q.add(next);
                }
            }
        }
        // 최단 거리가 k인 도시의 번호를 오름차순으로 출력
        boolean check = false;
        for(int i = 1; i <= N; i++) {
            if(d[i] == K) {
                System.out.println(i);
                check = true;
            }
        }
        // 최단거리가 k인 도시가 없으면 -1 출력
        if(!check) {
            System.out.println(-1);
        }

    }

}
