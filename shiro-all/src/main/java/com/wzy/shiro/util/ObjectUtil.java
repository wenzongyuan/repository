package com.wzy.shiro.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ken
 */
public class ObjectUtil {
    private static String[] DefaultDatePattern = new String[]{
            "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss SSS"};

    private static String[] DefaultTrueValues = new String[]{
            "true",
            "1",
            "on"
    };

	/*------------------------------------------------------------------------------------
                                int
	------------------------------------------------------------------------------------*/


    public static int convertToInt(Object obj) {
        return Integer.parseInt(obj.toString());
    }

    public static int convertToInt(Object obj, int defaultValue) {
        if (obj == null)
            return defaultValue;
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Integer convertToIntObject(Object obj, Integer defaultValue) {
        if (obj == null)
            return defaultValue;
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

	/*------------------------------------------------------------------------------------
                                long
	------------------------------------------------------------------------------------*/


    public static long convertToLong(Object obj) {
        return Long.parseLong(obj.toString());
    }

    public static long convertToLong(Object obj, long defaultValue) {
        if (obj == null)
            return defaultValue;
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Long convertToLongObject(Object obj, Long defaultValue) {
        if (obj == null)
            return defaultValue;
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

	/*------------------------------------------------------------------------------------
	                            float
	------------------------------------------------------------------------------------*/

    public static float convertToFloat(Object obj) {
        return Float.parseFloat(obj.toString());
    }

    public static float convertToFloat(Object obj, float defaultValue) {
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Float convertToFloatObject(Object obj, Float defaultValue) {
        if (obj == null)
            return defaultValue;
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

	/*------------------------------------------------------------------------------------
	                            double
	------------------------------------------------------------------------------------*/


    public static double convertToDouble(Object obj) {
        return Double.parseDouble(obj.toString());
    }

    public static double convertToDouble(Object obj, double defaultValue) {
        if (obj == null)
            return defaultValue;
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Double convertToDoubleObject(Object obj, Double defaultValue) {
        if (obj == null)
            return defaultValue;
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /*------------------------------------------------------------------------------------
                                boolean
    ------------------------------------------------------------------------------------*/
    public static boolean convertToBoolean(Object obj, String... trueValue) {
        if (trueValue == null || trueValue.length == 0) {
            trueValue = DefaultTrueValues;
        }
        if (obj == null) {
            for (String s : trueValue) {
                if (s == null)
                    return true;
            }
        } else {
            for (String s : trueValue) {
                if (obj.toString().equals(s))
                    return true;
            }
        }
        return false;
    }

	 

	/*------------------------------------------------------------------------------------
	                            date
	------------------------------------------------------------------------------------*/

    public static Date convertToDate(Object obj, Date defaultValue,
                                     String... datePattern) {
        if (obj == null)
            return defaultValue;
        if (datePattern == null || datePattern.length == 0) {
            datePattern = DefaultDatePattern;
        }

        if (obj instanceof Timestamp) {
            return new Date(((Timestamp) obj).getTime());
        }

        if (obj instanceof Date) {
            return (Date) obj;
        }

        for (String s : datePattern) {
            try {
                DateFormat sdf = formatMap.get(s);
                if (sdf == null) {
                    sdf = new SimpleDateFormat(s);
                    formatMap.put(s, sdf);
                }
                return sdf.parse(obj.toString());
            } catch (Exception e) {

            }
        }
        return defaultValue;
    }

    public static String trimString(Object obj, String defaultValue) {
        if (obj == null || "".equals(obj.toString().trim()))
            return defaultValue;
        return obj.toString().trim();
        // return obj == null ? "" : obj.toString().trim();
    }

    public static String trimToEmpty(Object obj) {
        return obj == null ? "" : obj.toString().trim();
    }

    public static String trimToNull(Object obj) {
        if (obj == null)
            return null;
        String s = obj.toString().trim();
        return s.equalsIgnoreCase("") ? null : s;
    }

    public static <T> boolean is(Object object, Class<T> cls) {
        if (object == null)
            return false;
        return cls.isAssignableFrom(object.getClass());
    }

    public static <T> T as(Object object, Class<T> cls) {
        return is(object, cls) ? (T) object : null;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNotEmptyOrBlank(Object obj) {
        return !isEmptyOrBlank(obj);
    }

    public static boolean isAllItemNotEmptyOrBlank(Object... obj) {
        for (Object item : obj) {
            if (isEmptyOrBlank(item))
                return false;
        }
        return true;
    }

    public static boolean isAllItemEmptyOrBlank(Object... obj) {
        for (Object item : obj) {
            if (isNotEmptyOrBlank(item))
                return false;
        }
        return true;
    }

    private static DecimalFormat decimalFormat = new DecimalFormat("##################.##");

    //四舍五入后计算金�?
    public static double roundFormatMoney(double money) {
        money = Math.round(money * 100) / 100d;
        return Double.valueOf(decimalFormat.format(money));
    }

    public static void main(String[] args) {
        System.out.println(roundFormatMoney(3.3333333333333333333));
        System.out.println(roundFormatMoney(3.3297333333333333));
        double d = -0.5900000000000001;
        System.out.println(roundFormatMoney(d));
        System.out.println(roundFormatMoney(d)+0.5900);

    }

    public static boolean isExistNotEmptyOrBlankItem(Object... obj) {
        for (Object item : obj) {
            if (isNotEmptyOrBlank(item))
                return true;
        }
        return false;
    }

    public static boolean isExistEmptyOrBlankItem(Object... obj) {
        for (Object item : obj) {
            if (isEmptyOrBlank(item))
                return true;
        }
        return false;
    }

    public static boolean isEmptyOrBlank(Object obj) {
        if (obj == null)
            return true;
        if (obj instanceof String) {
            return "".equals(((String) obj).trim());
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        return false;
    }

    public static String guid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static boolean isNumber(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof Number)
            return true;
        if (obj instanceof String) {
            return NumberUtils.isNumber((String) obj);
        }
        return false;
    }

    public static boolean isDigits(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof String || obj instanceof Number) {
            return NumberUtils.isDigits(obj.toString());
        }
        return false;
    }

    private static Map<String, DateFormat> formatMap = new HashMap<String, DateFormat>();

    public static String formatDate(Date d, String format) {
        if (d == null)
            return "";
        if (isEmptyOrBlank(format))
            return "";
        DateFormat df = formatMap.get(format);
        if (df == null) {
            df = new SimpleDateFormat(format);
            formatMap.put(format, df);
        }
        return df.format(d);

    }

    // --------------------------------------------------------


    public static boolean canConvertToInt(Object object) {
        try {
            if (object == null)
                return false;
            Integer.parseInt(object.toString());
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public static boolean canConvertToLong(Object object) {
        try {
            if (object == null)
                return false;
            Long.parseLong(object.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> splitStr(String str, String separateStr) {
        if (isEmptyOrBlank(separateStr))
            separateStr = ",";
        return new Vector(Arrays.asList(str.split(separateStr)));
    }

    public static boolean canConvertToFloat(Object object) {
        try {
            if (object == null)
                return false;
            Float.parseFloat(object.toString());
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public static boolean canConvertToDouble(Object object) {
        try {
            if (object == null)
                return false;
            Double.parseDouble(object.toString());
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public static boolean canConvertToDate(Object object, String... datePattern) {
        if (object == null)
            return false;
        if (object instanceof Date)
            return true;
        if (object instanceof Timestamp)
            return true;
        for (String s : datePattern) {
            try {
                DateFormat sdf = formatMap.get(s);
                if (sdf == null) {
                    sdf = new SimpleDateFormat(s);
                    formatMap.put(s, sdf);
                }
                sdf.parse(object.toString());
                return true;
            } catch (Exception e) {

            }
        }
        return false;
    }


    public static String ifEmptyOrBlank(Object object, String defaultValue) {
        if (isEmptyOrBlank(object))
            return defaultValue;
        return object.toString();
    }

    public static String upperFirstCharacter(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String lowerFirstCharacter(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String md5(String str) {
        return MD5Util.MD5(str);
    }

    public static boolean equalsOne(String str, String... compareStr) {
        for (String s : compareStr) {
            if (StringUtils.equals(str, s)) return true;
        }
        return false;
    }

    public static String returnFirstStrNotBlank(String... strs) {
        for (String s : strs) {
            if (!StringUtils.isBlank(s)) return s;
        }
        return null;
    }

    public static List<String> splitString(String str, String splitChar) {
        return splitString(str, splitChar, false);
    }

    public static List<String> splitString(String str, String splitChar, boolean removeNullOrEmptyElement) {
        List<String> result = new ArrayList<String>();
        if (str == null) return result;
        for (String s : str.split(splitChar)) {
            if (removeNullOrEmptyElement) {
                if (isNotEmptyOrBlank(s)) {
                    result.add(s);
                }
            } else {
                result.add(s);
            }
        }
        return result;
    }

    public static <T> List<T> removeNullOrEmptyElement(List<T> list) {
        if (list == null) return null;
        List<T> result = new ArrayList<T>();
        for (T el : list) {
            if (isEmptyOrBlank(el)) continue;
            result.add(el);
        }
        return result;
    }


}
