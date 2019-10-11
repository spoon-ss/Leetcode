package com.leetcode;

import java.util.ArrayList;
import java.util.List;

class SnapshotArray {

    private List<Integer> nums;
    private List<List<Pair>> indexSetHistory;
    private int snapShotCount;
    private class Pair{
        public int from;
        public int to;
        public int snapShotCount;
        public Pair(int _from, int _to, int _snapShotCount){
            this.from = _from;
            this.to = _to;
            this.snapShotCount = _snapShotCount;
        }
    }

    public SnapshotArray(int length) {
        this.nums = new ArrayList<Integer>();
        for(int i = 0; i < length; i++){
            this.nums.add(0);
        }
        this.indexSetHistory = new ArrayList<List<Pair>>();
        for(int i = 0; i < length; i++){
            this.indexSetHistory.add(new ArrayList<Pair>());
        }
        this.snapShotCount = 0;
    }

    public void set(int index, int val) {
        int from = nums.get(index);
        Pair newSet = new Pair(from, val, this.snapShotCount);
        this.nums.set(index, val);
        this.indexSetHistory.get(index).add(newSet);
        return;
    }

    public int snap() {
        this.snapShotCount += 1;
        return snapShotCount - 1;
    }

    public int get(int index, int snap_id) {
        List<Pair> indexSetList = this.indexSetHistory.get(index);
        for(int i = indexSetList.size() - 1; i >= 0; i--){
            Pair set = indexSetList.get(i);
            if(set.snapShotCount<= snap_id){
                return set.to;
            }
        }
        return 0;
    }
}
