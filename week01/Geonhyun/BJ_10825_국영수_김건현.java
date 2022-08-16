package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10825_국영수_김건현 {
    //클래스에 compareTo, toString, Constructor구현
    static class Student implements Comparable<Student>{

        String name;
        int kook;
        int eng;
        int math;

        public Student(String name, int kook, int eng, int math) {
            this.name = name;
            this.kook = kook;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name+"\n");
            return  sb.toString();
        }

        @Override
        public int compareTo(Student o) {
            if(this.kook==o.kook){
                if(this.eng==o.eng){
                    if(this.math==o.math){
                        return this.name.compareTo(o.name);
                    }
                    return Integer.valueOf(o.math).compareTo(this.math);
                }
                return Integer.valueOf(this.eng).compareTo(o.eng);
            }
            return Integer.valueOf(o.kook).compareTo(this.kook);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //학생수
        Student[] students = new Student[N]; //학생배열
        for (int i = 0; i < N; i++) { //초기화
            st = new StringTokenizer(br.readLine());
            students[i] = new Student(st.nextToken(),Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(students); //정렬
        for(Student s:students) System.out.print(s); //출력

    }
}
