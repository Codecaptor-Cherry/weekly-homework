package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//조합문제. 3개 무조건 채우기. 배열 복사 필요?
//3개 다 설치하면 -> 정답체크
public class 감시피하기 {
    static char[][] map;
    static boolean[][] visited;
    static String msg = "NO";
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        StringTokenizer st;
        //초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
//        System.out.println(N);
//        print();

        dfs(0, map);
        System.out.println(msg);


    }

    static void dfs(int obstacle, char[][] map){
        //답이 이미 나왔다면 더 이상 함수를 전개할 필요가 없으니 리턴
        if(msg.equals("YES")) return;
        //종료조건
        if(obstacle==3){
//            print();
            if(judge(map)){
                msg = "YES";
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 'X'&& !visited[i][j]){
                    visited[i][j] = true;
                    map[i][j] = 'O';
                    dfs(obstacle+1, map);
                    map[i][j] = 'X';
                    visited[i][j] = false;
                }
            }
        }

    }

    static void print(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }

    /** 장애물 설치 가능 여부. S가 아니고, T가 아니고 방문한 적이 없어야 한다*/
    static boolean canObstacle(int row, int col){
        return false;
    }

    /** 정답 여부 확인*/
    static boolean judge(char[][] map) {
        int[] dx = {-1, 1, 0, 0};//상하좌우
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //선생님을 만난다면 상하좌우로 검사 o를만나거나 끝까지가면 탐색종료 , 학생이 보이는 순간 false 리턴
                if (map[i][j] == 'T') {
                    //4방향 탐색
                    for (int pos = 0; pos < 4; pos++) {
                        //배열의 끝까지 탐색하기. 중간에 학생을 만나면 false return
                        int offset = 1;
                        int nx = offset*dx[pos] + i;
                        int ny = offset*dy[pos] + j;
                        for (; nx>=0 && nx < N && ny < N && ny>=0;) {
                            nx = offset*dx[pos] + i;
                            ny = offset*dy[pos] + j;
                            if(!(nx>=0 && nx < N && ny < N && ny>=0)) break;
                            if(map[nx][ny]=='S') return false;
                            else if(map[nx][ny]=='O') break; //장애물을 만나면 더 탐색할 필요 없음.
                            offset++;
                        }
                    }
                }
            }
        }
        return true;
    }

}
