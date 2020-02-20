package com.gsafety.dawn.community.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * The type Number util.
 * 通过 BigDecimal 实现浮点数精确计算
 */
public class NumberUtil {
    public NumberUtil() {
        // 无参构造
    }


    /**
     * Add string. 加法
     *
     * @param a the a
     * @param b the b
     * @return the string
     */
    public static String add(String a, String b) {
        BigDecimal number1 = new BigDecimal(a);
        BigDecimal number2 = new BigDecimal(b);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number1.add(number2));
    }

    /**
     * Multiply string. 乘法
     *
     * @param a the a
     * @param b the b
     * @return the string
     */
    public static String multiply(String a, String b) {
        BigDecimal number1 = new BigDecimal(a);
        BigDecimal number2 = new BigDecimal(b);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number1.multiply(number2));
    }

    /**
     * Divide string. 除法
     * <p>
     * 需要判断除数不能为 0
     *
     * @param a the a
     * @param b the b
     * @return the string
     */
    public static String divide(String a, String b) {
        BigDecimal dividend = new BigDecimal(a); // 被除数
        BigDecimal divisor = new BigDecimal(b); // 除数
        DecimalFormat df = new DecimalFormat("0.000000");
        return df.format(dividend.divide(divisor, 4, BigDecimal.ROUND_HALF_UP)); //四舍五入 ，并保留四位小数
    }
}
