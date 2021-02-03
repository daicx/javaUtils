package com.lenovo.javautils.utils.proxy.jdk;

public class HelloImpl implements Hello{
    @Override
    public void sayHello(String str) {
        System.out.println(str);
    }
}
