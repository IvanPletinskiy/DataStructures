package com.handen;

import java.util.Comparator;

/*
1. Вставляем всегда красный узел
2. При flipColors головной узел флипа окрашивается в красный
3. Вершина всегда чёрная, отсутствующие элементы тоже
 */

public class RBTree<K, V> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Comparator<String> comparator = String::compareTo;

    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = RED;
    }

    private Node put(Node node, K key, V value) {
        if(node == null)
            return new Node(key, value, RED);
        if(comparator.compare((String) key, (String )node.key) > 0) {
            put(node.right, key, value);
        }
        else
            if(comparator.compare((String) key, (String )node.key) < 0) {
                put(node.left, key, value);
            }
            else
                node.value = value;
        if(isRed(node.left) && !isRed(node.right))
            node = rotateLeft(node);
        if(isRed(node.left) && isRed(node.left))
            node = rotateRight(node);
        if(isRed(node.left) && isRed(node.right))
            flipColors(node);
        return node;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
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

    private boolean isRed(Node node) {
        if(node == null)
            return false;
        return node.color == RED;
    }

    private class Node {
        private K key;
        private V value;
        private boolean color;
        private Node left, right;

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }
}