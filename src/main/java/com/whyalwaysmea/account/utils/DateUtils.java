package com.whyalwaysmea.account.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Author: Long
 * @Date: Create in 15:48 2018/6/15
 * @Description:
 */
public class DateUtils {

    public static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String DATE_MONTH_FORMAT_PATTERN = "MM月dd日 ";
    public static final String WEEK_STR = "星期%s";

    /**
     * 获取当月第一天
     * @return
     */
    public static Date getFirstDayOfCurrentMonth() {
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        return localDate2Date(firstDayOfMonth);
    }

    /**
     * 获取本周第一天
     * @return
     */
    public static Date getFirstDayOfCurrentWeek() {
        LocalDate now = LocalDate.now();
        int value = now.getDayOfWeek().getValue();
        LocalDate localDate = now.minusDays(value - 1);
        return localDate2Date(localDate);
    }

    /**
     * 获取当前日期（因为sql用的是between，所以取明天的日期）
     * @return
     */
    public static Date getCurrentDate() {
        LocalDate now = LocalDate.now();
        return localDate2Date(now.plusDays(1));
    }

    /**
     * localDate 转换成 date
     * @param localDate
     * @return
     */
    private static Date localDate2Date(LocalDate localDate){
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant1 = zonedDateTime.toInstant();
        Date from = Date.from(instant1);
        return  from;
    }

    /**
     * date 转换成 localDate
     * @param date
     * @return
     */
    private static LocalDate date2LocalDate(Date date){
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }

    /**
     * date 转换成 星期
     * @param date
     * @return
     */
    private static int date2Week(Date date) {
        LocalDate localDate = date2LocalDate(date);
        int value = localDate.getDayOfWeek().getValue();
        return value;
    }

    /**
     * date 转换成 星期
     * @param date
     * @return
     */
    public static String date2WeekStr(Date date) {
        if(org.apache.commons.lang3.time.DateUtils.isSameDay(date, new Date())) {
            return "今天";
        }
        int week = date2Week(date);
        String day = "";
        switch (week) {
            case 1:
                day = "一";
                break;
            case 2:
                day = "二";
                break;
            case 3:
                day = "三";
                break;
            case 4:
                day = "四";
                break;
            case 5:
                day = "五";
                break;
            case 6:
                day = "六";
                break;
            case 7:
                day = "天";
                break;
        }
        return String.format(WEEK_STR, day);
    }

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.parse("2018-07-01");

        int i = date2Week(localDate2Date(localDate));
        System.out.println(i);
    }
}
