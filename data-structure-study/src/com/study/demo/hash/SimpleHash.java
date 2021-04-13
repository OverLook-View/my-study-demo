package com.study.demo.hash;

import java.util.TreeMap;

public class SimpleHash {

    public static void main(String[] args) {
        System.out.println(hash1("aaa", 1007));
        System.out.println(hash2("aaa", 1007));
        System.out.println(hash3("aaa", 1007));
    }

    public static int hash1(String key, int tableSize) {
        int hashVal = 0;

        for (int i = 0; i < key.length(); i++) {
            hashVal += key.charAt(i);
        }
        return hashVal % tableSize;
    }

    public static int hash2(String key, int tableSize) {
        return (key.charAt(0) + 27 * key.charAt(1) + 729 * key.charAt(2)) % tableSize;
    }

    public static int hash3(String key, int tableSize) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = 37 * hashVal + key.charAt(i);
        }
        hashVal %= tableSize;
        if (hashVal<0)
            hashVal+=tableSize;
        return hashVal;
    }
}
