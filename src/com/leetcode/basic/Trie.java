package com.leetcode.basic;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private class TrieNode{
        public char c;
        public Map<Character, TrieNode> children;
        public boolean isEnd;
        public TrieNode(){
            this.c = 0;
            this.isEnd = false;
            this.children = new HashMap<Character, TrieNode>();
        }
        public TrieNode(char _c, boolean _isEnd){
            this.c = _c;
            this.isEnd = _isEnd;
            this.children = new HashMap<Character, TrieNode>();
        }
    }
    private TrieNode dummyRoot;
    public Trie(){
        this.dummyRoot = new TrieNode();
    }

    private TrieNode find(String s){
        if(s == null || s.length() == 0){
            return null;
        }
        TrieNode previousNode = this.dummyRoot;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            TrieNode currentNode = previousNode.children.get(c);
            if(currentNode == null){
                return null;
            }
            previousNode = currentNode;
        }
        return previousNode;
    }

    public TrieNode insert(String s){
        if(s == null || s.length() == 0){
            return this.dummyRoot;
        }
        TrieNode previousNode = this.dummyRoot;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            TrieNode currentNode = previousNode.children.get(c);
            if(currentNode == null){
                currentNode = new TrieNode(c, false);
                previousNode.children.put(c, currentNode);
            }
            previousNode = currentNode;
        }
        previousNode.isEnd = true;
        return previousNode;
    }

    public boolean startWith(String s){
        TrieNode endNode = find(s);
        if(endNode == null){
            return false;
        }
        return true;
    }

    public boolean findString(String s){
        TrieNode endNode = find(s);
        if(endNode == null){
            return false;
        }
        if(endNode.isEnd == false){
            return false;
        }
        return true;
    }
}
