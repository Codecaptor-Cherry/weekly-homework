package com.ssafy.cfj.binarysearch;

import java.util.*;

public class SearchLyrics  {
    public static ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> reversed = new ArrayList<ArrayList<String>>();
    
    public static void main(String[] args) {
    	
    	String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
    	String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
    	
        for (int len = 0; len <= 100_000; len++) {
            array.add(new ArrayList<String>());
            reversed.add(new ArrayList<String>());
        }
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            array.get(word.length()).add(word);
            reversed.get(word.length()).add(reverse(word));
        }
        
        for (int len = 0; len <= 100_000; len++) {
            Collections.sort(array.get(len));
            Collections.sort(reversed.get(len));
        }
        
        ArrayList<Integer> answers = new ArrayList<>();
        for (int j = 0; j < queries.length; j++) {
            String query = queries[j];
            if (query.charAt(0) != '?') {
                String front = query.replaceAll("\\?", "a");
                String rear = query.replaceAll("\\?", "z");
                answers.add(count(array.get(query.length()), front, rear));
            } else {
                String reversedQuery = reverse(query);
                String front = reversedQuery.replaceAll("\\?", "a");
                String rear = reversedQuery.replaceAll("\\?", "z");
                answers.add(count(reversed.get(reversedQuery.length()), front, rear));
            }
        }
        
        int[] answerList = new int[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            answerList[i] = answers.get(i);
            
            System.out.print(answerList[i] + " ");
        }
//        return answerList;
    }
    
    public static int count(ArrayList<String> array, String front, String rear) {
        int frontMid = 0, rearMid = 0;
        
        // front 
        int start = 0;
        int end = array.size();
        
        while (start < end) {
            frontMid = (start + end) / 2;
            if (array.get(frontMid).compareTo(front) >= 0) end = frontMid;
            else start = frontMid + 1;
        }
        
        frontMid = end;
        
        // rear
        start = 0;
        end = array.size();
        
        while (start < end) {
            rearMid = (start + end) / 2;
            if (array.get(rearMid).compareTo(rear) >= 0) end = rearMid;
            else start = rearMid + 1;
        }
        
        rearMid = end;
        
        return rearMid - frontMid;
    }
    
    public static String reverse(String word) {
        return new StringBuffer(word).reverse().toString();
    }
}