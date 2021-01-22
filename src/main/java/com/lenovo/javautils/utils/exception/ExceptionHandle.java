package com.lenovo.javautils.utils.exception;

public class ExceptionHandle {
    public static void main(String[] args) {
        String a = null;
        try {
            a = ExceptionDemo.getA();
        } catch (Exception e) {
            System.out.println(e.getMessage()+"-----");
        }
        System.out.println(a);
    }
}
