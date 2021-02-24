package com.lenovo.javautils.utils.springbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 静态方法引入spring bean
 * @author dcx
 * @date 2021/2/24 17:41
 */

@Component
public class SpringBeanStatic {

    private static Test test;

    @Autowired
    public void setTest(Test test) {
        SpringBeanStatic.test = test;
    }

    public static String aAa() {
        String a = test.getA();
        System.out.println(a);
        return a;
    }
}
