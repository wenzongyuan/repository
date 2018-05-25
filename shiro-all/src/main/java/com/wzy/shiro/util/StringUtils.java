package com.wzy.shiro.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * String 工具类
 *
 * @author Jason
 */
public abstract class StringUtils {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(StringUtils.class);

    /**
     * 字符串是否为空
     * 
     * @param source源字符串
     * @return 是否为空
     */
    public static boolean isEmpty(final String source) {
        return null == source || source.trim().length() == 0;
    }

    /**
     * 字符串是否不为空
     * 
     * @param source源字符串
     * @return 是否不为空
     */
    public static boolean isNotEmpty(final String source) {
        return !isEmpty(source);
    }

    /**
     * 若字符串为null, 则输出空字符串
     * 
     * @param String src : 源字符串
     * @return String;
     */
    public static String nvl(final String src) {
        return src == null ? "" : src;
    }

    /**
     * 若字符串为null, 则输出空字符串
     * 
     * @param String src : 源字符串
     * @return String;
     * @parma String target: 若src=null， 则输出 target
     */
    public static String nvl(final String src, String target) {
        return src == null ? nvl(target) : src;
    }

    /**
     * 将一个字符串首字母大写
     * 
     * @param source 源字符串
     * @return 首字母变大写的目标字符串
     */
    public static String firstLetterToUpper(final String source) {
        if (isEmpty(source)) {
            return source;
        }

        // A ~ Z : 65 ~ 90; a ~ z : 97 ~ 122
        char[] array = source.toCharArray();

        // 字符串的第一个字母是小写的英文字母
        if (array[0] >= 97 && array[0] <= 122) {
            array[0] -= 32;

            return String.valueOf(array);
        }

        return source;

    }

    /**
     * 去掉指定字符串后number个字符
     * 
     * @param source 源字符串
     * @param number 需要取消的字符个数
     * @return 去掉后number为的字符串
     */
    public static String cutLastLetter(final String source, final int number) {
        if (isEmpty(source) || number <= 0) {
            return source;
        }

        if (number >= source.length()) {
            return "";
        }

        return source.substring(0, source.length() - number);
    }

    /**
     * toString 工具类，打印实体对象信息
     * 
     * @param entity 打印目标对象
     * @param fieldNames
     * @return 对象实体信息
     */
    public static String toString(final Object entity, final String... barringFieldNames) {

        Class<?> cls = entity.getClass();

        // 获取实体所有的公共方法
        List<Method> methods = CollectionUtils.convertArray2List(cls.getMethods());

        // 参数中指定不需要toString的属性
        List<String> barringMethodNames = null;
        if (null != barringFieldNames && barringFieldNames.length != 0) {
            barringMethodNames = new ArrayList<String>();

            for (String barringFieldName : barringFieldNames) {
                barringMethodNames.add("get" + firstLetterToUpper(barringFieldName));
            }
        }

        boolean hasBarring = !CollectionUtils.isEmpty(barringMethodNames);
        List<Method> barringMethods = new ArrayList<Method>();

        // 去掉非get开头的方法和不需要打印的属性的get方法
        for (Method method : methods) {
            // 非get开头的方法
            if (!method.getName().startsWith("get")) {
                barringMethods.add(method);
                continue;
            }
            if (hasBarring) {
                // 参数指定不需要打印的方法
                for (String barringMethodName : barringMethodNames) {
                    if (method.getName().equals(barringMethodName)) {
                        barringMethods.add(method);
                        barringMethodNames.remove(barringMethodName);

                        break;
                    }
                }
            }
        }

        StringBuffer buffer = new StringBuffer(cls.getSimpleName() + ": [");

        methods.removeAll(barringMethods);

        // 得到所有私有field
        Field[] fields = cls.getDeclaredFields();
        for (Method method : methods) {
            String mn = method.getName();
            for (Field field : fields) {
                String name = field.getName().toString();
                if (mn.equals("get" + firstLetterToUpper(name))) {
                    Object objV = null;
                    String value = "";
                    try {
                        objV = method.invoke(entity);

                        if (null == objV) {
                            value = "";
                        } else if (objV.getClass().isArray()) {
                            value = CollectionUtils.toString((Object[]) objV);
                        } else {
                            value = objV.toString();
                        }
                    } catch (Exception e) {
                        logger.error("Error.", e);
                    }

                    buffer.append(name + "=\"" + value + "\", ");
                }
            }
        }

        return buffer.substring(0, buffer.length() - 2) + "]";
    }

