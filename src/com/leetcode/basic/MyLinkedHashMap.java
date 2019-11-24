package com.leetcode.basic;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyLinkedHashMap {
    static public void main(String[] args){
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        Iterator<Integer> iterator = linkedHashMap.keySet().iterator();
        iterator.remove();
        System.out.println(iterator.next());
    }

}
