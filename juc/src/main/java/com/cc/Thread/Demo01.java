package com.cc.Thread;

import java.util.concurrent.*;

public class Demo01 {

    public static void main(String[] args) {
        //自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy() //银行满了，还有人进来，不处理这个人的，抛出异常
        );

        try {
            for (int i = 0; i < 5; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
