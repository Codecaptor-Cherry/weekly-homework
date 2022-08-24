package com.ssafy.cfj.implemantation;

public class CompressString {

	public static void main(String[] args) {
		System.out.println(Solution.solution("aabbaccc"));
	}

}

class Solution {
    public static int solution(String s) {
        /** Initialize the answer as the length of given string */
        int answer = s.length();
        
        /** size of slice ranges from 1 to half of the string's length */
        for (int slice = 1; slice <= s.length() / 2; slice++) {
            /** Initialize index and repeat times as 0*/
            int index = 0, rep = 0;
            /** Store the sliced string */
            String prev = s.substring(index, index + slice);
            /** Store the result */
            String result = "";
            
            /** while index is within the length */
            while(index < s.length()) {
                /** If the string could be sliced */
                if (index + slice <= s.length()) {
                    /** Current sliced string */
                    String current = s.substring(index, index + slice);
                    /** Compare current string with previous string */
                    if (prev.equals(current)) {
                        /** Increment the repeated number */
                        rep++;
                    } else {
                        /** Check if previous string repeated more than one time */
                        if (rep > 1) {
                            /** Append the number */
                            result += String.valueOf(rep);
                        }
                        /** Append the string */
                        result += prev;
                        /** Now current become prev string for next sliced one */
                        prev = current;
                        /** Current string is repeated one time */
                        rep = 1;
                    }
                    
                    /** Move */
                    index += slice;
                    
                    /** If reached to the end */
                    if (index == s.length()) {
                        if (rep > 1) {
                            result += String.valueOf(rep);
                        }
                        
                        result += prev;
                        /** Terminate loop */
                        break;
                    }
                } 
                /** If the string could not be sliced */
                else {
                    if (rep > 1) {
                        result += String.valueOf(rep);
                    }
                    
                    result += prev;
                    for (int i = index; i < s.length(); i++) {
                        result += s.charAt(i);
                    }
                    
                    break;
                }
            }
            
            answer = Math.min(answer, result.length());
        }
        
        
        return answer;
    }
}