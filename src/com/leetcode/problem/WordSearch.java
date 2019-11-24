package com.leetcode.problem;

import java.util.*;

public class WordSearch {
    static public void main(String[] args){
        WordSearch test = new WordSearch();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList =
                Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        test.ladderLength(beginWord, endWord, wordList);
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(endWord);
        set.add(endWord);
        int level = 0;
        while(!queue.isEmpty()){
            List<String> list = new ArrayList<>();
            while(!queue.isEmpty()){
                String s = queue.poll();
                System.out.println(s);
                list.add(s);
            }
            for(String s : list){

                if(oneCharDiff(s, beginWord)){
                    return level + 1;
                }
                for(String newS : wordList){
                    if(set.contains(newS) || !oneCharDiff(s, newS)){
                        continue;
                    }
                    set.add(newS);
                    queue.offer(newS);
                }
                level += 1;
            }
        }
        return 0;
    }

    private boolean oneCharDiff(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        int diff = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diff += 1;
            }
            if(diff == 2){
                return false;
            }
        }
        if(diff == 1){
            return true;
        }
        return false;
    }
}
