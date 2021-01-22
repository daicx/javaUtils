package com.lenovo.javautils.utils;

public class StringUtil {

    /**
     * string 值传递
     * @param str
     * @return void
     * @author dcx
     * @date 2021/1/12 20:41
     */
    public static void changeString(String str) {
        str = "修改之后的";
    }

    public static void changeStringBuilder(StringBuilder str) {
        str.append("修改之后的");
    }


    
    public static void main(String[] args) {
        System.out.println((System.currentTimeMillis()+"").length());
    }
}
