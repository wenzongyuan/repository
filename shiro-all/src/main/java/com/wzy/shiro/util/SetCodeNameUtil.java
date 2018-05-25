package com.wzy.shiro.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SetCodeNameUtil {
    private static final Log log = LogFactory.getLog(SetCodeNameUtil.class);

    public static <T, U> void setValue(List<T> m_List, U m_Bean)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Method getBeginProfitManner = m_Bean.getClass().getDeclaredMethod("getBeginProfitMannerCode");
        Method getBeginBenefitDayTypeCode = m_Bean.getClass().getDeclaredMethod("getBeginBenefitDayTypeCode");
        Method getBeginProfitDateTypeCode = m_Bean.getClass().getDeclaredMethod("getBeginProfitDateTypeCode");
        Method getRedeemTypeCode = m_Bean.getClass().getDeclaredMethod("getRedeemTypeCode");
        Method getRedeemDayTypeCode = m_Bean.getClass().getDeclaredMethod("getRedeemDayTypeCode");
        Method getRedeemDateTypeCode = m_Bean.getClass().getDeclaredMethod("getRedeemDateTypeCode");
        Method getProductProfitTypeCode = m_Bean.getClass().getDeclaredMethod("getProductProfitTypeCode");
        Method getCapitalRedeemRuleCode = m_Bean.getClass().getDeclaredMethod("getCapitalRedeemRuleCode");
        Method getProfitLiquidationMannerCode = m_Bean.getClass().getDeclaredMethod("getProfitLiquidationMannerCode");
        Method getProfitSettleMannerCode = m_Bean.getClass().getDeclaredMethod("getProfitSettleMannerCode");

        Method setBeginProfitMannerName = m_Bean.getClass().getDeclaredMethod("setBeginProfitMannerName", String.class);
        Method setBeginBenefitDayTypeName = m_Bean.getClass().getDeclaredMethod("setBeginBenefitDayTypeName", String.class);
        Method setBeginProfitDateTypeName = m_Bean.getClass().getDeclaredMethod("setBeginProfitDateTypeName", String.class);
        Method setRedeemType = m_Bean.getClass().getDeclaredMethod("setRedeemType", String.class);
        Method setRedeemDayTypeName = m_Bean.getClass().getDeclaredMethod("setRedeemDayTypeName", String.class);
        Method setRedeemDateTypeName = m_Bean.getClass().getDeclaredMethod("setRedeemDateTypeName", String.class);
        Method setProductProfitTypeName = m_Bean.getClass().getDeclaredMethod("setProductProfitTypeName", String.class);
        Method setCapitalRedeemRuleName = m_Bean.getClass().getDeclaredMethod("setCapitalRedeemRuleName", String.class);
        Method setProfitLiquidationMannerName = m_Bean.getClass().getDeclaredMethod("setProfitLiquidationMannerName", String.class);
        Method setProfitSettleMannerName = m_Bean.getClass().getDeclaredMethod("setProfitSettleMannerName", String.class);

        Method[] methodList = m_Bean.getClass().getDeclaredMethods();
        for (T t : m_List) {
            Method list_Method = t.getClass().getDeclaredMethod("getCode");
            Method list_GetCnName = t.getClass().getDeclaredMethod("getCnName");
            if (list_Method.invoke(t).equals(getBeginProfitManner.invoke(m_Bean))) {
                setBeginProfitMannerName.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getBeginBenefitDayTypeCode.invoke(m_Bean))) {
                setBeginBenefitDayTypeName.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getBeginProfitDateTypeCode.invoke(m_Bean))) {
                setBeginProfitDateTypeName.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getRedeemTypeCode.invoke(m_Bean))) {
                setRedeemType.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getRedeemDayTypeCode.invoke(m_Bean))) {
                setRedeemDayTypeName.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getRedeemDateTypeCode.invoke(m_Bean))) {
                setRedeemDateTypeName.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getProductProfitTypeCode.invoke(m_Bean))) {
                setProductProfitTypeName.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getCapitalRedeemRuleCode.invoke(m_Bean))) {
                setCapitalRedeemRuleName.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getProfitLiquidationMannerCode.invoke(m_Bean))) {
                setProfitLiquidationMannerName.invoke(m_Bean, list_GetCnName.invoke(t));
            }
            if (list_Method.invoke(t).equals(getProfitSettleMannerCode.invoke(m_Bean))) {
                setProfitSettleMannerName.invoke(m_Bean, list_GetCnName.invoke(t));
            }

        }

    }
}
