package com.leetcode.problem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumVariant {
    static public void main(String[] args){
        TwoSumVariant test = new TwoSumVariant();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(0);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(-2);

        System.out.println(test.getResult(list, 0));
    }
    public List<List<Integer>> getResult(List<Integer> list, int k){
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        List<List<Integer>> ansList = new ArrayList<>();
        for(int n : list){
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        for(int n : list){
            if(!countMap.containsKey(n)){
                continue;
            }
            if(!countMap.containsKey(k - n)){
                continue;
            }
            List<Integer> pair = new ArrayList<>();
            pair.add(n);
            pair.add(k - n);
            if(n == k - n && countMap.get(n) == 1){
                countMap.remove(n);
                continue;
            }
            ansList.add(pair);
            if(countMap.get(n) > 1){
                countMap.put(n, countMap.get(n) - 1);
            }else{
                countMap.remove(n);
            }
            if(countMap.get(k - n) > 1){
                countMap.put(k - n, countMap.get(k - n) - 1);
            }else{
                countMap.remove(k - n);
            }
        }
        return ansList;
    }
}
