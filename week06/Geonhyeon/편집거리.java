package com.kim.algoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//최소 교정 비용 문제.
// 빈문자열일 경우를 대비해 단어의길이+1만큼 배열 생성
//삽입 삭제 치환 중 가장 작은값에 +1
public class 편집거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String wordA = br.readLine();
        String wordB = br.readLine();
        int[][] table = new int[wordA.length() + 1][wordB.length() + 1];

        //사전 초기화 작업. 첫번째 행과 첫번째 열 초기화
        for (int i = 0; i < wordA.length() + 1; i++) {
            table[i][0] = i;
        }
        for (int i = 0; i < wordB.length() + 1; i++) {
            table[0][i] = i;
        }


        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                //같다면 => 그냥 대각선왼쪽값넣고 비용발생안하니 넘어간다.
                if (wordA.charAt(i - 1) == wordB.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1];
                } else {
                    table[i][j] = 1+Math.min(table[i - 1][j - 1], Math.min(table[i - 1][j], table[i][j - 1]));
                }
            }
        }

        /* 테스트 출력*/
//        for (int i = 0; i < table.length; i++) {
//            for (int j = 0; j < table[0].length; j++) {
//                System.out.print(table[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println(table[wordA.length()][wordB.length()]);
    }
}
