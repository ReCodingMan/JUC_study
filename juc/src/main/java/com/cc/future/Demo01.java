package com.cc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用
 */
public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //发起一个请求
        //没有返回值的 runAsync 异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync=>Void");
//        });
//
//        System.out.println(1111);
//
//        completableFuture.get(); // 获取阻塞执行结果

        //有返回结果的 supplyAsync 异步回调
        //ajax，成功和失败的回调
        //返回的是错误信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"runAsync=>Void");
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t=>" + t);
            System.out.println("u=>" + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233; // 获取错误结果
        }).get());
    }
}
