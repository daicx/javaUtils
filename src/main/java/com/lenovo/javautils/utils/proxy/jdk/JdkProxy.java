package com.lenovo.javautils.utils.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理修改hello
 *
 *
 * @author dcx
 * @date 2021/2/3 16:32
 */
@Slf4j
public class JdkProxy implements InvocationHandler {

    private Hello hello;

    public JdkProxy(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sayHello".equals(method.getName())){
            log.info("入参是:{}",args);
        }
        return method.invoke(hello,args);
    }
}
