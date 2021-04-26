package com.study.demo.hash;

import com.study.demo.utils.PrimeUtil;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theLists;
    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[PrimeUtil.nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myHash(x)];
        if (whichList.contains(x)) {
            whichList.add(x);

            if (++currentSize > theLists.length)
                rehash();
        }
    }

    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myHash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myHash(x)];
        return whichList.contains(x);
    }

    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    private void rehash() {
        List<AnyType>[] oldLists = theLists;
        theLists = new List[PrimeUtil.nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++) {
            theLists[j] = new LinkedList<>();
        }
        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++) {
            for (AnyType item : oldLists[i]) {
                insert(item);
            }
        }
    }

    private int myHash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0)
            hashVal += theLists.length;
        return hashVal;
    }

    private static int nextPrime(int n) {
        //todo
        return 0;
    }

    private static boolean isPrime(int n) {
        //todo
        return false;
    }
}
