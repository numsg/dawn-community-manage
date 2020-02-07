package com.gsafety.dawn.community.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    /**
     * 均分list为n个
     *
     * @param <T>    类型
     * @param source source
     * @param n      个数
     * @return list list
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<>();
        int remainder = source.size() % n; // 余数
        int number = source.size() / n; // 商
        int offset = 0;

        for (int i = 0; i < n; i++) {
            List<T> value;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
}
