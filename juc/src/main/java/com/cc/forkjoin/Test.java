package com.cc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1();//sum 500000000500000000 时间 263
        //test2();//sum 500000000500000000 时间 4987
        test3();//sum 500000000500000000 时间 167
    }

    public static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (long i = 1L; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();


        System.out.println("sum " + sum + " 时间 " + (end - start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();

        long end = System.currentTimeMillis();

        System.out.println("sum " + sum + " 时间 " + (end - start));
    }

    public static void test3() {
        long start = System.currentTimeMillis();

        Long sum = LongStream.rangeClosed(0L,10_0000_0000L).parallel().reduce(0,Long::sum);

        long end = System.currentTimeMillis();

        System.out.println("sum " + sum + " 时间 " + (end - start));
    }
}
