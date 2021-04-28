package com.lenovo.javautils.utils.jackson.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenovo.javautils.utils.MyThreadPoolExecutor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 此方法用来验证，jackson的objectMapper 单例和多例的速度和安全性
 * //multy thread useTime:7929
 * //single useTime:1054
 * //multy useTime:12320
 * //single thread useTime:1575
 * 可以证明objectMapper是线程安全的，并且在单例的执行情况下速度执行更快。
 *
 * @author dcx
 * @date 2021/4/28 2:15 下午
 */
@Slf4j
public class SingleAndMultTest {
    private static ThreadPoolExecutor instance = MyThreadPoolExecutor.INSTANCE.getInstance();

    @Data
    static class User {
        private String name;
        private String desc;
    }

    public static void main(String[] args) throws IOException {
        //multy thread useTime:7929
        //single useTime:1054
        //multy useTime:12320
        //single thread useTime:1575
//        multiThread();
//        single();
//        multi();
        singleThread();
    }

    public static void multiThread() throws IOException {
        long l2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            instance.execute(
                    () -> {
                        ObjectMapper objectMapper = new ObjectMapper();
                        User user = new User();
                        long l = System.currentTimeMillis();
                        user.setName("dadadad" + l);
                        user.setDesc("dadadadadda");
                        String s = null;
                        try {
                            s = objectMapper.writeValueAsString(user);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        try {
                            User user1 = objectMapper.readValue(s, User.class);
                            assert user1.getName().equals("dadadad" + l);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );

        }
        while (instance.getActiveCount() > 0) {
            System.out.println("getActiveCount:" + instance.getActiveCount());
        }
        //multy thread useTime:7062
        log.info("multy thread useTime:{}", System.currentTimeMillis() - l2);
    }

    public static void multi() throws IOException {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = new User();
            user.setName("dadadad" + i);
            user.setDesc("dadadadadda");
            String s = objectMapper.writeValueAsString(user);
            User user1 = objectMapper.readValue(s, User.class);
            assert user1.getName().equals("dadadad" + i);
        }
        //multy useTime:11898
        log.info("multy useTime:{}", System.currentTimeMillis() - l);
    }

    public static void single() throws IOException {
        long l1 = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            user.setName("dadadad" + i);
            user.setDesc("dadadadadda");
            String s = objectMapper.writeValueAsString(user);
            User user1 = objectMapper.readValue(s, User.class);
            assert user1.getName().equals("dadadad" + i);
        }
        //single useTime:977
        log.info("single useTime:{}", System.currentTimeMillis() - l1);
    }

    public static void singleThread() throws IOException {
        long l1 = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < 1000000; i++) {
            instance.execute(
                    () -> {
                        long l = System.currentTimeMillis();
                        User user = new User();
                        user.setName("dadadad" + l);
                        user.setDesc("dadadadadda");
                        User user1 = null;
                        try {
                            String s = objectMapper.writeValueAsString(user);
                            user1 = objectMapper.readValue(s, User.class);
                            assert user1.getName().equals("dadadad" + l);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );

        }
        while (instance.getActiveCount() > 0) {
            System.out.println("getActiveCount:" + instance.getActiveCount());
        }
        //single thread useTime:1575
        log.info("single thread useTime:{}", System.currentTimeMillis() - l1);
    }
}
