package com.lenovo.javautils.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {
    private static final  String FORMAT_TYPE1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 取本月第一天
     */
    public static LocalDate firstDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 取本月第N天
     */
    public static LocalDate dayOfThisMonth(int n) {
        LocalDate today = LocalDate.now();
        return today.withDayOfMonth(n);
    }

    /**
     * 取本月最后一天
     */
    public static LocalDate lastDayOfThisMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 取本月第一天的开始时间
     */
    public static LocalDateTime startOfThisMonth() {
        return LocalDateTime.of(firstDayOfThisMonth(), LocalTime.MIN);
    }


    /**
     * 取本月最后一天的结束时间
     */
    public static LocalDateTime endOfThisMonth() {
        return LocalDateTime.of(lastDayOfThisMonth(), LocalTime.MAX);
    }
    /**
     * 时间字符串转化为时间戳
     * @param dateStr
     * @param format
     * @return long
     * @author dcx
     * @date 2020/10/22 11:43
     */
    public static long toInstant(String dateStr,String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime parse = LocalDateTime.parse(dateStr, formatter);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 时间戳格式化为时间
     * @param million
     * @param format
     * @return java.lang.String
     * @author dcx
     * @date 2020/11/4 10:44
     */
    public static String instantToStr(Long million,String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(million), ZoneId.systemDefault());
        return localDateTime.format(formatter);
    }

    public static void main(String[] args) {
        long l = toInstant("2020-10-10 10:10:10", FORMAT_TYPE1);
        System.out.println(l);
    }
}
