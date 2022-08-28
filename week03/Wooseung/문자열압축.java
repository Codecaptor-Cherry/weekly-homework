import java.util.*;

class Solution {

    public int solution(String s) {
      
        int answer = s.length();

        for (int step = 1; step < s.length() / 2 + 1; step++) { // 1개 부터 문자열의 절반까지 압축 범위 설정
            String compressed = ""; // 압축한 문자열을 담을 compressed 생성
            String prev = s.substring(0, step); // 앞에서부터 압축 범위 만큼의 문자열 추출 
            int cnt = 1;
            // 압축 범위 만큼 증가시키며 이전 문자열과 비교 
            for (int j = step; j < s.length(); j += step) {
                // 문자열이 반복된다면 압축해야하므로 cnt증가
                String sub = "";
                for (int k = j; k < j + step; k++) {
                    if (k < s.length()) {
                      sub += s.charAt(k);
                    }
                }
                if (prev.equals(sub)) {
                  cnt += 1;
                }|
                // 다른 문자열이 나왔다면
                else {  // 카운트된 숫자와 반복되는 문자열을 압축한 문자열을 담는 compressed에 넣어준다
                    compressed += (cnt >= 2)? cnt + prev : prev;
                    sub = "";
                    for (int k = j; k < j + step; k++) {
                        if (k < s.length()) {
                          sub += s.charAt(k);
                        }
                    }
                    prev = sub; // 다시 상태 초기화
                    cnt = 1;
                }
            }
            // 남아있는 문자열에 대해서 처리
            compressed += (cnt >= 2)? cnt + prev : prev;
            // 만들어지는 압축 문자열이 가장 짧은 것이 정답
            answer = Math.min(answer, compressed.length());
        }
        return answer;
    } 
}
