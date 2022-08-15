import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q23_국영수 {
	
	public static class Student implements Comparable<Student> {
		public String name;
		public int korean;
		public int english;
		public int math;
		
		public Student(String name, int korean, int english, int math) {
			this.name = name;
			this.korean = korean;
			this.english = english;
			this.math = math;
		}
		
		@Override
		public int compareTo(Student o) {
			int comp1 = Integer.compare(o.korean, korean); // 국어 점수가 감소하는 순서로
			if (comp1 == 0) { // 국어 점수가 같으면
				int comp2 = Integer.compare(english, o.english); // 영어 점수가 증가하는 순서로
				if (comp2 == 0) { // 국어 점수와 영어 점수가 같으면
					int comp3 = Integer.compare(o.math, math); // 수학 점수가 감소하는 순서로
					if (comp3 == 0) { // 모든 점수가 같으면
						return (name.compareTo(o.name)); // 이름이 사전 순으로 증가하는 순서로
					}
					return (comp3);
				}
				return (comp2);
			}
			return (comp1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Student[] students = new Student[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(students);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(students[i].name).append("\n");
		}
		System.out.print(sb);
	}
	
}
