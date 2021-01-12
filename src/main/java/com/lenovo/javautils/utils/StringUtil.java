package com.lenovo.javautils.utils;

public class StringUtil {

    public static void changeString(String str) {
        str = "修改之后的";
    }

    public static void changeStringBuilder(StringBuilder str) {
        str.append("修改之后的");
    }

    public static void main(String[] args) {
        String str = "默认值";
        changeString(str);
        System.out.println(str);
        StringBuilder sb = new StringBuilder("默认值");
        changeStringBuilder(sb);
        System.out.println(sb);
    }
}
