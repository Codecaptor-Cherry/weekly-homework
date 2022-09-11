package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


//사과를 먹고 안없애서 헤맸다...
public class 뱀 {
    static int SECOND = 1;
    static int dx[] = {0, 1, 0, -1}; //우 하 좌 상
    static int dy[] = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int L;
        int[][] map = new int[N][N];
        int dir = 0;
        Point snakeHead = new Point(0, 0);
        Queue<Point> q = new LinkedList<>();
        Map<Integer, Character> hash = new HashMap<>();
        q.offer(new Point(snakeHead.row, snakeHead.col));

        //사과 좌표 초기화
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            map[row][col] = 1; //사과 표시
        }
        L = Integer.parseInt(br.readLine());

        //커맨드 초기화
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            hash.put(num, c);
        }

        //로직직
        while (true) {
            int row = snakeHead.row;
            int col = snakeHead.col;
            int nextRow = row+dx[dir];
            int nextCol = col+dy[dir];

            //종료조건
            if(isOver(nextRow, nextCol,N,q)){
                break;
            }

            if (hash.containsKey(SECOND)) {
                //해당 초에 해당하는 문자 가져오기 D or L
                char c = hash.get(SECOND);
                if (c == 'D') {
                    dir++;
                    if(dir == 4) dir = 0;
                } else {
                    dir--;
                    if(dir == -1) dir = 3;
                }
            }

            SECOND++; //초 늘리고


            //사괴 있는지 없는지에 따라 로직
            if (map[nextRow][nextCol] == 1) {
                snakeHead.row = nextRow;
                snakeHead.col = nextCol;
                q.offer(new Point(nextRow,nextCol));
                map[nextRow][nextCol] = 0;  //사과 없애주기
            } else {
                snakeHead.row = nextRow;
                snakeHead.col = nextCol;
                q.poll();
                q.offer(new Point(nextRow,nextCol));
            }


        }
        System.out.println(SECOND);


    }


    static boolean isOver(int row, int col, int N, Queue<Point> q) {
        if (row >= 0 && row < N && col >= 0 && col < N) {
            for (Point p : q) {
                if (p.row == row && p.col == col) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
