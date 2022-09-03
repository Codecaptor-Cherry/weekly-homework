package com.ssafy.cfj.bfsdfs;

import java.util.*;


public class ParenthesisConversion {
    public static void main(String[] args) {
        String p = ")(";
    	
    	System.out.println(makeCorrect(p));

    }
    
    public static String makeCorrect(String p) {
        if (p.length() == 0) return "";
        
        int index = findU(p);
        String u = makeU(p, index);
        String v = makeV(p, index);
        
        System.out.println(u + " " + v);
        
        if (checkU(u)) {
            String next = makeCorrect(v);
            return u + next;
        } else {
            String temp = "(";
            temp += makeCorrect(v);
            temp += ")";
            temp += reverse(u);
            return temp;
        }
    }
    
    public static String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        char[] array = u.toCharArray();
        int len = u.length();
        
        for (int i = 1; i < len - 1; i++) {
            if (array[i] == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        
        return sb.toString();
    }
    
    public static boolean checkU(String u) {
        char[] array = u.toCharArray();
        if (array[0] != '(' || array[array.length - 1] != ')') {
            return false;
        }
        
        int check = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') check++;
            else check--;
            
            if (check < 0) return false;
        }
        
        return true;
    }
    
    public static int findU(String p) {
        char[] array = p.toCharArray();
        int index = 0;
        
        int numOfLeft = 0;
        int numOfRight = 0;
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') numOfLeft++;
            else numOfRight++;
            
            if (numOfLeft > 0 && numOfRight > 0) {
                if (numOfLeft == numOfRight) {
                    index = i;
                    break;
                }
            }
        }
        
        return index;
    }
    
    public static String makeU(String p, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.substring(0, index + 1));
        
        return sb.toString();
    }
    
    public static String makeV(String p, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.substring(index + 1, p.length()));
        
        return sb.toString();
    }
}