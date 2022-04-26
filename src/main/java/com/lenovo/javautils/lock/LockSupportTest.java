package com.lenovo.javautils.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        System.out.println(1111);
        LockSupport.parkNanos(1000);
        System.out.println(2222);

    }
}
