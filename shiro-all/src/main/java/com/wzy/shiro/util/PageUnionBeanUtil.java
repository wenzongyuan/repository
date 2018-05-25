package com.wzy.shiro.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageUnionBeanUtil {
    private static final String serialVersionUID = "serialVersionUID";

    private static final Logger logger = LoggerFactory.getLogger(PageUnionBeanUtil.class);

    // private static boolean containField(Field[] fields, Field field) {
    // if (fields.length > 0) {
    // FieldMapping fieldMapping = field.getAnnotation(FieldMapping.class);
    // String fieldName = field.getName();
    // if (fieldMapping != null) {
    // fieldName = fieldMapping.value();
    // }
    // for (Field newField : fields) {
    // if (fieldName.equals(newField.getName()) && field.getType().equals(newField.getType())) {
    // return true;
    // }
    // }
    // }
    // return false;
    // }
    public static <D, T> D getBean(List<T> tList, Integer a, Class<D> d) {
        if (a == null) {
            BeanListUtil.getList(tList, d);
        }
        try {
            Field[] dFields = d.getDeclaredFields();
            D dObject = d.newInstance();
            // Method[] method = dObject.getClass().getMethods();
            a.getClass().getName();
            if (tList != null && tList.size() > 0) {
                for (Field dField : dFields) {
                    if (serialVersionUID.equals(dField.getName())) {
                        continue;
                    }
                    dField.setAccessible(true);
                    if (dField.getName().equals("totalPage") && dField.getType().isAssignableFrom(Integer.class)) {
                        PropertyDescriptor m_TotalPageProperty = new PropertyDescriptor(dField.getName(), d);
                        Method m_TotalPageMethod = m_TotalPageProperty.getWriteMethod();
                        m_TotalPageMethod.invoke(dObject, a);
                    }
                    if (dField.getName().matches("[a-zA-z]+List") && dField.getType().isAssignableFrom(List.class)) {
                        ParameterizedType m_parameterizedType = (ParameterizedType) dField.getGenericType();
                        Class m_ActualClass = (Class) m_parameterizedType.getActualTypeArguments()[0];
                        List<T> m_ListResult = new ArrayList<T>();

                        for (T t : tList) {
                            T m_objBean = (T) m_ActualClass.newInstance();
                            Object retVal = new Object();
                            for (Field AcutualDField : m_ActualClass.getDeclaredFields()) {
                                if (serialVersionUID.equals(AcutualDField.getName())) {
                                    continue;
                                }
                                AcutualDField.setAccessible(true);
                                PropertyDescriptor pdD = new PropertyDescriptor(AcutualDField.getName(), tList.get(0).getClass());
                                Field[] tFields = t.getClass().getDeclaredFields();
                                for (Field tfield : tFields) {
                                    if (tfield.getName().equals(AcutualDField.getName())) {
                                        PropertyDescriptor pdT = new PropertyDescriptor(tfield.getName(), t.getClass());
                                        Method method = pdT.getReadMethod();
                                        retVal = method.invoke(t);
                                        method = pdD.getWriteMethod();
                                        if (tfield.getName().equals(AcutualDField.getName())) {
                                            method.invoke(m_objBean, retVal);
                                            // method.invoke(dObject, retVal);
                                        }
                                    }
                                }
                            }
                            m_ListResult.add((T) m_objBean);
                        }
                        PropertyDescriptor pdDList = new PropertyDescriptor(dField.getName(), d);
                        Method ListMethod = pdDList.getWriteMethod();
                        ListMethod.invoke(dObject, m_ListResult);
                    }
                }
                return dObject;
            }
        } catch (Exception e) {
            logger.error("error!" + e.getMessage());
        }
        return null;

    }
}
