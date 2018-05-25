package com.wzy.shiro.util;

import org.springframework.util.StringUtils;


/**
 * 参数列表非空判断
 *
 * @author Steven
 * @Date 2017�?1�?7�?
 */
public class ParamNotNullJudge {
    /**
     * 判断参数列表是否全为非空或�?�非空字符串
     * 
     * @author Steven
     * @Date 2017�?1�?7�?
     * @param p_Params 参数列表
     * @return 是否有空参数
     */
    public static boolean judgeAllNotNull(Object... p_Params) {
        for (Object object : p_Params) {
            if (StringUtils.isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断参数列表是否有空值或者空字符�?
     * 
     * @author Steven
     * @Date 2017�?1�?7�?
     * @param p_Params
     * @return
     */
    public static boolean judgeExistNull(Object... p_Params) {
        for (Object object : p_Params) {
            if (StringUtils.isEmpty(object)) {
                return true;
            }
        }
        return false;
    }

    public static boolean whetherNullThrowExcep(Object... p_Params) throws Exception  {
        for (Object object : p_Params) {
            if (StringUtils.isEmpty(object)) {
                throw new Exception("参数缺失");
            }
        }
        return false;
    }
}
