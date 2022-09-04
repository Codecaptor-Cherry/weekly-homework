class Solution {
	int M, N, sr, er, sc, ec;
	
	// 배열 90도 회전
	public int[][] rotate(int[][] key) {
		int[][] temp = new int[M][M];
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = key[M - j - 1][i];
			}
		}
		return temp;
	}
	
	// 자물쇠와 열쇠 일치 여부 확인
	public boolean compare(int[][] key, int[][] lock, int r, int c) {
		for (int i = 0; i < er - sr + 1; i++) {
			for (int j = 0; j < ec - sc + 1; j++) {
                if (r + i >= M || c + j >= M || sr + i >= N || sc + j >= N) return false;
				if (key[r + i][c + j] == lock[sr + i][sc + j]) return false;
			}
		}
		return true;
	}

	// 행 확인
	public boolean checkRow(int[][] lock, int r) {
		for (int i = 0; i < N; i++) {
			if (lock[r][i] == 0) return false;
		}
		return true;
	}
	
	// 열 확인
	public boolean checkCol(int[][] lock, int c) {
		for (int i = 0; i < N; i++) {
			if (lock[i][c] == 0) return false;
		}
		return true;
	}
	
    public boolean solution(int[][] key, int[][] lock) {
        M = key[0].length;
        N = lock[0].length;
        
        sr = 0;
        sc = 0;
        er = N - 1;
        ec = N - 1;
        
        while (sr < N) {
        	if (!checkRow(lock, sr)) break ;
        	++sr;
        }

        while (er >= 0) {
        	if (!checkRow(lock, er)) break ;
        	--er;
        }
        
        while (sc < N) {
        	if (!checkCol(lock, sc)) break ;
        	++sc;
        }

        while (ec >= 0) {
        	if (!checkCol(lock, ec)) break ;
        	--ec;
        }
        
        // 모든 경우의 수 확인
        for (int k = 0; k < 4; k++) {
        	for (int i = 0; i <= N - (er - sr + 1); i++) {
        		for (int j = 0; j <= N - (ec - sc + 1); j++) {
        			if (compare(key, lock, i, j)) return true;
        		}
        	}
        	key = rotate(key);
        }
        
        return false;
    }
}

public class Q10_자물쇠와열쇠 {

	public static void main(String[] args) {
		Solution sol = new Solution();
//
//		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
//		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		int[][] lock = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		int[][] key = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		System.out.println(sol.solution(key, lock));
	}
	
}
