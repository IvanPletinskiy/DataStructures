package com.handen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        solveFirstTask("(([{}]{}[])");
        solveFirstTask("([)]");
        solveSecondTask("1 + 2) * 3 - 4) * (5 - 6) ) )");
        solveThirdTask();
        solveFourthTask("E A S Y Q E S T I O N");
        solveFifthTask("E A S Y Q U T I O N");
    }

    private static void solveFirstTask(String input) {
        System.out.println("First task: ");
        String s = input;
        MyStack<Character> stack = new MyStack<>();
        boolean result = true;
        for(char c : s.toCharArray()) {
            if(c == '[' || c == '(' || c == '{')
                stack.push(c);
            else {
                if(stack.isEmpty()) {
                    result = false;
                    break;
                }
                char currentChar = stack.pop();
                if((c == ']' && currentChar != '[') ||
                        (c == ')' && currentChar != '(') ||
                        (c == '}' && currentChar != '{')) {
                    result = false;
                    break;
                }
            }
        }
        System.out.println(result);
    }

    private static void solveSecondTask(String input) {
        System.out.println("Second task: ");
        String s = input;
        MyStack<Character> stack = new MyStack<>();
        boolean isPlacingBracets = false;
        int bracersCount = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);
            stack.push(currentChar);
            if(currentChar == ')')
                isPlacingBracets = false;
            if(currentChar == '+' || currentChar == '-' || currentChar == '*' ||  currentChar == '/') {
                isPlacingBracets  = true;
                bracersCount++;
            }
            if(currentChar >= '0' && currentChar <= '9' && isPlacingBracets) {
                for(int j = 0; j < bracersCount; j++) {
                    if(j != 0)
                        stack.push(' ');
                    stack.push('(');
                }
                bracersCount = 0;
            }
        }

        while(!stack.isEmpty())
            System.out.print(stack.pop());
    }

    private static void solveThirdTask() throws IOException {
        System.out.println("\nВведите условие 3 задания с клавиатуры");
        System.out.println("Third task: \n");
        int k = Integer.parseInt(reader.readLine());
        MyQueue<String> queue = new MyQueue<>();
        String s;
        do {
            s = reader.readLine();
            if(!s.equals(""))
                queue.push(s);
        }
        while(!s.equals(""));

        while(queue.size() != k)
            queue.pop();
        System.out.println(queue.pop());
    }

    private static void solveFourthTask(String input) {
        System.out.println("Fourth task: ");
        MyBinaryTree<String, Integer> binaryTree = new MyBinaryTree<>();
        String[] keys = input.split(" ");
        for(int i = 0; i < keys.length; i++) {
            binaryTree.put(keys[i], i);
        }
        binaryTree.print();
    }

    private static void solveFifthTask(String s) {
        System.out.println("\nFifth task: ");
        OldTree<String, Integer> rbTree = new OldTree<>();
        String[] keys = s.split(" ");
        for(int i = 0; i < keys.length; i++) {
            rbTree.put(keys[i], i);
        }
        rbTree.print();
    }
}