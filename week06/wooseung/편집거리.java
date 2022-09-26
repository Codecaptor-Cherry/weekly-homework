package codingtestproblem.p3.day0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 편집거리 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int ans = 0;

        ans += Math.abs(str1.length() - str2.length());

        int count = 0;

        for(int i = 0; i < str1.length(); i++) {
            boolean check = false;
            for(int j = 0; j < str2.length(); j++) {
                if(str1.charAt(i) == str2.charAt(j)){
                    check = true;
                }
            }
            if(check){
                count++;
            }
        }

        ans += str1.length() - count;
        System.out.println(ans);

    }

}
