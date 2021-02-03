package com.lenovo.javautils.utils.proxy.jdk;

import java.lang.reflect.Proxy;

public class JdkProxyUse {

    /**
     * jdk的动态代理,需要实现一个接口.在生成代理类的时候,需要一个类加载器,一个接口和接口对应的实现.
     * 会生成一个Proxy的子类,并且实现了给定的接口.
     * @param args
     * @return void
     * @author dcx
     * @date 2021/2/3 16:53
     */
    public static void main(String[] args) {
        Hello hello = (Hello) Proxy.newProxyInstance(JdkProxyUse.class.getClassLoader(),
                new Class[]{Hello.class}, new JdkProxy(new HelloImpl()));
        hello.sayHello("hello word");

    }
}
