package com.leetcode;
import com.leetcode.problem.KStack;
import com.leetcode.basic.Trie;
import com.leetcode.basic.UnionSet;

public class Main {

    public static void main(String[] args) {
        KStack test = new KStack(5, 3);
        System.out.println("push 0, 10");
        test.push(0,10);
        test.print();
        System.out.println("push 0, 11");
        test.push(0,11);
        test.print();
        System.out.println("push 1, 20");
        test.push(1, 20);
        test.print();
        System.out.println("push 1, 21");
        test.push(1,21);
        test.print();
        System.out.println("pop 0");
        test.pop(0);
        test.print();
        System.out.println("pop 1");
        test.pop(1);
        test.print();
        System.out.println("peek 1");
        test.peek(1);
        test.print();
        System.out.println("push 2 30");
        test.push(2, 30);
        test.print();
        System.out.println("pop 2");
        test.pop(2);
        test.print();

        System.out.println("Is 2 empty? " + test.isEmpty(2));
        test.print();
        System.out.println("Is 0 empty? " + test.isEmpty(0));
        test.print();
    }
}
