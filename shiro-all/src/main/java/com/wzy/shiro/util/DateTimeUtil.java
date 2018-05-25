package com.wzy.shiro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author Jason
 */
public class DateTimeUtil {

    /**
     * 获取昨天的开始时�?
     * 
     * @return
     */
    public static Date getYesterdayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return getStartTimeOfDay(calendar);
    }

    /**
     * 获取指定时间内昨天的�?始时�?
     */
    public static Date getYesterdayStartByParams(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return getStartTimeOfDay(calendar);
    }

    /**
     * 获取指定时间内昨天的结束时间
     */
    public static Date getYesterdayEndByParams(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return getEndTimeOfDay(calendar);
    }

    /**
     * 获取指定时间+1（明天）的开始时�?
     */
    public static Date getTomorrowStartByParam(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return getStartTimeOfDay(calendar);
    }

    /**
     * 获取参数:date的开始时�?
     */
    public static Date getStartTimeByParam(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getStartTimeOfDay(calendar);
    }

    /**
     * 获取参数:date的结束时�?
     */
    public static Date getEndTimeByParam(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getEndTimeOfDay(calendar);
    }

    /**
     * 获取昨天的结束时�?
     * 
     * @return
     */
    public static Date getYesterdayEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return getEndTimeOfDay(calendar);
    }

    /**
     * 获取本周的开始时�?
     * 
     * @return
     */
    public static Date getThisWeekStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getStartTimeOfDay(calendar);
    }

    /**
     * 获取本周的结束时�?
     * 
     * @return
     */
    public static Date getThisWeekEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getEndTimeOfDay(calendar);
    }

    /**
     * 获取上周的开始时�?
     * 
     * @return
     */
    public static Date getLastWeekStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getStartTimeOfDay(calendar);
    }

    /**
     * 获取上周的结束时�?
     * 
     * @return
     */
    public static Date getLastWeekEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getEndTimeOfDay(calendar);
    }

    /**
     * 获取当月的开始时�?
     * 
     * @return
     */
    public static Date getThisMonthStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartTimeOfDay(calendar);
    }

    /**
     * 获取当月的结束时�?
     * 
     * @return
     */
    public static Date getThisMonthEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return getEndTimeOfDay(calendar);
    }

    /**
     * 获取上月的开始时�?
     * 
     * @return
     */
    public static Date getLastMonthStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartTimeOfDay(calendar);
    }

    /**
     * 获取上月的结束时�?
     * 
     * @return
     */
    public static Date getLastMonthEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return getEndTimeOfDay(calendar);
    }

    /**
     * 获取�?天的�?始时�?
     * 
     * @return
     */
    public static Date getStartTimeOfDay(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取�?天的结束时间
     * 
     * @return
     */
    public static Date getEndTimeOfDay(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 59, 59);
        return calendar.getTime();
    }

    /**
     * 将一个日期增加count�?
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date addDate(Date date, int count) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, count);
        return cal.getTime();
    }

    /**
     * 将一个日期增加count�?
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date addMonth(Date date, int count) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, count);
        return cal.getTime();
    }

    /**
     * 将日期格式化成一个字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String dateString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFm = new SimpleDateFormat(pattern);
        String dateTime = dateFm.format(date);
        return dateTime;
    }

    /**
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getDateFormat(String date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    /**
     * 将指定日期转换成cronExpression表达�?
     * 
     * @param date
     * @return
     */
    public static String getCronExpression(Date date) {
        return dateString(date, "ss mm HH dd MM ? yyyy");
    }

    public static String getCronExpression(String date) throws ParseException {
        return dateString(getDateFormat(date, "yyyy-MM-dd hh:mm:ss"), "ss mm HH dd MM ? yyyy");
    }

    /**
     * 功能描述: 将毫秒转换成日期<br>
     *
     * @param millis 毫秒�?
     */
    public static Date convert2Date(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }

    /**
     * 计算两个日期之间相差月数 （date1- date2�?
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int monthsBetween(Date date1, Date date2) {
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(date1);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(date2);
        return (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
    }

    /**
     * 计算两个日期之间相差的天�?
     * 
     * @param smdate 较小的时�?
     * @param bdate 较大的时�?
     * @return 相差天数
     * @throws ParseException
     */
    public static long daysBetween(Date startDate, Date endDate) {
        Calendar startC = DateUtils.toCalendar(startDate);
        Calendar endC = DateUtils.toCalendar(endDate);
        startC.set(Calendar.HOUR, 0);
        startC.set(Calendar.MINUTE, 0);
        startC.set(Calendar.SECOND, 0);
        startC.set(Calendar.MILLISECOND, 0);

        endC.set(Calendar.HOUR, 0);
        endC.set(Calendar.MINUTE, 0);
        endC.set(Calendar.SECOND, 0);
        endC.set(Calendar.MILLISECOND, 0);

        return (endC.getTimeInMillis() - startC.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 计算两个日期之间相差的分�?
     * 
     * @param smdate 较小的时�?
     * @param bdate 较大的时�?
     * @return 相差分钟
     * @throws ParseException
     */
    public static long minutesBetween(Date smdate, Date bdate) {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // try {
        // smdate = sdf.parse(sdf.format(smdate));
        // bdate = sdf.parse(sdf.format(bdate));
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        // Calendar cal = Calendar.getInstance();
        // cal.setTime(smdate);
        // long time1 = cal.getTimeInMillis();
        // cal.setTime(bdate);
        // long time2 = cal.getTimeInMillis();

        return Math.abs(bdate.getTime() - smdate.getTime()) / (1000 * 60);
    }

    public static String getHourMin(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat df = new SimpleDateFormat("kk:mm");
        return df.format(date);
    }

    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * 功能描述: <br>
     * date间隔多少个双休日
     *
     * @param date
     * @return
     * @see [相关�?/方法](可�??)
     * @since [产品/模块版本](可�??)
     */
    public static int countWeekendDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        // TODO 可以加入判断节日方法
        boolean flag = DateTimeUtil.isWeekend(calendar.getTime());
        int count = 0;
        while (flag) {
            count++;
            calendar.add(Calendar.DATE, -1);
            // TODO 可以加入判断节日方法
            flag = DateTimeUtil.isWeekend(calendar.getTime());
        }

        return count;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println("yesterdayStartTime:" + format.format(getYesterdayStartTime()));
        // System.out.println("yesterdayEndTime:" + format.format(getYesterdayEndTime()));
        // System.out.println("thisWeekStartTime:" + format.format(getThisWeekStartTime()));
        // System.out.println("thisWeekEndTime:" + format.format(getThisWeekEndTime()));
        // System.out.println("lastWeekStartTime:" + format.format(getLastWeekStartTime()));
        // System.out.println("lastWeekEndTime:" + format.format(getLastWeekEndTime()));
        // System.out.println("thisMonthStartTime:" + format.format(getThisMonthStartTime()));
        // System.out.println("thisMonthEndTime:" + format.format(getThisMonthEndTime()));
        // System.out.println("lastMonthStartTime:" + format.format(getLastMonthStartTime()));
        // System.out.println("lastMonthEndTime:" + format.format(getLastMonthEndTime()));
        // System.out.println("lastMonthEndTime:" + format.format(getYesterdayStartByParams(new Date())));
        // System.out.println("lastMonthEndTime:" + format.format(getYesterdayEndByParams(new Date())));
        // System.out.println("lastMonthEndTime:" + format.format(getTomorrowStartByParam(new Date())));
        // System.out.println("lastMonthEndTime:" + format.format(getStartTimeByParam(new Date())));
        // System.out.println("lastMonthEndTime:" + format.format(getEndTimeByParam(new Date())));
        Date date = format.parse("2015-10-26 00:00:00");
        // System.out.println(getHourMin(date));

        System.out.println(DateTimeUtil.isWeekend(date));
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述�?�获取当前日期三个月前的日期
     *
     * @param date
     * @param amount 负数为指定日期之前的日期，正数为指定日期后的日期
     * @return
     * @see [相关�?/方法](可�??)
     * @since [产品/模块版本](可�??)
     */
    public static Date getMonthDateBefor(Date date, int amount) {
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(date);// 把当前时间赋给日�?
        calendar.add(calendar.MONTH, amount); // 设置为前N�?
        return calendar.getTime(); // 得到前N月的时间
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述�?�获取指定日期N天后的日�?
     *
     * @param date 指定日期
     * @param amount N�?(amount 负数为指定日期之前的日期，正数为指定日期后的日期)
     * @return
     * @see [相关�?/方法](可�??)
     * @since [产品/模块版本](可�??)
     */
    public static Date getDateTime(Date date, int amount) {
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(date);// 把当前时间赋给日�?
        calendar.add(calendar.DATE, amount); // 设置为前N�?
        return calendar.getTime(); // 得到前N天的时间
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述�?�获取两日期相隔天数
     *
     * @param date
     * @param date2
     * @return
     * @see [相关�?/方法](可�??)
     * @since [产品/模块版本](可�??)
     */
    public static int getDateMinus(Date date, Date date2) {
        Calendar nowDate = Calendar.getInstance(), oldDate = Calendar.getInstance();
        nowDate.setTime(date);
        oldDate.setTime(date2);
        long timeNow = nowDate.getTimeInMillis();
        long timeOld = oldDate.getTimeInMillis();
        long days = (timeNow - timeOld) / (1000 * 60 * 60 * 24);// 化为�?
        return (int) days;
    }

    /**
     * 转换时间戳到时间格式（为空则返回空）
     * 
     * @author Steven
     * @Date 2017�?1�?16�?
     * @param p_LongDate
     * @return 转换好的时间
     */
    public static Date convertLongToDate(Long p_LongDate) {
        Date m_Result = null;
        if (ParamNotNullJudge.judgeAllNotNull(p_LongDate)) {
            m_Result = new Date(p_LongDate);
        }
        return m_Result;
    }

    /**
     * 获取当日起始时间
     * 
     * @author Steven
     * @Date 2017�?1�?17�?
     * @return 当日起始时间
     */
    public static Date getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取当日结束时间
     * 
     * @author Steven
     * @Date 2017�?1�?17�?
     * @return 当日结束时间
     */
    public static Date getTodayEndTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }
}
