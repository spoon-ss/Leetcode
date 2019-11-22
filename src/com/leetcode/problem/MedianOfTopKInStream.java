package com.leetcode.problem;

import java.util.*;

public class MedianOfTopKInStream {

    static public void main(String[] args){
        MedianOfTopKInStream test = new MedianOfTopKInStream(2);
        test.add(1);
        test.add(2);
        System.out.println(test.getMedian());

    }

    private PriorityQueue<Integer> minHeap;
    private int min;

    private int capacity;
    private List<Integer> list;

    private int count;

    public MedianOfTopKInStream(int k){
        this.minHeap = new PriorityQueue<>();
        this.min = 0;
        this.capacity = k;
        this.list = new ArrayList<>();
        this.count = 0;
    }

    public void add(int n){

        if(count < capacity){
            list.add(n);
            Collections.sort(list);
        }else if(count == capacity){
            for(int i = list.size() - 1, c = 0; c < capacity / 2; i--, c++){
                minHeap.offer(list.get(i));
            }
            minHeap.offer(n);
            min = minHeap.poll();
        }else{
            minHeap.offer(n);
            min = minHeap.poll();
        }
        count += 1;


    }
    public double getMedian(){
        if(count <= 0){
            throw new NoSuchElementException();
        }
        double median = 0;
        if(count <= capacity){
            if(count % 2 == 0){
                median = (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2.0;
            }else{
                median = list.get(list.size() / 2);
            }
        }else{
            if(capacity % 2 == 0){
                median = (min + minHeap.peek()) / 2.0;
            }else{
                median = min;
            }
        }
        return median;
    }
}
