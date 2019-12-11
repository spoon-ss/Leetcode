package com.leetcode.basic;

import java.util.*;

public class CoordinationDivide {

    static public void main(String[] args){
        CoordinationDivide coordinationDivide = new CoordinationDivide();
        coordinationDivide.addX(0);
        coordinationDivide.addY(0);
        coordinationDivide.addX(2);
        coordinationDivide.addY(2);

        System.out.println(coordinationDivide.getSection(-1, -1));
        System.out.println(coordinationDivide.getSection(1, -1));
        System.out.println(coordinationDivide.getSection(-1, 1));
        System.out.println(coordinationDivide.getSection(1, 1));
        System.out.println(coordinationDivide.getSection(0, 0));
        System.out.println(coordinationDivide.getSection(3, 3));
        System.out.println(coordinationDivide.getSection(-2, 3));
    }
    private List<Integer> xList;
    private List<Integer> yList;

    public CoordinationDivide(){
        this.xList = new ArrayList<>();
        this.yList = new ArrayList<>();
    }
    public void addX(int x){
        int insertIndex = Collections.binarySearch(xList, x);
        if(insertIndex >= 0){
            return;
        }
        insertIndex = -(insertIndex + 1);
        xList.add(insertIndex, x);

    }
    public void addY(int y){
        int insertIndex = Collections.binarySearch(yList, y);
        if(insertIndex >= 0){
            return;
        }
        insertIndex = -(insertIndex + 1);
        yList.add(insertIndex, y);
    }
    public int getSection(int x, int y){
        int row = Collections.binarySearch(yList, y);
        if(row < 0){
            row = -(row + 1);
        }else{
            row += 1;
        }

        int col = Collections.binarySearch(xList, x);
        if(col < 0){
            col = -(col + 1);
        }else{
            col += 1;
        }

        int ans = row * (xList.size() + 1);
        ans += col;
        return ans;
    }

}
