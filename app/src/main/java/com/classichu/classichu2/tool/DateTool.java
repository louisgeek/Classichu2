package com.classichu.classichu2.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by louisgeek on 2016/6/19.
 */
public class DateTool {

    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TIME = "HH:mm:ss";

    public static final String DEFAULT_DATA = "1970-01-01";

    public static final String FORMAT_1 = "HH:mm";
    public static final String FORMAT_2 = "MM-dd";
    public static final String FORMAT_3 = "MM-dd HH:mm";
    public static final String FORMAT_4 = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_5 = "yyyy-MM-dd'T'HH:mm";
    public static final String FORMAT_6 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String FORMAT_7 = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String FORMAT_8 = "yyyy-MM-dd HH:mm:ss.S";
    public static final String FORMAT_9 = "yyyy-MM-dd HH:mm:SS";

    // public static final String FORMAT_dot_1 = "HH:mm";
    public static final String FORMAT_dot_2 = "MM.dd";
    public static final String FORMAT_dot_3 = "MM.dd HH:mm";
    public static final String FORMAT_dot_4 = "yyyy.MM.dd HH:mm";
    public static final String FORMAT_dot_5 = "yyyy.MM.dd'T'HH:mm";
    public static final String FORMAT_dot_6 = "yyyy.MM.dd'T'HH:mm:ss.SSSZ";
    public static final String FORMAT_dot_7 = "yyyy.MM.dd'T'HH:mm:ssZ";
    public static final String FORMAT_dot_8 = "yyyy.MM.dd HH:mm:ss.S";
    public static final String FORMAT_dot_9 = "yyyy.MM.dd HH:mm:SS";

    // public static final String FORMAT_dot_1 = "HH:mm";
    public static final String FORMAT_slash_2 = "MM/dd";
    public static final String FORMAT_slash_3 = "MM/dd HH:mm";
    public static final String FORMAT_slash_4 = "yyyy/MM/dd HH:mm";
    public static final String FORMAT_slash_5 = "yyyy/MM/dd'T'HH:mm";
    public static final String FORMAT_slash_6 = "yyyy/MM/dd'T'HH:mm:ss.SSSZ";
    public static final String FORMAT_slash_7 = "yyyy/MM/dd'T'HH:mm:ssZ";
    public static final String FORMAT_slash_8 = "yyyy/MM/dd HH:mm:ss.S";
    public static final String FORMAT_slash_9 = "yyyy/MM/dd HH:mm:SS";

    // public static final String FORMAT_dot_1 = "HH:mm";
    public static final String FORMAT_cn_2 = "MM月dd日";
    public static final String FORMAT_cn_3 = "MM月dd日 HH:mm";
    public static final String FORMAT_cn_4 = "yyyy年MM月dd日 HH:mm";
    public static final String FORMAT_cn_5 = "yyyy年MM月dd日'T'HH:mm";
    public static final String FORMAT_cn_6 = "yyyy年MM月dd日'T'HH:mm:ss.SSSZ";
    public static final String FORMAT_cn_7 = "yyyy年MM月dd日'T'HH:mm:ssZ";
    public static final String FORMAT_cn_8 = "yyyy年MM月dd日 HH:mm:ss.S";
    public static final String FORMAT_cn_9 = "yyyy年MM月dd日 HH:mm:SS";


    private static List<SimpleDateFormat> formats = new ArrayList<>();

    static {
        formats.add(new SimpleDateFormat(FORMAT_1, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_2, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_3, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_4, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_5, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_6, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_7, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_8, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_9, Locale.CHINA));
        //
        formats.add(new SimpleDateFormat(FORMAT_dot_2, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_dot_3, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_dot_4, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_dot_5, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_dot_6, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_dot_7, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_dot_8, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_dot_9, Locale.CHINA));
        //
        formats.add(new SimpleDateFormat(FORMAT_slash_2, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_slash_3, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_slash_4, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_slash_5, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_slash_6, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_slash_7, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_slash_8, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_slash_9, Locale.CHINA));
        //
        formats.add(new SimpleDateFormat(FORMAT_cn_2, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_cn_3, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_cn_4, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_cn_5, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_cn_6, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_cn_7, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_cn_8, Locale.CHINA));
        formats.add(new SimpleDateFormat(FORMAT_cn_9, Locale.CHINA));
    }


