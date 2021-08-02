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

    private static String getKey(Object phoneLogId) {
        return String.format("jrtt.gdt.%s", phoneLogId);
    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("11234");
        System.out.println(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
    }
}
