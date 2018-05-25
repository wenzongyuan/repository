package com.wzy.shiro.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * number handle util
 * 
 * @author boyce
 * @version 2014-4-9
 */
public abstract class NumberUtils {
    private static final Logger logger = LoggerFactory.getLogger(NumberUtils.class);

    /**
     * parse a string to long with default value
     * 
     * @param str a string
     * @param defaultValue if string is empty or is not a number, return default value
     * @return a long
     */
    public static Long toLong(final String str, final Long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str.trim());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * parse a string to long with a default value 0
     * 
     * @param str a string
     * @return a long
     */
    public static long toLong(final String str) {
        return toLong(str, 0L);
    }

    /**
     * parse a string to int with a default value 0
     * 
     * @param str a string
     * @return a long
     */
    public static int toInt(final String str) {
        return toInt(str, 0);
    }

    /**
     * parse a string to int with default value
     * 
     * @param str a string
     * @param defaultValue if string is empty or is not a number, return default value
     * @return a int
     */
    public static int toInt(final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Integer toInteger(final String str, final Integer defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 仅返回成功解析的数字，无有效数据时返回空list，不会返回null
     */
    public static List<Long> convert(List<String> ids) {
        if (ids == null || ids.isEmpty())
            return Collections.emptyList();

        List<Long> list = new ArrayList<Long>();
        for (String id : ids) {
            Long l = toLong(id, null);
            if (l != null)
                list.add(l);
        }
        return list;
    }

    public static Integer parseInt(String source) {
        return parseInt(source, null);
    }

    public static Integer parseInt(String source, Integer defaultValue) {
        if (StringUtils.isEmpty(source))
            return defaultValue;

        try {
            Integer value = Integer.parseInt(source);
            return value;
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            return defaultValue;
        }
    }

    public static Short parseShort(String source) {
        return parseShort(source, null);
    }

    public static Short parseShort(String source, Short defaultValue) {
        if (StringUtils.isEmpty(source))
            return defaultValue;

        try {
            Short value = Short.parseShort(source);
            return value;
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            return defaultValue;
        }
    }

    public static Double parseDouble(String source) {
        return parseDouble(source, null);
    }

    public static Double parseDouble(String source, Double defaultValue) {
        if (StringUtils.isEmpty(source))
            return defaultValue;

        try {
            return Double.parseDouble(source);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            return defaultValue;
        }
    }

    /**
     * 若某数为null，则强制转为 rst
     * 
     * @param first
     * @param rst
     * @return
     */
    public static int nvl(Integer first, int rst) {
        return first == null ? rst : first;
    }

    public static int nvl(Integer first) {
        return nvl(first, 0);
    }

    public static float nvl(Float first, float rst) {
        return first == null ? rst : first;
    }

    public static float nvl(Float first) {
        return nvl(first, 0);
    }

    public static int getRandInt(int rangeStart, int rangeEnd) {
        int randResult = rangeStart + (int) (Math.random() * (rangeEnd - rangeStart + 1));
        return randResult;
    }

    /**
     * 功能描述: 支持将BigDecimal/Long/Integer/String转换成int<br>
     */
    public static int convertObj2Int(final Object obj) {
        if (obj == null)
            return 0;

        if (obj instanceof BigDecimal)
            return ((BigDecimal) obj).intValue();
        else if (obj instanceof Long)
            return ((Long) obj).intValue();
        else if (obj instanceof Integer)
            return ((Integer) obj).intValue();
        else if (obj instanceof String)
            return toInt((String) obj);
        else
            return 0;
    }

    /**
     * 功能描述: 判断�?批long类型的数据，是否都大�?0<br>
     */
    public static boolean isAllGreaterThanZero(long... ids) {
        if (ids == null) {
            return false;
        }

        for (long id : ids) {
            if (id <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证参数
     */
    public static boolean checkLongParams(Long... params) {
        // (alan)这个方法可以写成在util类里
        if (params != null) {
            for (Long param : params) {
                if (param == null || param <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
