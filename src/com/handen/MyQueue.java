package com.handen;

/**
   Реализация очереди с помощью массива
 */
class MyQueue<T> {
    private Object[] elements;
    private int popIndex; //Индекс взятия элемента
    private int pushIndex; //Индекс вставки элемента
    private int size;

    public MyQueue() {
        elements = new Object[10]; //TODO Вопрос, нельзя создать массив new T[10], какой лучший вариант, что можно сделать?
        popIndex = 0;
        pushIndex = 0;
        size = 0;
    }

    public T pop() {
        if(popIndex == pushIndex)
            return null;
        size--;
        T element = (T) elements[popIndex];
        popIndex++;
        if(popIndex == elements.length)
            popIndex = 0;
        return element;
    }

    public synchronized void push(T value) {
        if(pushIndex == elements.length) { //Удваиваем размер массива и копируем элементы с предыдущего
            Object[] newArray = new Object[elements.length * 2];
            System.arraycopy(elements, popIndex, newArray, 0, elements.length - popIndex);
            pushIndex -= popIndex;
            popIndex = 0;
            elements = newArray;
        }
        elements[pushIndex] = value;
        pushIndex++;
        size++;
    }
    public int size() {
        return size;
    }
}
