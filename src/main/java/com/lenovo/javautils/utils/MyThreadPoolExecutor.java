package com.lenovo.javautils.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 自定义线程池
 * @author: dcx
 * @create: 2020-10-25 15:51
 **/
public enum MyThreadPoolExecutor {
    //创建实例
    INSTANCE;
    private final ThreadPoolExecutor pool;

    MyThreadPoolExecutor() {
        pool = new ThreadPoolExecutor(10, 10, 3, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(200),
                new MyThreadFactory(), new CustomRejectedExecutionHandler());
    }

    public ThreadPoolExecutor getInstance() {
        return pool;
    }


    /**
     * 线程池创建工厂
     */
    private static class MyThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);


        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(MyThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1));
            return thread;
        }
    }


    /**
     * 自定义堵塞策略
     */
    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {


        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                //由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
