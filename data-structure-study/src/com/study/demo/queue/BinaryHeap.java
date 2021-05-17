package com.study.demo.queue;

import java.lang.reflect.Array;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private AnyType[] array;

    public BinaryHeap() {
    }

    public BinaryHeap(int capacity) {
    }

    public BinaryHeap(AnyType[] items) {
    }

    public void insert(AnyType x) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }
        int hole = ++currentSize;
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];
        array[hole] = x;
    }

    public AnyType findMin() {
        return currentSize > 0 ? array[0] : null;
    }

    public AnyType deleteMin() {
        if (isEmpty())
//            throw new UnderflowException();
            return null;
        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0 && array.length == 0;
    }

    public void makeEmpty() {
        currentSize=0;
        Class<? extends Comparable> aClass = array[0].getClass();
        array= (AnyType[]) Array.newInstance(aClass,DEFAULT_CAPACITY);
    }

    private void percolateDown(int hole) {
        int child;
        AnyType tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    private void buildHeap() {

    }

    private void enlargeArray(int newSize) {

    }
}
