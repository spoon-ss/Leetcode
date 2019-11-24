package com.leetcode.problem;

import javax.swing.text.MaskFormatter;

public class MaximumLengthofRepeatedSubarray {
    public static void main(String[] args) {
        int[] A = {1, 0, 0, 0, 1, 0, 0, 1, 0, 0};
        int[] B = {0, 1, 1, 1, 0, 1, 1, 1, 0, 0};

        MaximumLengthofRepeatedSubarray test =
                new MaximumLengthofRepeatedSubarray();
        System.out.println(test.findLength(A, B));
    }

    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[2][B.length + 1];
        int max = 0;
        int oldI = 0;
        int newI = 1;
        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < B.length + 1; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[newI][j] = Math.max(1, dp[oldI][j - 1] + 1);
                    max = Math.max(dp[newI][j], max);
                } else {
                    dp[newI][j] = 0;
                }
            }
            newI = newI == 1 ? 0 : 1;
            oldI = oldI == 0 ? 1 : 0;
        }
        return max;

    }
}
