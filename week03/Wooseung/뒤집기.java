package codingtestproblem.p3.day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 뒤집기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        char ch = str.charAt(0);
        int z = 0;
        int o = 0;

        if(ch == '0') {
            z++;
        }else {
            o++;
        }
        for(int i = 0; i < str.length(); i++) {
            if(ch == str.charAt(i)) {
                continue;
            }else {
                ch = str.charAt(i);
                if(ch == '0') {
                    z++;
                }else {
                    o++;
                }
            }
        }
        System.out.println(Math.min(z, o));
    }

}
