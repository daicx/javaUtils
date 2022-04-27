package com.lenovo.javautils.lock;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> objects = new CopyOnWriteArrayList<>();
        objects.add(11);
        objects.add(2);
        objects.add(13);
        objects.add(14);
        System.out.println(objects);
        System.out.println(objects.get(1));
    }
}
