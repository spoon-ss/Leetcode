package com.leetcode.problem;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {


        if(s == null || s.length () == 0){
            return 0;
        }
        int[] map = new int[26];
        char[] sCharArray = s.toCharArray();
        int i, j = 0;
        int ans = 0;
        for(i = 0; i < sCharArray.length; i++){
            while(j < sCharArray.length){
                int changeCount = getResult(map);
                if(changeCount > k){
                    break;
                }
                ans = Math.max(ans, j - i);
                map[sCharArray[j] - 'A'] += 1;
                j += 1;
            }
            int changeCount = getResult(map);
            if(changeCount <= k){
                ans = Math.max(ans, j - i);
            }
            map[sCharArray[i] - 'A'] -= 1;
        }
        return ans;
    }

    private int getResult(int[] map){
        int count = 0;
        int max = 0;
        for(int i = 0; i < map.length; i++){
            count += map[i];
            max = Math.max(max, map[i]);
        }
        return count - max;
    }
}
