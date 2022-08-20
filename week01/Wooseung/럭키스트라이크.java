package codingtestproblem.p3.day0816;

import java.util.Scanner;

public class 럭키스트라이크 {  // 322

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        int left = 0;
        int right = 0;
        int num = str.length();

        // 홀수가 있을 때
//        if(num % 2 == 1) {  
//            for(int i = 0; i < num / 2; i++) {
//                left += str.charAt(i) - '0';
//            }
//
//            for(int i = num / 2 + 1; i < str.length(); i++) {
//                right += str.charAt(i) - '0';
//            }
//        }else {
//            for(int i = 0; i < num / 2; i++) {
//                left += str.charAt(i) - '0';
//            }
//
//            for(int i = num / 2; i < str.length(); i++) {
//                right += str.charAt(i) - '0';
//            }
//        }

        for(int i = 0; i < num / 2; i++) {  // 왼쪽 부분의 총 합
            left += str.charAt(i) - '0';
        }

        for(int i = num / 2; i < str.length(); i++) {   // 오른쪽 부분의 총합
            right += str.charAt(i) - '0';
        }

        if(left == right) { // 왼쪽과 오른쪽의 총 합이 같으면 LUCKY 출력
            System.out.println("LUCKY");
        }else { // 다르면 READY
            System.out.println("READY");
        }

    }

}
