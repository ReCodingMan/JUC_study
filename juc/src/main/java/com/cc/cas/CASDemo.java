package com.cc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    //CAS
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        //    public final boolean compareAndSet(int expect, int update) {
        //期望，更新
        atomicInteger.compareAndSet(2020,2021);
        System.out.println(atomicInteger.get());

    }
}
