package com.lenovo.javautils.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MyThreadPoolExecutorUse {

    public static void submitPool() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor instance = ThreadPoolUtil.INSTANCE.getInstance();
        for (int i = 0; i < 10; i++) {
            Future<?> submit = instance.submit(() -> {
                System.out.println("submitPool");
                System.out.println("submitPool1");
                return 1;
            });
            System.out.println(submit.get());
        }
    }

    public static void excPool() {
        ThreadPoolExecutor instance = ThreadPoolUtil.INSTANCE.getInstance();
        for (int i = 0; i < 10; i++) {
            instance.execute(() -> {
                System.out.println("excPool");
                System.out.println("excPool1");
            });
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //需要获取返回值
        submitPool();
        //不需要获取返回值
        excPool();
        System.exit(0);
    }
}
