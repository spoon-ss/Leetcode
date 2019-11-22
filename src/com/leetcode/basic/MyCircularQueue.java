package com.leetcode.basic;

class MyCircularQueue {

    private int[] nums;
    private int head;
    private int tail;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.nums = new int[k + 1];
        this.head = 0;
        this.tail = (head + 1) % this.nums.length;
    }


    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        nums[this.tail] = value;
        this.tail = (this.tail + 1) % this.nums.length;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        int value = this.nums[(this.head + 1) % this.nums.length];
        this.head = (this.head + 1) % this.nums.length;
        return true;

    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return this.nums[(this.head + 1) % this.nums.length];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return this.nums[(this.tail - 1 + this.nums.length) % this.nums.length];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (this.tail - this.head - 1 + this.nums.length) % this.nums.length == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return this.head == this.tail;
    }
}
