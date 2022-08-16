package com.kim.algoStudy;

import java.io.*;

public class 럭키스트레이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String score = br.readLine();
        int left =0,right = 0;
        int half = score.length()/2;
        for (int i = 0,j=half; i < half ; i++,j++) {
            left+=score.charAt(i)-'0';
            right+=score.charAt(j)-'0';
        }
        if(left == right) bw.write("LUCKY");
        else bw.write("READY");
        bw.flush();
        bw.close();
        br.close();
    }
}
