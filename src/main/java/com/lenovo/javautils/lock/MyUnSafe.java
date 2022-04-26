package com.lenovo.javautils.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MyUnSafe {
    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(null);
    }
}
