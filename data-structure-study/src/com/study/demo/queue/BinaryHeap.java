package com.study.demo.queue;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

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
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public void makeEmpty() {

    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private AnyType[] array;

    private void percolateDown(int hole) {

    }

    private void buildHeap() {

    }

    private void enlargeArray(int newSize) {

    }
}
