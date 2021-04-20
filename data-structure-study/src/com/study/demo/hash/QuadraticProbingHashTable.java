package com.study.demo.hash;

public class QuadraticProbingHashTable<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<AnyType>[] array;

    private int currentSize;

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    public void insert(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos))
            return;
        array[currentPos] = new HashEntry<>(x, true);
        if (currentSize > array.length / 2)
            rehash();
    }

    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            array[currentPos].isActive = false;
        }
    }

    private void allocateArray(int arraySize) {
        array = new HashEntry[nexPrime(arraySize)];
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myHash(x);
        while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }
        return currentPos;
    }

    private void rehash() {

    }

    private int myHash(AnyType x) {
        return 0;
    }

    private static int nexPrime(int n) {
        return 0;
    }

    private static boolean isPrime(int n) {
        return false;
    }

    private static class HashEntry<AnyType> {
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType element, boolean isActive) {
            this.element = element;
            this.isActive = isActive;
        }

        public HashEntry(AnyType element) {
            this(element, true);
        }
    }
}
