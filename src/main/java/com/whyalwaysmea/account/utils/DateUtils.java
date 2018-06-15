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


}
