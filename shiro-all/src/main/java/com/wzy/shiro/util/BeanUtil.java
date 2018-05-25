package com.wzy.shiro.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wzy.shiro.annotion.FieldMapping;


public class BeanUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    private static final String serialVersionUID = "serialVersionUID";

    /**
     * 实体类的转换
     * 
     * @param t 源目标对�?
     * @param d �?要转换成的目的class
     * @return
     */
    public static <T, D> D getBean(T t, Class<D> d) {
        try {
            if (t != null) {
                // 获取T里声明的字段信息
                Field[] tFields = t.getClass().getDeclaredFields();
                // 按照空的够着函数生成对象
                D dObject = d.newInstance();
                // 获取D里声明的字段信息
                Field[] fields = d.getDeclaredFields();
                // 循环赋�??
                for (Field field : fields) {
                    // 判断是否包含�?要转换的字段信息
                    String fieldName = field.getName();
                    if (serialVersionUID.equals(fieldName)) {
                        continue;
                    }

                    if (containField(tFields, field)) {
                        field.setAccessible(true);
                        // 是否有字段注�?
                        FieldMapping fieldMapping = field.getAnnotation(FieldMapping.class);
                        if (fieldMapping != null) {
                            // 获取注解对应的字段信�?
                            fieldName = fieldMapping.value();
                        }
                        // bean的反射取值�?�赋�?
                        PropertyDescriptor pd = new PropertyDescriptor(fieldName, t.getClass());
                        Method method = pd.getReadMethod();
                        Object retVal = method.invoke(t);
                        pd = new PropertyDescriptor(field.getName(), d);
                        method = pd.getWriteMethod();
                        method.invoke(dObject, retVal);
                    } else {
                        logger.warn("no field {} found in class {} ", field.getName(), t.getClass().getName());
                    }
                }
                return dObject;
            }
        } catch (Exception e) {
            logger.error("BeanUtil parser error：{}", e.getMessage());
        }
        return null;
    }

    private static boolean containField(Field[] fields, Field field) {
        if (fields.length > 0) {
            FieldMapping fieldMapping = field.getAnnotation(FieldMapping.class);
            String fieldName = field.getName();
            if (fieldMapping != null) {
                fieldName = fieldMapping.value();
            }
            for (Field newField : fields) {
                if (fieldName.equals(newField.getName()) && field.getType().equals(newField.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

}
