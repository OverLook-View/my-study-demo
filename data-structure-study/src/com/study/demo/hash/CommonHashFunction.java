package com.study.demo.hash;

public class CommonHashFunction {

    public static int universalHash(int x, int A, int B, int P, int M) {
        return (int) (((((long) A * x) + B) % P) % M);
    }

    public static final int DIGS = 31;
    public static final int mersennep = (1 << DIGS) - 1;

    public static int universalHash(int x, int A, int B, int M) {
        long hashVal = (long) A * x + B;
        hashVal = (hashVal >> DIGS) + (hashVal & mersennep);
        if (hashVal >= mersennep)
            hashVal -= mersennep;
        return (int) (hashVal % M);
    }
}
