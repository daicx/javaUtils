package com.lenovo.javautils.utils.map;

import java.text.NumberFormat;

public class TreeMaps {

    public static void main(String[] args) {
        int a = 0;
        int b = 10;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String format = numberFormat.format(((float) b / (float) a) * 100);
        System.out.println(format + "%");
    }
}
