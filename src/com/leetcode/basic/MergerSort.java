package com.leetcode.basic;

import java.util.Arrays;

public class MergerSort {

    static public void main(String[] args){
        MergerSort test = new MergerSort();
        int[] nums = {1, 3, 2, 3, 5, -1, 0};
        test.mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void mergeSort(int[] nums){
        if(nums == null || nums.length <= 1){
            return;
        }
        for(int l = 1; l < nums.length; l *= 2){
            for(int i = 0; i < nums.length; i += 2 * l){
                int l1 = i, r1 = l1 + l - 1;
                if(r1 >= nums.length){
                    continue;
                }
                int l2 = r1 + 1, r2 = l2 + l - 1;
                if(l2 >= nums.length){
                    continue;
                }
                if(r2 >= nums.length){
                    r2 = nums.length - 1;
                }
                mergeTwoArray(nums, l1, r1, l2, r2);
            }
        }
        return;
    }

    private void mergeTwoArray(int[] nums, int l1, int r1, int l2, int r2){
        while(l1 <= r1 && l2 <= r2){
            if(nums[l1] <= nums[l2]){
                l1 += 1;
            }else{
                int temp = nums[l2];
                for(int i = l2; i > l1; i--){
                    nums[i] = nums[i - 1];
                }
                nums[l1] = temp;
                l1 += 1;
                l2 += 1;
            }
        }
    }
}
