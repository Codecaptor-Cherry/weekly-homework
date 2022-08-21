package com.kim.algoStudy;

import java.util.Scanner;

public class 곱하기혹은더하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int N = str.charAt(0) - '0';
        for (int i = 1; i < str.length(); i++) {
            N = Math.max(N * (str.charAt(i) - '0'), N + (str.charAt(i) - '0'));
        }
        System.out.println(N);
    }
}
