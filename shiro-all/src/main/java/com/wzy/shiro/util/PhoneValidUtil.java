package com.wzy.shiro.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidUtil {
    private static String c_Pattern = "[1](\\d{10})";

    /**
     * 验证手机号码规则
     *
     * @param p_PhoneNumber 待验证的电话号码
     * @return 是否符合规则
     */
    public static boolean validatePhone(String p_PhoneNumber) {
        Pattern pattern = Pattern.compile(c_Pattern);
        Matcher matcher = pattern.matcher(p_PhoneNumber);
        return matcher.matches();
    }

    /**
     * 验证手机号码规则
     * 
     * @param p_PhoneNumber 待验证的电话号码列表
     * @return 符合规则的电话号码列�?
     */
    public static List<String> validatePhoneList(List<String> p_PhoneNumber) {
        if (CollectionUtils.isEmpty(p_PhoneNumber)) {
            return null;
        } else {
            List<String> m_PhoneNumberResult = new ArrayList<String>();
            // 循环验证
            for (String m_PhoneNumber : p_PhoneNumber) {
                Pattern pattern = Pattern.compile(c_Pattern);
                Matcher matcher = pattern.matcher(m_PhoneNumber);
                if (StringUtils.isNotEmpty(m_PhoneNumber)) {
                    if (matcher.matches()) {
                        m_PhoneNumberResult.add(m_PhoneNumber);
                    }
                }
            }
            return m_PhoneNumberResult;
        }
    }

}
