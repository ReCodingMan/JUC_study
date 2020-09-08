package com.cc.pc;

/**
 * 线程之间的通信问题：生产者和消费者问题
 * 线程交替执行
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

class Data {

    private int num = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        while (num!=0) {
            //等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        //通知其他线程
        this.notifyAll();
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        while (num==0) {
            //等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        //通知其他线程
        this.notifyAll();
    }
}
