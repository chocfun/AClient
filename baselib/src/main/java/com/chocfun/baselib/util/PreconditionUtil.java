package com.chocfun.baselib.util;

public class PreconditionUtil {
    /**
     * 确保输入参数不能为 null
     * @param reference 输入参数
     * @param message 提示信息
     * @param <T> 输入参数类型
     * @return 输入参数
     */
    public static <T> T assertNotNull(T reference, String message) {
        if (reference == null) {
            throw new NullPointerException(message);
        } else {
            return reference;
        }
    }

    public static <T> T assertNotNull(T reference, String message, Object...args) {
        return assertNotNull(reference, String.format(message, args));
    }
}
