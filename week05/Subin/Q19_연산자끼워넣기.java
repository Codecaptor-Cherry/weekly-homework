import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q19_연산자끼워넣기 {

	static int N, A[], oper[], 
			max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static char[] selected;
	
	static final char[] operator = {'+', '-', '*', '/'}; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		oper = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) oper[i] = Integer.parseInt(st.nextToken());
		
		selected = new char[N - 1];
		perm(0);
		System.out.println(max + "\n" + min);
	}

	private static void perm(int cnt) {
		if (cnt == N - 1) {
			int num = calc();
			
			max = Math.max(max, num);
			min = Math.min(min, num);
			return ;
		}

		for (int i = 0; i < 4; i++) {
			if (oper[i] > 0) {
				--oper[i];
				selected[cnt] = operator[i];
				perm(cnt + 1);
				++oper[i];
			}
		}
		
	}

	private static int calc() {
		int num = A[0];
		for (int i = 1; i < N; i++) {
			switch (selected[i - 1]) {
			case '+':
				num += A[i];
				break;
			case '-':
				num -= A[i];
				break;
			case '*':
				num *= A[i];
				break;
			case '/':
				num /= A[i];
				break;
			default:
				break;
			}
		}
		
		return num;
	}
	
	
	
}
