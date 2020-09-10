package com.cc.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 不保证原子性
 */
public class Test2 {

    //原子类的Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        //num++; //不是原子性操作
        num.getAndIncrement();//+1，CAS
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+" " +num);
    }

}
