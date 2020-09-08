package com.cc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A->B->C
 */
public class C {

    public static void main(String[] args) {
        Data3 data3 = new Data3();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();
    }


}

class Data3 {

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    private int num = 1; //1A 2B 3C

    public void printA() {

        lock.lock();

        try {
            //业务，判断，执行，通知
            while (num != 1) {
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>AAAAAAAAAA");
            //唤醒B
            num = 2;
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB() {

        lock.lock();

        try {

            //业务，判断，执行，通知
            while (num != 2) {
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>BBBBBBBBB");
            //唤醒B
            num = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {

        lock.lock();

        try {

            //业务，判断，执行，通知
            while (num != 3) {
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>CCCCCCCCCCCC");
            //唤醒B
            num = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
