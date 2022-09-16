import java.util.LinkedList;
import java.util.Queue;

class Solution {
	
	static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static final int[][] horizon = {{0, 1}, {2, 1}, {0, 3}, {2, 3}};
	static final int[][] vertical = {{1, 2}, {3, 2}, {1, 0}, {3, 0}};

	class robot {
		int lr, lc, rr, rc;
		boolean horizon;
		
		public robot(int lr, int lc, int rr, int rc) {
			// 왼쪽 로봇이 항상 더 작도록 설정
			if (lr <= rr && lc <= rc) {
				this.lr = lr;
				this.lc = lc;
				this.rr = rr;
				this.rc = rc;
			} else {
				this.lr = rr;
				this.lc = rc;
				this.rr = lr;
				this.rc = lc;
			}
			
			horizon = lr - rr == 0; // 행이 같다면 수평, 행이 같지 않다면 수직
		}
	}
	
    public int solution(int[][] board) {
    	int n = board.length;
    	
        int answer = -1;

        Queue<robot> q = new LinkedList<>();
        q.add(new robot(0, 0, 0, 1));
        
    	boolean[][][][] visited = new boolean[n][n][n][n]; // 로봇의 위치 저장 배열 -> 이미 확인한 로봇의 위치라면 무시하도록 한다.
    	visited[0][0][0][1] = true;
        
        while (!q.isEmpty()) {
        	int size = q.size();
        	++answer;

        	while (size-- > 0) {
	        	robot cur = q.poll();
	        	if ((cur.lr == n - 1 && cur.lc == n - 1) || 
	        		(cur.rr == n - 1 && cur.rc == n - 1)) return answer;
	        	
	        	// 사방 이동
	        	for (int i = 0; i < 4; i++) {
	        		int lr = cur.lr + dir[i][0];
	        		int lc = cur.lc + dir[i][1];
	        		int rr = cur.rr + dir[i][0];
	        		int rc = cur.rc + dir[i][1];
	        		
	        		if (checkRange(lr, lc, n) && checkRange(rr, rc, n) && 
	        			board[lr][lc] == 0 && board[rr][rc] == 0 && !visited[lr][lc][rr][rc]) {
	    	        	visited[lr][lc][rr][rc] = true;
	        			q.add(new robot(lr, lc, rr, rc));
	        		}
	        	}
	        	
	        	// 90도 회전 이동
	        	robot r = null;
	        	for (int i = 0; i < 4; i++) {
	        		if (i < 2) { // 0, 1: 왼쪽 이동, 오른쪽 고정
		        		if (cur.horizon) r = moveLeft(board, cur, horizon[i]);
		        		else r = moveLeft(board, cur, vertical[i]);
	        		} else { // 2, 3: 오른쪽 이동, 왼쪽 고정
		        		if (cur.horizon) r = moveRight(board, cur, horizon[i]);
		        		else r = moveRight(board, cur, vertical[i]);
	        		}
	        		
	        		// 로봇이 회전 가능하다면 방문체크하고 회전
	        		if (r != null && !visited[r.lr][r.lc][r.rr][r.rc]) {
	        			visited[r.lr][r.lc][r.rr][r.rc] = true;
	        			q.add(new robot(r.lr, r.lc, r.rr, r.rc));
	        		}
	        	}
        	}
        	
        }

        return answer;
    }
    
    // i가 0~n 사이에 속하는지 확인
    public boolean checkRange(int r, int c, int n) {
    	return 0 <= r && r < n && 0 <= c && c < n;
    }
    
    // 왼쪽 이동, 오른쪽 고정
    public robot moveLeft(int[][] board, robot r, int[] d) {
    	int n = board.length;
    	
    	int lr = r.lr, lc = r.lc;
    	for (int i = 0; i < 2; i++) {
    		lr += dir[d[i]][0];
    		lc += dir[d[i]][1];
    		// 갈 수 없다면 return null
    		if (!checkRange(lr, lc, n) || board[lr][lc] == 1) return null;
    	}
    	
    	return new robot(lr, lc, r.rr, r.rc);
    }
    
    // 오른쪽 이동, 왼쪽 고정
    public robot moveRight(int[][] board, robot r, int[] d) {
    	int n = board.length;
    	
    	int rr = r.rr, rc = r.rc;
    	for (int i = 0; i < 2; i++) {
    		rr += dir[d[i]][0];
    		rc += dir[d[i]][1];
    		// 갈 수 없다면 return null
    		if (!checkRange(rr, rc, n) || board[rr][rc] == 1) return null;
    	}
    	
    	return new robot(r.lr, r.lc, rr, rc);
    }
}

public class Q22_블록이동하기 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		
		int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
		System.out.println(sol.solution(board));
	}
	
}
