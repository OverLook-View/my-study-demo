package com.study.demo.hash;

public class CuckooHashTable<AnyType> {
    private static final double MAX_LOAD = 0.4;
    private static final int ALLOWED_REHASHES = 1;
    private static final int DEFAULT_TABLE_SIZE = 101;

    private final HashFamily<? super AnyType> hashFunctions;
    private final int numHashFunctions;
    private AnyType[] array;
    private int currentSize;

    public CuckooHashTable(HashFamily<? super AnyType> hf) {

    }

    public CuckooHashTable(HashFamily<? super AnyType> hf, int size) {

    }

    public void makeEmpty() {
        doClear();
    }

    public boolean contains(AnyType x) {

    }

    private int myHash(AnyType x, int which) {

    }

    private int findPos(AnyType x) {

    }

    public boolean remove(AnyType x) {

    }

    public boolean insert(AnyType x) {

    }

    private void expand() {

    }

    private void rehash() {

    }

    private void doClear() {

    }

    private void allocateArray(int arraySize) {
        array = (AnyType[]) new Object[arraySize];
    }
}
