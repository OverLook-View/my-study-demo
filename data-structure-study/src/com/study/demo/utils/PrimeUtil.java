package com.study.demo.utils;

public class PrimeUtil {

    public static boolean isPrime(int n) {
        if (n < 2)
            return false;
        else if (n > 2) {
            double sqrt = Math.sqrt(n);
            for (int i = 2; i <= sqrt; i++) {
                if (n % i == 0)
                    return false;
            }
        }
        return true;
    }

    public static int nextPrime(int n) {
        if (n < 2)
            return 2;
        while (!isPrime(++n)) {

        }
        return n;
    }

    public static void main(String[] args) {
        int prime = 0;
        for (int i = 0; i < 100; i++) {
            prime = nextPrime(prime);
            System.out.println(prime);
        }
    }
}
