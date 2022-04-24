package com.lenovo.javautils.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureThread {
    static class MyFutureTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("异步执行代码");
            return 1;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyFutureTask myFutureTask = new MyFutureTask();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(myFutureTask);
        new Thread(integerFutureTask, "12").start();
        Integer result = integerFutureTask.get();
        System.out.println(result);
    }
}
