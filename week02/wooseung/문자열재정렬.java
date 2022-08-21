package codingtestproblem.p3.day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 문자열재정렬 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Character> list = new ArrayList<>();

        String str = br.readLine();
        int sum = 0;

        for(int i = 0; i < str.length(); i++) {
            int temp = str.charAt(i) - '0';
            char c = str.charAt(i);
            if(0 <= temp  && temp < 10) {
                sum += temp;
            }else{
                list.add(c);
            }
        }

        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
        System.out.println(sum);

    }

}
