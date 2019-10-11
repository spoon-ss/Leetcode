package com.leetcode;

import java.util.Arrays;

class Solution {
    private class Tuple{
        public int start;
        public int end;
        public int sum;
        public Tuple(int _start, int _end, int _sum){
            this.start = _start;
            this.end = _end;
            this.sum = _sum;
        }

    }
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if(nums.length < 3 * k){
            return new int[3];
        }
        int sum = 0;
        for(int i = 0; i < k - 1; i++){
            sum += nums[i];
        }
        Tuple[][] dp = new Tuple[nums.length + 1][3];
        for(int i = k; i < dp.length; i++){
            sum += nums[i - 1];

            int previousSum = getSum(dp[i - 1]);
            int currentSum = getTwoLargestSum(dp[i - k]) + sum;
            if(currentSum > previousSum){
                for(int j = 0; j < 3; j++){
                    dp[i][j] = dp[i - k][j];
                }
                pushMax(dp[i], new Tuple(i - k, i - 1, sum));
            }else{
                for(int j = 0; j < 3; j++){
                    dp[i][j] = dp[i - 1][j];
                }
            }
            sum -= nums[i - k];
        }
        int[] ans = new int[3];
        for(int i = 0; i < 3; i++){
            ans[i] = dp[dp.length - 1][i].start;
        }
        pushMax(dp[7], new Tuple(6, 7, 29));
        int i = -1;
        for(Tuple[] tuples : dp){
            System.out.println(i);
            i += 1;
            for(Tuple tuple : tuples){
                if(tuple == null){
                    System.out.println("null");
                    continue;
                }
                System.out.print("start " + tuple.start);
                System.out.print("end " + tuple.end);
                System.out.print("sum " + tuple.sum);
                System.out.println("");
            }
        }
        Arrays.sort(ans);
        return ans;
    }

    private void pushMax(Tuple[] tuples, Tuple tuple){
        if(tuples[0] == null || tuple.sum > tuples[0].sum){
            tuples[2] = tuples[1];
            tuples[1] = tuples[0];
            tuples[0] = tuple;
        }else if(tuples[1] == null || tuple.sum > tuples[1].sum){
            tuples[2] = tuples[1];
            tuples[1] = tuple;
        }else if(tuples[2] == null || tuple.sum > tuples[2].sum){
            tuples[2] = tuple;
        }
        return;
    }
    private int getSum(Tuple[] tuples){
        int sum = 0;
        for(Tuple tuple : tuples){
            if(tuple == null){
                continue;
            }
            sum += tuple.sum;
        }
        return sum;
    }
    private int getTwoLargestSum(Tuple[] tuples){
        if(tuples[0] == null){
            return 0;
        }
        if(tuples[1] == null){
            return tuples[0].sum;
        }
        return tuples[0].sum + tuples[1].sum;
    }
}