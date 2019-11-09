package com.handen;

import java.util.EmptyStackException;

class MyStack<T> {
    private Node topNode;

    public synchronized void push(T element) {
        if(topNode == null)
            topNode = new Node(element);
        else {
            Node currentNode = new Node(element);
            currentNode.previousNode = topNode;
            topNode = currentNode;
        }
    }

    public synchronized T pop() {
        if(topNode != null) {
            Node currentNode = topNode;
            topNode = topNode.previousNode;
            return currentNode.value;
        }
        else
            throw new EmptyStackException();
    }

    public T peek() {
        if(topNode != null)
            return topNode.value;
        else
            throw new EmptyStackException();
    }

    public boolean isEmpty() {
        return topNode == null;
    }

    private class Node {
        private T value;
        private Node previousNode;

        private Node(T element) {
            this.value = element;
        }
    }
}