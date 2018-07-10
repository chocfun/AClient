package com.chocfun.baselib.util;

import android.support.annotation.Nullable;

public class XTextUtil {
    /**
     * 字符串是否为空 null 或者 长度为0
     * @param str 待检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
