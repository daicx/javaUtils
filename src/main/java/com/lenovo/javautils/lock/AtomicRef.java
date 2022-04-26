package com.lenovo.javautils.lock;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicRef {
    @Data
    static class User {
        private int a;
        private int b;

        public User(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        User user = new User(11,12);
        User user1 = new User(22,23);
        AtomicReference<User> userAtomicReference = new AtomicReference<>(user);
        boolean b = userAtomicReference.compareAndSet(user, user1);
        System.out.println(b);
        System.out.println(userAtomicReference.get());
    }
}
