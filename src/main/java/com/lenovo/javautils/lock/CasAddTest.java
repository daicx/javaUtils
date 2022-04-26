package com.lenovo.javautils.lock;

import com.lenovo.javautils.threads.MyThreadPoolExecutor;
import lombok.Getter;
import lombok.Setter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CasAddTest {
    @Getter
    @Setter
    private volatile int value;

    private static long offset = 0;
    private static Unsafe unsafe;
    @Getter
    private final AtomicInteger casCount = new AtomicInteger(0);

    static {
        try {
            unsafe = MyUnSafe.getUnsafe();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Field monField = null;
        try {
            monField = CasAddTest.class.getDeclaredField("value");
            offset = unsafe.objectFieldOffset(monField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println("monField:" + monField);
    }

    public CasAddTest() throws NoSuchFieldException, IllegalAccessException {
    }

    public boolean cas(int old, int update) {
        return unsafe.compareAndSwapInt(this, offset, old, update);
    }

    public void add() {
        while (!cas(value, value + 1)) {
            System.out.println("cas");
            casCount.addAndGet(1);
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {

        CasAddTest casAddTest = new CasAddTest();
        casAddTest.setValue(100);
        System.out.println("offset:" + offset);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            MyThreadPoolExecutor.INSTANCE.getInstance().execute(
                    () -> {
                        casAddTest.add();
                        countDownLatch.countDown();
                    }
            );
        }
        countDownLatch.await();
        int value = casAddTest.getValue();
        System.out.println("result=" + value + "casCount=" + casAddTest.getCasCount());
    }
}
