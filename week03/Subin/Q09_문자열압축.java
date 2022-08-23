
class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) { // 압축 단위
        	int compress = 0; // 압축한 문자열의 길이
        	int size = 1; // 반복 횟수
        	
        	// 압축이 가능한지 체크하기 위해 필요한 인덱스
        	int sidx = 0;
        	int cidx = i;
        	
        	for (int j = i; j < len; j += i) {
        		for (int k = 0; k < i; k++) {
        			// 범위를 벗어나거나 문자열이 다르다면 더 이상 압축하지 못함
        			if (cidx + k >= len || s.charAt(sidx + k) != s.charAt(cidx + k)) {
        				compress += i + getDigit(size); // 문자열의 길이 더하기
        				size = 1; // 반복 횟수 초기화
        				break;
        			}
        			
        			if (k == i - 1) size++; // 모든 문자열이 일치하면 반복 횟수 증가
        		}
        		
        		// 다음 문자열 확인
        		sidx += i;
        		cidx += i;
        	}
			compress += len - sidx + getDigit(size); // 문자열의 길이 더하기
        	answer = Math.min(answer, compress); // 최소값 갱신
        }
        
        return answer;
    }

    // 반복 횟수의 자릿수 구하기
	private int getDigit(int num) {
		if (num == 1) return 0; // 1은 생략함
		
		int digit = 0;
		
		while (num > 0) {
			num /= 10;
			digit++;
		}
		
		return digit;
	}
}

public class Q09_문자열압축 {

	public static void main(String[] args) {
		Solution sol = new Solution();

		System.out.println(sol.solution("aabbaccc"));
		System.out.println(sol.solution("ababcdcdababcdcd"));
		System.out.println(sol.solution("abcabcdede"));
		System.out.println(sol.solution("abcabcabcabcdededededede"));
		System.out.println(sol.solution("xababcdcdababcdcd"));
	}
   
}
