package com.leetcode.basic;

class Heap{
    public static void main(String[] args) {
        Heap heap = new Heap(4);
        heap.push(1);
        heap.push(3);
        heap.push(0);
        heap.push(1);
        while(!heap.isEmpty()){
            System.out.println(heap.pop());
        }
    }
    private int[] nums;
    private int size;
    private int capacity;

    public Heap(int capacity){
        this.nums = new int[capacity + 1];
        this.size = 0;
        this.capacity = capacity;
    }
    public int size(){
        return this.size;
    }
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        int ans = nums[1];
        nums[1] = nums[size];
        this.size -= 1;
        int candidateI = 1;
        int candidateV = nums[1];
        while(candidateI <= size / 2){
            int childLI = Math.min(candidateI * 2, size);
            int childRI = Math.min(candidateI * 2 + 1, size);
            int childLV = nums[childLI];
            int childRV = nums[childRI];
            if(candidateV < childLV && candidateV < childRV){
                break;
            }
            if(childLV < childRV){
                nums[candidateI] = childLV;
                nums[childLI] = candidateV;

                candidateV = childLV;
                candidateI = childLI;
            }else{
                nums[candidateI] = childRV;
                nums[childRI] = candidateV;

                candidateV = childRV;
                candidateI = childRI;
            }
        }
        return ans;

    }
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        return nums[1];
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean isFull(){
        return size == capacity;
    }
    public void push(int val){
        if(isFull()){
            throw new RuntimeException();
        }
        this.size += 1;
        nums[size] = val;
        int candidateV = val;
        int candidateI = size;
        while(candidateI > 1){
            int parentI = candidateI / 2;
            int parentV = nums[parentI];
            if(candidateV >= parentV){
                break;
            }
            nums[parentI] = candidateV;
            nums[candidateI] = parentV;
            candidateI /= 2;
        }
        return;
    }

}
