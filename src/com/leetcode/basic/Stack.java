package com.leetcode.basic;

public class Stack {
    private int[] nums;
    private int size;

    public Stack(int size){
        this.nums = new int[size];
        this.size = 0;
    }
    public void push(int n){
        this.nums[size] = n;
        this.size += 1;
    }
    public int pop(){
        int value = this.nums[size - 1];
        this.size -= 1;
        return value;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    public boolean isFull(){
        return this.size == this.nums.length;
    }
    public int size(){
        return this.size;
    }
}
