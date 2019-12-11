package com.leetcode.problem;

import java.util.*;

public class ConcatenatedWords {
    public static void main(String[] args) {
        ConcatenatedWords concatenatedWords = new ConcatenatedWords();
        String[] words = new String[]{"cats","dog", "catsdogcats"};
        System.out.println(concatenatedWords.findAllConcatenatedWordsInADict(words));
    }
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (s1, s2)->{
            if(s1.length() == s2.length()){
                return s1.compareTo(s2);
            }else{
                return s1.length() - s2.length();
            }
        });
        Set<String> set = new HashSet<>();
        List<String> ansList = new ArrayList<>();
        for(String s : words){
            if(canMake(s, set)){
                ansList.add(s);
            }
            set.add(s);
        }
        return ansList;
    }
    private boolean canMake(String word, Set<String> set){
        if(word.length() == 0){
            return false;
        }
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for(int l = 1; l <= word.length(); l++){
            for(int i = 0; i < l; i++){
                dp[l] |= dp[i] && set.contains(word.substring(i, l));
                if(dp[l] == true){
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }
}
