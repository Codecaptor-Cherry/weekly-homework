package 구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 자물쇠 : NxN 크기의 정사각형 격자
 * 열쇠 : MxM 크기의 정사각형 격자
 * 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 열리는 구조
 * 자물쇠 영역을 벗어난 부분에 있는 열쇠는 영향 x
 * 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하고, 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됨
 * 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있음
 * 열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열 수 있느면 true, 없으면 false를 return하는 프로그램
 */

public class PM_60059_자물쇠와열쇠_문희주 {
	static int N, M;
	static int[][] key, lock;
	public static void main(String[] args) {
		// 홈(0), 돌기(1)
		lock = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}; // 자물쇠
		key = new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}; // 열쇠
		
		N = lock.length; // 자물쇠 격자 길이 N
		M = key.length; // 열쇠 격자 길이 M
		
		boolean answer = false;
		for(int i = 0; i < 4; i++) {
			answer = check(); // 현재 열쇠에 대해 정답 체크

			if(answer) break; // 정답 나오면 종료
			rotate(); // 정답 안나오면 열쇠 회줜
			
		}
	}

	// 열쇠 회전
	public static void rotate() {
		int[][] temp = new int[M][M];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = key[M-j-1][i];
			}
		}
		
		for(int i = 0; i < M; i++) {
			key[i] = temp[i].clone();
		}
	}
	
	// 자물쇠와 열쇠 맞춰보기
	public static boolean check() {
		boolean flag = true; // 정답 여부
        
		// 자물쇠의 시작 좌표(i, j)
		// 오른쪽 상단에 걸치는 경우 -M+1
		for(int i = -M+1; i < N; i++) {
			for(int j = -M+1; j < N; j++) {
				flag = true; // 현재 시작좌표에 대한 정답 여부
				
                int[][] copy = new int[N][N]; // 현재 자물쇠 배열 copy
                for(int k = 0; k < N; k++){
                copy[k] = lock[k].clone();
                }
                
				// lock과 key 맞춰보기
				for(int a = 0; a < M; a++) {
					if(i+a < 0 || i+a >= N) continue; // x 좌표 범위체크
					for(int b = 0; b < M; b++) {
						if(j+b < 0 || j+b >= N) continue; // y 좌표 범위체크
						if(copy[i+a][j+b] + key[a][b] != 1) { // 돌기가 겹치거나(2) 홈이 맞지 않은 경우(0)
                            flag = false; // 오답
 							break; // 다음 좌표 수행
						}
                        copy[i+a][j+b] = 1; // 홈과 돌기가 맞은 경우 copy에 입력
					}
					if(!flag) break; // 오답이라면 다음 좌표 수행
				}
				if(checkOpen(copy)) return true; // 해당 케이스에 대해 정답 여부 확인 ~ 정답이 나왔다면 리턴
			}
		}
		return false;
	}
    
    public static boolean checkOpen(int[][] copy){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(copy[i][j] != 1) return false;
            }
        }
        
        return true;
    }
}
