package codingtestproblem.p3.day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Student implements Comparable<Student>{   // 학생의 이름과 성적 정보를 담을 Student클래스 생성

    String name;
    int korean;
    int english;
    int math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student o) {   //
        if(o.korean == this.korean) {   // 국어 점수가 같으면 영어 점수 오름차순 
            if(this.english == o.english) { //  영어 점수가 같으면  수함 점수 내림 차순
                if(this.math == o.math) {   // 모든 점수가 같으면 이름 오름차순
                    return this.name.compareTo(o.name);
                }
                return o.math - this.math;
            }
            return this.english - o.english;
        }
        return o.korean - this.korean;  // 기본적으로 국어 점수 내림차순
    }



}

public class 국영수 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(in.readLine());

        List<Student> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            list.add(new Student(name, korean, english, math));
        }
        Collections.sort(list);
        for(Student s : list) {
            System.out.println(s.getName());
        }
    }

}
