package com.wzy.shiro.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * String ������
 *
 * @author Jason
 */
public abstract class StringUtils {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(StringUtils.class);

    /**
     * �ַ����Ƿ�Ϊ��
     * 
     * @param sourceԴ�ַ���
     * @return �Ƿ�Ϊ��
     */
    public static boolean isEmpty(final String source) {
        return null == source || source.trim().length() == 0;
    }

    /**
     * �ַ����Ƿ�Ϊ��
     * 
     * @param sourceԴ�ַ���
     * @return �Ƿ�Ϊ��
     */
    public static boolean isNotEmpty(final String source) {
        return !isEmpty(source);
    }

    /**
     * ���ַ���Ϊnull, ��������ַ���
     * 
     * @param String src : Դ�ַ���
     * @return String;
     */
    public static String nvl(final String src) {
        return src == null ? "" : src;
    }

    /**
     * ���ַ���Ϊnull, ��������ַ���
     * 
     * @param String src : Դ�ַ���
     * @return String;
     * @parma String target: ��src=null�� ����� target
     */
    public static String nvl(final String src, String target) {
        return src == null ? nvl(target) : src;
    }

    /**
     * ��һ���ַ�������ĸ��д
     * 
     * @param source Դ�ַ���
     * @return ����ĸ���д��Ŀ���ַ���
     */
    public static String firstLetterToUpper(final String source) {
        if (isEmpty(source)) {
            return source;
        }

        // A ~ Z : 65 ~ 90; a ~ z : 97 ~ 122
        char[] array = source.toCharArray();

        // �ַ����ĵ�һ����ĸ��Сд��Ӣ����ĸ
        if (array[0] >= 97 && array[0] <= 122) {
            array[0] -= 32;

            return String.valueOf(array);
        }

        return source;

    }

    /**
     * ȥ��ָ���ַ�����number���ַ�
     * 
     * @param source Դ�ַ���
     * @param number ��Ҫȡ�����ַ�����
     * @return ȥ����numberΪ���ַ���
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
     * toString �����࣬��ӡʵ�������Ϣ
     * 
     * @param entity ��ӡĿ�����
     * @param fieldNames
     * @return ����ʵ����Ϣ
     */
    public static String toString(final Object entity, final String... barringFieldNames) {

        Class<?> cls = entity.getClass();

        // ��ȡʵ�����еĹ�������
        List<Method> methods = CollectionUtils.convertArray2List(cls.getMethods());

        // ������ָ������ҪtoString������
        List<String> barringMethodNames = null;
        if (null != barringFieldNames && barringFieldNames.length != 0) {
            barringMethodNames = new ArrayList<String>();

            for (String barringFieldName : barringFieldNames) {
                barringMethodNames.add("get" + firstLetterToUpper(barringFieldName));
            }
        }

        boolean hasBarring = !CollectionUtils.isEmpty(barringMethodNames);
        List<Method> barringMethods = new ArrayList<Method>();

        // ȥ����get��ͷ�ķ����Ͳ���Ҫ��ӡ�����Ե�get����
        for (Method method : methods) {
            // ��get��ͷ�ķ���
            if (!method.getName().startsWith("get")) {
                barringMethods.add(method);
                continue;
            }
            if (hasBarring) {
                // ����ָ������Ҫ��ӡ�ķ���
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

        // �õ�����˽��field
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
     * ת��List��String���Զ��ŷָ���
     * 
     * @param p_Strings ��ת��List
     * @return ת�����String
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
     * �����ֻ��š����п����е��ַ�
     *
     * @param source
     * @param startIndex �ӵڼ�λ��ʼ
     * @param endIndex ���ڼ�λ����
     * @param regex ����ɵ��ַ��� ��"*"
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