    public static String valueOf(Object obj) {
        return (obj == null) ? null : obj.toString();
    }

    public static List<String> split(final String str, final String regex) {
        if (str == null || regex == null)
            return Collections.emptyList();
        return CollectionUtils.convertArray2List(str.split(regex));
    }

    public static List<Long> splitLong(final String str, final String regex) {
        List<String> list = split(str, regex);

        List<Long> result = new ArrayList<Long>();

        if (CollectionUtils.isEmpty(list))
            return result;

        for (String s : list) {
            Long n = NumberUtils.toLong(s, null);
            if (n != null)
                result.add(n);
        }

        return result;
    }

    public static List<Integer> splitInteger(final String str, final String regex) {
        List<String> list = split(str, regex);

        List<Integer> result = new ArrayList<>();

        if (CollectionUtils.isEmpty(list))
            return result;

        for (String s : list) {
            Integer n = NumberUtils.toInteger(s, null);
            if (n != null)
                result.add(n);
        }

        return result;
    }

    public static List<Byte> splitByte(final String str, final String regex) {
        List<String> list = split(str, regex);

        List<Byte> result = new ArrayList<Byte>();

        if (CollectionUtils.isEmpty(list))
            return result;

        for (String s : list) {
            Integer n = NumberUtils.toInteger(s, null);
            if (n != null)
                result.add(n.byteValue());
        }

        return result;
    }

    public static int chineseLength(String text) {
        if (StringUtils.isEmpty(text)) {
            return 0;
        }
        return text.length();
        /*
         * int length = 0; char[] charArray = text.toCharArray(); for (char c : charArray) { if (c < 0x80) { length +=
         * 1; } else { length += 3; } } return length;
         */
    }

    public static String between(String org, String s1, String s2) {
        int idx1 = s1 == null ? 0 : org.indexOf(s1) + s1.length();
        int idx2 = s2 == null ? org.length() : org.indexOf(s2);
        if (idx1 < 0)
            return null;
        if (idx2 < 0)
            return org.substring(idx1);
        return org.substring(idx1, idx2);
    }

    public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("UTF-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }
            b += 256;
            if ((b ^ 0xC0) >> 4 == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            } else if ((b ^ 0xE0) >> 4 == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            } else if ((b ^ 0xF0) >> 4 == 0) {
                i += 4;
            } else {
                i += 1;
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }

    /**
     * 转换List到String（以逗号分隔）
     * 
     * @param p_Strings 待转换List
     * @return 转换后的String
     */
    public static String convertList2String(List<String> p_Strings) {
        if (CollectionUtils.isEmpty(p_Strings)) {
            return null;
        } else {
            StringBuilder m_StringBuilder = new StringBuilder();
            for (String m_String : p_Strings) {
                m_StringBuilder.append(m_String).append(",");
            }
            m_StringBuilder.deleteCharAt(m_StringBuilder.length() - 1);
            return m_StringBuilder.toString();
        }
    }

    /**
     * 隐藏手机号、银行卡号中的字符
     *
     * @param source
     * @param startIndex 从第几位开始
     * @param endIndex 到第几位结束
     * @param regex 替代成的字符串 如"*"
     * @return
     */
    public static String replaceWithRegex(String source, int startIndex, int endIndex, String regex) {
        if (isEmpty(source)) {
            return null;
        } else if (endIndex < startIndex) {
            return null;
        } else {
            StringBuilder m_regex = new StringBuilder();
            StringBuilder m_StringBuilder = new StringBuilder(source);
            for (int i = 0; i < endIndex - startIndex; i++) {
                m_regex.append(regex);
            }
            StringBuilder m_result = m_StringBuilder.replace(startIndex, endIndex, m_regex.toString());
            return m_result.toString();
        }
    }

}
