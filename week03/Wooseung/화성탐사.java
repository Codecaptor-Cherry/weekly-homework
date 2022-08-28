package codingtestproblem.p3.day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {

    int x;
    int y;
    int distance;

    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
    @Override
    public int compareTo(Node other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}

public class 화성탐사 {

    public static final int INF = 99999999;
    public static int[][] map;
    public static int[][] visited;
    public static int N;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 최단 거리 테이블을 모두 무한으로 초기화
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], INF);
            }

            int x = 0, y = 0; // 시작 위치는 (0, 0)
            // 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0, map[x][y]));
            visited[x][y] = map[x][y];

            while(!pq.isEmpty()) { // 다익스트라 시작
                // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
                Node node = pq.poll();
                int dist = node.distance;
                x = node.x;
                y = node.y;
                // 현재 노드가 이미 방문한적이 있는 노드라면 무시
                if (visited[x][y] < dist) {
                    continue;
                }
                // 현재 노드와 연결된 다른 인접한 노드들을 확인
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    // 배열의 범위를 벗어나는 경우 무시
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue;
                    }
                    int cost = dist + map[nx][ny];
                    // 현재 노드를 거쳐서 다른 노드로 이동했을 때 거리가 더 짧은 경우
                    if (cost < visited[nx][ny]) {
                        visited[nx][ny] = cost;
                        pq.offer(new Node(nx, ny, cost));
                    }
                }
            }
            System.out.println(visited[N - 1][N - 1]);
        }
    }
}