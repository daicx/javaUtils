package com.lenovo.javautils.lock;

import com.lenovo.javautils.threads.MyThreadPoolExecutor;
import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

public class AddTest {
    @Data
    static class SelfAdd {
        private int a;
        private int b;
        private final Integer lockA = 1;
        private final Integer lockB = 1;
        private static int c;

        public void add() {
            a++;
        }

        public synchronized void addSyn() {
            b++;
        }

        /**
         * 粗粒度的锁方法
         */
        public synchronized void addMulSyn() {
            b++;
            a++;
        }

        public void addMul1Syn() {
            //细粒度的锁代码块
            synchronized (lockA) {
                a++;
            }
            synchronized (lockB) {
                b++;
            }
        }
        //类锁
        public static synchronized void addCSyn() {
            c++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor poolExecutor = MyThreadPoolExecutor.INSTANCE.getInstance();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        SelfAdd selfAdd = new SelfAdd();
        poolExecutor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                selfAdd.add();
//                selfAdd.addSyn();
            }
            countDownLatch.countDown();
        });
        poolExecutor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                selfAdd.add();
//                selfAdd.addSyn();
            }
            countDownLatch.countDown();
        });
        countDownLatch.await();
        System.out.println("a=" + selfAdd.getA());
        System.out.println("b=" + selfAdd.getB());
    }
}
