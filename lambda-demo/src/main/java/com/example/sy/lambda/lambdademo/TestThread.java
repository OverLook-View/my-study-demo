package com.example.sy.lambda.lambdademo;

public class TestThread implements Runnable {
    static TestThread t = new TestThread();

    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public TestThread() {
    }

    public TestThread(String a) {
        this.a = a;
    }

    public static void main(String[] args) throws InterruptedException {

        TestThread testThread = new TestThread("1");
        TestThread testThread1 = new TestThread("2");
        TestThread testThread2 = new TestThread("3");
        TestThread testThread3 = new TestThread("4");
        TestThread testThread4 = new TestThread("5");
        Thread thread = new Thread(testThread);
        Thread thread1 = new Thread(testThread1);
        Thread thread2 = new Thread(testThread2);
        Thread thread3 = new Thread(testThread3);
        Thread thread4 = new Thread(testThread4);
        thread.start();
        Thread.sleep(100l);
        thread1.start();
        Thread.sleep(100l);
        thread2.start();
        Thread.sleep(100l);
        thread3.start();
        Thread.sleep(100l);
        thread4.start();
        Thread.sleep(100l);
        synchronized (t) {
            t.notify();
        }
        Thread.sleep(100l);
        synchronized (t) {
            t.notify();
        }
        Thread.sleep(100l);
        synchronized (t) {
            t.notify();
        }
        Thread.sleep(100l);
        synchronized (t) {
            t.notify();
        }
        Thread.sleep(100l);
        synchronized (t) {
            t.notify();
        }
        System.out.println("end");
    }

    @Override
    public void run() {
        synchronized (t) {
            try {
                t.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(a);
    }
}