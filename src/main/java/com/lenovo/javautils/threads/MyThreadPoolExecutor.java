package com.lenovo.javautils.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 自定义线程池
 * @author: dcx
 * @create: 2020-10-25 15:51
 **/
@Slf4j
public enum MyThreadPoolExecutor {
    //创建实例
    INSTANCE;
    private final ThreadPoolExecutor pool;
    private static final String poolName = MyThreadPoolExecutor.class.getSimpleName();

    private static final ThreadLocal<Long> start = new ThreadLocal<>();
    private static final ThreadLocal<String> currentThreadName = new ThreadLocal<>();

    MyThreadPoolExecutor() {
        pool = new ThreadPoolExecutor(10, 10, 3,
                TimeUnit.HOURS, new LinkedBlockingQueue<>(200),
                new MyThreadFactory(), new CustomRejectedExecutionHandler()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                //任务开始执行
                log.info("thread start:{}", t.getName());
                start.set(System.currentTimeMillis());
                currentThreadName.set(t.getName());
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                //任务执行完毕
                super.afterExecute(r, t);
                log.info("thread:{}, end,useTime:{}ms", currentThreadName.get(), System.currentTimeMillis() - start.get());
                start.remove();
                currentThreadName.remove();
            }

            @Override
            protected void terminated() {
                //线程池终止
                super.terminated();
                log.info("threadPool stop,msg:{}", poolName);
            }
        };
        initGracefullyShutDown();
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
            thread.setName(poolName + count.addAndGet(1));
            return thread;
        }
    }

    /**
     * 自定义堵塞策略
     */
    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.error("threadpool reject,currentCount:{}", executor.getTaskCount());
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    executor);
        }
    }

    /**
     * 添加钩子函数
     */
    private void initGracefullyShutDown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> shutDownThreadPool(pool, poolName)));
    }

    /**
     * 优雅关闭线程池。
     * 自身关闭，await 60s，强制关闭。
     */
    private void shutDownThreadPool(ExecutorService threadPool, String alias) {
        log.info("Start to shutdown the thead pool : {}", alias);

        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                log.warn("Interrupt the worker, which may cause some task inconsistent");
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    log.warn("Thread pool can't be shutdown even with interrupting worker threads, which may cause some task inconsistent.");
                }
            }
        } catch (InterruptedException ie) {
            threadPool.shutdownNow();
            log.warn("The current server thread is interrupted when it is trying to stop the worker threads. This may leave an inconsistent state.");
            Thread.currentThread().interrupt();
        }
    }
}
