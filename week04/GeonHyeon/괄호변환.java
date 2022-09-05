package com.kim.algoStudy;

import java.util.Stack;

public class 괄호변환 {
    //스택을 이용해 체크
    static boolean check(String str){
        Stack<Character> st = new Stack<>();
        for (char c : str.toCharArray()) {
            if(c=='(') {
                st.push('(');
            }
            else{
                if(st.isEmpty()) {
                    return false;
                }
                else {
                    st.pop();
                }
            }
        }
        if(st.isEmpty()) return true;
        else return true;
    }

    public String solution(String p) {
//        StringBuilder sb = new StringBuilder(); //정답 출력용
        String answer = "";
        //조건1번. 빈문자열이면 바로리턴
        if(p.equals("")) return answer;
        int idx = findIdx(p);
        //u와 v로 나누기
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        //다음조건. 올바르고&&균형잡힌 문자열이면 solution(v)를 붙여서 리턴
        if(check(u)) answer = (u + solution(v));
        else{
            //조건. 처음에 (를 붙이고, 제일 뒤에 )를 붙인다.
            //이후 첫번째와 마지막 문자를 제거한다.
//            sb.append("(");
//            sb.append(solution(v));
//            sb.append(")");
            answer = "(";
            answer += solution(v);
            answer += ")";
            u = u.substring(1, u.length() - 1);
            String tmp = "";
            for (int i = 0; i < u.length(); i++) {
                if(u.charAt(i) == '(' ) tmp +=")";
                else tmp+="(";
            }
            answer+=tmp;
        }

        return answer;
    }

    static int findIdx(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            //왼쪽괄호면 -1 오른쪽 괄호면 +1. 0이면 균형을 이룬다고 볼 수 있다.
            if (str.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            //균형잡힌괄호상태라면 그 상태의 인덱스를 반환
            if(cnt == 0) return i;
        }
        return cnt;
    }



}
