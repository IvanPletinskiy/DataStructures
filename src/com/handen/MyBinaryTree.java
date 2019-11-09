package com.handen;

import java.util.Comparator;

class MyBinaryTree<K, V> {
    private Comparator<K> comparator = Comparator.comparing(o -> ((String) o));  //Компаратор используется для сравнения ключей
    private Node root;

    public V get(K key) {
        if(root != null)
            return get(root, key);
        else
            return null;
    }

    private V get(Node node, K key) {
        if(node == null)
            return null;

        if(comparator.compare(key, node.key) > 0)
            return get(node.right, key);
        else
            if(comparator.compare(key, node.key) < 0)
                return get(node.left, key);
            else
                return node.value;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if(node == null) {
            return new Node(key, value);
        }
        if(comparator.compare(key, node.key) > 0)
            node.right = put(node.right, key, value);
        else
            if(comparator.compare(key, node.key) < 0)
                node.left = put(node.left, key, value);
            else {
                node.value = value;
            }
        return node;
    }

    private class Node {
        private Node left, right;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
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
            System.out.print(current.fst.key + " ");
            if(current.fst.left != null)
                queue.push(new Pair<>(current.fst.left, current.snd + 1));
            if(current.fst.right != null)
                queue.push(new Pair<>(current.fst.right, current.snd + 1));
        }
    }
}