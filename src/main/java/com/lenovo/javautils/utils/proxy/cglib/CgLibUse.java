//package com.lenovo.javautils.utils.proxy.cglib;
//
//import net.sf.cglib.proxy.Enhancer;
//
//public class CgLibUse {
//
//    public static void main(String[] args) {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(Hello.class);
//        enhancer.setCallback(new CgLibInterceptor());
//        Hello hello = (Hello) enhancer.create();
//        hello.sayHello("cglib hello");
//    }
//}