    public static String getNextDayDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);//加1天  -1减一天  和Calendar.DAY_OF_MONTH值一样
        return parseCalendar2Str(calendar, FORMAT_DATE_TIME);
    }

    public static String getNextDayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);//加1天  -1减一天  和Calendar.DAY_OF_MONTH值一样
        return parseCalendar2Str(calendar, FORMAT_DATE);
    }

    public static String parseTimeInMillis2Str(long timeInMillis, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.CHINA);
        return sdf.format(timeInMillis);
    }

    /**
     * 取当前China日期
     *
     * @return
     */
    public static String getChinaDate() {
        return parseDate2Str(new Date(), FORMAT_DATE);//取当前时间
    }

    /**
     * 取当前China日期时间
     *
     * @return
     */
    public static String getChinaDateTime() {
        return parseDate2Str(new Date(), FORMAT_DATE_TIME);//取当前时间
    }

    /**
     * 取当前China时间
     *
     * @return
     */
    public static String getChinaTime() {
        return parseDate2Str(new Date(), FORMAT_TIME);//取当前时间
    }

    /**
     * 多参数 Calendar 获取日期
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static String getChinaDateFromCalendar(int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE, Locale.CHINA);//Locale.SIMPLIFIED_CHINESE和Locale.CHINA一样
        //new Data(int,int,int)过时了
        GregorianCalendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);//初始具有指定年月日的公历类对象。
        Long timeInMillis = calendar.getTimeInMillis();
        return sdf.format(timeInMillis);//new Data()//取当前时间
    }

    /**
     * 多参数 Calendar 获取时间
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static String getChinaDateTimeFromCalendar(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, int second) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME, Locale.CHINA);//Locale.SIMPLIFIED_CHINESE和Locale.CHINA一样
        //new Data(int,int,int)过时了
        GregorianCalendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth, hourOfDay, minute, second);//初始具有指定年月日的公历类对象。
        Long timeInMillis = calendar.getTimeInMillis();
        return sdf.format(timeInMillis);//new Data()//取当前时间
    }


    //不推荐  好用的
    @Deprecated
    public static String getZhongGuoTime() {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_TIME);
        String times = format.format(new Date());
        System.out.print("日期格式---->" + times);
        return times;
    }


    public static Date parseStr2Date(String dateStr, String formatStr) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.CHINA);//Locale.SIMPLIFIED_CHINESE和Locale.CHINA一样
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String parseDate2Str(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.CHINA);//Locale.SIMPLIFIED_CHINESE和Locale.CHINA一样
        return sdf.format(date);
    }

    public static String parseCalendar2Str(Calendar calendar, String formatStr) {
        Date date = parseCalendar2Date(calendar);
        return parseDate2Str(date, formatStr);
    }


    public static Calendar parseStr2Calendar(String dateStr, String formatStr) {
        Date date = parseStr2Date(dateStr, formatStr);
        return parseDate2Calendar(date);
    }

    public static Date parseCalendar2Date(Calendar calendar) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        Date date = calendar.getTime();
        return date;
    }

    public static Calendar parseDate2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static boolean canParseToDate(String stringMayBeDate) {
        if (stringMayBeDate == null) {
            return false;
        }
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE, Locale.CHINA);//Locale.SIMPLIFIED_CHINESE和Locale.CHINA一样
        try {
            date = sdf.parse(stringMayBeDate);
        } catch (Exception e) {
            return false;
        }
        return date != null;
    }

    public static boolean canParseToDateTime(String stringMayBeDateTime) {
        if (stringMayBeDateTime == null) {
            return false;
        }
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME, Locale.CHINA);//Locale.SIMPLIFIED_CHINESE和Locale.CHINA一样
        try {
            date = sdf.parse(stringMayBeDateTime);
        } catch (Exception e) {
            return false;
        }
        return date != null;
    }

    public static String dealMaybeCanParseDateOrDefaultDateOrJustBackSource(String stringDateMayBe) {
        String temp;
        if (DateTool.canParseToDateTime(stringDateMayBe) || DateTool.canParseToDate(stringDateMayBe)) {
            //格式化时间
            temp = DateTool.parseDate2Str(DateTool.parseStr2Date(stringDateMayBe, DateTool.FORMAT_DATE), DateTool.FORMAT_DATE);
            //默认时间
            if (temp.equals(DEFAULT_DATA)) {
                temp = "暂无";
            }
        } else {
            temp = stringDateMayBe;
        }
        return temp;

    }

    public static long timeCompare(String anotherTimeStr) {
        return timeCompare(getChinaDateTime(), anotherTimeStr);
    }

    public static Date getDateFromAll(String dateStr) {
        Date date = null;
        for (SimpleDateFormat format :
                formats) {
            try {
                date = format.parse(dateStr);
            } catch (ParseException e) {
                continue;
            }
        }
        return date;
    }

    public static long getBetween(String time) {

        Date date = getDateFromAll(time);
        if (null == date) {
            date = new Date();
        }
        return date.getTime() - System.currentTimeMillis();
    }

    public static long timeCompare(String oneTimeStr, String anotherTimeStr) {
        Date oneTimeDate = DateTimeTool.parseDateStr2Date(oneTimeStr);
        Date anotherTimeDate = DateTimeTool.parseDateStr2Date(anotherTimeStr);
        long diffTime = oneTimeDate.getTime() - anotherTimeDate.getTime();
        return diffTime;
    }

    /**
     * 利用时间戳取得文件名
     *
     * @param prefixStr IMG
     * @param suffixStr .png
     * @return
     */
    public static String getFileNameByDateTime(String prefixStr, String suffixStr) {
        suffixStr = suffixStr.equals("") ? ".png" : suffixStr;
        suffixStr = suffixStr.contains(".") ? suffixStr : "." + suffixStr;
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        return prefixStr + "_" + dateFormat.format(date) + suffixStr;
    }
}
