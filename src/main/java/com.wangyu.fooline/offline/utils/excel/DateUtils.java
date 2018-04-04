package com.wangyu.fooline.offline.utils.excel;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by wuxiaoyan on 2016/12/12.
 */
public class DateUtils {

    public final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDD_000000 = "yyyy-MM-dd 00:00:00";
    public static final TimeZone CHINA = TimeZone.getTimeZone("GMT+8");
    public static final TimeZone UTC = TimeZone.getTimeZone("GMT");

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private DateUtils() {
    }

    /**
     * 相隔天数
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
   /*  *
     * @return
     *//*
    public static Date getCurrentDate() {
        Date date = getCurrentDate(DEFAULT_PATTERN);
        return date;
    }*/

    /**
     *
     * @param format
     * @return
     */
    /*public static Date getCurrentDate(String format) {
        //Date date = DateFormatUtils.formatDate(new Date(), format);
        return date;
    }*/

    /**
     *
     * @param date
     * @return
     */
    public static String getDateStr(Date date) {
        return getDateStr(date, DEFAULT_PATTERN);
    }

    /**
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String getDateStr(Date date, String formatStr) {
        if (date == null){
            return "";
        }
        if (StringUtils.isBlank(formatStr)) {
            formatStr = DEFAULT_PATTERN;
        }
        SimpleDateFormat dateformat1 = new SimpleDateFormat(formatStr);
        return dateformat1.format(date);

    }

    /**
     *
     * @param dateStr
     * @return
     */
   /* public static Date getDateFromStr(String dateStr) {
        //Date date = DateFormatUtils.parseDate(dateStr, DEFAULT_PATTERN);
       // return date;
    }*/

    /**
     *
     * @param dateLong
     * @return
     */
    public static Date getLongDate(Long dateLong) {
        Date date = new Date(dateLong);
        return date;

    }

    /**
     *
     * @param date
     * @return
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }

    /**
     *
     * @param cal
     * @return
     * @throws Exception
     */
    public static Date convertToDate(XMLGregorianCalendar cal) throws Exception {
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    /**
     *
     * @param dateStr
     * @param dateFromat
     * @param stringFormat
     * @return
     * @throws ParseException
     */
    public static String getDateStr(String dateStr, String dateFromat, String stringFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();

        format.applyPattern(dateFromat);
        Date date = format.parse(dateStr);
        format.applyPattern(stringFormat);
        return format.format(date);
    }

    public static long getDateInMill(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.getTimeInMillis();
    }

    /**
     * 获取输入日期的前、后N天
     * <br/> 输入正数，返回N天之后的日期；
     * <br/> 输入负数，返回N天之前的日期
     *
     * @param currentDate 输入日期
     * @param day         加减的天数
     * @return
     */
    public static Date addDay(Date currentDate, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, day);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
        return createDate(sdf.format(d), YYYYMMDD_HHMMSS);
    }
    /**
     * 获取输入日期的前后N秒
     * 输入正数，返回N秒之后
     * 输入负数，返回N秒之前
     * @param currentDate
     * @param second
     * @return
     */
    public static Date addSecond(Date currentDate, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.SECOND, second);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
        return createDate(sdf.format(d), YYYYMMDD_HHMMSS);
    }

    /**
     * 将时间格式的字符串，转化为Date
     *
     * @param dateFormat exp: 2010-7-22
     * @return java.util.Date
     */
    public static Date createDate(String dateString, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date d = null;
        try {
            d = sdf.parse(dateString);
        } catch (ParseException e) {
            logger.error("字符串转化为Date失败,[string=" + dateString + "]", e);
        }
        return d;
    }

    /**
     * 获取指定天的开始时间
     * @param date
     * @return
     */
    public static Date getDatesBeginTime(Date date){
        Calendar calendar = new GregorianCalendar();
        if(date==null){
            date = new Date();
        }
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定天的结束时间
     * @param date
     * @return
     */
    public static Date getDatesEndTime(Date date){
        Calendar calendar = new GregorianCalendar();
        if(date==null){
            date = new Date();
        }
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }
}
