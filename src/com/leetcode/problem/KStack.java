package com.leetcode.problem;

public class KStack {
    private int[] nums;
    private int[] indexes;
    private int cap;

    public KStack(int cap, int k){
        this.nums = new int[k * cap];
        this.cap = cap;
        this.indexes = new int[k];
        for(int i = 0; i < this.indexes.length; i++){
            this.indexes[i] = i * cap;
        }
    }
    public void push(int num, int value){
        int index = this.indexes[num];
        this.nums[index] = value;
        this.indexes[num] += 1;

    }

    public int peek(int num){
        if(isEmpty(num)){
            return -1;
        }
        int index = this.indexes[num];
        return this.nums[index];

    }
    public int pop(int num){
        if(isEmpty(num)){
            return -1;
        }
        int index = this.indexes[num] - 1;
        int value = this.nums[index];
        this.nums[index] = 0;
        this.indexes[num] -= 1;
        return value;

    }
    public boolean isEmpty(int num){
        int index = this.indexes[num];
        if(index - num * this.cap == 0){
            return true;
        }
        return false;
    }

    public void print(){
        for(int i = 0; i < this.indexes.length; i++){
            System.out.println("It is the " + i +"th stack");
            for(int j = i * this.cap; j < (i + 1) * this.cap; j++){
                System.out.print(this.nums[j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

}
