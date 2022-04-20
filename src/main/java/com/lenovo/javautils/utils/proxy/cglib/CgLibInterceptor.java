//package com.lenovo.javautils.utils.proxy.cglib;
//
//import lombok.extern.slf4j.Slf4j;
//import net.sf.cglib.proxy.MethodProxy;
//import org.springframework.cglib.proxy.MethodInterceptor;
//
//import java.lang.reflect.Method;
//
//@Slf4j
//public class CgLibInterceptor implements MethodInterceptor {
//
//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        log.info("入参是:{}", objects);
//        return methodProxy.invokeSuper(o,objects);
//    }
//}
