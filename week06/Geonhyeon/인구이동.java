package com.kim.algoStudy;

//DFS BFS?=> BFS가 적절해 보인다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 인구이동 {
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        //변수선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        int day = 0;
        boolean flag = true;

        //초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(flag) {
            if(bfs()==0)
                flag = false;
            else
                day++;
        }
        System.out.println(day);



    }

    static int bfs(){
        int unionNum = 0; //연합의 수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                    //방문한 적이 없는 곳이면 BFS실행
                    Queue<Point> queue = new LinkedList<>();
                    Point p = new Point(i,j);
                    queue.offer(p);

                    List<Point> list = new ArrayList<>();
                    list.add(p);

                    visited[p.x][p.y] = true;//방문쳌

                    int sum = map[p.x][p.y];

                    while (!queue.isEmpty()) {
                        Point now = queue.poll();

                        for (int pos = 0; pos < 4; pos++) {
                            int x = now.x+dx[pos];
                            int y = now.y+dy[pos];
                            if(isRange(x,y)){
                                if (!visited[x][y] && isUnion(now.x, now.y, x, y)) {
                                    queue.add(new Point(x, y));
                                    list.add(new Point(x, y));
                                    visited[x][y] = true;
                                    unionNum++;
                                    sum += map[x][y];
                                }
                            }
                        }
                    }

                    //인구분배
                    if (unionNum > 0) {
                        int avg = sum/list.size();
                        for (int rep = 0; rep < list.size(); rep++) {
                            Point now = list.get(rep);
                            map[now.x][now.y] = avg;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i],false);
        }

        return unionNum;
    }

    //배열 범위체크
    static boolean isRange(int x ,int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }
        return false;
    }

    //L,R값 확인해서 옆나라랑 인구비교해서 연합 되는지
    static boolean isUnion(int x1, int y1, int x2, int y2){
        int populationDiff = Math.abs(map[x1][y1] - map[x2][y2]);
        if(populationDiff>=L && populationDiff <=R) return true;
        return false;
    }


    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
