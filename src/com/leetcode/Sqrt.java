package com.leetcode;

public class Sqrt {
    public int getSqrt(int n){
        if(n == 0 || n == 1){
            return n;
        }
        long left = 0;
        long right = n - 1;

        while(left + 1 < right){
            long mid = (left + right) / 2;
            if(mid * mid <= n){
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        if(left == right){
            return (int)left;
        }else{
            return right * right <= n ? (int)right : (int)left;
        }
    }
}
