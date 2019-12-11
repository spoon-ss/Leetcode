package com.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReverseKArray {
    public static void main(String[] args){
        ReverseKArray reverseKArray = new ReverseKArray();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        reverseKArray.reverseK(nums, 5);
        System.out.println(Arrays.toString(nums));
    }
    public void reverseK(int[] nums, int k){
        if(nums == null || nums.length == 0){
            return;
        }
        if(k <= 0){
            return;
        }
        for(int i = 0; i < nums.length; i += k){
            int j = Math.min(i + k - 1, nums.length - 1);
            reverse(nums, i, j);
        }
    }

    private void reverse(int[] nums, int left, int right){
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left += 1;
            right -= 1;
        }
    }
}
