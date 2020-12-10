package com.lenovo.javautils.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DateUtil {
    private static final  String FORMAT_TYPE1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TYPE2 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String ZONESTR = "Asia/Shanghai";

    /**
     * 获取天数差
     * @param from
     * @param to
     * @return long
     * @author dcx
     * @date 2020/12/10 11:44
     */
    public static long getDaysBetween(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE1);
        LocalDate start = LocalDate.parse(from, formatter);
        LocalDate end = LocalDate.parse(to, formatter);
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * 获取当前时间p
     *
     * @param format
     * @return java.lang.String
     * @author dcx
     * @date 2020/9/15 11:50
     */
    public static String getCurrentDateTime(String format) {
        return ZonedDateTime.now(ZoneId.of(ZONESTR)).format(DateTimeFormatter.ofPattern(format));
    }


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

    public static void test(StringBuilder a){
        a .append( "test");
    }

    public static void main(String[] args) {
        long l = toInstant("2020-10-10 10:10:10", FORMAT_TYPE1);
        System.out.println(l);
        StringBuilder b = new StringBuilder("a");
        test(b);
        System.out.println(b);
        List<Object> objects = new ArrayList<>();
        System.out.println(Optional.ofNullable(objects.get(0)));
    }
}
