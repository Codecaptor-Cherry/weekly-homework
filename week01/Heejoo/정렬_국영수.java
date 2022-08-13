import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 국어 점수 내림차순
 * 2. 국어점수가 같으면 영어 점수 오름차순
 * 3. 국어, 영어 점수가 같으면 수학 점수 내림차순
 * 4. 모든 점수가 같으면 이름 사전 순
 */

class Student implements Comparable<Student>{
	String name;
	int k, e, m;
	
	public Student(String name, int k, int e, int m) {
		this.name = name;
		this.k = k;
		this.e = e;
		this.m = m;
	}
	
	/**
	 * return 1 : 자리 바뀜
	 * return -1 : 자리 안 바뀜
	 */
	@Override
	public int compareTo(Student o) {
		// 1. 국어 점수 내림차순
		if(this.k > o.k) return -1;
		// 2. 영어 점수 오름차순
		else if (this.k == o.k) {
			if (this.e < o.e) return -1;
			// 3. 수학 점수 내림차순
			else if(this.e == o.e) {
				if (this.m > o.m) return -1;
				// 4. 이름 사전 순
				else if(this.m == o.m) {
					return this.name.compareTo(o.name);
				}
				return 1;
			}
			return 1;
		}
		return 1;
	}
}
public class 정렬_국영수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 학생 수 N
		int n = getInt(br.readLine());
		
		Student[] arr = new Student[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Student(st.nextToken(), getInt(st.nextToken()), getInt(st.nextToken()), getInt(st.nextToken()));
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < n; i++) {
			sb.append(arr[i].name + " \n");
		}
		
		System.out.println(sb);
	}
	public static int getInt(String s) {
		return Integer.parseInt(s);
	}
}
