package com.cc.tvolatile;

import java.util.concurrent.TimeUnit;

public class Test {

    private static int num = 0;

    public static void main(String[] args) { // main

        new Thread(() -> { // 线程1
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
