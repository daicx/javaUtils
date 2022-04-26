package com.lenovo.javautils.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static int a = 1;

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            a++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println(a);
    }
}
