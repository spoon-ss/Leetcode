package com.leetcode.basic;

public class UnionSet {
    private int[] fathers;
    private int setCount;
    public UnionSet(int n){
        this.fathers = new int[n];
        for(int i = 0; i < this.fathers.length; i++){
            this.fathers[i] = i;
        }
        this.setCount = n;
    }
    private int find(int x){
        if(this.fathers[x] == x){
            return x;
        }
        int father = find(this.fathers[x]);
        this.fathers[x] = father;
        return father;
    }
    public void connect(int x1, int x2){
        int father1 = find(x1);
        int father2 = find(x2);
        if(father1 != father2){
            this.fathers[father2] = father1;
            this.setCount -= 1;
        }
        return;
    }
    public boolean isConnected(int x1, int x2){
        int boss1 = find(x1);
        int boss2 = find(x2);
        return boss1 == boss2;
    }

}
