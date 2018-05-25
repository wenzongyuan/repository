package com.wzy.shiro.util;

import java.util.Random;

/**
 * @author Jason.Zhang
 */
public class CaptchaUtil {
    /**
     * 生成验证�?
     * 
     * @param length 验证码长�?
     * @param hex 验证码是�?16进制
     * @return
     */
    public static String generateCode(int length, boolean hex) {
        if (length < 1 || hex && length > 7 || !hex && length > 9)
            return "";
        int base, min, max;
        if (hex) {
            base = 0x10;
        } else {
            base = 10;
        }
        min = new Double(Math.pow(base, length - 1)).intValue();
        max = min * base - 1;
        Random RANDOM = new Random();
        int result = RANDOM.nextInt(max - min) + min;

        if (hex) {
            return Integer.toHexString(result).toUpperCase();
        } else {
            return Integer.toString(result);
        }
    }
}
