package com.leetcode.problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeBFS {
    class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public List<Double> AverageByLevel(TreeNode root){
        List<Double> ansList = new ArrayList<>();
        if(root == null){
            return ansList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<TreeNode> list = new ArrayList<>();
            while(!queue.isEmpty()){
                list.add(queue.poll());
            }
            int count = 0;
            int sum = 0;
            for(TreeNode current : list){
                count += 1;
                sum += current.val;
                if(current.left != null){
                    queue.offer(current.left);
                }
                if(current.right != null){
                    queue.offer(current.right);
                }
            }
            ansList.add(((double) sum) / ((double) count));
        }
        return ansList;

    }
}
