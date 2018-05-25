package com.wzy.shiro.util;

import java.text.DecimalFormat;
import java.util.Date;

public class AppUtil {

    //�?天的毫秒�?,日期计算时经常用�?
    public static long ONE_DAY_MILL_SECOND = 1 * 24 * 60 * 60 * 1000;

    //t_invite_reward_rule 奖励方式�?0 现金�?1 体验金；2 投资红包�?
    //t_invite_detail 奖励方式�?     0 体验金；1 投资红包�?2 现金�?
    public static String convertInviteRuleRewardTypeToInviteDetailRewardType(String type){
        if("0".equals(type)) return "2";
        if("1".equals(type)) return "0";
        if("2".equals(type)) return "1";
        return type;
    }

    //�?天的第一�?
    //比如传入 2015-12-12 12:00:01
    //返回的结果应该是 2015-12-12 00:00:00
    public static Date getDateOneDayBeginSecond(Date date){
        String s = ObjectUtil.formatDate(date,"yyyy-MM-dd");
        s += " 00:00:00";
        return ObjectUtil.convertToDate(s,null,"yyyy-MM-dd HH:mm:ss");
    }

    //�?天的�?后一�?
    //比如传入 2015-12-12 12:00:01
    //返回的结果应该是 2015-12-12 23:59:59
    public static Date getDateOneDayLastSecond(Date date){
        String s = ObjectUtil.formatDate(date,"yyyy-MM-dd");
        s += " 23:59:59";
        return ObjectUtil.convertToDate(s,null,"yyyy-MM-dd HH:mm:ss");
    }




    //加密密码
    public static String encodePassword(String password){
        if(ObjectUtil.isNotEmptyOrBlank(password)){
            return MD5Util.MD5(MD5Util.MD5(password+"&p%2&1sd"));
        }
        return password;
    }

    public static String convertToHtmlString(Object value) {

        if (value == null) return "";
        return String.valueOf(value);
        //if ("".equals(value.toString().trim())) return "";
        //return value.toString().replace(" ", "&nbsp;").replace("\r\n", "<br>").replace("\n", "<br>");
    }


    private static DecimalFormat decimalFormat = new DecimalFormat("##################.######");

    //修正浮点数精度的影响
    public static double fix(double value){
        value = Math.round(value * 1000000) / 1000000d;
        double v = Double.valueOf(decimalFormat.format(value));
        if(v == 0) v = 0d;
        return v;
    }


    /*
    public static void main(String[] args) {
        System.out.println(encodePassword("000000"));
        //F85C28E594E73E65FD8936E1892B55D4
    }
    */
}
