package com.cc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    //CAS
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        //对于我们平时写的SQL：乐观锁

        //    public final boolean compareAndSet(int expect, int update) {
        //期望，更新
        //===============捣乱的线程================
        atomicInteger.compareAndSet(2020,2021);
        System.out.println(atomicInteger.get());

        atomicInteger.compareAndSet(2021,2020);
        System.out.println(atomicInteger.get());

        //===============期望的线程================
        atomicInteger.compareAndSet(2020,6666);
        System.out.println(atomicInteger.get());
    }
}
