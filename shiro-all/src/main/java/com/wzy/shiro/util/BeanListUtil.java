package com.wzy.shiro.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanListUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanListUtil.class);

    public static <T, D> List<D> getList(List<T> tList, Class<D> d) {
        try {
            if (tList != null && tList.size() > 0) {
                List<D> dList = new ArrayList<D>();
                for (T t : tList) {
                    D dObject = BeanUtil.getBean(t, d);
                    dList.add(dObject);
                }
                return dList;
            }
        } catch (Exception e) {
            logger.error("BeanListUtil parser error!" + e.getMessage());
        }
        return Collections.emptyList();
    }
}
