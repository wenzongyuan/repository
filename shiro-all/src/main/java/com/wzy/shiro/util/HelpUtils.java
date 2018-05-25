package com.wzy.shiro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelpUtils {

    /**
     * 获取当前时间
     * 
     * @return
     */
    public static Date getDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(df.format(new Date()));
        } catch (ParseException e) {
            return new Date();
        }

    }

}
