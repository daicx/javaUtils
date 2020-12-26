package com.lenovo.javautils.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @description:
 * @author: dcx
 * @create: 2020-12-26 16:40
 **/
public class UrlEncode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String strBulider = "dadad%25";
        String decode = URLDecoder.decode(strBulider.toString(), "UTF-8");
        System.out.println(decode);
    }
}
