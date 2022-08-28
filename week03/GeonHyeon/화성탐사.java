package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 화성탐사 {
    static final int INF = (int) 1e9; //큰 수
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] dx = {-1, 1, 0, 0}; //상하좌우순
        int[] dy = {0, 0, -1, 1};

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int[][] d = new int[N][N];

            //d를 INF로 채우기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    d[i][j] = INF;
                }
            }

            //map 초기화
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //0,0을 pq에 넣기
            d[0][0] = map[0][0];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0, map[0][0]));

            while (!pq.isEmpty()) {
                Node n = pq.poll();
                int row = n.row;
                int col = n.col;
                //거리가 현재 저장된 것 보다 더 크다 -> 버림
                if (d[row][col] < n.weight) continue;
                //4way search
                for (int pos = 0; pos < 4; pos++) {
                    int nextRow = row + dx[pos];
                    int nextCol = col + dy[pos];
                    //범위 안이면 다음 갈 곳의 거리는 현재거리+다음 갈 곳에 적힌 숫자
                    if (isRange(nextRow, nextCol)) {
                        if (d[nextRow][nextCol] > n.weight + map[nextRow][nextCol]){
                            d[nextRow][nextCol] = n.weight + map[nextRow][nextCol];
                            pq.offer(new Node(nextRow, nextCol, n.weight+map[nextRow][nextCol]));
                        }
                    }
                }
            }

//            //test출력
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(d[i][j]+" ");
//                }
//                System.out.println();
//            }

            System.out.println(d[N - 1][N - 1]);

        }
    }

    static boolean isRange(int row, int col) {
        if (row >= 0 && row < N && col >= 0 && col < N) {
            return true;
        }
        return false;
    }

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int weight; //범위상 int여도 괜찮음

        public Node(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight < o.weight ? -1 : 1;
        }
    }
}
