package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소 {
    static int[] dx = {-1, 1, 0, 0}; //상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        //초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit(0, map, visited);
        System.out.print(MAX);

    }
    //print
    static void print(int[][]map){
        System.out.println("--------------------------");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
    //dfs
    static void visit(int count, int[][] map, boolean[][] visited) {
        if (count == 3) {
            int cnt = countZero(map);
            if (cnt > MAX) {
                MAX = cnt;

            }
//            print(map);
            return;
        }

//        print(map);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    // tmp복사본으로 바꿔줌
                    int[][] tmp = new int[map.length][map[0].length];
                    copy(map, tmp);
                    visited[i][j] = true;
                    tmp[i][j] = 1;
                    visit(count + 1, tmp, visited);
                    visited[i][j] = false;
                }
            }
        }


    }

    //벽이아니다/바이러스가아니다/이미방문하지 않았다 세 조건을 만족하면 큐에 넣기
    static boolean isPossible(int row, int col, int[][] map, boolean[][] visited) {
        if (row >= 0 && row < map.length && col >= 0 && col < map[0].length) {
            if (map[row][col] == 0 && visited[row][col] == false) {
                return true;
            }
        }
        return false;
    }

    //바이러스 확산시키고 0의개수 리턴
    static int countZero(int[][] src) {
        int[][] map = new int[src.length][src[0].length];
        copy(src,map);
        int cnt = 0;
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Point> q = new LinkedList<>();

        //Q에 넣을 바이러스 찾기
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 2) {
                    q.offer(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        //BFS
        while (!q.isEmpty()) {
            Point p = q.poll();
            //방문한 Point를 2로 만들기.
            map[p.x][p.y] = 2;
            //큐에서 꺼낸 point에 대해 4방향 체크
            for (int i = 0; i < 4; i++) {
                if (isPossible(p.x + dx[i], p.y + dy[i], map, visited)) {
                    visited[p.x + dx[i]][p.y + dy[i]] = true;
                    q.offer(new Point(p.x + dx[i], p.y + dy[i]));
                }
            }
        }

        //0의개수 세기
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) cnt++;
            }
        }

//        print(map);
//        System.out.println("cnt==> "+cnt);
        return cnt;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void copy(int[][] map, int[][] tmp){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                tmp[i][j] = map[i][j];
            }
        }
    }

}
