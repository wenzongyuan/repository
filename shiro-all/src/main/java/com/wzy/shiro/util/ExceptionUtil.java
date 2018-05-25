package com.wzy.shiro.util;

public class ExceptionUtil {
    public static Throwable getRootCause(Throwable p_Throwable) {
        Throwable m_Throwable = null;
        if (p_Throwable.getCause() != null) {
            return getRootCause(p_Throwable.getCause());
        }
        return p_Throwable;
    }
}
