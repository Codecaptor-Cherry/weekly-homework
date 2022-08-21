package com.kim.algoStudy;

import java.io.*;
import java.util.*;

public class 문자열재정렬 {
    public static void main(String[] args) throws IOException {
        //기본세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        char[] charArr = str.toCharArray();
        List<Integer> intList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();

        //로직
        for(char c:charArr){
            //배열을 돌며 문자가 숫자이면 숫자리스트에추가. 아니면 charList에 추가
            if(Character.isDigit(c)) intList.add(Character.getNumericValue(c));
            else charList.add(c);
        }

        //각 LIST정렬
        Collections.sort(intList);
        Collections.sort(charList);

        //출력
        for(char c:charList) bw.write(c);
        for(int n:intList) bw.write(n+"");

        //매무리
        bw.flush();
        bw.close();
        br.close();
    }
}
