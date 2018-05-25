package com.wzy.shiro.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnionQueryUtil {
    public static <T, D, A> A UnionTranslateBean(T t, D d, Field field, Class<A> a) throws Exception, IllegalAccessException {
        String serialVersionUID = "serialVersionUID";
        Field[] tFields = t.getClass().getDeclaredFields();
        Field[] dFields = d.getClass().getDeclaredFields();
        Field[] aFields = a.getDeclaredFields();
        if (containField(dFields, field) && containField(tFields, field) && containField(aFields, field)) {
            A returnObject = a.newInstance();
            for (Field aField : aFields) {
                if (serialVersionUID.equals(aField.getName())) {
                    continue;
                }
                aField.setAccessible(true);
                for (Field tField : tFields) {
                    if (serialVersionUID.equals(tField.getName())) {
                        continue;
                    }
                    tField.setAccessible(true);
                    if (tField.getName().equals(aField.getName())) {
                        PropertyDescriptor pd = new PropertyDescriptor(tField.getName(), t.getClass());
                        Method method = pd.getReadMethod();
                        Object retVal = method.invoke(t);
                        pd = new PropertyDescriptor(aField.getName(), a);
                        method = pd.getWriteMethod();
                        if (aField.getType().getSimpleName().equals("Date")) {
                            Date dateVal = (Date) retVal;
                            method.invoke(returnObject, dateVal);
                        } else {
                            method.invoke(returnObject, retVal);
                        }
                    }
                }
                for (Field dField : dFields) {
                    if (serialVersionUID.equals(dField.getName())) {
                        continue;
                    }
                    dField.setAccessible(true);
                    if (dField.getName().equals(aField.getName())) {
                        PropertyDescriptor pd = new PropertyDescriptor(dField.getName(), d.getClass());
                        Method method = pd.getReadMethod();
                        Object retVal = method.invoke(d);
                        pd = new PropertyDescriptor(aField.getName(), a);
                        method = pd.getWriteMethod();
                        if (aField.getType().getSimpleName().equals("Date")) {
                            Date dateVal = (Date) retVal;
                            method.invoke(returnObject, dateVal);
                        } else {
                            method.invoke(returnObject, retVal);
                        }
                    }
                }
            }
            return returnObject;
        }
        return null;
    }

    public static <T, D, A> List<A> getList(List<T> tList, List<D> dList, Class<A> a, Class<T> tClazz, Class<D> dClazz, Field field) throws Exception {
        List<A> aList = new ArrayList<A>();
        Field[] tFields = tClazz.getDeclaredFields();
        Field[] dFields = dClazz.getDeclaredFields();
        Field[] aFields = a.getDeclaredFields();
        if (containField(dFields, field) && containField(tFields, field) && containField(aFields, field)) {
            for (T t : tList) {
                for (D d : dList) {
                    PropertyDescriptor pDescriptorT = new PropertyDescriptor(field.getName(), tClazz);
                    PropertyDescriptor pDescriptorD = new PropertyDescriptor(field.getName(), dClazz);
                    Method methodT = pDescriptorT.getReadMethod();
                    Method methodD = pDescriptorD.getReadMethod();
                    Object DRetVal = methodD.invoke(d);
                    Object TRetVal = methodT.invoke(t);
                    if (DRetVal.equals(TRetVal)) {
                        A aObj = a.newInstance();
                        aObj = UnionTranslateBean(t, d, field, a);
                        aList.add(aObj);
                    }
                }
            }
            return aList;
        }
        return null;
    }

    private static boolean containField(Field[] fields, Field field) {
        for (Field fieldIterator : fields) {
            if (fieldIterator.getName().equals(field.getName())) {
                return true;
            }
        }
        return false;
    }
}
