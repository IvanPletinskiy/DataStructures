package com.handen;

import java.util.Comparator;

class OldTree<K, V> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Comparator<K> comparator = Comparator.comparing(o -> ((String) o)); //Компаратор используется для сравнения ключей

    private Node put(Node node, K key, V value) {
        if(node == null) {
            return new Node(key, value, RED);
        }
        if(comparator.compare(key, node.key) > 0)
            node.right = put(node.right, key, value);
        else
            if(comparator.compare(key, node.key) < 0)
                node.left = put(node.left, key, value);
            else
                node.value = value;
        if(isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if(isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if(isRed(node.left) && isRed(node.right))
            flipColors(node);
        return node;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK; //TODO Почему нужно окрашивать корень в чёрный?
    }

    private boolean isRed(Node node) {
        if(node == null)
            return false;
        return node.color == RED;
    }

    private Node rotateLeft(Node head) {
        Node x = head.right;
        head.right = x.left;
        x.left = head;
        x.color = head.color;
        head.color = RED;
        return x;
    }

    private Node rotateRight(Node head) {
        Node x = head.left;
        head.left = x.right;
        x.right = head;
        x.color = head.color;
        head.color = RED;
        return x;
    }

    private void flipColors(Node head) {
        head.color = RED;
        head.left.color = BLACK;
        head.right.color = BLACK;
    }

    public void print() {
        int level = 0;
        System.out.println();
        MyQueue<Pair<Node, Integer>> queue = new MyQueue<>();
        queue.push(new Pair(root, level));
        while(queue.size() != 0) {
            Pair<Node, Integer> current = queue.pop();
            if(current.snd > level) {
                level++;
                System.out.print("\n");
            }
            System.out.print(current.fst.key);
            if(current.fst.color == RED)
                System.out.print("(RED)" + " ");
            else
                System.out.print("(BLACK)" + " ");
            if(current.fst.left != null)
                queue.push(new Pair<>(current.fst.left, current.snd + 1));
            if(current.fst.right != null)
                queue.push(new Pair<>(current.fst.right, current.snd + 1));
        }
    }

    private class Node {
        private Node left, right;
        private K key;
        private V value;
        boolean color;

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }
}
