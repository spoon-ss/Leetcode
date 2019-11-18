package com.leetcode;

import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] args){
        BasicCalculator test = new BasicCalculator();
        test.calculate("1 + 1");
    }
    private class Operator{
        public char sign;
        public int priority;
        public Operator(char sign){
            this.sign = sign;
            if(sign == '+' || sign == '-'){
                this.priority = 1;
            }else if(sign == '*' || sign == '/'){
                this.priority = 2;
            }else if(sign == '(' || sign == ')'){
                this.priority = 0;
            }
        }
    }
    public int calculate(String s) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Operator> operatorStack = new Stack<>();
        s = "(" + s +")";
        int num = 0;
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            if(c == ' '){
                i += 1;
                continue;
            }
            if(Character.isDigit(c)){
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    char digit = s.charAt(i);
                    num = 10 * num + (digit - '0');
                    i += 1;
                }
                operandStack.push(num);
                num = 0;
                continue;
            }

            if(c == '('){
                Operator op = new Operator(c);
                operatorStack.push(op);
            }else if(c == ')'){
                while(operatorStack.peek().sign != '('){
                    int rightInt = operandStack.pop();
                    int leftInt = operandStack.pop();
                    int result = doOperation(leftInt, rightInt, operatorStack.pop());
                    operandStack.push(result);
                }
                operatorStack.pop();
            }else{
                Operator op = new Operator(c);
                while(!operatorStack.isEmpty()
                        && operatorStack.peek().priority >= op.priority){
                    int rightInt = operandStack.pop();
                    int leftInt = operandStack.pop();
                    int result = doOperation(leftInt, rightInt, operatorStack.pop());
                    operandStack.push(result);
                }
                operatorStack.push(op);
            }
            i += 1;
        }
        return operandStack.pop();

    }
    private int doOperation(int opa1, int opa2, Operator op){
        if(op.sign == '+'){
            return opa1 + opa2;
        }else if(op.sign == '-'){
            return opa1 - opa2;
        }else if(op.sign == '*'){
            return opa1 * opa2;
        }else if(op.sign == '/'){
            return opa1 / opa2;
        }
        throw new UnsupportedOperationException();
    }
}
