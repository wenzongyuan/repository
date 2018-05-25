package com.wzy.shiro.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数字金额转中文大�?
 *
 * @author Du
 */
public class ConvertNum {

    private static final Logger logger = LoggerFactory.getLogger(ConvertNum.class);
    /**
     * 把金额阿拉伯数字转换为汉字表示，小数点后四舍五入保留两位 还有�?种方法可以在转换的过程中不�?�虑连续0的情况，然后对最终的结果进行�?次遍历合并连续的�?
     */
    public static String[] ChineseNum = new String[] { "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?" };

    public static String NumToChinese(double num) {
        if (num > 99999999999999.99 || num < -99999999999999.99) {
            logger.error("参数值超出允许范�? (-99999999999999.99 �? 99999999999999.99)�?");
            return "";
        }
        boolean negative = false;// 正负标号
        if (num < 0) {
            negative = true;
            num = num * (-1);
        }
        long temp = Math.round(num * 100);
        int numFen = (int) (temp % 10);// �?
        temp = temp / 10;
        int numJiao = (int) (temp % 10);// �?
        temp = temp / 10;
        // 此时temp只包含整数部�?
        int[] parts = new int[20];// 将金额整数部分分为在0-9999之间数的各个部分
        int numParts = 0;// 记录把原来金额整数部分分割为几个部分
        for (int i = 0;; i++) {
            if (temp == 0)
                break;
            int part = (int) (temp % 10000);
            parts[i] = part;
            temp = temp / 10000;
            numParts++;
        }
        boolean beforeWanIsZero = true;// 标志位，记录万的下一级是否为0
        String chineseStr = "";
        for (int i = 0; i < numParts; i++) {
            String partChinese = partConvert(parts[i]);
            if (i % 2 == 0) {
                if ("".equals(partChinese))
                    beforeWanIsZero = true;
                else
                    beforeWanIsZero = false;
            }
            if (i != 0) {
                if (i % 2 == 0)// 亿的部分
                    chineseStr = "�?" + chineseStr;
                else {
                    if ("".equals(partChinese) && !beforeWanIsZero)// 如果“万”对应的 part �? 0，�?��?�万”下面一级不�? 0，则不加“万”，而加“零�?
                        chineseStr = "�?" + chineseStr;
                    else {
                        if (parts[i - 1] < 1000 && parts[i - 1] > 0)// 如果万的部分不为0，�?�万前面的部分小�?1000大于0，则万后面应该跟�?
                            chineseStr = "�?" + chineseStr;
                        chineseStr = "�?" + chineseStr;
                    }
                }
            }
            chineseStr = partChinese + chineseStr;
        }
        if ("".equals(chineseStr))// 整数部分�?0，则表示为零�?
            chineseStr = ChineseNum[0];
        else if (negative)// 整数部分部位0，但是为负数
            chineseStr = "�?" + chineseStr;
        chineseStr = chineseStr + "�?";
        if (numFen == 0 && numJiao == 0) {
            chineseStr = chineseStr + "�?";
        } else if (numFen == 0) {// 0�?
            chineseStr = chineseStr + ChineseNum[numJiao] + "�?";
        } else {
            if (numJiao == 0)
                chineseStr = chineseStr + "�?" + ChineseNum[numFen] + "�?";
            else
                chineseStr = chineseStr + ChineseNum[numJiao] + "�?" + ChineseNum[numFen] + "�?";
        }
        return chineseStr;
    }

    // 转换拆分后的每个部分�?0-9999之间
    public static String partConvert(int partNum) {
        if (partNum < 0 || partNum > 10000) {
            logger.error("参数必须是大于等�?0或小�?10000的整�?");
            return "";
        }
        String[] units = new String[] { "", "�?", "�?", "�?" };
        int temp = partNum;
        String partResult = new Integer(partNum).toString();
        int partResultLength = partResult.length();
        boolean lastIsZero = true;// 记录上一位是否为0
        String chineseStr = "";
        for (int i = 0; i < partResultLength; i++) {
            if (temp == 0)// 高位无数�?
                break;
            int digit = temp % 10;
            if (digit == 0) {
                if (!lastIsZero)// 如果前一个数字不�?0则在当前汉字串前加零
                    chineseStr = "�?" + chineseStr;
                lastIsZero = true;
            } else {
                chineseStr = ChineseNum[digit] + units[i] + chineseStr;
                lastIsZero = false;
            }
            temp = temp / 10;
        }
        return chineseStr;
    }

}
