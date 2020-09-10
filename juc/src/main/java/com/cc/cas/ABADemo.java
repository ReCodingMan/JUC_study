package com.cc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    //CAS
    public static void main(String[] args) {

        /**
         * AtomicStampedReference 【注意】：
         * 如果泛型是一个包装类，注意对象的引用问题
         */
        AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<>(1, 1);

        new Thread(()->{

            System.out.println("A1=>"+atomicInteger.getStamp());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicInteger.compareAndSet(1,2,
                    atomicInteger.getStamp(),atomicInteger.getStamp()+1);
            System.out.println("A2=>"+atomicInteger.getStamp());

            atomicInteger.compareAndSet(2,1,
                    atomicInteger.getStamp(),atomicInteger.getStamp()+1);
            System.out.println("A3=>"+atomicInteger.getStamp());

        },"A").start();


        new Thread(()->{

            System.out.println("B1=>"+atomicInteger.getStamp());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicInteger.compareAndSet(1,6,
                    atomicInteger.getStamp(),atomicInteger.getStamp()+1);

            System.out.println("B2=>"+atomicInteger.getStamp());


        },"B").start();

    }
}
